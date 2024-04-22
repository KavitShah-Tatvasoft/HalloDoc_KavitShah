package hallodoc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.email.EmailService;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
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
}
