package hallodoc.service;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUserRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestType;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.PatientNewRequestDao;

import java.net.Authenticator.RequestorType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class PatientRequestsService {
	
	@Autowired
	private AspNetUserDao apsnetuserdao;
	
	@Autowired
	private PatientNewRequestDao patientNewRequestDao;
	

	public String isPatientAvailable(String email) {
		
		String isValid = apsnetuserdao.isUserPresent(email);
		return isValid;
	}
	
	public String isStateAvailable(String state) {
		
//		String isValid = patientNewRequestDao.getRegionEntry(state);
		String isValid;
		String formattedState =  state.substring(0, 1).toUpperCase() + state.substring(1);
		List<Region> list = patientNewRequestDao.getRegionEntry(formattedState);
		if(list.size()>0) {isValid = "success";}
		else {isValid = "failure";}
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
		
        return day+month+year;
	}
	
	private String getNewConfirmationNumber(CreatePatientRequestDto createPatientRequestDto, Region region, Date date) {
		
		String regAbbrevation = region.getAbbreviation();
		String req_date = getFormattedDate(date);
		String lastNameAbbr = createPatientRequestDto.getLastName().substring(0,2);
		String firstNameAbbr = createPatientRequestDto.getFirstName().substring(0,2);
		
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
		String currentNewRequests = String.format("%04d", patientNewRequestDao.getNewRequestsNo(startDate,endDate));
		
		String confirmationNumber = regAbbrevation+req_date+lastNameAbbr+firstNameAbbr+currentNewRequests;
		System.out.println(confirmationNumber);
		
		
		return confirmationNumber;
	}
	
	public boolean addRequestForNewPatient(CreatePatientRequestDto createPatientRequestDto) throws ParseException{
		
		String pass1 = createPatientRequestDto.getPassword();
		String pass2 = createPatientRequestDto.getConfirmPassword();
		

		if(!(pass1.equals(pass2))) {
			return false;
		}
		
		else {
		
		// Creating required objects
		AspNetUsers aspNetUsers = new AspNetUsers();
		User user = new User();
		Region region;
		Request request = new Request();
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient = new RequestClient();
		//generating hash
		
		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(createPatientRequestDto.getPassword())
                .with(bcrypt);
		
		//setters of aspNetUsers
		
		aspNetUsers.setEmail(createPatientRequestDto.getEmail());
		aspNetUsers.setPhone_number(createPatientRequestDto.getMobileNumber());
		aspNetUsers.setUser_name(createPatientRequestDto.getEmail());
		aspNetUsers.setCreated_date(currentDate);
		aspNetUsers.setPassword_hash(hash.getResult());   
		
		//persisting object of AspNetUser
		int aspNetId = apsnetuserdao.createPatient(aspNetUsers);	
		
		//Get Object corresponding to region
		List<Region> list = patientNewRequestDao.getRegionEntry(createPatientRequestDto.getState());
		region = list.get(0);
		
		// Extarcting required fields from date
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String dateString = dateFormat.format(createPatientRequestDto.getFormatedDate());
        String[] tokens = dateString.split("-");
        int day = Integer.parseInt(tokens[0]);
        int year = Integer.parseInt(tokens[2]);
        String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(createPatientRequestDto.getFormatedDate());

        // Getting Role
        AspNetRoles role = patientNewRequestDao.getRoleObject("Patient");
		
		//Setters of User Entity
		
		user.setAspNetUsers(aspNetUsers);
		user.setFirstName(createPatientRequestDto.getFirstName());
		user.setLastName(createPatientRequestDto.getLastName());
		user.setEmail(createPatientRequestDto.getEmail());
		user.setMobile(createPatientRequestDto.getMobileNumber());
		user.setStreet(createPatientRequestDto.getStreet());
		user.setState(createPatientRequestDto.getState());
		user.setCity(createPatientRequestDto.getCity());
		user.setZipcode(createPatientRequestDto.getZipcode());
		user.setRegion(region);
		user.setStrMonth(monthName);
		user.setIntYear(year);
		user.setIntDate(day);
		user.setCreatedBy(aspNetUsers);
		user.setCreatedDate(currentDate);
		user.setDeleted(false);
		user.setRequestWithEmail(false);
		user.setAspNetRoles(role);
		
		//persisting object of User
		int userId = patientNewRequestDao.addNewPatientRequest(user);
		
		//Getting Request Type Object
		requestType = patientNewRequestDao.getRequestTypeObject("Patient");
		
		//Setters of Request Entity
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
		request.setCaseNumber(getNewConfirmationNumber(createPatientRequestDto, region, currentDate ));
		
		//persisting object of Request
		int requestId = patientNewRequestDao.addNewRequest(request);
		
		
		//Setters of RequestClient
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
		
		//persisting object of Request
		int requestClientId = patientNewRequestDao.addNewRequestClient(requestClient);
				
		return true;
		}
	}
	
	public int addRequestForExsitingPatient(CreatePatientRequestDto createPatientRequestDto){
		int id = 0;
		return id;
	}
}
