package hallodoc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.email.EmailService;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.MessageTypeEnum;
import hallodoc.enumerations.RequestStatus;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailLog;
import hallodoc.model.Physician;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.User;
import hallodoc.repository.AspNetUserDao;
import hallodoc.repository.LogsDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
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

	public List<PhysicianRequestDataDto> getPhysicianRequests(String status,HttpServletRequest request) {

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
		List<PhysicianRequestDataDto>  returnList= new ArrayList<PhysicianRequestDataDto>();
		
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
			if(request2.getEncounterForm() != null) {
				
				physicianRequestDataDto.setFinalized(request2.getEncounterForm().isFinalized());
			}else {
				physicianRequestDataDto.setFinalized(false);
			}
			returnList.add(physicianRequestDataDto);
		}

		return returnList;
	}
	
public List<Integer> getStatusWiseCount(HttpServletRequest httpServletRequest){
		
		int physicianId = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician().getPhysicianId();
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
		Physician physician = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
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
		Physician physician = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		request.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		request.setPhysician(null);
		request.setModifieDate(date);
		requestDao.updateRequest(request);
		
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes("Dr." + physician.getFirstName() + " transferred this case back to admin on " + date + " :"  + description);
		requestStatusLog.setPhysician(null);
		requestStatusLog.setRequest(request);
		requestStatusLog.setStatus(RequestStatus.UNASSIGNED.getRequestId());
		requestStatusLog.setTransToAdmin(true);
		
		this.requestStatusLogDao.addNewRequestStatusLog(requestStatusLog);
		
		return "Transferred Case to Admin";
	}
	
	public String changeCallType(int reqId, int callType, HttpServletRequest httpServletRequest) {
		Request request = this.requestDao.getRequestOb(reqId);
		if(callType == 1) {
		}else {
			Physician physician = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
			request.setStatus(RequestStatus.CONCLUDE.getRequestId());
			RequestStatusLog requestStatusLog = new RequestStatusLog();
			Date date = new Date();
			requestStatusLog.setCreatedDate(date);
			requestStatusLog.setNotes("Physician Dr. "+ physician.getFirstName() + " " + physician.getLastName() + " concluded the case on " + date.getDate() + "-" + date.getMonth() + "-" + date.getYear());
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
		Physician physician = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		Request request = this.requestDao.getRequestOb(reqId);
		RequestStatusLog requestStatusLog = new RequestStatusLog();
		Date date = new Date();
		request.setStatus(RequestStatus.CONCLUDE.getRequestId());
		request.setModifieDate(date);
		
		
		requestStatusLog.setCreatedDate(date);
		requestStatusLog.setNotes("Physician Dr. "+ physician.getFirstName() + " " + physician.getLastName() + " concluded the case on " + date.getDate() + "-" + date.getMonth() + "-" + date.getYear());
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
		int physicianId = ((AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser")).getPhysician().getPhysicianId();
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
				this.emailService.sendRequestToAdmin(httpServletRequest,subject,addresses,description);
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
	
	public List<PhysicianRequestDataDto> getFilteredPhysicianRequests(RequestFiltersDto requestFiltersDto, HttpServletRequest httpServletRequest){
		List<Request> filteredList = requestDao.getPhysicianFilteredRequests(requestFiltersDto, httpServletRequest);
		List<PhysicianRequestDataDto>  returnList= new ArrayList<PhysicianRequestDataDto>();
		
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
			if(request2.getEncounterForm() != null) {
				
				physicianRequestDataDto.setFinalized(request2.getEncounterForm().isFinalized());
			}else {
				physicianRequestDataDto.setFinalized(false);
			}
			returnList.add(physicianRequestDataDto);
		}

		return returnList;

	}

}
