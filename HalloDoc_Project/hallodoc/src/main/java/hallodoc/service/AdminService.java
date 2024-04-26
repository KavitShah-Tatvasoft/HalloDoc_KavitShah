package hallodoc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.AdminAddressDto;
import hallodoc.dto.AdminContactDto;
import hallodoc.dto.AdminRegions;
import hallodoc.dto.AssignCaseDto;
import hallodoc.dto.NewProviderAccountDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailLog;
import hallodoc.model.Physician;
import hallodoc.model.PhysicianNotification;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.User;
import hallodoc.repository.AspNetRolesDao;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.LogsDao;
import hallodoc.repository.PhysicianDao;
import hallodoc.repository.PhysicianNotificationDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.email.*;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.MessageTypeEnum;
import hallodoc.helper.Constants;



@Service
public class AdminService {

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogsDao logsDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private RequestStatusLogDao requestStatusLogDao;

	@Autowired
	private PhysicianDao physicianDao;

	@Autowired
	private AspNetUserDao aspNetUserDao;

	@Autowired
	private AspNetRolesDao aspNetRolesDao;

	@Autowired
	private PhysicianNotificationDao physicianNotificationDao;
	

	public List<NewRequestDataDto> getStatusCorrespondingRequests(String status) {

		List<Integer> statusList = new ArrayList<Integer>();

		if (status.equalsIgnoreCase("new")) {
			statusList.add(hallodoc.enumerations.RequestStatus.UNASSIGNED.getRequestId());
		}

		if (status.equalsIgnoreCase("pending")) {
			statusList.add(hallodoc.enumerations.RequestStatus.ACCEPTED.getRequestId());
		}

		if (status.equalsIgnoreCase("active")) {
			statusList.add(hallodoc.enumerations.RequestStatus.MD_EN_ROUTE.getRequestId());
			statusList.add(hallodoc.enumerations.RequestStatus.MD_ON_SITE.getRequestId());
		}

		if (status.equalsIgnoreCase("conclude")) {
			statusList.add(hallodoc.enumerations.RequestStatus.CONCLUDE.getRequestId());
		}

		if (status.equalsIgnoreCase("to-close")) {
			statusList.add(hallodoc.enumerations.RequestStatus.CANCELLED.getRequestId());
			statusList.add(hallodoc.enumerations.RequestStatus.CANCELLED_BY_PATIENT.getRequestId());
			statusList.add(hallodoc.enumerations.RequestStatus.CLOSED.getRequestId());
		}

		if (status.equalsIgnoreCase("unpaid")) {
			statusList.add(hallodoc.enumerations.RequestStatus.UNPAID.getRequestId());
		}

		List<Request> requestsList = requestDao.getRequstStatusData(statusList);

		List<NewRequestDataDto> newRequestDataDtoList = new ArrayList<NewRequestDataDto>();
		NewRequestDataDto newRequestDataDto;
		for (Request request : requestsList) {
			newRequestDataDto = RequestNewDataDtoMapper.mapDataNeWDataDto(request);
			newRequestDataDtoList.add(newRequestDataDto);
		}

		return newRequestDataDtoList;
	}

	public List<Integer> getStatusWiseRequestCount() {
		List<StatusWiseCountDto> list = requestDao.getStatusWiseRequestCount();

		int newCount = 0;
		int pendingCount = 0;
		int activeCount = 0;
		int concludeCount = 0;
		int toCloseCount = 0;
		int unpaidCount = 0;

		for (StatusWiseCountDto statusWiseCountDto : list) {
			if (statusWiseCountDto.getStatus() == 1) {
				newCount += statusWiseCountDto.getCount();
			}

			if (statusWiseCountDto.getStatus() == 2) {
				pendingCount += statusWiseCountDto.getCount();
			}

			if (statusWiseCountDto.getStatus() == 4 || statusWiseCountDto.getStatus() == 5) {
				activeCount += statusWiseCountDto.getCount();
			}

			if (statusWiseCountDto.getStatus() == 6) {
				concludeCount += statusWiseCountDto.getCount();
			}

			if (statusWiseCountDto.getStatus() == 3 || statusWiseCountDto.getStatus() == 7
					|| statusWiseCountDto.getStatus() == 8) {
				toCloseCount += statusWiseCountDto.getCount();
			}

			if (statusWiseCountDto.getStatus() == 9) {
				unpaidCount += statusWiseCountDto.getCount();
			}

		}

		List<Integer> countList = new ArrayList<Integer>();
		countList.add(newCount);
		countList.add(pendingCount);
		countList.add(activeCount);
		countList.add(concludeCount);
		countList.add(toCloseCount);
		countList.add(unpaidCount);

		return countList;
	}

	public boolean sendRequestLink(HttpServletRequest request, SendLinkDto sendLinkDto) {

		AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		LocalDateTime date = LocalDateTime.now();
		EmailLog emailLog = new EmailLog();
		emailLog.setSubjectName(sendLinkDto.getEmailSubject());
		emailLog.setEmailId(sendLinkDto.getEmail());
		emailLog.setCreatedDate(date);
		emailLog.setSentDate(date);
		emailLog.setAction(1);
		emailLog.setRecipientName(sendLinkDto.getFirstName() + " " + sendLinkDto.getLastName());
		emailLog.setAdminId(admin.getAdminId());
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				emailService.sendRequestLinkByEmail(request, sendLinkDto);
				isSent = true;
				emailLog.setSentTries(i);
				emailLog.setEmailSent(isSent);
				logsDao.addEmailLogEntry(emailLog);

			} catch (Exception e) {
				if (i == 3) {
					emailLog.setSentTries(3);
					emailLog.setEmailSent(false);
					logsDao.addEmailLogEntry(emailLog);
				}
			}
		}

		return isSent;
	}

	public List<PhysicianAssignCaseDto> getPhysicianByRegion(int regionId) {
//		List<Region> region =  regionDao.getRegionById(regionId);
//		List<Physician> physicians = region.get(0).getPhysician();
//		List<PhysicianAssignCaseDto> physicianAssignCaseDtos = new ArrayList<PhysicianAssignCaseDto>();
//		
//		for (Physician physician : physicians) {
//			PhysicianAssignCaseDto physicianAssignCaseDto = new PhysicianAssignCaseDto();
//			physicianAssignCaseDto.setFirstName(physician.getFirstName());
//			physicianAssignCaseDto.setLastName(physician.getLastName());
//			physicianAssignCaseDto.setPhysicianId(physician.getPhysicianId());
//			physicianAssignCaseDtos.add(physicianAssignCaseDto);
//		}

		List<PhysicianAssignCaseDto> physicianAssignCaseDtos = physicianDao.getPhysicianByRegion(regionId);

		return physicianAssignCaseDtos;
	}

	public void assignPhysicianToRequest(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(assignCaseDto.getReqId());
		Physician physician = physicianDao.getPhysicianById(assignCaseDto.getPhysicianId());
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();

		String pattern = "MMMM dd, yyyy";
		String timePattern = "KK:mm:ss aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
		String fDate = simpleDateFormat.format(date);
		String fTime = simpleTimeFormat.format(date);

		request.setPhysician(physician);
		request.setModifieDate(date);
		String transferNote = "Admin transferred case to Dr. " + physician.getFirstName() + " on " + fDate + " at "
				+ fTime + " : " + assignCaseDto.getDescription();

		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(hallodoc.enumerations.RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setAdmin(admin);
		requestStatusLog.setPhysician(physician);
		requestStatusLog.setTransToPhysician(physician);
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes(transferNote);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
	}

	public void transferRequestedCase(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {

		Request request = requestDao.getRequestOb(assignCaseDto.getReqId());
		Physician physician = physicianDao.getPhysicianById(assignCaseDto.getPhysicianId());
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();

		String pattern = "MMMM dd, yyyy";
		String timePattern = "KK:mm:ss aa";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
		String fDate = simpleDateFormat.format(date);
		String fTime = simpleTimeFormat.format(date);

		String transferNote = "Admin transferred case to Dr. " + physician.getFirstName() + " on " + fDate + " at "
				+ fTime + " : " + assignCaseDto.getDescription();

		request.setStatus(1);
		request.setPhysician(physician);
		request.setModifieDate(date);

		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(hallodoc.enumerations.RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setAdmin(admin);
		requestStatusLog.setPhysician(physician);
		requestStatusLog.setTransToPhysician(physician);
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes(transferNote);

		requestDao.updateRequest(request);
		requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

	}

	public List<AdminRegions> getAdminRegions(Admin admin) {

		List<Region> regions = admin.getRegions();
		List<Region> allRegions = regionDao.getAllRegions();
		List<AdminRegions> adminRegions = new ArrayList<AdminRegions>();

		for (Region allRegionsList : allRegions) {
			AdminRegions adminRegionsOb = new AdminRegions();
			adminRegionsOb.setRegionId(allRegionsList.getRegionId());
			adminRegionsOb.setRegionName(allRegionsList.getName());
			adminRegionsOb.setIsSelected(0);
			adminRegions.add(adminRegionsOb);
		}

		for (AdminRegions adRegion : adminRegions) {
			for (Region selectedRegion : regions) {
				if (adRegion.getRegionId() == selectedRegion.getRegionId()) {
					adRegion.setIsSelected(1);
				}

			}
		}

		return adminRegions;
	}

	public String updateAspUserPassword(AspNetUsers aspNetUsers, String password, HttpServletRequest request) {

		HttpSession session = request.getSession();
		AspNetUsers asp = aspNetUsers;
		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(password).with(bcrypt);
		asp.setPassword_hash(hash.getResult());
		session.setAttribute("aspUser", asp);
		this.aspNetUserDao.updateAspNetUser(aspNetUsers);

		return "updated";
	}

	public String updateAspUserAddress(HttpServletRequest httpServletRequest, AdminAddressDto adminAddressDto) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		User user = aspNetUsers.getUser();
		Admin admin = aspNetUsers.getAdmin();
		Region region = regionDao.getRegionById(adminAddressDto.getRegionId()).get(0);

		admin.setAddressOne(adminAddressDto.getAddress1());
		admin.setAddressTwo(adminAddressDto.getAddress2());
		admin.setCity(adminAddressDto.getCity());
		admin.setZip(adminAddressDto.getZipcode());
		admin.setRegionId(adminAddressDto.getRegionId());
		admin.setAltPhone(adminAddressDto.getPhone());

		user.setStreet(adminAddressDto.getAddress1() + ", " + adminAddressDto.getAddress2());
		user.setCity(adminAddressDto.getCity());
		user.setState(region.getName());
		user.setRegion(region);
		user.setZipcode(adminAddressDto.getZipcode());

		aspNetUsers.setAdmin(admin);
		aspNetUsers.setUser(user);

		aspNetUserDao.updateAspNetUser(aspNetUsers);

		httpServletRequest.getSession().setAttribute("aspUser", aspNetUsers);
		return "updated";
	}

	public String updateAdminContactDetails(AdminContactDto adminContactDto, HttpServletRequest httpServletRequest) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		User user = aspNetUsers.getUser();
		Admin admin = aspNetUsers.getAdmin();

		aspNetUsers.setPhone_number(adminContactDto.getPhoneNumber());

		user.setMobile(adminContactDto.getPhoneNumber());
		user.setFirstName(adminContactDto.getFirstName());
		user.setLastName(adminContactDto.getLastName());

		admin.setFirstName(adminContactDto.getFirstName());
		admin.setLastName(adminContactDto.getLastName());

		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("aspUser", aspNetUsers);
		regionDao.deleteAdminRegionEntry(admin.getAdminId());

		String[] regionIds = adminContactDto.getCheckedId().split(",");
		List<Region> adminRegion = new ArrayList<Region>();
		List<Region> regions = regionDao.getAllRegions();

		for (Region region : regions) {
			for (String regId : regionIds) {
				if (region.getRegionId() == Integer.parseInt(regId)) {
					adminRegion.add(region);
				}
			}
		}

		admin.setRegions(adminRegion);
		aspNetUsers.setAdmin(admin);
		aspNetUsers.setUser(user);
		aspNetUserDao.updateAspNetUser(aspNetUsers);

		return "updated";
	}

	public boolean isFileEmpty(CommonsMultipartFile file) {
		if (file == null || file.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private String uploadFile(CommonsMultipartFile file, int physicianId, String filename, String path, boolean isNameChange) {
		try {
			String fileExtension = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			String fullPath = path + File.separator + physicianId + File.separator + filename;
			if(isNameChange ) {
				fullPath +=  "." + fileExtension;
			}
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(fullPath);
			fos.write(data);
			fos.close();
			System.out.println("file uploaded");
			return "File Uploaded";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Uploading Error");
			return "File Upload Failed";
		}
	}

	public String createNewProvider(NewProviderAccountDto newProviderAccountDto,
			HttpServletRequest httpServletRequest) {
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		AspNetUsers aspNetUsers = new AspNetUsers();
		Physician physician = new Physician();
		User user = new User();
		PhysicianNotification physicianNotification = new PhysicianNotification();
		List<Region> regions = this.regionDao.getAllRegions();
		Date date = new Date();
		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(newProviderAccountDto.getpPassword()).with(bcrypt);
		Region region = this.regionDao.getRegionById(newProviderAccountDto.getpState()).get(0);
		AspNetRoles aspNetRoles = this.aspNetRolesDao.getRoleObject(AspNetRolesEnum.PROVIDER.getAspNetRolesName());
		HttpSession session = httpServletRequest.getSession();

		String[] tokenId = newProviderAccountDto.getpRegions().split(",");
		List<Region> physicianRegion = new ArrayList<Region>();

		for (Region allRegion : regions) {
			for (String stringRegion : tokenId) {
				if (allRegion.getRegionId() == Integer.parseInt(stringRegion)) {
					physicianRegion.add(allRegion);
				}
			}
		}

		aspNetUsers.setCreated_date(date);
		aspNetUsers.setModified_date(date);
		aspNetUsers.setEmail(newProviderAccountDto.getpEmail());
		aspNetUsers.setPassword_hash(hash.getResult());
		aspNetUsers.setPhone_number(newProviderAccountDto.getpPhone());
		aspNetUsers.setUser_name(newProviderAccountDto.getpUsername());

		user.setAspNetUsers(aspNetUsers);
		user.setFirstName(newProviderAccountDto.getpFirstName());
		user.setLastName(newProviderAccountDto.getpLastName());
		user.setEmail(newProviderAccountDto.getpEmail());
		user.setMobile(newProviderAccountDto.getpPhone());
		user.setStreet(newProviderAccountDto.getpAddressOne() + " " + newProviderAccountDto.getpAddressTwo());
		user.setCity(newProviderAccountDto.getpCity());
		user.setState(region.getName());
		user.setRegion(region);
		user.setZipcode(newProviderAccountDto.getpZip());
		user.setCreatedBy(adminOb);
		user.setCreatedDate(date);
		user.setModifiedBy(adminOb);
		user.setModifiedDate(date);
		user.setStatus(newProviderAccountDto.getpStatus());
		user.setDeleted(false);
		user.setAspNetRoles(aspNetRoles);

		physician.setAspNetUsers(aspNetUsers);
		physician.setFirstName(newProviderAccountDto.getpFirstName());
		physician.setLastName(newProviderAccountDto.getpLastName());
		physician.setEmail(newProviderAccountDto.getpEmail());
		physician.setMobile(newProviderAccountDto.getpMPhone());
		physician.setMedicalLicense(newProviderAccountDto.getpLicense());
		physician.setAdminNotes(newProviderAccountDto.getpNotes());
		physician.setAddressOne(newProviderAccountDto.getpAddressOne());
		physician.setAddressTwo(newProviderAccountDto.getpAddressTwo());
		physician.setCity(newProviderAccountDto.getpCity());
		physician.setRegionId(region.getRegionId());
		physician.setZip(newProviderAccountDto.getpZip());
		physician.setAltPhone(newProviderAccountDto.getpMPhone());
		physician.setCreatedBy(adminOb);
		physician.setCreatedDate(date);
		physician.setModifiedBy(adminOb);
		physician.setModifiedDate(date);
		physician.setStatus(newProviderAccountDto.getpStatus());
		physician.setBusinessName(newProviderAccountDto.getpBusinessName());
		physician.setBusinessWebsite(newProviderAccountDto.getpBusinessWebsite());
		physician.setIsDeleted(false);
		physician.setRole(null);
		physician.setNpiNumber(newProviderAccountDto.getpNPI());
		physician.setSyncEmailAddress(newProviderAccountDto.getpSyncEmail());
		physician.setRegions(physicianRegion);

		CommonsMultipartFile photo = newProviderAccountDto.getpPhoto();
		CommonsMultipartFile agreementDoc = newProviderAccountDto.getpAgreement();
		CommonsMultipartFile backgroundDoc = newProviderAccountDto.getpBackgroundCheck();
		CommonsMultipartFile licenseDoc = newProviderAccountDto.getpLicenseDoc();
		CommonsMultipartFile signatureDoc = newProviderAccountDto.getpSignature();
		CommonsMultipartFile hipaaDoc = newProviderAccountDto.getpHipaa();
		CommonsMultipartFile ndsDoc = newProviderAccountDto.getpNda();

		String path = Constants.getProviderUplaodPath(session);

		if (isFileEmpty(photo)) {
			physician.setPhoto(photo.getOriginalFilename());
		}

		if (isFileEmpty(agreementDoc)) {
			physician.setAgreementDoc(true);
		} else {
			physician.setAgreementDoc(false);
		}

		if (isFileEmpty(backgroundDoc)) {
			physician.setBackgroundDoc(true);
		} else {
			physician.setBackgroundDoc(false);
		}

		if (isFileEmpty(ndsDoc)) {
			physician.setNonDisclosureDoc(true);
		} else {
			physician.setNonDisclosureDoc(false);
		}

		if (isFileEmpty(licenseDoc)) {
			physician.setIsLicenseDoc(true);
		} else {
			physician.setIsLicenseDoc(false);
		}

		if (isFileEmpty(signatureDoc)) {
			physician.setSignature(signatureDoc.getOriginalFilename());
		}

		if (isFileEmpty(hipaaDoc)) {
			physician.setIsHipaaDoc(true);
		} else {
			physician.setIsHipaaDoc(false);
		}

		aspNetUsers.setUser(user);
		aspNetUsers.setPhysician(physician);
		aspNetUserDao.createAspnetUser(aspNetUsers);

		Physician updatedPhysician = this.physicianDao.getPhysicianByEmail(newProviderAccountDto.getpEmail()).get(0);

		physicianNotification.setIsNotificationStopped(false);
		physicianNotification.setPhysicianId(updatedPhysician.getPhysicianId());
		physicianNotificationDao.savePhysicianNotificationOb(physicianNotification);

		int physicianId = updatedPhysician.getPhysicianId();
		
		String createDirectoryPath = path + File.separator + physicianId;
		File f1 = new File(createDirectoryPath);
		f1.mkdir();
		

		if (isFileEmpty(photo)) {
			uploadFile(photo, physicianId, photo.getOriginalFilename(), path,false);
		}

		if (isFileEmpty(agreementDoc)) {
			uploadFile(agreementDoc, physicianId, "Agreement_Document", path,true);
		}

		if (isFileEmpty(backgroundDoc)) {
			uploadFile(backgroundDoc, physicianId, "Background_Check_Document", path,true);
		}

		if (isFileEmpty(ndsDoc)) {
			uploadFile(ndsDoc, physicianId, "NDS_Document", path,true);
		}

		if (isFileEmpty(licenseDoc)) {
			uploadFile(licenseDoc, physicianId, "License_Document", path,true);
		}

		if (isFileEmpty(signatureDoc)) {
			uploadFile(signatureDoc, physicianId, signatureDoc.getOriginalFilename(), path,false);
		}

		if (isFileEmpty(hipaaDoc)) {
			uploadFile(hipaaDoc, physicianId, "HIPAA_Document", path,true);
		}
		LocalDateTime localDateTime = LocalDateTime.now();
		EmailLog emailLog = new EmailLog();
		String subject = "Credentials";
		emailLog.setSubjectName(subject);
		emailLog.setEmailId(newProviderAccountDto.getpEmail());
		emailLog.setAdminId(adminOb.getAdmin().getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.SEND_CREDENTIALS_PROVIDER.getMessageTypeId());
		emailLog.setRecipientName(newProviderAccountDto.getpFirstName() + " " + newProviderAccountDto.getpLastName());
		
		
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

		this.logsDao.addEmailLogEntry(emailLog);
		return "created";
	}
}
