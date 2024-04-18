package hallodoc.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.http.*;
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
import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.UpdateCaseDto;
import hallodoc.dto.UserProfileDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.DocType;
import hallodoc.helper.Constants;
import hallodoc.helper.ExcelSheetHelper;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.mapper.ViewNotesMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.BlockRequests;
import hallodoc.model.CaseTag;
import hallodoc.model.EmailToken;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestNotes;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.BlockRequestsDao;
import hallodoc.repository.CaseTagDao;
import hallodoc.repository.EmailTokenDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestNotesDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestWiseFileDao;

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
				String status = resendCreatePasswordMail(user.getUser(),request);
				return -2;
			} else {
				BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
				int role = user.getUser().getAspNetRoles().getId();
				boolean verified = Password.check(password, passwordHash).with(bcrypt);

				if (verified) {
					
					List<Region> regionList = regionDao.getAllRegions();
					
					System.out.println("verified" + role);
					HttpSession session = request.getSession();
					session.setAttribute("aspUser",user);
					session.setAttribute("regionList", regionList);
					return role;
				} else {
					System.out.println("password not match");
					return -3;
				}

			}

		}
	}
	
	
	public String uploadRequestDocument(CommonsMultipartFile document, String name, Request request, HttpSession session, Date date) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");

		String formattedDate = sdf.format(date);
		
		RequestWiseFile requestWiseFile = new RequestWiseFile();

		// Getting details from file obj

		String fileName = document.getOriginalFilename();
		byte[] data = document.getBytes();
		String fileExtension = document.getOriginalFilename().substring(document.getOriginalFilename().lastIndexOf('.') + 1);
		String storedFileName = "patient" + formattedDate +"-"+ fileName ;
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
		
		requestWiseFileDao.addNewRequestWiseFile(requestWiseFile);
		
		return "Uploaded Succesfully";
	}
	
	public Request getRequestObject(int id) {
		Request request =  requestDao.getRequestOb(id);
		return request;
	}
	
	public List<NewRequestDataDto> getFilteredRequest(RequestFiltersDto requestFiltersDto){
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
		String[] months = {"January","February","March","April","June","July","August","September","October","November","December"};
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
		
		if(request.getRequestType().getRequestTypeId() == 2) {
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
	
	public List<CaseTag> getAllCancellationReasons(){
		List<CaseTag> caseTags = caseTagDao.getCancellationReasons();
		return caseTags;
	}
	
	public boolean cancelRequestedCase(CancelCaseDetailsDto cancelCaseDetailsDto , HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(cancelCaseDetailsDto.getRequestId());
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
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
	
	public boolean blockRequestedCase(BlockCaseDto blockCaseDto,HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(blockCaseDto.getRequestId());
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
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
	
	public List<ViewNotesDto> getRequestSpecificLogs(int reqId){
		List<RequestStatusLog> requestStatusLogs = requestStatusLogDao.getAllRequestSpecificLogs(reqId);
		List<RequestNotes> requestNotes = requestNotesDao.getRequestSpecificNote(reqId);
		List<ViewNotesDto> viewNotesDtos = new ArrayList<ViewNotesDto>();
		
		if(requestStatusLogs.size() == 0 && requestNotes.size() == 0) {
			return viewNotesDtos;
		}
		if(requestStatusLogs.size() == 0 && requestNotes.size()>0) {
			ViewNotesDto viewNotesDto = new ViewNotesDto();
			viewNotesDto.setAdminNotes(requestNotes.get(0).getAdminNotes());
			viewNotesDto.setProviderNotes(requestNotes.get(0).getPhysicanNotes());
			viewNotesDtos.add(viewNotesDto);
			return viewNotesDtos;
		}
		if(requestStatusLogs.size()>0) {
			for (RequestStatusLog requestStatusLog : requestStatusLogs) {
				ViewNotesDto viewNotesDto = ViewNotesMapper.mapViewNotesData(requestStatusLog);
				if(requestNotes.size()>0) {
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
		if(requestNotes.size()>0) {
			RequestNotes requestNote = requestNotes.get(0);
			requestNote.setAdminNotes(adminNote);
			requestNote.setModifiedBy(user);
			
			requestNotesDao.updateRequestNotes(requestNote);
		}
		else {
			RequestNotes requestNote = new RequestNotes();
			Request request = requestDao.getRequestOb(reqId);
			requestNote.setAdminNotes(adminNote);
			requestNote.setCreatedBy(user);
			requestNote.setModifiedBy(user);
			requestNote.setRequest(request);
			
			requestNotesDao.saveRequestNotes(requestNote);
		}
	}
	
	
	public ResponseEntity<Resource> exportDataToExcelSheet(List<NewRequestDataDto> list,String status) throws IOException {
		
		String fileName = status + "-exportData.xlsx";
		
		ByteArrayInputStream byteArrayInputStream =  ExcelSheetHelper.dataTOExcel(list, status);
		InputStreamResource file = new InputStreamResource(byteArrayInputStream);
		
		ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
		.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		
		return body;
	}
	
	
	public void clearRequestedCase(int reqId, HttpServletRequest httpServletRequest) {
		
		Request request = requestDao.getRequestOb(reqId);
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		Date date = new Date();
		String note = "Admin " +  admin.getFirstName() + " cancelled the case on " + date;

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
		Request request = requestDao.getRequestOb(reqId);
		RequestClient requestClient = request.getRequestClient();
		
		sendAgreementDto.setEmail(requestClient.getEmail());
		sendAgreementDto.setReqId(reqId);
		String mobileNumber = requestClient.getPhoneNumber();
		String updatedMobileNumber = mobileNumber.replace("+91","");
		sendAgreementDto.setPhoneNumber(updatedMobileNumber.trim());
		
		return sendAgreementDto;
		
	}

}
