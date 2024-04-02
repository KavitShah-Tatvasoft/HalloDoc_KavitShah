package hallodoc.service;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.SomeoneElseRequestDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.DocType;
import hallodoc.enumerations.RequestStatus;
import hallodoc.helper.Constants;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
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
public class RegisteredPatientOthersRequestService {

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

	@Autowired
	private EmailTokenDao emailTokenDao;

	@Autowired
	private EmailService mailer;

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

	private String getNewConfirmationNumber(SomeoneElseRequestDto someoneElseRequestDto, Region region, Date date) {

		String regAbbrevation = region.getAbbreviation();
		String req_date = getFormattedDate(date);
		String lastNameAbbr = someoneElseRequestDto.getLastName().substring(0, 2).toUpperCase();
		String firstNameAbbr = someoneElseRequestDto.getFirstName().substring(0, 2).toUpperCase();

		Date startDate = new Date();
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);

		Date endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);

		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		System.out.println(confirmationNumber);

		return confirmationNumber;
	}

	private AspNetUsers createAspNetUsers(SomeoneElseRequestDto someoneElseRequestDto, Date date) {

		AspNetUsers aspNetUsers = new AspNetUsers();

		// setters of aspNetUsers

		aspNetUsers.setEmail(someoneElseRequestDto.getEmail());
		aspNetUsers.setPhone_number(someoneElseRequestDto.getPhoneNumber());
		aspNetUsers.setUser_name(someoneElseRequestDto.getEmail());
		aspNetUsers.setCreated_date(date);
		return aspNetUsers;
	}

	private User createUser(SomeoneElseRequestDto someoneElseRequestDto, Date currentDate, AspNetUsers aspNetUsers,
			Region region, int day, int year, String monthName, AspNetRoles role) {

		User userOb = new User();

		// Setters of User Entity

		userOb.setAspNetUsers(aspNetUsers);
		userOb.setFirstName(someoneElseRequestDto.getFirstName());
		userOb.setLastName(someoneElseRequestDto.getLastName());
		userOb.setEmail(someoneElseRequestDto.getEmail());
		userOb.setMobile(someoneElseRequestDto.getPhoneNumber());

		userOb.setStrMonth(monthName);
		userOb.setIntYear(year);
		userOb.setIntDate(day);

		userOb.setCreatedDate(currentDate);
		userOb.setDeleted(false);
		userOb.setRequestWithEmail(false);
		userOb.setAspNetRoles(role);
		userOb.setRequestWithEmail(true);
		return userOb;
	}

	private Request createRequest(SomeoneElseRequestDto someoneElseRequestDto, Date currentDate,
			RequestType requestType, User user, User requestorUser, Region region) {
		Request request = new Request();

		// Setters of Request Entity
		request.setRequestType(requestType);
		request.setUser(user);
		request.setFirstName(requestorUser.getFirstName());
		request.setLastName(requestorUser.getLastName());
		request.setPhoneNumber(requestorUser.getMobile());
		request.setEmail(requestorUser.getEmail());
		request.setCreatedDate(currentDate);
		request.setModifieDate(currentDate);
		request.setDeleted(false);
		request.setCompletedByPhysician(false);
		request.setConfirmationNumber(getNewConfirmationNumber(someoneElseRequestDto, region, currentDate));
		request.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		request.setRelationName(someoneElseRequestDto.getRelation());
		return request;
	}

	private RequestClient createRequestClient(SomeoneElseRequestDto someoneElseRequestDto, Date currentDate,
			Request request, Region region, int day, int year, String monthName) {
		RequestClient requestClient = new RequestClient();

		// Setters of RequestClient
		requestClient.setRequest(request);
		requestClient.setFirstName(someoneElseRequestDto.getFirstName());
		requestClient.setLastName(someoneElseRequestDto.getLastName());
		requestClient.setNotiMobile(someoneElseRequestDto.getPhoneNumber());
		requestClient.setRegion(region);
		requestClient.setPhoneNumber(someoneElseRequestDto.getPhoneNumber());
		requestClient.setNotiEmail(someoneElseRequestDto.getEmail());
		requestClient.setNotes(someoneElseRequestDto.getSymptoms());
		requestClient.setEmail(someoneElseRequestDto.getEmail());
		requestClient.setStrMonth(monthName);
		requestClient.setIntYear(year);
		requestClient.setIntDate(day);
		requestClient.setStreet(someoneElseRequestDto.getStreet());
		requestClient.setCity(someoneElseRequestDto.getCity());
		requestClient.setState(someoneElseRequestDto.getState());
		requestClient.setZipcode(someoneElseRequestDto.getZipcode());

		return requestClient;
	}

	private RequestStatusLog creatRequestStatusLog(SomeoneElseRequestDto someoneElseRequestDto, Date currentDate,
			Request request) {

		RequestStatusLog requestStatusLog = new RequestStatusLog();

		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setCreatedDate(currentDate);

		return requestStatusLog;

	}

	private RequestWiseFile creatRequestWiseFile(SomeoneElseRequestDto someoneElseRequestDto, Date currentDate,
			Request request, HttpSession session, User requestorUser) {

		RequestWiseFile requestWiseFile = new RequestWiseFile();

		// Getting details from file obj
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
		String formattedDate = sdf.format(currentDate);

		CommonsMultipartFile file = someoneElseRequestDto.getDocument();
		String fileName = file.getOriginalFilename();
		String storedFileName = "patient" + formattedDate + "-" + fileName;
		String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		String name = requestorUser.getFirstName() + " " + requestorUser.getLastName();
		String path = Constants.getUplaodPath(session) + storedFileName;
		byte[] data = file.getBytes();

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
		requestWiseFile.setUploaderName(name);
		requestWiseFile.setFileExtension(fileExtension);
		requestWiseFile.setStoredFileName(storedFileName);

		return requestWiseFile;
	}

	private String sendCreatePasswordMail(SomeoneElseRequestDto someoneElseRequestDto,
			HttpServletRequest httpServletRequest, String isExsist) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		System.out.println(createdToken);

		emailToken.setToken(createdToken);
		emailToken.setEmail(someoneElseRequestDto.getEmail());
		emailToken.setResetCompleted(false);
		emailToken.setSentDate(LocalDateTime.now());

		// persisting the object of EmailToken

		if (isExsist.equals("old")) {
			List<EmailToken> emailList = emailTokenDao.getDuplicateEmailEntry(someoneElseRequestDto.getEmail());

			for (EmailToken email : emailList) {
				email.setResetCompleted(true);
			}

			String emailChange = emailTokenDao.updateOldEmailResetStatus(emailList);
			System.out.println(emailChange);
		}

		int mailId = emailTokenDao.createNewEmail(emailToken);

		mailer.sendCreatePasswordMailForOthers(someoneElseRequestDto, httpServletRequest, LocalDateTime.now(),
				emailToken);

		return "success";
	}
	
	private void UpdateAspNetUser(SomeoneElseRequestDto someoneElseRequestDto, AspNetUsers aspNetUsers, Region region, int day,
			int year, String month, Date date) {

		User user = aspNetUsers.getUser();

		aspNetUsers.setModified_date(date);
		aspNetUsers.setPhone_number(someoneElseRequestDto.getPhoneNumber());

		user.setFirstName(someoneElseRequestDto.getFirstName());
		user.setLastName(someoneElseRequestDto.getLastName());
		user.setMobile(someoneElseRequestDto.getPhoneNumber());

		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(month);

		user.setModifiedDate(date);
		aspNetUsers.setUser(user);
		apsnetuserdao.updateAspNetUser(aspNetUsers);
	}

	public String createOldOthersRequest(SomeoneElseRequestDto someoneElseRequestDto, HttpServletRequest httpRequest,
			AspNetUsers registeredAspNetUser) throws ParseException {
		// Creating required objects
		AspNetUsers aspNetUsers;
		AspNetUsers requestorAspNetUsers;
		User user;
		User requestorUser;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestStatusLog requestStatusLog;
		RequestWiseFile requestWiseFile;

		requestorAspNetUsers = (AspNetUsers) httpRequest.getSession().getAttribute("aspUser");

		if (requestorAspNetUsers == null) {
//				throw new Exception("No Session obj found in new other request");
			System.out.println("No Session obj found in new other request");
		}

		requestorUser = requestorAspNetUsers.getUser();

		// Get Object corresponding to region
		List<Region> regionList = regionDao.getRegionEntry(someoneElseRequestDto.getState());
		region = regionList.get(0);

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(someoneElseRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(someoneElseRequestDto.getFormatedDate());

		// Getting Role
		AspNetRoles role = aspNetRolesDao.getRoleObject(AspNetRolesEnum.PATIENT.getAspNetRolesName());

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.FAMILY.getRequestType());

		aspNetUsers = registeredAspNetUser;
		user = aspNetUsers.getUser();
		String password = aspNetUsers.getPassword_hash();

		// updating aspNetUser object
		UpdateAspNetUser(someoneElseRequestDto, aspNetUsers, region, day, year, monthName, currentDate);

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.FAMILY.getRequestType());

		// Setting the request object
		request = createRequest(someoneElseRequestDto, currentDate, requestType, user,requestorUser, region);

		// Setting the requestClient object
		requestClient = createRequestClient(someoneElseRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);

		// Setting the requestStatusLogobject
		requestStatusLog = creatRequestStatusLog(someoneElseRequestDto, currentDate, request);
		request.setRequestStatusLogs(requestStatusLog);

		if (!(someoneElseRequestDto.getDocument().isEmpty())) {
			HttpSession session = httpRequest.getSession();
			// Setting the requestWiseFile
			requestWiseFile = creatRequestWiseFile(someoneElseRequestDto, currentDate, request, session,requestorUser);
			List<RequestWiseFile> requestWiseFilesList = new ArrayList<RequestWiseFile>();
			requestWiseFilesList.add(requestWiseFile);
			request.setListRequestWiseFiles(requestWiseFilesList);

		}

		// persisting object of Request
		int requestId = requestDao.addNewRequest(request);


		if (password == null) {
			String isExsist = "old";
			String mailSentStatus = sendCreatePasswordMail(someoneElseRequestDto, httpRequest, isExsist);
		}

		return "created old";
	}

	public String createNewOthersRequest(SomeoneElseRequestDto someoneElseRequestDto, HttpServletRequest httpRequest)
			throws ParseException {

		// Creating required objects
		AspNetUsers aspNetUsers;
		AspNetUsers requestorAspNetUsers;
		User user;
		User requestorUser;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestStatusLog requestStatusLog;
		RequestWiseFile requestWiseFile;

		requestorAspNetUsers = (AspNetUsers) httpRequest.getSession().getAttribute("aspUser");
		if (requestorAspNetUsers == null) {
//				throw new Exception("No Session obj found in new other request");
			System.out.println("No Session obj found in new other request");
		}

		requestorUser = requestorAspNetUsers.getUser();

		// Get Object corresponding to region
		List<Region> regionList = regionDao.getRegionEntry(someoneElseRequestDto.getState());
		region = regionList.get(0);

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(someoneElseRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(someoneElseRequestDto.getFormatedDate());

		// Getting Role
		AspNetRoles role = aspNetRolesDao.getRoleObject(AspNetRolesEnum.PATIENT.getAspNetRolesName());

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.FAMILY.getRequestType());

		// method for new user
		System.out.println("New");
		aspNetUsers = createAspNetUsers(someoneElseRequestDto, currentDate);

		user = createUser(someoneElseRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);
		aspNetUsers.setUser(user);

		// Setting the request object
		request = createRequest(someoneElseRequestDto, currentDate, requestType, user, requestorUser, region);

		// Setting the requestClient object
		requestClient = createRequestClient(someoneElseRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);

		// Setting the requestStatusLogobject
		requestStatusLog = creatRequestStatusLog(someoneElseRequestDto, currentDate, request);
		request.setRequestStatusLogs(requestStatusLog);

		if (!(someoneElseRequestDto.getDocument().isEmpty())) {

			// Setting the requestWiseFile
			HttpSession session = httpRequest.getSession();
			requestWiseFile = creatRequestWiseFile(someoneElseRequestDto, currentDate, request, session, requestorUser);
			List<RequestWiseFile> requestWiseFilesList = new ArrayList<RequestWiseFile>();
			requestWiseFilesList.add(requestWiseFile);
			request.setListRequestWiseFiles(requestWiseFilesList);
		}

		int aspNetId = apsnetuserdao.createPatient(aspNetUsers);
		int requestId = requestDao.addNewRequest(request);
		String isExsist = "new";
		String mailSentStatus = sendCreatePasswordMail(someoneElseRequestDto, httpRequest, isExsist);
		System.out.println(mailSentStatus);

		return "created new";
	}

	public String createOthersRequest(SomeoneElseRequestDto someoneElseRequestDto, HttpServletRequest httpRequest)
			throws ParseException {

		String ptPhoneNumber = someoneElseRequestDto.getPhoneNumber();

		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ptPhoneNumber);

		try {
			if (!matcher.matches()) {
				throw new Exception("phone number dosen't match");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("phone number dosen't match");
		}

		List<AspNetUsers> list = apsnetuserdao.getUserByEmail(someoneElseRequestDto.getEmail());

		if (list.size() > 0) {
			// method for old user
			AspNetUsers aspNetUsers = list.get(0);
			String status = createOldOthersRequest(someoneElseRequestDto, httpRequest,aspNetUsers );
			return status;
		}

		else {
			String status = createNewOthersRequest(someoneElseRequestDto, httpRequest);
			return status;
		}
	}

}
