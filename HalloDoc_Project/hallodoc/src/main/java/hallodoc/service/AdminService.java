package hallodoc.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import hallodoc.dto.AssignCaseDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailLog;
import hallodoc.model.Physician;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.repository.LogsDao;
import hallodoc.repository.PhysicianDao;
import hallodoc.repository.RegionDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.email.*;

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

	public boolean sendRequestLink(HttpServletRequest request,SendLinkDto sendLinkDto) {
		
		LocalDateTime date = LocalDateTime.now();
		EmailLog emailLog = new EmailLog();
		emailLog.setSubjectName(sendLinkDto.getEmailSubject());
		emailLog.setEmailId(sendLinkDto.getEmail());
		emailLog.setCreatedDate(date);
		emailLog.setSentDate(date);
		emailLog.setAction(1);
		emailLog.setRecipientName(sendLinkDto.getFirstName()+" "+sendLinkDto.getLastName());
		boolean isSent = false;
		int sentTries = 1;
		for (int i = sentTries; i <= 3; i++) {
			if(isSent) {
				continue;
			}
			try {
				emailService.sendRequestLinkByEmail(request, sendLinkDto);
				isSent = true;
				emailLog.setSentTries(i);
				emailLog.setEmailSent(isSent);
				logsDao.addEmailLogEntry(emailLog);
				
			} catch (Exception e) {
				if(i==3) {
					emailLog.setSentTries(3);
					emailLog.setEmailSent(false);	
					logsDao.addEmailLogEntry(emailLog);
				}
			}
		}	
		
		return isSent;
	}
	
	public List<PhysicianAssignCaseDto> getPhysicianByRegion(int regionId){
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
	
	public void assignPhysicianToRequest(AssignCaseDto assignCaseDto,HttpServletRequest httpServletRequest) {
		Request request = requestDao.getRequestOb(assignCaseDto.getReqId());
		Physician physician = physicianDao.getPhysicianById(assignCaseDto.getPhysicianId());
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
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
		String transferNote = "Admin transferred case to Dr. " + physician.getFirstName() + " on "+ fDate + " at " +
				fTime + " : " + assignCaseDto.getDescription();
		
		
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
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
		Date date = new Date();
		
		String pattern = "MMMM dd, yyyy";
		String timePattern = "KK:mm:ss aa"; 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
		String fDate = simpleDateFormat.format(date);
		String fTime = simpleTimeFormat.format(date);
		
		String transferNote = "Admin transferred case to Dr. " + physician.getFirstName() + " on "+ fDate + " at " +
				fTime + " : " + assignCaseDto.getDescription();
		
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

}
