package hallodoc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.RequestDocumentsDto;
import hallodoc.email.EmailService;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestWiseFileDao;
import hallodoc.service.PatientService;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private RequestStatusLogDao requestStatusLogDao;
	
	@Autowired
	private PatientService pService;

	@RequestMapping(value = "/sendReviewAgreement/{reqId}")
	public String showAgreementEmail(@PathVariable("reqId") int reqId, Model m) {
		
		boolean validateAgreement = uService.validateAgreementRequest(reqId);
		if(validateAgreement) {
			Request request = this.requestDao.getRequestOb(reqId);
			m.addAttribute("reqId",reqId);
			m.addAttribute("name", EmailService.capitalize(request.getRequestClient().getFirstName()) + " " + EmailService.capitalize(request.getRequestClient().getLastName()));
		return "common/review-agreement";
		
		}else {
			return "common/agreement-error";
		}
	}
	
	@RequestMapping(value="/acceptedReviewAgreement/{reqId}")
	public String acceptedReviewAgreement(@PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest) {
		
		uService.acceptAgreement(reqId, httpServletRequest, true, "");

		return "patient/patient-site";
	}
	
	@RequestMapping("/cancelAgreementDetails/{reqId}")
	public RedirectView cancelAgreementDetails(@RequestParam("cancel-details") String cancellationReasons, @PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest ) {
		
		uService.acceptAgreement(reqId, httpServletRequest, false, cancellationReasons);
		
		return new RedirectView(httpServletRequest.getContextPath() + "/patientHome");
	}
	
	@RequestMapping("/viewRequestUploads/{reqId}")
	public String viewRequestUploads(@PathVariable("reqId") int reqId , HttpServletRequest httpServletRequest, Model m) {
		m.addAttribute("reqId", reqId);
		Request request = uService.getRequestById(reqId);
		m.addAttribute("ptName",request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber() );
		List<RequestDocumentsDto> requests = pService.getRequestDocuments(reqId);
		m.addAttribute("docList", requests);
		AspNetUsers aspNetUsersOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		User userOb = aspNetUsersOb.getUser();

		m.addAttribute("userOb", userOb);
		
		return "common/requested-case-view-uploads";
	}
	
	@RequestMapping(value = "/uploadRequestDocument", method = RequestMethod.POST)
	public RedirectView uploadRequestDocument(@RequestParam("documentFile") CommonsMultipartFile document,
			@RequestParam("uploaderName") String name, @RequestParam("requestId") int requestId,
			HttpServletRequest request) {
		System.out.println("in upload");
		Date date = new Date();
		Request requestOb = uService.getRequestObject(requestId);
		try {
			HttpSession session = request.getSession(false);
			uService.uploadRequestDocument(document, name, requestOb, session, date);
		} catch (Exception e) {
			System.out.println("Http Session not found");
		}
		RedirectView redirectView = new RedirectView("/user/viewRequestUploads/" + requestId, true);
		return redirectView;
	}
	
	@ResponseBody
	@RequestMapping(value="/softDeleteUploadedFile", method = RequestMethod.POST)
	public String deleteUploadedFile(@RequestParam("fileId") int fileId) {
		
		uService.softDeleteRequestedFile(fileId);
		return "deleted";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/deleteMultipleFiles", method = RequestMethod.POST)
	public String deleteMultipleFiles(@RequestParam("data") String list) {
		String[] arrOfStr = list.split(",");
		for (String a : arrOfStr) {
			uService.softDeleteRequestedFile(Integer.parseInt(a));   // can you in query
		} 
		 
		return "Success";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendFilesToPatient", method = RequestMethod.POST)
	public String sendFilesToPatient(@RequestParam("reqId") int reqId, @RequestParam("attachmentList") String list, HttpServletRequest httpServletRequest) {
		String status = uService.sendFilesToPatient(reqId,list,httpServletRequest);
		return status;
	}
	
}

	
