package hallodoc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.event.MenuDragMouseEvent;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import hallodoc.dto.CreateRoleDataDto;
import hallodoc.dto.CreateShiftDto;
import hallodoc.dto.EditRoleDto;
import hallodoc.dto.EditShiftDetailsDto;
import hallodoc.dto.EditShiftDto;
import hallodoc.dto.EventsDto;
import hallodoc.dto.GetRolesDto;
import hallodoc.dto.MenusDto;
import hallodoc.dto.NewProviderAccountDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.NewStatePageDataDto;
import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.dto.PhysicianResources;
import hallodoc.dto.ProviderMailingDto;
import hallodoc.dto.ProviderMenuDto;
import hallodoc.dto.ProviderUpdatedInfoDto;
import hallodoc.dto.ReviewShiftDetailsDto;
import hallodoc.dto.ReviewShiftDto;
import hallodoc.dto.RolesDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.ShowProviderDetailsDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.dto.UserAccessDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailLog;
import hallodoc.model.Menu;
import hallodoc.model.Physician;
import hallodoc.model.PhysicianNotification;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.Role;
import hallodoc.model.Shift;
import hallodoc.model.ShiftDetails;
import hallodoc.model.SmsLog;
import hallodoc.model.User;
import hallodoc.repository.AdminDao;
import hallodoc.repository.AspNetRolesDao;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.LogsDao;
import hallodoc.repository.PhysicianDao;
import hallodoc.repository.PhysicianNotificationDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RoleAccessDao;
import hallodoc.repository.ShiftDao;
import hallodoc.sms.SmsService;
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

	@Autowired
	private SmsService smsService;

	@Autowired
	private RoleAccessDao roleAccessDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ShiftDao shiftDao;

	public NewStatePageDataDto getStatusCorrespondingRequests(String status, int pageNo) {

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

		NewStatePageDataDto requestsList = requestDao.getRequstStatusData(statusList, pageNo);

		return requestsList;
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
		Admin admin;
		Physician physician;
		EmailLog emailLog = new EmailLog();

		if (aspNetUsers.getAdmin() != null) {
			admin = aspNetUsers.getAdmin();
			emailLog.setAdminId(admin.getAdminId());
		} else {
			physician = aspNetUsers.getPhysician();
			emailLog.setPhysicianId(physician.getPhysicianId());
		}

		LocalDateTime date = LocalDateTime.now();
		emailLog.setSubjectName(sendLinkDto.getEmailSubject());
		emailLog.setEmailId(sendLinkDto.getEmail());
		emailLog.setCreatedDate(date);
		emailLog.setSentDate(date);
		emailLog.setAction(1);
		emailLog.setRecipientName(sendLinkDto.getFirstName() + " " + sendLinkDto.getLastName());

		emailLog.setRoleId(3);
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

	private String uploadFile(CommonsMultipartFile file, int physicianId, String filename, String path,
			boolean isNameChange) {
		try {
			String fileExtension = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			String fullPath = path + File.separator + physicianId + File.separator + filename;
			if (isNameChange) {
				fullPath += "." + fileExtension;
			}
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(fullPath);
			fos.write(data);
			fos.close();
			;
			return "File Uploaded";
		} catch (Exception e) {
			e.printStackTrace();
			;
			return "File Upload Failed";
		}
	}

	public String createNewProvider(NewProviderAccountDto newProviderAccountDto,
			HttpServletRequest httpServletRequest) {

		Role role = this.roleAccessDao.getRoleOb(newProviderAccountDto.getpRole());
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
		physician.setRole(role);
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
		physicianNotification.setPhysician(updatedPhysician);
		physicianNotificationDao.savePhysicianNotificationOb(physicianNotification);

		int physicianId = updatedPhysician.getPhysicianId();

		String createDirectoryPath = path + File.separator + physicianId;
		File f1 = new File(createDirectoryPath);
		f1.mkdir();

		if (isFileEmpty(photo)) {
			uploadFile(photo, physicianId, photo.getOriginalFilename(), path, false);
		}

		if (isFileEmpty(agreementDoc)) {
			uploadFile(agreementDoc, physicianId, "Agreement_Document", path, true);
		}

		if (isFileEmpty(backgroundDoc)) {
			uploadFile(backgroundDoc, physicianId, "Background_Check_Document", path, true);
		}

		if (isFileEmpty(ndsDoc)) {
			uploadFile(ndsDoc, physicianId, "NDS_Document", path, true);
		}

		if (isFileEmpty(licenseDoc)) {
			uploadFile(licenseDoc, physicianId, "License_Document", path, true);
		}

		if (isFileEmpty(signatureDoc)) {
			uploadFile(signatureDoc, physicianId, signatureDoc.getOriginalFilename(), path, false);
		}

		if (isFileEmpty(hipaaDoc)) {
			uploadFile(hipaaDoc, physicianId, "HIPAA_Document", path, true);
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
		emailLog.setPhysicianId(physicianId);
		emailLog.setRecipientName(newProviderAccountDto.getpFirstName() + " " + newProviderAccountDto.getpLastName());
		emailLog.setRoleId(2);
		String status = "";
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendProviderCredentials(subject, httpServletRequest, newProviderAccountDto);
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

	public List<Region> getRegionList() {
		return this.regionDao.getAllRegions();
	}

	public ShowProviderDetailsDto getProviderDetails(Integer phyId) {
		Physician physician = this.physicianDao.getPhysicianById(phyId);
		AspNetUsers aspNetUsers = physician.getAspNetUsers();
		ShowProviderDetailsDto showDetails = new ShowProviderDetailsDto();
		List<Integer> phyRegions = this.regionDao.getPhysicianRegion(phyId);
		List<Region> regions = this.regionDao.getAllRegions();
		List<Region> providerRegion = new ArrayList<Region>();

		for (Region region : regions) {
			for (Integer regionId : phyRegions) {
				if (regionId == region.getRegionId()) {
					providerRegion.add(region);
				}
			}
		}

		showDetails.setpUsername(aspNetUsers.getUser_name());
		showDetails.setpStatus(physician.getStatus());
		showDetails.setpRole(0);
		showDetails.setpFirstName(physician.getFirstName());
		showDetails.setpLastName(physician.getLastName());
		showDetails.setpEmail(aspNetUsers.getEmail());
		showDetails.setpPhone(physician.getMobile());
		showDetails.setpLicense(physician.getMedicalLicense());
		showDetails.setpNPI(physician.getNpiNumber());
		showDetails.setpSyncEmail(physician.getSyncEmailAddress());
		showDetails.setpRegions(providerRegion);
		showDetails.setpAddressOne(physician.getAddressOne());
		showDetails.setpAddressTwo(physician.getAddressTwo());
		showDetails.setpCity(physician.getCity());
		showDetails.setpState(physician.getRegionId());
		showDetails.setpZip(physician.getZip());
		showDetails.setpMPhone(physician.getAltPhone());
		showDetails.setpBusinessName(physician.getBusinessName());
		showDetails.setpBusinessWebsite(physician.getBusinessWebsite());
		showDetails.setPsignatureName(physician.getSignature());
		showDetails.setpNotes(physician.getAdminNotes());
		showDetails.setpAgreement(physician.getIsAgreementDoc());
		showDetails.setpBackgroundCheck(physician.getIsBackgroundDoc());
		showDetails.setpHipaa(physician.getIsHipaaDoc());
		showDetails.setpNda(physician.getIsNonDisclosureDoc());
		showDetails.setpLicenseDoc(physician.getIsLicenseDoc());
		showDetails.setpId(physician.getPhysicianId());
		return showDetails;
	}

	public String updateProviderRoleStatus(Integer id, Integer role, Integer status,
			HttpServletRequest httpServletRequest) {
		Role roleOb = this.roleAccessDao.getRoleOb(role);
		Physician physician = this.physicianDao.getPhysicianById(id);
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		physician.setRole(roleOb);
		physician.setStatus(status);
		physician.setModifiedDate(new Date());
		physician.setModifiedBy(adminOb);
		this.physicianDao.updatePhysician(physician);
		return "updated physician role and status";
	}

	public String updatePhysicianPassword(Integer id, String password, HttpServletRequest httpServletRequest) {
		Physician physician = this.physicianDao.getPhysicianById(id);
		AspNetUsers aspNetUsers = physician.getAspNetUsers();
		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(password).with(bcrypt);
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");

		aspNetUsers.setPassword_hash(hash.getResult());
		aspNetUsers.setModified_date(new Date());

		this.aspNetUserDao.updateAspNetUser(aspNetUsers);

		LocalDateTime localDateTime = LocalDateTime.now();
		EmailLog emailLog = new EmailLog();
		String subject = "Reset Credentials";
		emailLog.setSubjectName(subject);
		emailLog.setEmailId(physician.getEmail());
		emailLog.setAdminId(adminOb.getAdmin().getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.SEND_CREDENTIALS_PROVIDER.getMessageTypeId());
		emailLog.setPhysicianId(physician.getPhysicianId());
		emailLog.setRecipientName(physician.getFirstName() + " " + physician.getLastName());
		emailLog.setRoleId(2);
		String status = "";
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendProviderResetCredentials(subject, httpServletRequest, password, physician);
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

		return "updated password";
	}

	public String updateProviderMailingDetails(ProviderMailingDto providerMailingDto,
			HttpServletRequest httpServletRequest) {

		Physician physician = this.physicianDao.getPhysicianById(providerMailingDto.getId());
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Region region = this.regionDao.getRegionById(Integer.parseInt(providerMailingDto.getState())).get(0);
		Date date = new Date();
		AspNetUsers phyAspUser = physician.getAspNetUsers();
		User user = phyAspUser.getUser();

		physician.setAddressOne(providerMailingDto.getAddress1());
		physician.setAddressTwo(providerMailingDto.getAddress2());
		physician.setCity(providerMailingDto.getCity());
		physician.setRegionId(region.getRegionId());
		physician.setZip(providerMailingDto.getZip());
		physician.setAltPhone(providerMailingDto.getPhone());
		physician.setModifiedBy(adminOb);
		physician.setModifiedDate(date);

		user.setStreet(providerMailingDto.getAddress1() + ", " + providerMailingDto.getAddress2());
		user.setCity(providerMailingDto.getCity());
		user.setState(region.getName());
		user.setZipcode(providerMailingDto.getZip());
		user.setModifiedBy(adminOb);
		user.setModifiedDate(date);
		user.setRegion(region);

		phyAspUser.setUser(user);
		phyAspUser.setPhysician(physician);

		aspNetUserDao.updateAspNetUser(phyAspUser);

		return "Updated mailinf information";
	}

	public String updateProviderInfo(ProviderUpdatedInfoDto providerUpdatedInfoDto,
			HttpServletRequest httpServletRequest) {

		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Physician physician = this.physicianDao.getPhysicianById(providerUpdatedInfoDto.getId());
		this.regionDao.deleteProviderRegionEntry(physician.getPhysicianId());
		AspNetUsers phyAspUser = physician.getAspNetUsers();
		User phyUser = phyAspUser.getUser();
		Date date = new Date();
		String[] regionTokens = providerUpdatedInfoDto.getRegions().split(",");
		List<Region> phyRegions = new ArrayList<Region>();
		List<Region> allRegions = regionDao.getAllRegions();

		for (Region region : allRegions) {
			for (String strRegion : regionTokens) {
				if (region.getRegionId() == Integer.parseInt(strRegion)) {
					phyRegions.add(region);
				}
			}
		}

		phyAspUser.setPhone_number(providerUpdatedInfoDto.getPhone());
		phyAspUser.setModified_date(date);

		phyUser.setFirstName(providerUpdatedInfoDto.getfName());
		phyUser.setLastName(providerUpdatedInfoDto.getlName());
		phyUser.setMobile(providerUpdatedInfoDto.getPhone());
		phyUser.setModifiedBy(adminOb);
		phyUser.setModifiedDate(date);

		physician.setFirstName(providerUpdatedInfoDto.getfName());
		physician.setLastName(providerUpdatedInfoDto.getlName());
		physician.setMobile(providerUpdatedInfoDto.getPhone());
		physician.setMedicalLicense(providerUpdatedInfoDto.getLicense());
		physician.setNpiNumber(providerUpdatedInfoDto.getNpi());
		physician.setSyncEmailAddress(providerUpdatedInfoDto.getSyncMail());
		physician.setRegions(phyRegions);
		physician.setModifiedDate(date);
		physician.setModifiedBy(adminOb);

		phyAspUser.setUser(phyUser);
		phyAspUser.setPhysician(physician);

		this.aspNetUserDao.updateAspNetUser(phyAspUser);

		return "Updated Physician Information";

	}

	public String deleteProviderAccount(Integer phyId) {
		this.physicianDao.deleteProviderAccount(phyId);
		return "Soft Deleted";
	}

	public List<ProviderMenuDto> getProviderMenuDetails(Integer regionId) {
		List<Physician> physicians;
		List<ProviderMenuDto> providerMenuDtos = new ArrayList<ProviderMenuDto>();

		if (regionId == 0) {
			physicians = this.physicianDao.getAllActivePhysician();
		} else {
			List<Integer> phyIdList = this.physicianDao.getPhysicianObByRegion(regionId);
			physicians = this.physicianDao.getPhysicianByRegionList(phyIdList);
		}

		for (Physician phy : physicians) {
			String physicianStatus = phy.getStatus() == 1 ? "Active" : "Inactive";
			ProviderMenuDto providerMenuDto = new ProviderMenuDto();
			providerMenuDto.setOnCallStatus("Available");
			providerMenuDto.setPhysicianStatus(phy.getStatus());
			providerMenuDto.setProviderName(phy.getFirstName() + " " + phy.getLastName());
			providerMenuDto.setRole("Provider");
			providerMenuDto.setPhysicianStatus(phy.getStatus());
			providerMenuDto.setStatus(physicianStatus);
			providerMenuDto.setStopNotification(phy.getPhysicianNotification().getIsNotificationStopped());
			providerMenuDto.setPhysicianId(phy.getPhysicianId());
			providerMenuDtos.add(providerMenuDto);
		}

		return providerMenuDtos;

	}

	public String changeProviderNotification(Integer id) {

		return this.physicianNotificationDao.toggleNotification(id);

	}

	private String sendSMSPhysician(Physician physician, String message, HttpServletRequest httpServletRequest) {
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		LocalDateTime localDateTime = LocalDateTime.now();
		SmsLog smsLog = new SmsLog();
		smsLog.setMobileNumber(physician.getMobile());
		smsLog.setAdminId(adminOb.getAdmin().getAdminId());
		smsLog.setPhysicianId(physician.getPhysicianId());
		smsLog.setCreatedDate(localDateTime);
		smsLog.setSentDate(localDateTime);
		smsLog.setAction(MessageTypeEnum.COMMUNICTION_MESSAGE.getMessageTypeId());
		smsLog.setRecipientName(physician.getFirstName() + " " + physician.getLastName());
		smsLog.setRoleId(2);
		String status = "";
		boolean isSmsSent = false;
		int smsSentTries = 1;
		for (int i = smsSentTries; i <= 3; i++) {
			if (isSmsSent) {
				continue;
			}
			try {
				this.smsService.sendCommunicationSms("Communication SMS", message, physician);
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
					status = status + " failed to send send";

				}
			}
		}

		return status;

	}

	private String sendEmailPhysician(Physician physician, String message, HttpServletRequest httpServletRequest) {
		AspNetUsers adminOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		LocalDateTime localDateTime = LocalDateTime.now();
		EmailLog emailLog = new EmailLog();
		String subject = "Communication Email";
		emailLog.setSubjectName(subject);
		emailLog.setEmailId(physician.getEmail());
		emailLog.setAdminId(adminOb.getAdmin().getAdminId());
		emailLog.setCreatedDate(localDateTime);
		emailLog.setSentDate(localDateTime);
		emailLog.setAction(MessageTypeEnum.COMMUNICTION_MESSAGE.getMessageTypeId());
		emailLog.setPhysicianId(physician.getPhysicianId());
		emailLog.setRecipientName(physician.getFirstName() + " " + physician.getLastName());
		emailLog.setRoleId(2);
		String status = "";
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.communicationEmail(subject, message, physician);
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
		return status;
	}

	public String contactProvider(Integer id, String method, String message, HttpServletRequest httpServletRequest) {
		Physician physician = this.physicianDao.getPhysicianById(id);

		switch (method) {
		case "EMAIL":
			return sendEmailPhysician(physician, message, httpServletRequest);
		case "SMS":
			return sendSMSPhysician(physician, message, httpServletRequest);

		default:
			String status = "";
			status += sendEmailPhysician(physician, message, httpServletRequest);
			status = status + " " + sendSMSPhysician(physician, message, httpServletRequest);
			return status;
		}
	}

	public List<MenusDto> getRoleMenus(Integer roleId) {
		List<MenusDto> menuDtoList = new ArrayList<MenusDto>();
		List<Menu> menus = this.roleAccessDao.getRoleRelatedMenus(roleId);

		for (Menu menu : menus) {
			MenusDto menusDto = new MenusDto();
			menusDto.setAccountType(menu.getAccountType());
			menusDto.setMenuId(menu.getMenuId());
			menusDto.setName(menu.getName());
			menuDtoList.add(menusDto);
		}

		return menuDtoList;
	}

	public void createNewRoleAccess(CreateRoleDataDto createRoleDataDto, HttpServletRequest httpServletRequest) {
		AspNetUsers adminAsp = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		List<Integer> menuIds = new ArrayList<Integer>();
		String[] menus = createRoleDataDto.getSelectedMenu().split(",");

		for (String string : menus) {
			menuIds.add(Integer.parseInt(string));
		}

		List<Menu> menuList = this.roleAccessDao.getMenuObList(menuIds);

		Role role = new Role();
		role.setCreatedBy(adminAsp);
		role.setModifiedBy(adminAsp);
		role.setDeleted(false);
		role.setMenus(menuList);
		role.setName(createRoleDataDto.getRoleName());
		role.setAccountType(createRoleDataDto.getRoleType());

		this.roleAccessDao.saveRole(role);
	}

	public List<RolesDto> getRolesDataForAccountAccess() {
		List<RolesDto> rolesDtos = new ArrayList<RolesDto>();
		List<Role> roles = this.roleAccessDao.getAllActiveRoles();

		for (Role role : roles) {
			RolesDto rolesDto = new RolesDto();
			rolesDto.setAccountType(role.getAccountType());
			rolesDto.setRoleId(role.getRoleId());
			rolesDto.setRoleName(role.getName());
			rolesDtos.add(rolesDto);
		}

		return rolesDtos;
	}

	@Transactional
	public EditRoleDto getEditRolesDetails(Integer roleId) {

		EditRoleDto editRoleDto = new EditRoleDto();

		Role role = this.roleAccessDao.getRoleOb(roleId);
		List<MenusDto> listMenu = new ArrayList<MenusDto>();
		List<MenusDto> roleTypeAllMenus = new ArrayList<MenusDto>();

		List<Menu> menus = role.getMenus();
		List<Menu> roleTypeMenus = this.roleAccessDao.getAllMenuForRoleType(role.getAccountType());

		for (Menu menu : menus) {
			MenusDto menusDto = new MenusDto();
			menusDto.setAccountType(menu.getAccountType());
			menusDto.setMenuId(menu.getMenuId());
			menusDto.setName(menu.getName());
			listMenu.add(menusDto);
		}

		for (Menu menu : roleTypeMenus) {
			MenusDto menusDto = new MenusDto();
			menusDto.setAccountType(menu.getAccountType());
			menusDto.setMenuId(menu.getMenuId());
			menusDto.setName(menu.getName());
			roleTypeAllMenus.add(menusDto);
		}

		editRoleDto.setRoleName(role.getName());
		editRoleDto.setRoleType(role.getAccountType());
		editRoleDto.setAllRoleTypeMenuList(roleTypeAllMenus);
		editRoleDto.setMenuList(listMenu);

		return editRoleDto;

	}

	public void updateRoleDetails(String menusString, Integer roleId) {

		Role role = this.roleAccessDao.getRoleOb(roleId);

		List<Integer> menuIds = new ArrayList<Integer>();
		String[] menus = menusString.split(",");

		for (String string : menus) {
			menuIds.add(Integer.parseInt(string));
		}

		this.roleAccessDao.deleteAllRoleMenuById(roleId);

		List<Menu> menuList = this.roleAccessDao.getMenuObList(menuIds);
		role.setMenus(menuList);
		this.roleAccessDao.updateRole(role);
	}

	public void deleteRole(Integer roleId) {
		this.roleAccessDao.deleteRoleById(roleId);
	}

	private List<UserAccessDto> getPhysicianList(List<Physician> phyList) {
		List<UserAccessDto> userAccessDtos = new ArrayList<UserAccessDto>();
		for (Physician physician : phyList) {
			UserAccessDto userAccessDto = new UserAccessDto();
			userAccessDto.setAccountType("Provider");
			userAccessDto.setName(physician.getFirstName() + " " + physician.getLastName());

			List<Request> physicianRequests = this.requestDao.getPhysicianRequests(physician.getPhysicianId());

			userAccessDto.setOpenRequests(physicianRequests.size());
			String status = physician.getStatus() == 1 ? "Active" : "Inactive";
			userAccessDto.setStatus(status);
			userAccessDto.setUserId(physician.getPhysicianId());
			userAccessDto.setPhoneNumber(physician.getMobile());
			userAccessDtos.add(userAccessDto);

		}
		return userAccessDtos;

	}

	private List<UserAccessDto> getAdminList(List<Admin> adminList, Integer noRequest) {

		List<UserAccessDto> userAccessDtos = new ArrayList<UserAccessDto>();
		for (Admin admin : adminList) {
			UserAccessDto userAccessDto = new UserAccessDto();
			userAccessDto.setAccountType("Admin");
			userAccessDto.setName(admin.getFirstName() + " " + admin.getLastName());
			userAccessDto.setOpenRequests(noRequest);
			userAccessDto.setStatus("Active");
			userAccessDto.setUserId(admin.getAdminId());
			userAccessDto.setPhoneNumber(admin.getAltPhone());
			userAccessDtos.add(userAccessDto);

		}

		return userAccessDtos;

	}

	@Transactional
	public List<UserAccessDto> getUserAccessData(Integer typeId) {

		Integer noRequest = this.requestDao.getOpenReqeustCount();
		List<Admin> adminList = this.adminDao.getAdminData();
		List<Physician> phyList = this.physicianDao.getAllActivePhysician();

		if (typeId == 0) {
			List<UserAccessDto> physicianLists = getPhysicianList(phyList);
			List<UserAccessDto> adminLists = getAdminList(adminList, noRequest);
			List<UserAccessDto> commonList = Stream.concat(physicianLists.stream(), adminLists.stream()).toList();
			return commonList;
		} else if (typeId == 1) {
			return getAdminList(adminList, noRequest);
		} else {
			return getPhysicianList(phyList);
		}

	}

	public List<GetRolesDto> getPhysicianRoles() {
		List<Role> rolesList = this.roleAccessDao.getPhysicianRoles();
		List<GetRolesDto> list = new ArrayList<GetRolesDto>();
		for (Role role : rolesList) {
			GetRolesDto getRolesDto = new GetRolesDto();
			getRolesDto.setRoleId(role.getRoleId());
			getRolesDto.setRoleName(role.getName());
			list.add(getRolesDto);
		}

		return list;
	}

	private ShiftDetails createNewShiftDetails(CreateShiftDto createShiftDto, AspNetUsers aspNetUsers,
			LocalDate shiftDate, LocalTime startTime, LocalTime endTime, Shift shift) {
		ShiftDetails shiftDetails = new ShiftDetails();
		shiftDetails.setDeleted(false);
		shiftDetails.setEndTime(endTime);
		shiftDetails.setModifiedBy(aspNetUsers);
		shiftDetails.setRegionId(createShiftDto.getRegion());
		shiftDetails.setShiftDate(shiftDate);
		shiftDetails.setShiftId(shift);
		shiftDetails.setStartTime(startTime);
		shiftDetails.setStatus(1);
		return shiftDetails;
	}

	private boolean physicianAvailableForShift(CreateShiftDto createShiftDto, Physician physician, LocalDate shiftDate,
			LocalTime startTime, LocalTime endTime, String daysString) {

		List<LocalDate> shiftDates = new ArrayList<LocalDate>();
		int shiftDateInt = shiftDate.getDayOfWeek().getValue();
		if (createShiftDto.getIsRepeated().equals("true")) {
			for (int i = 0; i < daysString.length(); i++) {
				if (daysString.charAt(i) == '1') {
					int diff;
					if (shiftDateInt > i) {
						diff = 7 - Math.abs(i - shiftDateInt) + 1;
					} else {
						diff = Math.abs(i - shiftDateInt) + 1;
					}

					LocalDate nextDate = shiftDate.plusDays(diff);
					shiftDates.add(nextDate);

					for (int j = 0; j < createShiftDto.getRepeatTimes() - 1; j++) {
						LocalDate futureShiftDate = nextDate.plusDays(7);
						shiftDates.add(futureShiftDate);
					}
				}
			}
		} else {
			shiftDates.add(shiftDate);
		}

		if (daysString.charAt(shiftDateInt - 1) == '0') {
			shiftDates.add(shiftDate);
		}

		List<ShiftDetails> physicianShifts = this.shiftDao.getPhysicianAvailbility(shiftDates, physician);
		boolean flag = true;
		for (ShiftDetails shiftDetails : physicianShifts) {
			if ((startTime.isAfter(shiftDetails.getStartTime()) && startTime.isBefore(shiftDetails.getEndTime()))
					|| (endTime.isAfter(shiftDetails.getStartTime()) && endTime.isBefore(shiftDetails.getEndTime()))
					|| (startTime.isBefore(shiftDetails.getStartTime())
							&& endTime.isAfter(shiftDetails.getEndTime()))) {
				flag = false;
				break;
			}
		}

		return flag;
	}

	public boolean createNewShift(CreateShiftDto createShiftDto, HttpServletRequest httpServletRequest) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate shiftDate = LocalDate.parse(createShiftDto.getShiftDate(), formatter);
		int shiftDateInt = shiftDate.getDayOfWeek().getValue();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
		LocalTime startTime = LocalTime.parse(createShiftDto.getStartTime(), timeFormatter);
		LocalTime endTime = LocalTime.parse(createShiftDto.getEndTime(), timeFormatter);

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Physician physician = this.physicianDao.getPhysicianById(createShiftDto.getPhysicianId());

		String[] selectedDays = createShiftDto.getSelectedDays().split(",");
		String daysString = selectedDays[3] + selectedDays[5] + selectedDays[1] + selectedDays[4] + selectedDays[6]
				+ selectedDays[2] + selectedDays[0];
		boolean physicainAvailable = physicianAvailableForShift(createShiftDto, physician, shiftDate, startTime,
				endTime, daysString);
		if (physicainAvailable) {

			Shift shift = new Shift();
			List<ShiftDetails> shiftDetailsList = new ArrayList<ShiftDetails>();
			shift.setCreatedBy(aspNetUsers);
			shift.setPhysicianId(physician);
			if (createShiftDto.getIsRepeated().equals("true")) {
				shift.setRepeat(true);
			} else {
				shift.setRepeat(false);
			}
			shift.setRepeatUpto(createShiftDto.getRepeatTimes());
			shift.setStartDate(shiftDate);
			shift.setWeekDays(createShiftDto.getSelectedDays());

			if (createShiftDto.getIsRepeated().equals("true")) {
				for (int i = 0; i < daysString.length(); i++) {
					if (daysString.charAt(i) == '1') {
						int diff;
						if (shiftDateInt > i) {
							diff = 7 - Math.abs(i - shiftDateInt) + 1;
						} else {
							diff = Math.abs(i - shiftDateInt) + 1;
						}

						LocalDate nextDate = shiftDate.plusDays(diff);
						ShiftDetails shiftDetails = createNewShiftDetails(createShiftDto, aspNetUsers, nextDate,
								startTime, endTime, shift);
						shiftDetailsList.add(shiftDetails);

						for (int j = 0; j < createShiftDto.getRepeatTimes() - 1; j++) {
							LocalDate futureShiftDate = nextDate.plusDays(7);
							ShiftDetails futureshiftDetails = createNewShiftDetails(createShiftDto, aspNetUsers,
									futureShiftDate, startTime, endTime, shift);
							shiftDetailsList.add(futureshiftDetails);
						}
					}
				}
			} else {

				ShiftDetails shiftDetails = createNewShiftDetails(createShiftDto, aspNetUsers, shiftDate, startTime,
						endTime, shift);
				shiftDetailsList.add(shiftDetails);
			}

			if (daysString.charAt(shiftDateInt - 1) == '0' && createShiftDto.getIsRepeated().equals("true")) {
				ShiftDetails shiftDetails = createNewShiftDetails(createShiftDto, aspNetUsers, shiftDate, startTime,
						endTime, shift);
				shiftDetailsList.add(shiftDetails);
			}

			shift.setShiftDetails(shiftDetailsList);
			this.shiftDao.addNewShift(shift);
			return true;
		} else {
			return false;
		}

	}

	public List<PhysicianResources> getAllPhysicianDetails(HttpServletRequest httpServletRequest, int regionId) {

		List<PhysicianResources> physicianResources = new ArrayList<PhysicianResources>();
		List<Physician> physicians;
		if (regionId == 0) {
			physicians = this.physicianDao.getAllActivePhysician();
		} else {
			List<Integer> regionList = this.physicianDao.getPhysicianObByRegion(regionId);
			physicians = this.physicianDao.getPhysicianByRegionList(regionList);
		}
		for (Physician physician : physicians) {
			String path = Constants.PROVIDER_DOC_PATH + File.separator + physician.getPhysicianId() + File.separator
					+ physician.getPhoto();
			PhysicianResources phyResources = new PhysicianResources();
			phyResources.setPhysicianId(physician.getPhysicianId());
			phyResources.setPhysicianName(physician.getFirstName() + " " + physician.getLastName());
			if (physician.getPhoto() != null) {
				phyResources.setPath(path);
			} else {

			}

			physicianResources.add(phyResources);
		}
		return physicianResources;
	}

	public List<EventsDto> getAllActiveEvents(int regionId) {
		List<EventsDto> eventsDtos = new ArrayList<EventsDto>();

		List<ShiftDetails> shiftDetailsList = this.shiftDao.getAllActiveShifts(regionId);

		for (ShiftDetails shiftDetails : shiftDetailsList) {
			EventsDto eventsDto = new EventsDto();
			eventsDto.setEndTime(shiftDetails.getEndTime().toString());
			eventsDto.setPhysicianId(shiftDetails.getShiftId().getPhysicianId().getPhysicianId());
			eventsDto.setRegionAbbr(regionDao.getRegionById(shiftDetails.getRegionId()).get(0).getAbbreviation());
			eventsDto.setShiftDate(shiftDetails.getShiftDate().toString());
			eventsDto.setShiftDetailId(shiftDetails.getShiftDetailId());
			eventsDto.setStartTime(shiftDetails.getStartTime().toString());
			eventsDto.setStatus(shiftDetails.getStatus());
			eventsDto.setRegionId(shiftDetails.getRegionId());
			eventsDtos.add(eventsDto);
		}

		return eventsDtos;
	}

	public EditShiftDto getEventDetails(int eventId) {

		ShiftDetails shiftDetails = this.shiftDao.getEventById(eventId);
		Region region = regionDao.getRegionById(shiftDetails.getRegionId()).get(0);
		EditShiftDto editShiftDto = new EditShiftDto();
		editShiftDto.setEndTime(shiftDetails.getEndTime().toString());
		editShiftDto.setPhysicianId(shiftDetails.getShiftId().getPhysicianId().getPhysicianId());
		editShiftDto.setPhysicianName("Dr." + shiftDetails.getShiftId().getPhysicianId().getFirstName() + " "
				+ shiftDetails.getShiftId().getPhysicianId().getLastName());
		editShiftDto.setRegionId(shiftDetails.getRegionId());
		editShiftDto.setRegionName(region.getName());
		editShiftDto.setShiftDate(shiftDetails.getShiftDate().toString());
		editShiftDto.setShiftDetailId(shiftDetails.getShiftDetailId());
		editShiftDto.setStartTime(shiftDetails.getStartTime().toString());

		return editShiftDto;
	}

	public boolean editShiftDetails(EditShiftDetailsDto editShiftDetailsDto, HttpServletRequest httpServletRequest) {
		ShiftDetails shiftDetails = this.shiftDao.getEventById(editShiftDetailsDto.getShiftDetailId());
		Physician physician = shiftDetails.getShiftId().getPhysicianId();

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
		LocalTime startTime = LocalTime.parse(editShiftDetailsDto.getStartTime(), timeFormatter);
		LocalTime endTime = LocalTime.parse(editShiftDetailsDto.getEndTime(), timeFormatter);

		List<LocalDate> localDates = new ArrayList<LocalDate>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate shiftDate = LocalDate.parse(editShiftDetailsDto.getShiftDate(), formatter);
		localDates.add(shiftDate);

		List<ShiftDetails> physicianShifts = this.shiftDao.getPhysicianAvailbility(localDates, physician);

		boolean flag = true;
		for (ShiftDetails oldShift : physicianShifts) {

			if (oldShift.getStartTime() == startTime && oldShift.getEndTime() == endTime) {

			} else {
				if ((startTime.isAfter(oldShift.getStartTime()) && startTime.isBefore(oldShift.getEndTime()))
						|| (endTime.isAfter(oldShift.getStartTime()) && endTime.isBefore(oldShift.getEndTime()))
						|| (startTime.isBefore(oldShift.getStartTime()) && endTime.isAfter(oldShift.getEndTime()))) {
					flag = false;
					break;
				}
			}

		}

		if (flag) {
			shiftDetails.setStartTime(startTime);
			shiftDetails.setEndTime(endTime);
			this.shiftDao.updateShiftDetails(shiftDetails);
			return true;
		} else {
			return false;
		}
	}

	public String toggleShiftStatus(int shiftDetailId) {
		return this.shiftDao.toggleShiftStatus(shiftDetailId);
	}

	public String deleteShift(int shiftDetailId) {
		return this.shiftDao.deleteShift(shiftDetailId);
	}

	public ReviewShiftDto getReviewShiftData(int regionId, int pageNo) {

		ReviewShiftDto reviewShiftDto = new ReviewShiftDto();
		List<ReviewShiftDetailsDto> reviewShiftDetailsDtos = new ArrayList<ReviewShiftDetailsDto>();
		Long count = this.shiftDao.getFilteredShiftReviewDetailsCount(regionId, pageNo);
		List<ShiftDetails> shiftDetails = this.shiftDao.getFilteredShiftReviewDetails(regionId, pageNo);
//		Region region = this.regionDao.getRegionById(regionId).get(0);

		for (ShiftDetails shift : shiftDetails) {
			ReviewShiftDetailsDto reviewShiftDetailsDto = new ReviewShiftDetailsDto();
			reviewShiftDetailsDto.setEndTime(shift.getEndTime().toString());
			reviewShiftDetailsDto.setRegionId(shift.getRegionId());
			String name = "Dr." + shift.getShiftId().getPhysicianId().getFirstName() + " "
					+ shift.getShiftId().getPhysicianId().getLastName();
			reviewShiftDetailsDto.setPhysicainName(name);
			Region region = this.regionDao.getRegionById(shift.getRegionId()).get(0);
			reviewShiftDetailsDto.setRegionName(region.getName());
			reviewShiftDetailsDto.setShiftDate(shift.getShiftDate().toString());
			reviewShiftDetailsDto.setStartTime(shift.getStartTime().toString());
			reviewShiftDetailsDto.setShiftDetailId(shift.getShiftDetailId());
			reviewShiftDetailsDtos.add(reviewShiftDetailsDto);
		}
		reviewShiftDto.setCount(count);
		reviewShiftDto.setReviewShiftDetailsDto(reviewShiftDetailsDtos);
		
		return reviewShiftDto;
	}
}
