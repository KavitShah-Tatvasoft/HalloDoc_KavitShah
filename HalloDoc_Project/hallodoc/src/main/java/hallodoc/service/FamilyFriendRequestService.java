package hallodoc.service;

import java.io.File;
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

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
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
public class FamilyFriendRequestService {

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
		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		 ;

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

	private User createUser(CommonRequestDto commonRequestDto, Date currentDate, AspNetUsers aspNetUsers, Region region,
			int day, int year, String monthName, AspNetRoles role) {

		User userOb = new User();

		// Setters of User Entity

		userOb.setAspNetUsers(aspNetUsers);
		userOb.setFirstName(commonRequestDto.getPtFirstName());
		userOb.setLastName(commonRequestDto.getPtLastName());
		userOb.setEmail(commonRequestDto.getPtEmail());
		userOb.setMobile(commonRequestDto.getPtMobileNumber());
//		userOb.setStreet(commonRequestDto.getPtStreet());
//		userOb.setState(commonRequestDto.getPtState());
//		userOb.setCity(commonRequestDto.getPtCity());
//		userOb.setZipcode(commonRequestDto.getPtZipcode());
//		userOb.setRegion(region);
		userOb.setStrMonth(monthName);
		userOb.setIntYear(year);
		userOb.setIntDate(day);
		userOb.setCreatedBy(aspNetUsers);
		userOb.setCreatedDate(currentDate);
		userOb.setDeleted(false);
		userOb.setRequestWithEmail(false);
		userOb.setAspNetRoles(role);
		userOb.setRequestWithEmail(true);
		return userOb;
	}

	private Request createRequest(CommonRequestDto commonRequestDto, Date currentDate, RequestType requestType,
			User user, Region region) {
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

	private RequestClient createRequestClient(CommonRequestDto commonRequestDto, Date currentDate, Request request,
			Region region, int day, int year, String monthName) {
		RequestClient requestClient = new RequestClient();

		// Setters of RequestClient
		requestClient.setRequest(request);
		requestClient.setFirstName(commonRequestDto.getPtFirstName());
		requestClient.setLastName(commonRequestDto.getPtLastName());
		requestClient.setNotiMobile(commonRequestDto.getPtMobileNumber());
		requestClient.setRegion(region);
		requestClient.setPhoneNumber(commonRequestDto.getPtMobileNumber());
		requestClient.setNotiEmail(commonRequestDto.getPtEmail());
		requestClient.setNotes(commonRequestDto.getSymptoms());
		requestClient.setEmail(commonRequestDto.getPtEmail());
		requestClient.setStrMonth(monthName);
		requestClient.setIntYear(year);
		requestClient.setIntDate(day);
		requestClient.setStreet(commonRequestDto.getPtStreet());
		requestClient.setCity(commonRequestDto.getPtCity());
		requestClient.setState(commonRequestDto.getPtState());
		requestClient.setZipcode(commonRequestDto.getPtZipcode());

		return requestClient;
	}

	private RequestWiseFile creatRequestWiseFile(CommonRequestDto commonRequestDto, Date currentDate, Request request,
			HttpSession session) {

		RequestWiseFile requestWiseFile = new RequestWiseFile();

		// Getting details from file obj
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
		String formattedDate = sdf.format(currentDate);

		CommonsMultipartFile file = commonRequestDto.getDocument();
		String fileName = file.getOriginalFilename();
		String storedFileName = "patient" + formattedDate +"-"+ fileName ;
		String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		String name = commonRequestDto.getReqFirstName()+" "+commonRequestDto.getReqLastName();
		String path = Constants.getUplaodPath(session) + storedFileName;
		byte[] data = file.getBytes();

		
		 ;

		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			 ;
		} catch (Exception e) {
			e.printStackTrace();
			 ;
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

	private void UpdateAspNetUser(CommonRequestDto commonRequestDto, AspNetUsers aspNetUsers, Region region, int day,
			int year, String month, Date date) {

		User user = aspNetUsers.getUser();

		aspNetUsers.setModified_date(date);
		aspNetUsers.setPhone_number(commonRequestDto.getPtMobileNumber());

		user.setFirstName(commonRequestDto.getPtFirstName());
		user.setLastName(commonRequestDto.getPtLastName());
		user.setMobile(commonRequestDto.getPtMobileNumber());
//		user.setStreet(commonRequestDto.getPtStreet());
//		user.setCity(commonRequestDto.getPtCity());
//		user.setState(commonRequestDto.getPtState());
//		user.setRegion(region);
//		user.setZipcode(commonRequestDto.getPtZipcode());
		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(month);
//		user.setModifiedBy(aspNetUsers);
		user.setModifiedDate(date);
		aspNetUsers.setUser(user);
		apsnetuserdao.updateAspNetUser(aspNetUsers);
	}

	private String sendCreatePasswordMail(CommonRequestDto commonRequestDto, HttpServletRequest httpServletRequest,
			String isExsist) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		 ;

		emailToken.setToken(createdToken);
		emailToken.setEmail(commonRequestDto.getPtEmail());
		emailToken.setResetCompleted(false);
		emailToken.setSentDate(LocalDateTime.now());

		// persisting the object of EmailToken

		if (isExsist.equals("old")) {
			List<EmailToken> emailList = emailTokenDao.getDuplicateEmailEntry(commonRequestDto.getPtEmail());

			for (EmailToken email : emailList) {
				email.setResetCompleted(true);
			}

			String emailChange = emailTokenDao.updateOldEmailResetStatus(emailList);
			 ;
		}

		int mailId = emailTokenDao.createNewEmail(emailToken);
		
		mailer.sendCreatePasswordMail(commonRequestDto, httpServletRequest, LocalDateTime.now(), emailToken);

		return "success";
	}

	private boolean createNewUserFamilyFriendRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws ParseException {
		 ;

		// Creating required objects
		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestWiseFile requestWiseFile;

		// Setting object of AspNetUsers
		aspNetUsers = createAspNetUsers(commonRequestDto, currentDate);

		// Get Object corresponding to region
		List<Region> list = regionDao.getRegionEntry(commonRequestDto.getPtState());
		region = list.get(0);

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(commonRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(commonRequestDto.getFormatedDate());

		// Getting Role
		AspNetRoles role = aspNetRolesDao.getRoleObject(AspNetRolesEnum.PATIENT.getAspNetRolesName());

		// Setting the user object
		user = createUser(commonRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);
		aspNetUsers.setUser(user);

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.FAMILY.getRequestType());

		// Setting the request object
		request = createRequest(commonRequestDto, currentDate, requestType, user, region);

		// Setting the requestClient object
		requestClient = createRequestClient(commonRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);
		
		if (!(commonRequestDto.getDocument().isEmpty())) {

			// Setting the requestWiseFile
			requestWiseFile = creatRequestWiseFile(commonRequestDto, currentDate, request, session);
			List<RequestWiseFile> requestWiseFilesList = new ArrayList<RequestWiseFile>();
			requestWiseFilesList.add(requestWiseFile);
			request.setListRequestWiseFiles(requestWiseFilesList);
			
//			// Persisting the requestWiseFile
//			int requestWiseFileId = requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
		}

		// persisting object

		int aspNetId = apsnetuserdao.createPatient(aspNetUsers);

//		int userId = userDao.addNewPatientRequest(user); 


//		int requestClientId = requestClientDao.addNewRequestClient(requestClient);


		int requestId = requestDao.addNewRequest(request);
		String isExsist = "new";
		String mailSentStatus = sendCreatePasswordMail(commonRequestDto, httpServletRequest,isExsist);
		 ;
		return true;
	}

	private boolean createOldUserFamilyFriendRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws ParseException {
		 ;

		// Creating required objects
		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		RequestClient requestClient;
		RequestWiseFile requestWiseFile;

		aspNetUsers = apsnetuserdao.getUserByEmail(commonRequestDto.getPtEmail()).get(0);
		user = aspNetUsers.getUser();
		String password = aspNetUsers.getPassword_hash();

		// Get Object corresponding to region
		List<Region> list = regionDao.getRegionEntry(commonRequestDto.getPtState());
		region = list.get(0);

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(commonRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(commonRequestDto.getFormatedDate());

		// updating aspNetUser object
		UpdateAspNetUser(commonRequestDto, aspNetUsers, region, day, year, monthName, currentDate);

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.FAMILY.getRequestType());

		// Setting the request object
		request = createRequest(commonRequestDto, currentDate, requestType, user, region);


		// Setting the requestClient object
		requestClient = createRequestClient(commonRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);
		

		if (!(commonRequestDto.getDocument().isEmpty())) {

			// Setting the requestWiseFile
			requestWiseFile = creatRequestWiseFile(commonRequestDto, currentDate, request, session);
			List<RequestWiseFile> requestWiseFilesList = new ArrayList<RequestWiseFile>();
			requestWiseFilesList.add(requestWiseFile);
			request.setListRequestWiseFiles(requestWiseFilesList);
			
//			// Persisting the requestWiseFile
//			int requestWiseFileId = requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
		}

		// persisting object of Request
		int requestId = requestDao.addNewRequest(request);
		
//		// persisting object of RequestClient
//		int requestClientId = requestClientDao.addNewRequestClient(requestClient);
		
		if (password==null) {
			String isExsist = "old";
			String mailSentStatus = sendCreatePasswordMail(commonRequestDto, httpServletRequest,isExsist);
		}

		return true;
	}

	public boolean createNewFamilyFriendRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws Exception {

		String reqPhoneNumber = commonRequestDto.getReqMobileNumber();
		String ptPhoneNumber = commonRequestDto.getPtMobileNumber();
		
		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ptPhoneNumber);
		Matcher matcher2 = pattern.matcher(reqPhoneNumber);

		

//	if (!matcher.matches()) {
//		return false;
//		}
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
				createOldUserFamilyFriendRequest(commonRequestDto, session, httpServletRequest);
			}

			else {
				// method for new user
				createNewUserFamilyFriendRequest(commonRequestDto, session, httpServletRequest);
			}

			return true;
		}
	}

}
