package hallodoc.service;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.enumerations.DocType;
import hallodoc.enumerations.RequestStatus;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUserRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetRolesDao;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.PatientNewRequestDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestClientDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestTypeDao;
import hallodoc.repository.RequestWiseFileDao;
import hallodoc.repository.UserDao;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Authenticator.RequestorType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

@Service
public class PatientRequestsService {

	@Autowired
	private AspNetUserDao apsnetuserdao;

	@Autowired
	private PatientNewRequestDao patientNewRequestDao;
	
	@Autowired
	private AspNetRolesDao aspNetRolesDao;
	
	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private RequestClientDao requestClientDao;

	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private RequestStatusLogDao requestStatusLogDao;
	
	@Autowired
	private RequestTypeDao requestTypeDao;
	
	@Autowired
	private RequestWiseFileDao requestWiseFileDao;
	
	@Autowired
	private UserDao userDao;

	
	public String isPatientAvailable(String email) {

		String isValid = apsnetuserdao.isUserPresent(email);
		return isValid;
	}

	public String isStateAvailable(String state) {

//		String isValid = patientNewRequestDao.getRegionEntry(state);
		String isValid;
		String formattedState = state.substring(0, 1).toUpperCase() + state.substring(1);
		List<Region> list = regionDao.getRegionEntry(formattedState);
		if (list.size() > 0) {
			isValid = "success";
		} else {
			isValid = "failure";
		}
		return isValid;
	}

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

	private String getNewConfirmationNumber(CreatePatientRequestDto createPatientRequestDto, Region region, Date date) {

		String regAbbrevation = region.getAbbreviation();
		String req_date = getFormattedDate(date);
		String lastNameAbbr = createPatientRequestDto.getLastName().substring(0, 2).toUpperCase();
		String firstNameAbbr = createPatientRequestDto.getFirstName().substring(0, 2).toUpperCase();

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
		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		System.out.println(confirmationNumber);

		return confirmationNumber;
	}

	private AspNetUsers createAspNetUsers(CreatePatientRequestDto createPatientRequestDto, Date date) {

		AspNetUsers aspNetUsers = new AspNetUsers();

		// generating hash

		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(createPatientRequestDto.getPassword()).with(bcrypt);

		// setters of aspNetUsers

		aspNetUsers.setEmail(createPatientRequestDto.getEmail());
		aspNetUsers.setPhone_number(createPatientRequestDto.getMobileNumber());
		aspNetUsers.setUser_name(createPatientRequestDto.getEmail());
		aspNetUsers.setCreated_date(date);
		aspNetUsers.setPassword_hash(hash.getResult());

		return aspNetUsers;
	}

	private User createUser(CreatePatientRequestDto createPatientRequestDto, Date currentDate, AspNetUsers aspNetUsers,
			Region region, int day, int year, String monthName, AspNetRoles role) {

		User user_ob = new User();

		// Setters of User Entity

		user_ob.setAspNetUsers(aspNetUsers);
		user_ob.setFirstName(createPatientRequestDto.getFirstName());
		user_ob.setLastName(createPatientRequestDto.getLastName());
		user_ob.setEmail(createPatientRequestDto.getEmail());
		user_ob.setMobile(createPatientRequestDto.getMobileNumber());
		user_ob.setStreet(createPatientRequestDto.getStreet());
		user_ob.setState(createPatientRequestDto.getState());
		user_ob.setCity(createPatientRequestDto.getCity());
		user_ob.setZipcode(createPatientRequestDto.getZipcode());
		user_ob.setRegion(region);
		user_ob.setStrMonth(monthName);
		user_ob.setIntYear(year);
		user_ob.setIntDate(day);
		user_ob.setCreatedBy(aspNetUsers);
		user_ob.setCreatedDate(currentDate);
		user_ob.setDeleted(false);
		user_ob.setRequestWithEmail(false);
		user_ob.setAspNetRoles(role);

		return user_ob;
	}

	private Request createRequest(CreatePatientRequestDto createPatientRequestDto, Date currentDate,
			RequestType requestType, User user, Region region) {
		Request request = new Request();

		// Setters of Request Entity
		request.setRequestType(requestType);
		request.setUser(user);
		request.setFirstName(createPatientRequestDto.getFirstName());
		request.setLastName(createPatientRequestDto.getLastName());
		request.setPhoneNumber(createPatientRequestDto.getMobileNumber());
		request.setEmail(createPatientRequestDto.getEmail());
		request.setCreatedDate(currentDate);
		request.setModifieDate(currentDate);
		request.setDeleted(false);
		request.setCompletedByPhysician(false);
		request.setConfirmationNumber(getNewConfirmationNumber(createPatientRequestDto, region, currentDate));
		request.setStatus(RequestStatus.UNASSIGNED.getRequestId());

		return request;
	}

	private RequestClient createRequestClient(CreatePatientRequestDto createPatientRequestDto, Date currentDate,
			Request request, Region region, int day, int year, String monthName) {
		RequestClient requestClient = new RequestClient();

		// Setters of RequestClient
		requestClient.setRequest(request);
		requestClient.setFirstName(createPatientRequestDto.getFirstName());
		requestClient.setLastName(createPatientRequestDto.getLastName());
		requestClient.setNotiMobile(createPatientRequestDto.getMobileNumber());
		requestClient.setRegion(region);
		requestClient.setPhoneNumber(createPatientRequestDto.getMobileNumber());
		requestClient.setNotiEmail(createPatientRequestDto.getEmail());
		requestClient.setNotes(createPatientRequestDto.getSymptoms());
		requestClient.setEmail(createPatientRequestDto.getEmail());
		requestClient.setStrMonth(monthName);
		requestClient.setIntYear(year);
		requestClient.setIntDate(day);
		requestClient.setStreet(createPatientRequestDto.getStreet());
		requestClient.setCity(createPatientRequestDto.getCity());
		requestClient.setState(createPatientRequestDto.getState());
		requestClient.setZipcode(createPatientRequestDto.getZipcode());

		return requestClient;
	}

	private RequestStatusLog creatRequestStatusLog(CreatePatientRequestDto createPatientRequestDto, Date currentDate,
			Request request) {

		RequestStatusLog requestStatusLog = new RequestStatusLog();

		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setCreatedDate(currentDate);

		return requestStatusLog;

	}

	private RequestWiseFile creatRequestWiseFile(CreatePatientRequestDto createPatientRequestDto, Date currentDate,
			Request request, HttpSession session) {

		RequestWiseFile requestWiseFile = new RequestWiseFile();

		// Getting details from file obj

		CommonsMultipartFile file = createPatientRequestDto.getDocument();
		String fileName = file.getOriginalFilename();
		byte[] data = file.getBytes();

		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "patient" + File.separator
				+ file.getOriginalFilename();

		System.out.println(path);

		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("file uploaded");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Uploading Error");
		}

		requestWiseFile.setRequest(request);
		requestWiseFile.setFileName(fileName);
		requestWiseFile.setCreatedDate(currentDate);
		requestWiseFile.setDocType(DocType.TEST_ONE.getDocId());
		requestWiseFile.setFinalize(false);
		requestWiseFile.setDeleted(false);

		return requestWiseFile;
	}

	private void UpdateAspNetUser(CreatePatientRequestDto createPatientRequestDto, AspNetUsers aspNetUsers) {
		
		aspNetUsers.setModified_date(new Date());
		aspNetUsers.setPhone_number(createPatientRequestDto.getMobileNumber());	
		apsnetuserdao.updateAspNetUser(aspNetUsers);
	}
	
	private void UpdateUser(CreatePatientRequestDto createPatientRequestDto, User user,Region region, int day, int year, String monthName, AspNetUsers aspNetUsers) {
		user.setFirstName(createPatientRequestDto.getFirstName());
		user.setLastName(createPatientRequestDto.getLastName());
		user.setMobile(createPatientRequestDto.getMobileNumber());
		user.setStreet(createPatientRequestDto.getStreet());
		user.setCity(createPatientRequestDto.getCity());
		user.setState(createPatientRequestDto.getState());
		user.setZipcode(createPatientRequestDto.getZipcode());
		user.setRegion(region);
		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(monthName);
		user.setModifiedBy(aspNetUsers);
		user.setModifiedDate(new Date());
		
		userDao.updateUser(user);
	}
	
	public boolean addRequestForNewPatient(CreatePatientRequestDto createPatientRequestDto, HttpSession session)
			throws ParseException {

		String pass1 = createPatientRequestDto.getPassword();
		String pass2 = createPatientRequestDto.getConfirmPassword();
		String phoneNumber = createPatientRequestDto.getMobileNumber();
		String zipCode = createPatientRequestDto.getZipcode();

		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);

		String regex1 = "^\\d+$";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(zipCode);

		if (!(pass1.equals(pass2))) {
			return false;
		}

		else if (!matcher.matches()) {
			return false;
		}

		else if (!matcher1.matches()) {
			return false;
		}

		else {

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

			// generating hash

			BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
			Hash hash = Password.hash(createPatientRequestDto.getPassword()).with(bcrypt);

			// Setting object of AspNetUsers
			aspNetUsers = createAspNetUsers(createPatientRequestDto, currentDate);

			// persisting object of AspNetUser
			int aspNetId = apsnetuserdao.createPatient(aspNetUsers);

			// Get Object corresponding to region
			List<Region> list = regionDao.getRegionEntry(createPatientRequestDto.getState());
			region = list.get(0);

			// Extarcting required fields from date

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			String dateString = dateFormat.format(createPatientRequestDto.getFormatedDate());
			String[] tokens = dateString.split("-");
			int day = Integer.parseInt(tokens[0]);
			int year = Integer.parseInt(tokens[2]);
			String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH)
					.format(createPatientRequestDto.getFormatedDate());

			// Getting Role
			AspNetRoles role = aspNetRolesDao.getRoleObject("Patient");

			// Setting the user object
			user = createUser(createPatientRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);

			// persisting object of User
			int userId = userDao.addNewPatientRequest(user);

			// Getting Request Type Object
			requestType = requestTypeDao.getRequestTypeObject("Patient");

			// Setting the request object
			request = createRequest(createPatientRequestDto, currentDate, requestType, user, region);

			// persisting object of Request
			int requestId = requestDao.addNewRequest(request);

			// Setting the requestClient object
			requestClient = createRequestClient(createPatientRequestDto, currentDate, request, region, day, year,
					monthName);

			// persisting object of Request
			int requestClientId = requestClientDao.addNewRequestClient(requestClient);

			// Setting the requestStatusLogobject
			requestStatusLog = creatRequestStatusLog(createPatientRequestDto, currentDate, request);

			// Persisting the requestStatusLogobject
			int requestStatusLogId = requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

			if (!(createPatientRequestDto.getDocument().isEmpty())) {

				// Setting the requestWiseFile
				requestWiseFile = creatRequestWiseFile(createPatientRequestDto, currentDate, request, session);

				// Persisting the requestWiseFile
				int requestWiseFileId = requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
			}

			return true;
		}
	}

	public boolean addRequestForExsitingPatient(CreatePatientRequestDto createPatientRequestDto, HttpSession session)
			throws ParseException {
		String pass1 = createPatientRequestDto.getPassword();
		String pass2 = createPatientRequestDto.getConfirmPassword();
		String phoneNumber = createPatientRequestDto.getMobileNumber();
		String zipCode = createPatientRequestDto.getZipcode();

		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);

		String regex1 = "^\\d+$";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(zipCode);

		if (!(pass1.equals(pass2))) {
			return false;
		}

		else if (!matcher.matches()) {
			return false;
		}

		else if (!matcher1.matches()) {
			return false;
		}

		else {
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

			// Get Object corresponding to region
			List<Region> list = regionDao.getRegionEntry(createPatientRequestDto.getState());
			region = list.get(0);

			// Extarcting required fields from date

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			String dateString = dateFormat.format(createPatientRequestDto.getFormatedDate());
			String[] tokens = dateString.split("-");
			int day = Integer.parseInt(tokens[0]);
			int year = Integer.parseInt(tokens[2]);
			String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH)
					.format(createPatientRequestDto.getFormatedDate());

			aspNetUsers = apsnetuserdao.getUserByEmail(createPatientRequestDto.getEmail()).get(0);
			user = userDao.getUserByEmail(createPatientRequestDto.getEmail()).get(0);
			
			//updating aspNetUser object
			UpdateAspNetUser(createPatientRequestDto, aspNetUsers);
			
			//updating User object
			UpdateUser(createPatientRequestDto, user, region, day, year, monthName, aspNetUsers);
			
			// Getting Request Type Object
			requestType = requestTypeDao.getRequestTypeObject("Patient");

			// Setting the request object
			request = createRequest(createPatientRequestDto, currentDate, requestType, user, region);

			// persisting object of Request
			int requestId = requestDao.addNewRequest(request);

			// Setting the requestClient object
			requestClient = createRequestClient(createPatientRequestDto, currentDate, request, region, day, year,
					monthName);

			// persisting object of Request
			int requestClientId = requestClientDao.addNewRequestClient(requestClient);

			// Setting the requestStatusLogobject
			requestStatusLog = creatRequestStatusLog(createPatientRequestDto, currentDate, request);

			// Persisting the requestStatusLogobject
			int requestStatusLogId = requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

			if (!(createPatientRequestDto.getDocument().isEmpty())) {

				// Setting the requestWiseFile
				requestWiseFile = creatRequestWiseFile(createPatientRequestDto, currentDate, request, session);

				// Persisting the requestWiseFile
				int requestWiseFileId =requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
			}

			return true;
		}

	}

}
