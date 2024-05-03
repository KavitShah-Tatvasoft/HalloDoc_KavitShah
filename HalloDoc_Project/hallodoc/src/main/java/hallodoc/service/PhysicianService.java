package hallodoc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.enumerations.RequestStatus;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Physician;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;

@Service
public class PhysicianService {
	
	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private RequestStatusLogDao requestStatusLogDao;

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
		
		List<PhysicianRequestDataDto> requestList = this.requestDao.getRequestByPhysicianId(statusList, physicianId);
		

		return requestList;
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

}
