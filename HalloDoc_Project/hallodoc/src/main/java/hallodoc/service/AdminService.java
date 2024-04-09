package hallodoc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.EmailLog;
import hallodoc.model.Request;
import hallodoc.repository.LogsDao;
import hallodoc.repository.RequestDao;
import hallodoc.email.*;

@Service
public class AdminService {

	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LogsDao logsDao;

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

}
