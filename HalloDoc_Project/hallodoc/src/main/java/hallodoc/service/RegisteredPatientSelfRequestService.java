package hallodoc.service;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.enumerations.DocType;
import hallodoc.enumerations.RequestStatus;
import hallodoc.helper.Constants;
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

@Service
public class RegisteredPatientSelfRequestService {

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

		String currentNewRequests = String.format("%04d", requestDao.getNewRequestsNo(startDate, endDate));

		String confirmationNumber = regAbbrevation + req_date + lastNameAbbr + firstNameAbbr + currentNewRequests;
		System.out.println(confirmationNumber);

		return confirmationNumber;
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

	private RequestWiseFile creatRequestWiseFile(CreatePatientRequestDto createPatientRequestDto, Date currentDate,
			Request request, HttpSession session) {

		RequestWiseFile requestWiseFile = new RequestWiseFile();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
		String formattedDate = sdf.format(currentDate);

		// Getting details from file obj

		CommonsMultipartFile file = createPatientRequestDto.getDocument();
		String fileName = file.getOriginalFilename();
		String storedFileName = "patient" + formattedDate + "-" + fileName;
		String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		byte[] data = file.getBytes();
		String name = createPatientRequestDto.getFirstName() + " " + createPatientRequestDto.getLastName();
		String path = Constants.getUplaodPath(session) + storedFileName;
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

	public String createNewSelfRequest(CreatePatientRequestDto createPatientRequestDto, HttpServletRequest httpRequest)
			throws ParseException {

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

		aspNetUsers = (AspNetUsers) httpRequest.getSession().getAttribute("aspUser");
		user = aspNetUsers.getUser();


		// Getting Request Type Object
		requestType = requestTypeDao.getRequestTypeObject(hallodoc.enumerations.RequestType.PATIENT.getRequestType());

		// Setting the request object
		request = createRequest(createPatientRequestDto, currentDate, requestType, user, region);

		// Setting the requestClient object
		requestClient = createRequestClient(createPatientRequestDto, currentDate, request, region, day, year,
				monthName);
		request.setRequestClient(requestClient);

		if (!(createPatientRequestDto.getDocument().isEmpty())) {

			// Setting the requestWiseFile
			requestWiseFile = creatRequestWiseFile(createPatientRequestDto, currentDate, request,
					httpRequest.getSession());
			List<RequestWiseFile> requestWiseFilesList = new ArrayList<RequestWiseFile>();
			requestWiseFilesList.add(requestWiseFile);
			request.setListRequestWiseFiles(requestWiseFilesList);
		}

		// persisting object of Request
		int requestId = requestDao.addNewRequest(request);
		return "Created";
	}

}
