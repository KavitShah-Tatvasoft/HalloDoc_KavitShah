package hallodoc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import enumerations.RequestStatus;
import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.PatientNewRequestDao;

@Service
public class FamilyFriendRequestService {

	@Autowired
	private AspNetUserDao apsnetuserdao;

	@Autowired
	private PatientNewRequestDao patientNewRequestDao;
	
	private String getFormattedDate(Date date) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
		String dateString = dateFormat.format(date);
		String[] tokens = dateString.split("-");
		String day = tokens[0];
		String month = tokens[1];
		String year = tokens[2];

//        if(Integer.parseInt(month)<10) {
//        	month = "0" + month;
//        }

		return day + month + year;
	}
	
	private String getNewConfirmationNumber(CommonRequestDto commonRequestDto, Region region, Date date) {

		String regAbbrevation = region.getAbbreviation();
		String req_date = getFormattedDate(date);
		String lastNameAbbr = commonRequestDto.getPtLastName().substring(0, 2).toUpperCase();
		String firstNameAbbr = commonRequestDto.getPtFirstName().substring(0, 2).toUpperCase();

		Date startDate = new Date();
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);

		Date endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);

//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//		
//		String startString = formatter.format(startDate);
//		String endString = formatter.format(endDate);

//		String currentNewRequests = String.format("%04d", patientNewRequestDao.getNewRequestsNo(new Date()));
		String currentNewRequests = String.format("%04d", patientNewRequestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		System.out.println(confirmationNumber);

		return confirmationNumber;
	}


	private AspNetUsers createAspNetUsers(CommonRequestDto commonRequestDto, Date date) {

		AspNetUsers aspNetUsers = new AspNetUsers();

		// setters of aspNetUsers

		aspNetUsers.setEmail(commonRequestDto.getPtEmail());
		aspNetUsers.setPhone_number(commonRequestDto.getPtMobileNumber());
		aspNetUsers.setUser_name(commonRequestDto.getPtEmail());
		aspNetUsers.setCreated_date(date);
		return aspNetUsers;
	}

	private User createUser(CommonRequestDto commonRequestDto, Date currentDate, AspNetUsers aspNetUsers,
			Region region, int day, int year, String monthName, AspNetRoles role) {

		User userOb = new User();

		// Setters of User Entity

		userOb.setAspNetUsers(aspNetUsers);
		userOb.setFirstName(commonRequestDto.getPtFirstName());
		userOb.setLastName(commonRequestDto.getPtLastName());
		userOb.setEmail(commonRequestDto.getPtEmail());
		userOb.setMobile(commonRequestDto.getPtMobileNumber());
		userOb.setStreet(commonRequestDto.getPtStreet());
		userOb.setState(commonRequestDto.getPtState());
		userOb.setCity(commonRequestDto.getPtCity());
		userOb.setZipcode(commonRequestDto.getPtZipcode());
		userOb.setRegion(region);
		userOb.setStrMonth(monthName);
		userOb.setIntYear(year);
		userOb.setIntDate(day);
		userOb.setCreatedBy(aspNetUsers);
		userOb.setCreatedDate(currentDate);
		userOb.setDeleted(false);
		userOb.setRequestWithEmail(false);
		userOb.setAspNetRoles(role);

		return userOb;
	}
	
	private Request createRequest(CommonRequestDto commonRequestDto, Date currentDate,
			RequestType requestType, User user, Region region) {
		Request request = new Request();

		// Setters of Request Entity
		request.setRequestType(requestType);
		request.setUser(user);
		request.setFirstName(commonRequestDto.getReqFirstName());
		request.setLastName(commonRequestDto.getReqLastName());
		request.setPhoneNumber(commonRequestDto.getReqMobileNumber());
		request.setEmail(commonRequestDto.getReqEmail());
		request.setCreatedDate(currentDate);
		request.setModifieDate(currentDate);
		request.setDeleted(false);
		request.setCompletedByPhysician(false);
		request.setConfirmationNumber(getNewConfirmationNumber(commonRequestDto, region, currentDate));
		request.setStatus(RequestStatus.UNASSIGNED.getRequestId());

		return request;
	}
	
	private boolean createNewUserFamilyFriendRequest(CommonRequestDto commonRequestDto) throws ParseException {
		System.out.println("New");

		// Creating required objects
		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestStatusLog requestStatusLog;
		RequestWiseFile requestWiseFile;

		// Setting object of AspNetUsers
		aspNetUsers = createAspNetUsers(commonRequestDto, currentDate);

		// persisting object of AspNetUser
		int aspNetId = apsnetuserdao.createPatient(aspNetUsers);

		// Get Object corresponding to region
		List<Region> list = patientNewRequestDao.getRegionEntry(commonRequestDto.getPtState());
		region = list.get(0);

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(commonRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(commonRequestDto.getFormatedDate());

		// Getting Role
		AspNetRoles role = patientNewRequestDao.getRoleObject("Patient");

		// Setting the user object
		user = createUser(commonRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);

		// persisting object of User
		int userId = patientNewRequestDao.addNewPatientRequest(user);
		
		// Getting Request Type Object
		requestType = patientNewRequestDao.getRequestTypeObject("Family");

		// Setting the request object
		request = createRequest(commonRequestDto, currentDate, requestType, user, region);

		// persisting object of Request
		int requestId = patientNewRequestDao.addNewRequest(request);

		return true;
	}

	private boolean createOldUserFamilyFriendRequest(CommonRequestDto commonRequestDto) {
		System.out.println("Old");
		return true;
	}

	public boolean createNewFamilyFriendRequest(CommonRequestDto commonRequestDto, HttpSession session)
			throws Exception {

		String ptPhoneNumber = commonRequestDto.getPtMobileNumber();
		String reqPhoneNumber = commonRequestDto.getReqMobileNumber();
		String ptZipCode = commonRequestDto.getPtZipcode();

		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ptPhoneNumber);
		Matcher matcher2 = pattern.matcher(reqPhoneNumber);

		String regex1 = "^\\d+$";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(ptZipCode);

//	if (!matcher.matches()) {
//		return false;
//		}
//
//		else if (!matcher1.matches()) {
//		return false;
//	}
//
//		else if (!matcher2.matches()) {
//			return false;
//		}
//
//		else
		{

			// Creating required objects

			List<AspNetUsers> list = apsnetuserdao.getUserByEmail(commonRequestDto.getPtEmail());

			if (list.size() > 0) {
				// method for old user
				createOldUserFamilyFriendRequest(commonRequestDto);
			}

			else {
				// method for new user
				createNewUserFamilyFriendRequest(commonRequestDto);
			}

			return true;
		}
	}

}
