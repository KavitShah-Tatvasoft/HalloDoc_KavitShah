package hallodoc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

import hallodoc.dto.CommonRequestDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.RequestStatus;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Business;
import hallodoc.model.Concierge;
import hallodoc.model.EmailToken;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestBusiness;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestConcierge;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetRolesDao;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.BusinessDao;
import hallodoc.repository.ConciergeDao;
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
public class BusinessRequestService {

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
	private ConciergeDao conciergeDao;

	@Autowired
	private BusinessDao businessDao;

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

		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

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
//		userOb.setCreatedBy(aspNetUsers);
		userOb.setCreatedDate(currentDate);
		userOb.setDeleted(false);
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
		request.setCaseNumber(commonRequestDto.getReqCaseNumber());
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

	private String updateBusiness(CommonRequestDto commonRequestDto, Business business, LocalDateTime date,
			Region region) {

		String name = commonRequestDto.getReqFirstName() + " " + commonRequestDto.getReqLastName();
		business.setName(name);
		business.setAddressOne(commonRequestDto.getReqStreet());
		business.setAddressTwo(commonRequestDto.getReqState());
		business.setCity(commonRequestDto.getReqCity());
		business.setRegion(region);
		business.setZipCode(commonRequestDto.getReqZipcode());
		business.setEmail(commonRequestDto.getReqEmail());
		business.setPhoneNumber(commonRequestDto.getReqMobileNumber());
		business.setPropertyName(commonRequestDto.getReqProperty());
		business.setModifiedDate(date);
		business.setDelete(false);
		return "updated";
	}

	private Business createNewBusiness(CommonRequestDto commonRequestDto, LocalDateTime date, Region region) {

		Business business = new Business();
		String name = commonRequestDto.getReqFirstName() + " " + commonRequestDto.getReqLastName();
		business.setName(name);
		business.setAddressOne(commonRequestDto.getReqStreet());
		business.setAddressTwo(commonRequestDto.getReqState());
		business.setCity(commonRequestDto.getReqCity());
		business.setRegion(region);
		business.setZipCode(commonRequestDto.getReqZipcode());
		business.setEmail(commonRequestDto.getReqEmail());
		business.setPhoneNumber(commonRequestDto.getReqMobileNumber());
		business.setPropertyName(commonRequestDto.getReqProperty());
		business.setCreatedDate(date);
		business.setDelete(false);
		return business;
	}

	private RequestBusiness createNewRequestBusiness(Request request, Business business) {

		RequestBusiness requestBusiness = new RequestBusiness();
		requestBusiness.setRequest(request);
		requestBusiness.setBusiness(business);

		return requestBusiness;
	}

	private String sendCreatePasswordMail(CommonRequestDto commonRequestDto, HttpServletRequest httpServletRequest,
			String isExsist) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		System.out.println(createdToken);

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
			System.out.println(emailChange);
		}

		int mailId = emailTokenDao.createNewEmail(emailToken);

		mailer.sendCreatePasswordMail(commonRequestDto, httpServletRequest, LocalDateTime.now(), emailToken);

		return "success";
	}

	private void UpdateAspNetUser(CommonRequestDto commonRequestDto, AspNetUsers aspNetUsers, Region region, int day,
			int year, String month, Date date) {

		User user = aspNetUsers.getUser();

		aspNetUsers.setModified_date(date);
		aspNetUsers.setPhone_number(commonRequestDto.getPtMobileNumber());

		user.setFirstName(commonRequestDto.getPtFirstName());
		user.setLastName(commonRequestDto.getPtLastName());
		user.setMobile(commonRequestDto.getPtMobileNumber());
		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(month);
		user.setModifiedDate(date);
		aspNetUsers.setUser(user);
		apsnetuserdao.updateAspNetUser(aspNetUsers);
	}

	private boolean createNewUserBusinessRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws ParseException {

		System.out.println("New Business");

		// Creating required objects
		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		LocalDateTime currentLocalDate = LocalDateTime.now();
		RequestClient requestClient;
		RequestStatusLog requestStatusLog;
		RequestWiseFile requestWiseFile;
		Business businessObj;

		// Setting object of AspNetUsers
		aspNetUsers = createAspNetUsers(commonRequestDto, currentDate);

		// Get Object corresponding to region
		List<Region> list = regionDao.getRegionEntry(commonRequestDto.getPtState());
		region = list.get(0);

		// Getting Role
		AspNetRoles role = aspNetRolesDao.getRoleObject(AspNetRolesEnum.PATIENT.getAspNetRolesName());

		// Extarcting required fields from date

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		String dateString = dateFormat.format(commonRequestDto.getFormatedDate());
		String[] tokens = dateString.split("-");
		int day = Integer.parseInt(tokens[0]);
		int year = Integer.parseInt(tokens[2]);
		String monthName = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(commonRequestDto.getFormatedDate());

		// Setting the user object
		user = createUser(commonRequestDto, currentDate, aspNetUsers, region, day, year, monthName, role);
		aspNetUsers.setUser(user);

		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.BUSINESS.getRequestType());

		// Setting the request object
		request = createRequest(commonRequestDto, currentDate, requestType, user, region);

		// Setting the requestClient object
		requestClient = createRequestClient(commonRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);
		
		String businessEmail = commonRequestDto.getReqEmail();
		List<Business> businessList = businessDao.getExistingBusinessByEmail(businessEmail);

		System.out.println("Here1");
		if (businessList.size() > 0) {
			businessObj = businessList.get(0);

			String updateBusiness = updateBusiness(commonRequestDto, businessObj, currentLocalDate, region);
			String updated = businessDao.updateBusiness(businessObj);
			System.out.println(updated);
		} else {
			System.out.println("Here2");
			businessObj = createNewBusiness(commonRequestDto, currentLocalDate, region);
			int businessId = businessDao.addBusiness(businessObj);
		}

		RequestBusiness requestBusiness = createNewRequestBusiness(request, businessObj);

		// persisting object

		int aspNetId = apsnetuserdao.createPatient(aspNetUsers);

//		int userId = userDao.addNewPatientRequest(user);  //dont uncomment 

		int requestId = requestDao.addNewRequest(request);

//		int requestClientId = requestClientDao.addNewRequestClient(requestClient);

//		int requestStatusLogId = requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

		int requestBusinessId = businessDao.addRequestBusiness(requestBusiness);

		String isExsist = "new";
		String mailSentStatus = sendCreatePasswordMail(commonRequestDto, httpServletRequest, isExsist);
		System.out.println(mailSentStatus);

		return true;

	}

	private boolean createOldUserBusinessRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws ParseException {

		System.out.println("Old Business");

		// Creating required objects
		AspNetUsers aspNetUsers;
		User user;
		Region region;
		Region businessRegion;
		Request request;
		RequestType requestType;
		Date currentDate = new Date();
		LocalDateTime currentLocalDate = LocalDateTime.now();
		RequestClient requestClient;
		RequestStatusLog requestStatusLog;
		RequestWiseFile requestWiseFile;
		Business businessObj;

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
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.BUSINESS.getRequestType());
		
		// Setting the request object
		request = createRequest(commonRequestDto, currentDate, requestType, user, region);

		// Setting the requestClient object
		requestClient = createRequestClient(commonRequestDto, currentDate, request, region, day, year, monthName);
		request.setRequestClient(requestClient);
		
		String businessEmail = commonRequestDto.getReqEmail();
		List<Business> businessList = businessDao.getExistingBusinessByEmail(businessEmail);

		if (businessList.size() > 0) {
			businessObj = businessList.get(0);

			String updateBusiness = updateBusiness(commonRequestDto, businessObj, currentLocalDate, region);
			String updated = businessDao.updateBusiness(businessObj);
			System.out.println(updated);
		} else {
			businessObj = createNewBusiness(commonRequestDto, currentLocalDate,region);
			int businessId = businessDao.addBusiness(businessObj);
		}

		RequestBusiness requestBusiness = createNewRequestBusiness(request, businessObj);

		// persisting object of Request
		int requestId = requestDao.addNewRequest(request);
		// persisting object of RequestClient
//		int requestClientId = requestClientDao.addNewRequestClient(requestClient);
		// Persisting the requestStatusLogobject
//		int requestStatusLogId = requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

		int requestBusinessId = businessDao.addRequestBusiness(requestBusiness);

		if (password == null) {
			String isExsist = "old";
			String mailSentStatus = sendCreatePasswordMail(commonRequestDto, httpServletRequest, isExsist);
		}
		return true;

	}

	public boolean createBusinessRequest(CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws Exception {

		String ptPhoneNumber = commonRequestDto.getPtMobileNumber();
		String reqPhoneNumber = commonRequestDto.getReqMobileNumber();

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

			List<AspNetUsers> list = apsnetuserdao.getUserByEmail(commonRequestDto.getPtEmail());

			if (list.size() > 0) {
				// method for old user
				createOldUserBusinessRequest(commonRequestDto, session, httpServletRequest);
			}

			else {
				// method for new user
				createNewUserBusinessRequest(commonRequestDto, session, httpServletRequest);
			}

			return true;
		}
	}
}
