package hallodoc.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.http.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.BlockCaseDto;
import hallodoc.dto.CancelCaseDetailsDto;
import hallodoc.dto.ClearCaseDto;
import hallodoc.dto.CloseCaseEditDataDto;
import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.EncounterFormDto;
import hallodoc.dto.EncounterFormUserDetailsDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.OrderVendorDetailsDto;
import hallodoc.dto.OrdersDetailsDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.UpdateCaseDto;
import hallodoc.dto.UserProfileDto;
import hallodoc.dto.VendorDetailsDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.DocType;
import hallodoc.enumerations.MessageTypeEnum;
import hallodoc.helper.Constants;
import hallodoc.helper.ExcelSheetHelper;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.mapper.ViewNotesMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.BlockRequests;
import hallodoc.model.CaseTag;
import hallodoc.model.EmailLog;
import hallodoc.model.EmailToken;
import hallodoc.model.EncounterForm;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;
import hallodoc.model.OrderDetails;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestNotes;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.SmsLog;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.BlockRequestsDao;
import hallodoc.repository.CaseTagDao;
import hallodoc.repository.EmailTokenDao;
import hallodoc.repository.EncounterFormDao;
import hallodoc.repository.HealthProfessionalsDao;
import hallodoc.repository.LogsDao;
import hallodoc.repository.OrderDetailsDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestNotesDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestWiseFileDao;
import hallodoc.sms.SmsService;

@Service
public class UserService {

	@Autowired
	private AspNetUserDao apsnetuserdao;

	@Autowired
	private EmailTokenDao emailTokenDao;

	@Autowired
	private RequestWiseFileDao requestWiseFileDao;

	@Autowired
	private EmailService mailer;

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private RequestStatusLogDao requestStatusLogDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private CaseTagDao caseTagDao;

	@Autowired
	private BlockRequestsDao blockRequestsDao;

	@Autowired
	private RequestNotesDao requestNotesDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogsDao logsDao;

	@Autowired
	private SmsService smsService;

	@Autowired
	private HealthProfessionalsDao healthProfessionalsDao;
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	@Autowired
	private EncounterFormDao encounterFormDao;

	private String resendCreatePasswordMail(User user, HttpServletRequest httpServletRequest) {

		EmailToken emailToken = new EmailToken();

		UUID newToken = UUID.randomUUID();
		String createdToken = newToken.toString();

		System.out.println(createdToken);

		emailToken.setToken(createdToken);
		emailToken.setEmail(user.getEmail());
		emailToken.setResetCompleted(false);
		emailToken.setSentDate(LocalDateTime.now());

		// persisting the object of EmailToken

		List<EmailToken> emailList = emailTokenDao.getDuplicateEmailEntry(user.getEmail());

		for (EmailToken email : emailList) {
			email.setResetCompleted(true);
		}

		String emailChange = emailTokenDao.updateOldEmailResetStatus(emailList);
		System.out.println(emailChange);

		int mailId = emailTokenDao.createNewEmail(emailToken);

		mailer.resendCreatePasswordMail(user, httpServletRequest, LocalDateTime.now(), emailToken);

		return "success";
	}

	public int validateUser(String username, String password, HttpServletRequest request) {

		List<AspNetUsers> list = apsnetuserdao.getUserByUsername(username);

		if (list.isEmpty()) {
			System.out.println("No such user found");
			return -1;
		}

		else {
			AspNetUsers user = list.get(0);
			String passwordHash = user.getPassword_hash();

			if (passwordHash == null) {
				System.out.println("password not set");
				String status = resendCreatePasswordMail(user.getUser(), request);
				return -2;
			} else {
				BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
				int role = user.getUser().getAspNetRoles().getId();
				boolean verified = Password.check(password, passwordHash).with(bcrypt);

				if (verified) {

					List<Region> regionList = regionDao.getAllRegions();

					System.out.println("verified" + role);
					HttpSession session = request.getSession();
					session.setAttribute("aspUser", user);
					session.setAttribute("regionList", regionList);
					return role;
				} else {
					System.out.println("password not match");
					return -3;
				}

			}

		}
	}

	public String uploadRequestDocument(CommonsMultipartFile document, String name, Request request,
			HttpSession session, Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");

		String formattedDate = sdf.format(date);

		RequestWiseFile requestWiseFile = new RequestWiseFile();

		AspNetUsers aspNetUsers = (AspNetUsers) session.getAttribute("aspUser");
		User user = aspNetUsers.getUser();
		// Getting details from file obj

		String fileName = document.getOriginalFilename();
		byte[] data = document.getBytes();
		String fileExtension = document.getOriginalFilename()
				.substring(document.getOriginalFilename().lastIndexOf('.') + 1);
		String storedFileName = "patient" + formattedDate + "-" + fileName;
		System.out.println(storedFileName);
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
		requestWiseFile.setCreatedDate(date);
		requestWiseFile.setDocType(DocType.TEST_ONE.getDocId());
		requestWiseFile.setFinalize(false);
		requestWiseFile.setDeleted(false);
		requestWiseFile.setUploaderName(name);
		requestWiseFile.setFileExtension(fileExtension);
		requestWiseFile.setStoredFileName(storedFileName);

		switch (user.getAspNetRoles().getId()) {
		case 1:
			requestWiseFile.setAdmin(aspNetUsers.getAdmin());
			break;
		case 2:
			requestWiseFile.setPhysician(null);
			break;
		default:
			break;
		}

		requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);

		return "Uploaded Succesfully";
	}

	public Request getRequestObject(int id) {
		Request request = requestDao.getRequestOb(id);
		return request;
	}

	public List<NewRequestDataDto> getFilteredRequest(RequestFiltersDto requestFiltersDto) {
		List<Request> filteredList = requestDao.getFilteredRequests(requestFiltersDto);
		List<NewRequestDataDto> newRequestDataDtos = new ArrayList<NewRequestDataDto>();

		NewRequestDataDto newRequestDataDto;
		for (Request request : filteredList) {
			newRequestDataDto = RequestNewDataDtoMapper.mapDataNeWDataDto(request);
			newRequestDataDtos.add(newRequestDataDto);
		}
		return newRequestDataDtos;
	}

//	public Request getViewCaseRequest(int reqId) {
//		Request requestOb = requestDao.getRequestOb(reqId);
//		return requestOb;
//	}

	public String updateViewCaseDetails(UpdateCaseDto updateCaseDto) {

//		if(updateCaseDto.getPhoneNumber().contains("+")) {
//			String phone = updateCaseDto.getPhoneNumber();
////			String tokens[2] = phone.split("");
//		}
		System.out.println(updateCaseDto.getDateOfBirth());
		String[] tokens = updateCaseDto.getDateOfBirth().split("-");
		int day = Integer.parseInt(tokens[2]);
		int monthInt = Integer.parseInt(tokens[1]) - 1;
		int year = Integer.parseInt(tokens[0]);
		String[] months = { "January", "February", "March", "April", "June", "July", "August", "September", "October",
				"November", "December" };
		String month = months[monthInt];

		Request request = requestDao.getRequestOb(updateCaseDto.getReqId());
		RequestClient requestClient = request.getRequestClient();
		User user = request.getUser();
		AspNetUsers aspNetUsers = user.getAspNetUsers();

		aspNetUsers.setPhone_number(updateCaseDto.getPhoneNumber());

		user.setFirstName(updateCaseDto.getFirstName());
		user.setLastName(updateCaseDto.getLastName());
		user.setMobile(updateCaseDto.getPhoneNumber());
		user.setIntDate(day);
		user.setIntYear(year);
		user.setStrMonth(month);

		aspNetUsers.setUser(user);

		if (request.getRequestType().getRequestTypeId() == 2) {
			request.setFirstName(updateCaseDto.getFirstName());
			request.setLastName(updateCaseDto.getLastName());
			request.setPhoneNumber(updateCaseDto.getPhoneNumber());
		}

		requestClient.setFirstName(updateCaseDto.getFirstName());
		requestClient.setLastName(updateCaseDto.getLastName());
		requestClient.setPhoneNumber(updateCaseDto.getPhoneNumber());
		requestClient.setIntDate(day);
		requestClient.setIntYear(year);
		requestClient.setStrMonth(month);
		requestClient.setNotiMobile(updateCaseDto.getPhoneNumber());
		request.setRequestClient(requestClient);

		apsnetuserdao.updateAspNetUser(aspNetUsers);
		requestDao.updateRequest(request);

		return "Updated";
	}

	public List<CaseTag> getAllCancellationReasons() {
		List<CaseTag> caseTags = caseTagDao.getCancellationReasons();
		return caseTags;
	}

	public boolean cancelRequestedCase(CancelCaseDetailsDto cancelCaseDetailsDto,
			HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(cancelCaseDetailsDto.getRequestId());
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();
		request.setStatus(3);
		request.setCaseTag(cancelCaseDetailsDto.getCaseTagId());
		request.setModifieDate(date);

		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(3);
		requestStatusLog.setAdmin(admin);
		requestStatusLog.setNotes(cancelCaseDetailsDto.getAdditionalNotes());
		requestStatusLog.setCreatedDate(date);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

		return true;
	}

	public boolean blockRequestedCase(BlockCaseDto blockCaseDto, HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(blockCaseDto.getRequestId());
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();

		request.setStatus(11);
		request.setModifieDate(date);

		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(11);
		requestStatusLog.setAdmin(admin);
		requestStatusLog.setNotes(blockCaseDto.getBlockReason());
		requestStatusLog.setCreatedDate(date);

		BlockRequests blockRequests = new BlockRequests();
		blockRequests.setPhoneNumber(request.getRequestClient().getPhoneNumber());
		blockRequests.setEmail(request.getRequestClient().getEmail());
		blockRequests.setActive(false);
		blockRequests.setReason(blockCaseDto.getBlockReason());
		blockRequests.setRequest(request);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		blockRequestsDao.addBlockRequest(blockRequests);

		return true;
	}

	public List<ViewNotesDto> getRequestSpecificLogs(int reqId) {
		List<RequestStatusLog> requestStatusLogs = requestStatusLogDao.getAllRequestSpecificLogs(reqId);
		List<RequestNotes> requestNotes = requestNotesDao.getRequestSpecificNote(reqId);
		List<ViewNotesDto> viewNotesDtos = new ArrayList<ViewNotesDto>();

		if (requestStatusLogs.size() == 0 && requestNotes.size() == 0) {
			return viewNotesDtos;
		}
		if (requestStatusLogs.size() == 0 && requestNotes.size() > 0) {
			ViewNotesDto viewNotesDto = new ViewNotesDto();
			viewNotesDto.setAdminNotes(requestNotes.get(0).getAdminNotes());
			viewNotesDto.setProviderNotes(requestNotes.get(0).getPhysicanNotes());
			viewNotesDtos.add(viewNotesDto);
			return viewNotesDtos;
		}
		if (requestStatusLogs.size() > 0) {
			for (RequestStatusLog requestStatusLog : requestStatusLogs) {
				ViewNotesDto viewNotesDto = ViewNotesMapper.mapViewNotesData(requestStatusLog);
				if (requestNotes.size() > 0) {
					viewNotesDto.setAdminNotes(requestNotes.get(0).getAdminNotes());
					viewNotesDto.setProviderNotes(requestNotes.get(0).getPhysicanNotes());
				}
				viewNotesDtos.add(viewNotesDto);
			}

			return viewNotesDtos;
		}

		return viewNotesDtos;
	}

	public void updateAdminNote(String adminNote, int reqId, AspNetUsers user) {
		List<RequestNotes> requestNotes = requestNotesDao.getRequestSpecificNote(reqId);
		if (requestNotes.size() > 0) {
			RequestNotes requestNote = requestNotes.get(0);
			requestNote.setAdminNotes(adminNote);
			requestNote.setModifiedBy(user);

			requestNotesDao.updateRequestNotes(requestNote);
		} else {
			RequestNotes requestNote = new RequestNotes();
			Request request = requestDao.getRequestOb(reqId);
			requestNote.setAdminNotes(adminNote);
			requestNote.setCreatedBy(user);
			requestNote.setModifiedBy(user);
			requestNote.setRequest(request);

			requestNotesDao.saveRequestNotes(requestNote);
		}
	}

	public ResponseEntity<Resource> exportDataToExcelSheet(List<NewRequestDataDto> list, String status)
			throws IOException {

		String fileName = status + "-exportData.xlsx";

		ByteArrayInputStream byteArrayInputStream = ExcelSheetHelper.dataTOExcel(list, status);
		InputStreamResource file = new InputStreamResource(byteArrayInputStream);

		ResponseEntity<Resource> body = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);

		return body;
	}

	public void clearRequestedCase(int reqId, HttpServletRequest httpServletRequest) {

		Request request = requestDao.getRequestOb(reqId);
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		Date date = new Date();
		String note = "Admin " + admin.getFirstName() + " cancelled the case on " + date;

		requestStatusLog.setAdmin(admin);
		requestStatusLog.setPhysician(request.getPhysician());
		requestStatusLog.setNotes(note);
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(10);
		requestStatusLog.setCreatedDate(date);

		request.setStatus(10);
		request.setModifieDate(date);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
	}

	public SendAgreementDto getRequiredSendAgreementDetails(int reqId, HttpServletRequest httpServletRequest) {

		SendAgreementDto sendAgreementDto = new SendAgreementDto();
		Request request = requestDao.getRequestOb(reqId); // change this
		RequestClient requestClient = request.getRequestClient();

		sendAgreementDto.setEmail(requestClient.getEmail());
		sendAgreementDto.setReqId(reqId);
		String mobileNumber = requestClient.getPhoneNumber();
		String updatedMobileNumber = mobileNumber.replace("+91", "");
		sendAgreementDto.setPhoneNumber(updatedMobileNumber.trim());

		return sendAgreementDto;

	}

	public String sendAgreementToPatient(SendAgreementDto sendAgreementDto, HttpServletRequest httpServletRequest) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Request request = requestDao.getRequestOb(sendAgreementDto.getReqId());
		EmailLog emailLog = new EmailLog();
		SmsLog smsLog = new SmsLog();
		LocalDateTime localDateTime = LocalDateTime.now();
		String recipientName = request.getRequestClient().getFirstName() + " "
				+ request.getRequestClient().getLastName();
		String status = "";

		emailLog.setSubjectName("Agreement Link");
		emailLog.setEmailId(sendAgreementDto.getEmail());
		emailLog.setRequestId(request.getRequestId());
		emailLog.setConfirmationNumber(request.getConfirmationNumber());
		emailLog.setAdminId(admin.getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.SEND_AGREEMENT.getMessageTypeId());
		emailLog.setRecipientName(recipientName);

		smsLog.setMobileNumber(sendAgreementDto.getPhoneNumber());
		smsLog.setRequestId(request.getRequestId());
		smsLog.setConfirmationNumber(request.getConfirmationNumber());
		smsLog.setAdminId(admin.getAdminId());
		smsLog.setCreatedDate(localDateTime);
		smsLog.setSentDate(localDateTime);
		smsLog.setAction(MessageTypeEnum.SEND_AGREEMENT.getMessageTypeId());
		smsLog.setRecipientName(recipientName);

		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendAgreementLink("Agreement Link", request, httpServletRequest, sendAgreementDto);
				isSent = true;
				emailLog.setSentTries(i);
				emailLog.setEmailSent(isSent);
				this.logsDao.addEmailLogEntry(emailLog);
				status = status + "mail send";

			} catch (Exception e) {
				if (i == 3) {
					emailLog.setSentTries(3);
					emailLog.setEmailSent(false);
					this.logsDao.addEmailLogEntry(emailLog);
					status = status + "failed to send mail";

				}
			}
		}

		boolean isSmsSent = false;
		int smsSentTries = 1;
		for (int i = smsSentTries; i <= 3; i++) {
			if (isSmsSent) {
				continue;
			}
			try {
				this.smsService.sendAgreementSms("Agreement Link", request, httpServletRequest, sendAgreementDto);
				isSmsSent = true;
				smsLog.setSentTries(i);
				smsLog.setSmsSent(isSmsSent);
				this.logsDao.addSmsLogEntry(smsLog);
				status = status + "sms send";

			} catch (Exception e) {
				if (i == 3) {
					smsLog.setSentTries(3);
					smsLog.setSmsSent(isSmsSent);
					this.logsDao.addSmsLogEntry(smsLog);
					status = status + "failed to send send";

				}
			}
		}

		return status;
	}

	public boolean validateAgreementRequest(int reqId) {
		Request request = requestDao.getRequestOb(reqId);
		if (request.getStatus() == 2) {
			return true;
		} else {
			return false;
		}

	}

	public String acceptAgreement(int reqId, HttpServletRequest httpServletRequest, boolean acceptance,
			String cancellationReasons) {

		Request request = requestDao.getRequestOb(reqId);
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		Date date = new Date();

		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setPhysician(request.getPhysician());
		requestStatusLog.setRequest(request);

		request.setModifieDate(date);

		if (acceptance) {
			requestStatusLog.setStatus(4);
			requestStatusLog.setNotes("Patient accepted the agreement on " + date);
			request.setStatus(4);
		} else {
			requestStatusLog.setNotes(cancellationReasons);
			requestStatusLog.setStatus(7);
			request.setStatus(7);
		}

		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		requestDao.updateRequest(request);
		return "accepted";
	}

	public Request getRequestById(int id) {
		return requestDao.getRequestOb(id);
	}

	public void softDeleteRequestedFile(int fileId) {
		RequestWiseFile requestWiseFile = this.requestWiseFileDao.getRequestWiseFileOb(fileId);
		requestWiseFile.setDeleted(true);
		requestWiseFileDao.deleteRequestedFile(requestWiseFile);
	}

	public void softDeleteMultipleRequestedFile(String list) {
		String[] arrOfStr = list.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (String string : arrOfStr) {
			idList.add(Integer.parseInt(string));
		}

		requestWiseFileDao.deleteMultipleRequestedFile(idList);
	}

	public String sendFilesToPatient(int reqId, String listOfId, HttpServletRequest httpServletRequest) {
		String[] arrOfStr = listOfId.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String a : arrOfStr) {
			list.add(Integer.parseInt(a));
		}
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();

		LocalDateTime localDateTime = LocalDateTime.now();

		Request request = requestDao.getRequestOb(reqId);

		String recipientName = request.getRequestClient().getFirstName() + " "
				+ request.getRequestClient().getLastName();

		List<RequestWiseFile> requestWiseFile = request.getListRequestWiseFiles();
		List<String> storedfileNames = new ArrayList<String>();
		List<String> realfileNames = new ArrayList<String>();

		requestWiseFile.stream().forEach(i -> {
			if (list.contains(i.getRequestWiseFileId())) {
				storedfileNames.add(i.getStoredFileName());
				realfileNames.add(i.getFileName());
			}
		});

		String status = "";
		EmailLog emailLog = new EmailLog();
		String subject = "Health Reports/ Documents";
		emailLog.setSubjectName(subject);
		emailLog.setEmailId(request.getRequestClient().getEmail());
		emailLog.setRequestId(request.getRequestId());
		emailLog.setConfirmationNumber(request.getConfirmationNumber());
		emailLog.setAdminId(admin.getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.SEND_FILES.getMessageTypeId());
		emailLog.setRecipientName(recipientName);
		emailLog.setPhysicianId(request.getPhysician().getPhysicianId());

		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendFilesViaEmail(subject, request, httpServletRequest, storedfileNames,
						realfileNames);
				isSent = true;
				emailLog.setSentTries(i);
				emailLog.setEmailSent(isSent);
				this.logsDao.addEmailLogEntry(emailLog);
				status = status + "mail send";

			} catch (Exception e) {
				if (i == 3) {
					emailLog.setSentTries(3);
					emailLog.setEmailSent(false);
					this.logsDao.addEmailLogEntry(emailLog);
					status = status + "failed to send mail";
					return status;
				}
			}

		}
		return status;

	}

	public List<HealthProfessionalTypes> getActiveProfessions() {

		List<HealthProfessionalTypes> list = this.healthProfessionalsDao.getProfessions();
		return list;
	}

	public List<VendorDetailsDto> getActiveVendors(int professionTypeId) {

		List<VendorDetailsDto> vendorList = this.healthProfessionalsDao.getVendors(professionTypeId);
		return vendorList;
	}

	public OrderVendorDetailsDto getOrderVendorDetails(int vendorId) {

		return this.healthProfessionalsDao.getOrderVendorDetails(vendorId);

	}

	public String sendOrderDetails(OrdersDetailsDto ordersDetailsDto, HttpServletRequest httpServletRequest) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		EmailLog emailLog = new EmailLog();
		OrderDetails orderDetail = new OrderDetails();
		LocalDateTime localDateTime = LocalDateTime.now();
		Request request = requestDao.getRequestOb(ordersDetailsDto.getOrderRequestId());
		HealthProfessionals healthProfessionals = this.healthProfessionalsDao.getProfessionalOb(ordersDetailsDto.getBusinessTypeId());
		
		orderDetail.setBusinessContact(ordersDetailsDto.getBusinessContactNumber());
		orderDetail.setBusinessEmail(ordersDetailsDto.getBusinessEmailContact());
		orderDetail.setCreatedBy(admin.getAdminId());
		orderDetail.setFaxNumber(ordersDetailsDto.getBusinessFaxNumber());
		orderDetail.setNumberOfRefills(ordersDetailsDto.getRefillsDetails());
		orderDetail.setRequestId(ordersDetailsDto.getOrderRequestId());
		orderDetail.setPrescriptions(ordersDetailsDto.getBusinessPrescriptionDetails());
		orderDetail.setVendorId(ordersDetailsDto.getBusinessTypeId());
		
		String subject = "New Order Details";
		emailLog.setSubjectName(subject);
		emailLog.setEmailId(request.getRequestClient().getEmail());
		emailLog.setRequestId(request.getRequestId());
		emailLog.setConfirmationNumber(request.getConfirmationNumber());
		emailLog.setAdminId(admin.getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.SEND_ORDERS.getMessageTypeId());
		emailLog.setRecipientName(healthProfessionals.getVendorName());

		int id = this.orderDetailsDao.saveOrderDetail(orderDetail);
		
		String status = "";
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendNewOrder("New Order Details", request, httpServletRequest, ordersDetailsDto, healthProfessionals.getVendorName() );
				isSent = true;
				emailLog.setSentTries(i);
				emailLog.setEmailSent(isSent);
				this.logsDao.addEmailLogEntry(emailLog);
				status = status + "mail send";

			} catch (Exception e) {
				if (i == 3) {
					emailLog.setSentTries(3);
					emailLog.setEmailSent(false);
					this.logsDao.addEmailLogEntry(emailLog);
					status = status + "failed to send mail";

				}
			}
		}
		
		return status;
	}
	
	public String updateEncounterFormDetails(EncounterFormDto encounterFormDto, HttpServletRequest httpServletRequest) {
		
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Request request = requestDao.getRequestOb(encounterFormDto.getRequestId());
		EncounterForm  encounterForm;
		List<EncounterForm> encounterForms = encounterFormDao.getEncounterFormByReqID(encounterFormDto.getRequestId());
		
		if(encounterForms.size()>0) {
			encounterForm = encounterForms.get(0);
		}else {			
			encounterForm = new EncounterForm();
		}
		
		encounterForm.setAbd(encounterFormDto.getAbd());
		encounterForm.setAdminId(admin.getAdminId());
		encounterForm.setAllergies(encounterFormDto.getAllergies());
		encounterForm.setBloodPresureneg(encounterFormDto.getBloodPresureneg());
		encounterForm.setBloodPresurePlus(encounterFormDto.getBloodPresurePlus());
		encounterForm.setChest(encounterFormDto.getChest());
		encounterForm.setCv(encounterFormDto.getCv());
		encounterForm.setDiagnosis(encounterFormDto.getDiagnosis());
		encounterForm.setExtr(encounterFormDto.getExtr());
		encounterForm.setFollowUp(encounterFormDto.getFollowUp());
		encounterForm.setHeent(encounterFormDto.getHeent());
		encounterForm.setHistoryOfIllness(encounterFormDto.getHistoryOfIllness());
		encounterForm.setHr(encounterFormDto.getHr());
		encounterForm.setMedicalHistory(encounterFormDto.getMedicalHistory());
		encounterForm.setMedications(encounterFormDto.getMedications());
		encounterForm.setNeuro(encounterFormDto.getNeuro());
		encounterForm.setO2(encounterFormDto.getO2());
		encounterForm.setOther(encounterFormDto.getOther());
		encounterForm.setPain(encounterFormDto.getPain());
		encounterForm.setProcedures(encounterFormDto.getProcedures());
		encounterForm.setRequest(request);
		encounterForm.setRr(encounterFormDto.getRr());
		encounterForm.setSkin(encounterFormDto.getSkin());
		encounterForm.setTemp(encounterFormDto.getTemp());
		encounterForm.setTreatmentPlan(encounterFormDto.getTreatmentPlan());
		encounterForm.setMedicationsDespensed(encounterFormDto.getMedicationsDespensed());

		if(encounterForms.size()>0) {
			encounterFormDao.updateEncounterForm(encounterForm);
			return "updated succesfully";
		}else {			
			encounterFormDao.saveEncounterForm(encounterForm);
			return "saved succesfully";
		}
	}
	
	public EncounterFormUserDetailsDto getEncounterFormUserDetailsDto(int reqId) {
		
		Request request = requestDao.getRequestOb(reqId);
		EncounterFormUserDetailsDto encounterFormUserDetailsDto = new EncounterFormUserDetailsDto();
		RequestClient requestClient = request.getRequestClient();
		encounterFormUserDetailsDto.setDob(requestClient.getDateObject());
		encounterFormUserDetailsDto.setEmail(requestClient.getEmail());
		encounterFormUserDetailsDto.setFirstName(requestClient.getFirstName());
		encounterFormUserDetailsDto.setLastName(requestClient.getLastName());
		encounterFormUserDetailsDto.setPhoneNumber(requestClient.getPhoneNumber());
		encounterFormUserDetailsDto.setDos(request.getDateOfServiceObject());
		encounterFormUserDetailsDto.setLocation(requestClient.getFullAddress());
		return encounterFormUserDetailsDto;
	}
	
	public EncounterFormDto getEncounterFormDtoOb(int reqId) {
		List<EncounterForm> encounterForms = encounterFormDao.getEncounterFormByReqID(reqId);
		EncounterForm encounterForm;
		EncounterFormDto encounterFormDto = new EncounterFormDto();
		if(encounterForms.size()>0) {
			encounterForm = encounterForms.get(0);
			
			encounterFormDto.setAbd(encounterForm.getAbd());
			encounterFormDto.setAllergies(encounterForm.getAllergies());
			encounterFormDto.setBloodPresureneg(encounterForm.getBloodPresureneg());
			encounterFormDto.setBloodPresurePlus(encounterForm.getBloodPresurePlus());
			encounterFormDto.setChest(encounterForm.getChest());
			encounterFormDto.setCv(encounterForm.getCv());
			encounterFormDto.setDiagnosis(encounterForm.getDiagnosis());
			encounterFormDto.setExtr(encounterForm.getExtr());
			encounterFormDto.setFollowUp(encounterForm.getFollowUp());
			encounterFormDto.setHeent(encounterForm.getHeent());
			encounterFormDto.setHistoryOfIllness(encounterForm.getHistoryOfIllness());
			encounterFormDto.setHr(encounterForm.getHr());
			encounterFormDto.setMedicalHistory(encounterForm.getMedicalHistory());
			encounterFormDto.setMedications(encounterForm.getMedications());
			encounterFormDto.setNeuro(encounterForm.getNeuro());
			encounterFormDto.setO2(encounterForm.getO2());
			encounterFormDto.setOther(encounterForm.getOther());
			encounterFormDto.setPain(encounterForm.getPain());
			encounterFormDto.setProcedures(encounterForm.getProcedures());
			encounterFormDto.setRr(encounterForm.getRr());
			encounterFormDto.setSkin(encounterForm.getSkin());
			encounterFormDto.setTemp(encounterForm.getTemp());
			encounterFormDto.setTreatmentPlan(encounterForm.getTreatmentPlan());
			encounterFormDto.setMedicationsDespensed(encounterForm.getMedicationsDespensed());
			
			return encounterFormDto;
			
		}else {
			return encounterFormDto;
		}
	}
	
	public ClearCaseDto getClearCasePtDetails(Request request) {
		ClearCaseDto clearCaseDto = new ClearCaseDto();
		clearCaseDto.setDob(request.getRequestClient().getDateObject());
		clearCaseDto.setEmail(request.getRequestClient().getEmail());
		clearCaseDto.setFirstName(request.getRequestClient().getFirstName());
		clearCaseDto.setLastName(request.getRequestClient().getLastName());
		clearCaseDto.setPhoneNumber(request.getRequestClient().getPhoneNumber());
		
		return clearCaseDto;
	}
	
	public String closeRequestedCase(int reqId, HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(reqId);
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();
		
		request.setStatus(9);
		request.setModifieDate(date);
		
		requestStatusLog.setAdmin(admin);
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes("Admin " + admin.getFirstName()+" "+admin.getLastName() + " closed this case on " + date);
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(9);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		
		return "status changed!";
	}
	
	public String editCloseCaseDetails(CloseCaseEditDataDto closeCaseEditDataDto) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		String dateInString = closeCaseEditDataDto.getDob();
		Date dob = formatter.parse(dateInString);
		
		Request request = requestDao.getRequestOb(closeCaseEditDataDto.getReqId());
		RequestClient requestClient = request.getRequestClient();
		User user = request.getUser();
		AspNetUsers aspNetUsers = user.getAspNetUsers();
		
		request.setFirstName(closeCaseEditDataDto.getfName());
		request.setLastName(closeCaseEditDataDto.getlName());
		request.setPhoneNumber(closeCaseEditDataDto.getpNumber());
		
	}
	

}
