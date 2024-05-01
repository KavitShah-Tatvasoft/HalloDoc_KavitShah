package hallodoc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.RequestStatus;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestNotes;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetRolesDao;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.EmailTokenDao;
import hallodoc.repository.PatientNewRequestDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestClientDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestTypeDao;
import hallodoc.repository.RequestWiseFileDao;
import hallodoc.repository.UserDao;

@Service
public class AdminNewPatientRequestService {

	@Autowired
	private AspNetUserDao aspNetUserDao;

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
	private RequestTypeDao requestTypeDao;

	@Autowired
	private RequestWiseFileDao requestWiseFileDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailService mailer;

	@Autowired
	private EmailTokenDao emailTokenDao;

	private String getFormattedDate(Date date) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);
		String dateString = dateFormat.format(date);
		String[] tokens = dateString.split("-");
		String day = tokens[0];
		String month = tokens[1];
		String year = tokens[2];

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

//		String currentNewRequests = String.format("%04d", patientNewRequestDao.getNewRequestsNo(new Date()));
		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		 ;

		return confirmationNumber;
	}

	private AspNetUsers createAspNetUsers(CreatePatientRequestDto createPatientRequestDto, Date date) {

		AspNetUsers aspNetUsers = new AspNetUsers();

		// setters of aspNetUsers

		aspNetUsers.setEmail(createPatientRequestDto.getEmail());
		aspNetUsers.setPhone_number(createPatientRequestDto.getMobileNumber());
		aspNetUsers.setUser_name(createPatientRequestDto.getEmail());
		aspNetUsers.setCreated_date(date);

		return aspNetUsers;
	}

	private User createUser(CreatePatientRequestDto createPatientRequestDto, Date currentDate, AspNetUsers aspNetUsers,
			Region region, int day, int year, String monthName, AspNetRoles role) {

		User userOb = new User();

		// Setters of User Entity

		userOb.setAspNetUsers(aspNetUsers);
		userOb.setFirstName(createPatientRequestDto.getFirstName());
		userOb.setLastName(createPatientRequestDto.getLastName());
		userOb.setEmail(createPatientRequestDto.getEmail());
		userOb.setMobile(createPatientRequestDto.getMobileNumber());
		userOb.setStrMonth(monthName);
		userOb.setIntYear(year);
		userOb.setIntDate(day);
		userOb.setCreatedDate(currentDate);
		userOb.setDeleted(false);
		userOb.setRequestWithEmail(false);
		userOb.setAspNetRoles(role);

		return userOb;
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

	private String sendPatientCreatePasswordMail(CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest httpServletRequest, String ptStatus) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		 ;

		emailToken.setToken(createdToken);
		emailToken.setEmail(createPatientRequestDto.getEmail());
		emailToken.setResetCompleted(false);
		emailToken.setSentDate(LocalDateTime.now());

		// persisting the object of EmailToken

		if (ptStatus.equals("old")) {
			List<EmailToken> emailList = emailTokenDao.getDuplicateEmailEntry(createPatientRequestDto.getEmail());

			for (EmailToken email : emailList) {
				email.setResetCompleted(true);
			}

			String emailChange = emailTokenDao.updateOldEmailResetStatus(emailList);
			 ;
		}

		int mailId = emailTokenDao.createNewEmail(emailToken);

		mailer.sendPatientCreatePasswordMail(createPatientRequestDto, httpServletRequest, LocalDateTime.now(),
				emailToken);

		return "success";
	}

	private RequestNotes createNotes(CreatePatientRequestDto createPatientRequestDto, Request request,
			HttpServletRequest httpServletRequest) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");

		RequestNotes requestNotes = new RequestNotes();
		requestNotes.setRequest(request);
		requestNotes.setCreatedBy(aspNetUsers);
		requestNotes.setAdminNotes(createPatientRequestDto.getSymptoms());

		return requestNotes;
	}

	private void UpdateAspNetUser(CreatePatientRequestDto createPatientRequestDto, AspNetUsers aspNetUsers) {

		aspNetUsers.setModified_date(new Date());
		aspNetUsers.setPhone_number(createPatientRequestDto.getMobileNumber());
		aspNetUserDao.updateAspNetUser(aspNetUsers);
	}

	private void UpdateUser(CreatePatientRequestDto createPatientRequestDto, User user, Region region, int day,
			int year, String monthName, AspNetUsers aspNetUsers) {
		user.setFirstName(createPatientRequestDto.getFirstName());
		user.setLastName(createPatientRequestDto.getLastName());
		user.setMobile(createPatientRequestDto.getMobileNumber());

		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(monthName);
		user.setModifiedBy(aspNetUsers);
		user.setModifiedDate(new Date());

		userDao.updateUser(user);
	}

	public boolean addRequestForNewPatient(CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest httpRequest) throws ParseException {

		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestNotes requestNotes;
		RequestWiseFile requestWiseFile;

		aspNetUsers = createAspNetUsers(createPatientRequestDto, currentDate);

		List<Region> list = regionDao.getRegionEntry(createPatientRequestDto.getState());
		region = list.get(0);

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(createPatientRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH)
				.format(createPatientRequestDto.getFormatedDate());

		AspNetRoles role = aspNetRolesDao.getRoleObject("Patient");

		user = createUser(createPatientRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);
		aspNetUsers.setUser(user);

		requestType = requestTypeDao.getRequestTypeObject("Patient");

		request = createRequest(createPatientRequestDto, currentDate, requestType, user, region);

		requestClient = createRequestClient(createPatientRequestDto, currentDate, request, region, day, year,
				monthName);
		request.setRequestClient(requestClient);

		requestNotes = createNotes(createPatientRequestDto, request, httpRequest);
		request.setRequestNotes(requestNotes);

		int aspNetId = aspNetUserDao.createPatient(aspNetUsers);

		int requestId = requestDao.addNewRequest(request);

		String mailSentStatus = sendPatientCreatePasswordMail(createPatientRequestDto, httpRequest, "new");

		return true;
	}

	private boolean addRequestForExsistingPatient(CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest httpRequest) throws ParseException {

		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestNotes requestNotes;
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

		aspNetUsers = aspNetUserDao.getUserByEmail(createPatientRequestDto.getEmail()).get(0);
		user = userDao.getUserByEmail(createPatientRequestDto.getEmail()).get(0);
		String password = aspNetUsers.getPassword_hash();

		UpdateAspNetUser(createPatientRequestDto, aspNetUsers);

	
		UpdateUser(createPatientRequestDto, user, region, day, year, monthName, aspNetUsers);


		requestType = requestTypeDao.getRequestTypeObject("Patient");


		request = createRequest(createPatientRequestDto, currentDate, requestType, user, region);


		requestClient = createRequestClient(createPatientRequestDto, currentDate, request, region, day, year,
				monthName);
		request.setRequestClient(requestClient);
		
		requestNotes = createNotes(createPatientRequestDto, request, httpRequest);
		request.setRequestNotes(requestNotes);

		
		int requestId = requestDao.addNewRequest(request);
		
		if (password == null) {
			String mailSentStatus = sendPatientCreatePasswordMail(createPatientRequestDto, httpRequest, "old");
		}

		return true;
	}

	public String createNewRequest(HttpServletRequest request, CreatePatientRequestDto createPatientRequestDto)
			throws ParseException {

		String ptEmail = createPatientRequestDto.getEmail();
		List<AspNetUsers> userList = aspNetUserDao.getUserByEmail(ptEmail);

		if (userList.size() > 0) {

			User exisitingUser = userList.get(0).getUser();
			AspNetRoles aspNetRole = exisitingUser.getAspNetRoles();

			if (!aspNetRole.getName().equalsIgnoreCase("Patient")) {
				return "UserExsist";
			}

			boolean status = addRequestForExsistingPatient(createPatientRequestDto, request);
			return "Success";

		} else {
			boolean status = addRequestForNewPatient(createPatientRequestDto, request);
			return "Success";
		}
	}

}
