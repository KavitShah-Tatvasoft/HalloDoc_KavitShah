package hallodoc.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.CreateShiftDto;
import hallodoc.dto.EditShiftDetailsDto;
import hallodoc.dto.EditShiftDto;
import hallodoc.dto.EventsDto;
import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.MessageTypeEnum;
import hallodoc.enumerations.RequestStatus;
import hallodoc.helper.DateHelper;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailLog;
import hallodoc.model.EncounterForm;
import hallodoc.model.Physician;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.Shift;
import hallodoc.model.ShiftDetails;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.EncounterFormDao;
import hallodoc.repository.LogsDao;
import hallodoc.repository.PhysicianDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.ShiftDao;
import hallodoc.repository.UserDao;

@Service
public class PhysicianService {

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private RequestStatusLogDao requestStatusLogDao;

	@Autowired
	private AspNetUserDao aspNetUserDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogsDao logsDao;

	@Autowired
	private RegionDao regionDao;

	@Autowired
	private ShiftDao shiftDao;

	@Autowired
	private PhysicianDao physicianDao;

	@Autowired
	private EncounterFormDao encounterFormDao;
	
	public List<PhysicianRequestDataDto> getPhysicianRequests(String status, HttpServletRequest request) {

		AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
		int physicianId = aspNetUsers.getPhysician().getPhysicianId();

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

		List<Request> requestList = this.requestDao.getRequestByPhysicianId(statusList, physicianId);
		List<PhysicianRequestDataDto> returnList = new ArrayList<PhysicianRequestDataDto>();

		for (Request request2 : requestList) {
			PhysicianRequestDataDto physicianRequestDataDto = new PhysicianRequestDataDto();
			physicianRequestDataDto.setCallType(request2.getCallType());
			physicianRequestDataDto.setPatientFirstName(request2.getRequestClient().getFirstName());
			physicianRequestDataDto.setPatientLastName(request2.getRequestClient().getLastName());
			physicianRequestDataDto.setPtCity(request2.getRequestClient().getCity());
			physicianRequestDataDto.setPtPhoneNumber(request2.getRequestClient().getPhoneNumber());
			physicianRequestDataDto.setPtState(request2.getRequestClient().getState());
			physicianRequestDataDto.setPtStreet(request2.getRequestClient().getStreet());
			physicianRequestDataDto.setPtZipcode(request2.getRequestClient().getZipcode());
			physicianRequestDataDto.setReqId(request2.getRequestId());
			physicianRequestDataDto.setReqPhoneNumber(request2.getPhoneNumber());
			physicianRequestDataDto.setReqPhoneType(request2.getRequestType().getName());
			if (request2.getEncounterForm() != null) {

				physicianRequestDataDto.setFinalized(request2.getEncounterForm().isFinalized());
			} else {
				physicianRequestDataDto.setFinalized(false);
			}
			returnList.add(physicianRequestDataDto);
		}

		return returnList;
	}

	public List<Integer> getStatusWiseCount(HttpServletRequest httpServletRequest) {

		int physicianId = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician()
				.getPhysicianId();
		List<StatusWiseCountDto> list = requestDao.getStatusWisePhysicianRequestCount(physicianId);

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

	public String acceptCase(int reqId, HttpServletRequest httpServletRequest) {
		Date date = new Date();
		Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		Request request = requestDao.getRequestOb(reqId);
		request.setPhysician(physician);
		request.setAcceptedDate(date);
		request.setModifieDate(date);
		request.setStatus(RequestStatus.ACCEPTED.getRequestId());
		requestDao.updateRequest(request);
		return "Provider accepted the case";
	}

	public String transferCaseToAdmin(HttpServletRequest httpServletRequest, String description, int reqId) {
		Request request = this.requestDao.getRequestOb(reqId);
		Date date = new Date();
		Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		request.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		request.setPhysician(null);
		request.setModifieDate(date);
		requestDao.updateRequest(request);

		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes("Dr." + physician.getFirstName() + " transferred this case back to admin on " + date
				+ " :" + description);
		requestStatusLog.setPhysician(null);
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setTransToAdmin(true);

		this.requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);

		return "Transferred Case to Admin";
	}

	public String changeCallType(int reqId, int callType, HttpServletRequest httpServletRequest) {
		Request request = this.requestDao.getRequestOb(reqId);
		if (callType == 1) {
		} else {
			Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser"))
					.getPhysician();
			request.setStatus(RequestStatus.CONCLUDE.getRequestId());
			RequestStatusLog requestStatusLog = new RequestStatusLog();
			Date date = new Date();
			requestStatusLog.setCreatedDate(date);
			requestStatusLog.setNotes("Physician Dr. " + physician.getFirstName() + " " + physician.getLastName()
					+ " concluded the case on " + date.getDate() + "-" + date.getMonth() + "-" + date.getYear());
			requestStatusLog.setPhysician(physician);
			requestStatusLog.setRequest(request);
			requestStatusLog.setStatus(RequestStatus.CONCLUDE.getRequestId());

			this.requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		}
		request.setCallType(callType);
		request.setModifieDate(new Date());

		this.requestDao.updateRequest(request);
		return "Updated call type";
	}

	public String concludeCaseByReqId(int reqId, HttpServletRequest httpServletRequest) {
		Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		Request request = this.requestDao.getRequestOb(reqId);
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		Date date = new Date();
		request.setStatus(RequestStatus.CONCLUDE.getRequestId());
		request.setModifieDate(date);

		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes("Physician Dr. " + physician.getFirstName() + " " + physician.getLastName()
				+ " concluded the case on " + date.getDate() + "-" + date.getMonth() + "-" + date.getYear());
		requestStatusLog.setPhysician(physician);
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(RequestStatus.CONCLUDE.getRequestId());

		this.requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		this.requestDao.updateRequest(request);

		return "Case concluded";
	}

	public String changePassword(AspNetUsers aspNetUsers, String password) {

		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
		Hash hash = Password.hash(password).with(bcrypt);
		aspNetUsers.setPassword_hash(hash.getResult());
		aspNetUsers.setModified_date(new Date());
		aspNetUserDao.updateAspNetUser(aspNetUsers);

		return "Password Updated";
	}

	@Transactional
	public String sendRequestToAdmin(HttpServletRequest httpServletRequest, String description) {

		String status = "";
		String subject = "Request to Admin";
		LocalDateTime date = LocalDateTime.now();
		List<User> allAdmin = this.userDao.getAllAdminUsers();
		int physicianId = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician()
				.getPhysicianId();
		InternetAddress[] addresses = new InternetAddress[allAdmin.size()];
		try {
			for (int i = 0; i < allAdmin.size(); i++) {
				addresses[i] = new InternetAddress(allAdmin.get(i).getEmail());
			}
		} catch (AddressException e) {
			e.printStackTrace();
		}

		boolean isSent = false;
		int sentTries = 1;
		int tries;
		for (int i = sentTries; i <= 3; i++) {
			if (isSent) {
				continue;
			}
			try {
				this.emailService.sendRequestToAdmin(httpServletRequest, subject, addresses, description);
				isSent = true;
				tries = i;
				status = status + "mail send";

			} catch (Exception e) {
				if (i == 3) {
//					emailLog.setSentTries(3);
//					emailLog.setEmailSent(false);
					tries = 3;
					isSent = false;
					status = status + "failed to send mail";
					return status;
				}
			}

		}

		for (User adminUser : allAdmin) {
			EmailLog emailLog = new EmailLog();
			emailLog.setSubjectName(subject);
			emailLog.setEmailId(adminUser.getEmail());
//			emailLog.setAdminId(adminUser.getAspNetUsers().getAdmin().getAdminId());
			emailLog.setCreatedDate(date);
			emailLog.setSentDate(date);
			emailLog.setAction(MessageTypeEnum.REQUEST_CHANGES_TO_ADMIN.getMessageTypeId());
			emailLog.setRecipientName(adminUser.getFirstName() + " " + adminUser.getLastName());
			emailLog.setPhysicianId(physicianId);
			emailLog.setRoleId(1);
			emailLog.setSentTries(sentTries);
			emailLog.setEmailSent(isSent);
			logsDao.addEmailLogEntry(emailLog);
		}

		return status;
	}

	public List<PhysicianRequestDataDto> getFilteredPhysicianRequests(RequestFiltersDto requestFiltersDto,
			HttpServletRequest httpServletRequest) {
		List<Request> filteredList = requestDao.getPhysicianFilteredRequests(requestFiltersDto, httpServletRequest);
		List<PhysicianRequestDataDto> returnList = new ArrayList<PhysicianRequestDataDto>();

		for (Request request2 : filteredList) {
			PhysicianRequestDataDto physicianRequestDataDto = new PhysicianRequestDataDto();
			physicianRequestDataDto.setCallType(request2.getCallType());
			physicianRequestDataDto.setPatientFirstName(request2.getRequestClient().getFirstName());
			physicianRequestDataDto.setPatientLastName(request2.getRequestClient().getLastName());
			physicianRequestDataDto.setPtCity(request2.getRequestClient().getCity());
			physicianRequestDataDto.setPtPhoneNumber(request2.getRequestClient().getPhoneNumber());
			physicianRequestDataDto.setPtState(request2.getRequestClient().getState());
			physicianRequestDataDto.setPtStreet(request2.getRequestClient().getStreet());
			physicianRequestDataDto.setPtZipcode(request2.getRequestClient().getZipcode());
			physicianRequestDataDto.setReqId(request2.getRequestId());
			physicianRequestDataDto.setReqPhoneNumber(request2.getPhoneNumber());
			physicianRequestDataDto.setReqPhoneType(request2.getRequestType().getName());
			if (request2.getEncounterForm() != null) {

				physicianRequestDataDto.setFinalized(request2.getEncounterForm().isFinalized());
			} else {
				physicianRequestDataDto.setFinalized(false);
			}
			returnList.add(physicianRequestDataDto);
		}

		return returnList;

	}

	public List<Region> getPhysicianRegion(int phyId) {
		List<Region> regions = this.regionDao.getPhysicianRegions(phyId);
		for (Region region : regions) {
			System.out.println(region.getName());
		}

		return regions;
	}

	public List<EventsDto> getAllActivePhysicianEvents(int regionId, HttpServletRequest httpServletRequest) {
		List<EventsDto> eventsDtos = new ArrayList<EventsDto>();
		Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		List<ShiftDetails> shiftDetailsList = this.shiftDao.getAllActivePhysicianShifts(regionId,
				physician.getPhysicianId());

		for (ShiftDetails shiftDetails : shiftDetailsList) {
			EventsDto eventsDto = new EventsDto();
			eventsDto.setEndTime(shiftDetails.getEndTime().toString());
			eventsDto.setPhysicianId(shiftDetails.getShiftId().getPhysicianId().getPhysicianId());
			eventsDto.setPhysicianName("Dr." + shiftDetails.getShiftId().getPhysicianId().getFirstName() + " "
					+ shiftDetails.getShiftId().getPhysicianId().getLastName());
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
		shiftDetails.setStatus(0);
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
						nextDate = nextDate.plusDays(7);
						shiftDates.add(nextDate);
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
							nextDate = nextDate.plusDays(7);
							ShiftDetails futureshiftDetails = createNewShiftDetails(createShiftDto, aspNetUsers,
									nextDate, startTime, endTime, shift);
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
			
			if (editShiftDetailsDto.getShiftDetailId() == oldShift.getShiftDetailId()) {

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
	
	public String deleteShift(int shiftDetailId) {
		return this.shiftDao.deleteShift(shiftDetailId);
	}

	public String creatingPdf(int reqId, HttpServletRequest httpServletRequest) throws IOException {

		EncounterForm encounterForm = this.encounterFormDao.getEncounterFormByReqID(reqId).get(0);
		Request request = encounterForm.getRequest();

		String pathtemp = String.format("/%s/%s/%s/", "resources", "fileuploads", "Encounter");
		String path = httpServletRequest.getContextPath() + pathtemp + "EncounterForm_" + request.getRequestId() + ".pdf";
		
		HttpSession httpSession = httpServletRequest.getSession();
		String p = httpSession.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
				+ File.separator + "fileuploads" + File.separator + "Encounter" + File.separator + "EncounterForm_" + request.getRequestId() + ".pdf";
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(p));
		pdfDoc.addNewPage();

		Document document = new Document(pdfDoc);

		ImageData imageData = ImageDataFactory.create( 
				httpSession.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"	+ File.separator + "images/logo-halloDoc.png");
		Image image = new Image(imageData);
		image.setFixedPosition(pdfDoc.getDefaultPageSize().getWidth() / 2 - (image.getImageScaledWidth() / 2),
				pdfDoc.getDefaultPageSize().getHeight() / 2 - (image.getImageScaledHeight() / 2));
		image.setOpacity(0.3f);

		float width = pdfDoc.getDefaultPageSize().getWidth();
		float height = pdfDoc.getDefaultPageSize().getHeight();

		PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
		canvas.rectangle(20, 20, width - 40, height - 40);
		canvas.stroke();

		Paragraph paragraph1 = new Paragraph("Medical Report");
		paragraph1.setFontSize(26);
		paragraph1.setBold();
		paragraph1.setTextAlignment(TextAlignment.CENTER);

		Paragraph paragraph2 = new Paragraph("User Details");
		paragraph2.setFontSize(16);
		paragraph2.setMarginTop(12);
		paragraph2.setMarginBottom(7);
		paragraph2.setTextAlignment(TextAlignment.CENTER);

		Text text1 = new Text("First Name : ");
		text1.setBold();
		Text text2 = new Text(request.getRequestClient().getFirstName());

		Text text3 = new Text("Last Name : ");
		text3.setBold();
		Text text4 = new Text(request.getRequestClient().getLastName());

		Text text5 = new Text("Location : ");
		text5.setBold();
		Text text6 = new Text(request.getRequestClient().getStreet() + ", " + request.getRequestClient().getCity()
				+ ", " + request.getRequestClient().getState() + ", " + request.getRequestClient().getZipcode());

		String dob = request.getRequestClient().getIntDate() + " " + request.getRequestClient().getStrMonth() + ", " + request.getRequestClient().getIntYear();

		Text text11 = new Text("Date Of Birth : ");
		text11.setBold();
		Text text12 = new Text(dob);
		
		LocalDate localDate = LocalDate.from(request.getAcceptedDate().toInstant().atZone(ZoneId.systemDefault()));
		
		int date = localDate.getDayOfMonth();
		String month = localDate.getMonth().toString();
		int year = localDate.getYear();

		String dos = date + " " + month + ", " + year;

		Text text13 = new Text("Date Of Service : ");
		text13.setBold();
		Text text14 = new Text(dos);

		Text text7 = new Text("Phone Number : ");
		text7.setBold();
		Text text8 = new Text(request.getRequestClient().getPhoneNumber());

		Text text9 = new Text("Email : ");
		text9.setBold();
		Text text10 = new Text(request.getRequestClient().getEmail());

		Paragraph paragraph3 = new Paragraph();
		paragraph3.add(text1);
		paragraph3.add(text2);
		paragraph3.add("\n");
		paragraph3.add(text3);
		paragraph3.add(text4);
		paragraph3.add("\n");
		paragraph3.add(text5);
		paragraph3.add(text6);
		paragraph3.add("\n");
		paragraph3.add(text11);
		paragraph3.add(text12);
		paragraph3.add("\n");
		paragraph3.add(text13);
		paragraph3.add(text14);
		paragraph3.add("\n");
		paragraph3.add(text7);
		paragraph3.add(text8);
		paragraph3.add("\n");
		paragraph3.add(text9);
		paragraph3.add(text10);

		Paragraph paragraph4 = new Paragraph("Encounter Details");
		paragraph4.setFontSize(16);
		paragraph4.setMarginTop(12);
		paragraph4.setMarginBottom(10);
		paragraph4.setTextAlignment(TextAlignment.CENTER);

		Text text15 = new Text("History Of Present Illness Or Injury : ");
		text15.setBold();
		Text text16 = new Text(encounterForm.getHistoryOfIllness());

		Text text17 = new Text("Medical History : ");
		text17.setBold();
		Text text18 = new Text(encounterForm.getMedicalHistory());

		Text text19 = new Text("Medications : ");
		text19.setBold();
		Text text20 = new Text(encounterForm.getMedications());

		Text text21 = new Text("Allergies : ");
		text21.setBold();
		Text text22 = new Text(encounterForm.getAllergies());

		Text text23 = new Text("Temp : ");
		text23.setBold();
		Text text24 = new Text(encounterForm.getTemp());

		Text text25 = new Text("HR : ");
		text25.setBold();
		Text text26 = new Text(encounterForm.getHr());

		Text text27 = new Text("RR : ");
		text27.setBold();
		Text text28 = new Text(encounterForm.getRr());

		Text text29 = new Text("Blood Pressure(nagetive)  : ");
		text29.setBold();
		Text text30 = new Text(encounterForm.getBloodPresureneg());

		Text text31 = new Text("Blood Pressure(positive) : ");
		text31.setBold();
		Text text32 = new Text(encounterForm.getBloodPresurePlus());

		Text text33 = new Text("O2 : ");
		text33.setBold();
		Text text34 = new Text(encounterForm.getO2());

		Text text35 = new Text("Pain : ");
		text35.setBold();
		Text text36 = new Text(encounterForm.getPain());

		Text text37 = new Text("CV : ");
		text37.setBold();
		Text text38 = new Text(encounterForm.getCv());

		Text text39 = new Text("Chest : ");
		text39.setBold();
		Text text40 = new Text(encounterForm.getChest());

		Text text41 = new Text("ABD : ");
		text41.setBold();
		Text text42 = new Text(encounterForm.getAbd());

		Text text43 = new Text("Extr : ");
		text43.setBold();
		Text text44 = new Text(encounterForm.getExtr());

		Text text45 = new Text("Skin : ");
		text45.setBold();
		Text text46 = new Text(encounterForm.getSkin());

		Text text47 = new Text("Neuro : ");
		text47.setBold();
		Text text48 = new Text(encounterForm.getNeuro());

		Text text49 = new Text("Other : ");
		text49.setBold();
		Text text50 = new Text(encounterForm.getOther());

		Text text51 = new Text("Diagnosis : ");
		text51.setBold();
		Text text52 = new Text(encounterForm.getDiagnosis());

		Text text53 = new Text("Treatment Plan : ");
		text53.setBold();
		Text text54 = new Text(encounterForm.getTreatmentPlan());

		Text text55 = new Text("Medication Dispensed : ");
		text55.setBold();
		Text text56 = new Text(encounterForm.getMedicationsDespensed());

		Text text57 = new Text("Procedures : ");
		text57.setBold();
		Text text58 = new Text(encounterForm.getProcedures());

		Text text59 = new Text("Followup : ");
		text59.setBold();
		Text text60 = new Text(encounterForm.getFollowUp());

		Paragraph paragraph5 = new Paragraph();
		paragraph5.add(text15);
		paragraph5.add(text16);
		paragraph5.add("\n");
		paragraph5.add(text17);
		paragraph5.add(text18);
		paragraph5.add("\n");
		paragraph5.add(text19);
		paragraph5.add(text20);
		paragraph5.add("\n");
		paragraph5.add(text21);
		paragraph5.add(text22);
		paragraph5.add("\n");
		paragraph5.add(text23);
		paragraph5.add(text24);
		paragraph5.add("\n");
		paragraph5.add(text25);
		paragraph5.add(text26);
		paragraph5.add("\n");
		paragraph5.add(text27);
		paragraph5.add(text28);
		paragraph5.add("\n");
		paragraph5.add(text29);
		paragraph5.add(text30);
		paragraph5.add("\n");
		paragraph5.add(text31);
		paragraph5.add(text32);
		paragraph5.add("\n");
		paragraph5.add(text33);
		paragraph5.add(text34);
		paragraph5.add("\n");
		paragraph5.add(text35);
		paragraph5.add(text36);
		paragraph5.add("\n");
		paragraph5.add(text37);
		paragraph5.add(text38);
		paragraph5.add("\n");
		paragraph5.add(text39);
		paragraph5.add(text40);
		paragraph5.add("\n");
		paragraph5.add(text41);
		paragraph5.add(text42);
		paragraph5.add("\n");
		paragraph5.add(text43);
		paragraph5.add(text44);
		paragraph5.add("\n");
		paragraph5.add(text45);
		paragraph5.add(text46);
		paragraph5.add("\n");
		paragraph5.add(text47);
		paragraph5.add(text48);
		paragraph5.add("\n");
		paragraph5.add(text49);
		paragraph5.add(text50);
		paragraph5.add("\n");
		paragraph5.add(text51);
		paragraph5.add(text52);
		paragraph5.add("\n");
		paragraph5.add(text53);
		paragraph5.add(text54);
		paragraph5.add("\n");
		paragraph5.add(text55);
		paragraph5.add(text56);
		paragraph5.add("\n");
		paragraph5.add(text57);
		paragraph5.add(text58);
		paragraph5.add("\n");
		paragraph5.add(text59);
		paragraph5.add(text60);

		document.add(paragraph1);
		document.add(paragraph2);
		document.add(paragraph3);
		document.add(paragraph4);
		document.add(paragraph5);
		document.add(image);

		pdfDoc.close();

		return path;

	}


}
