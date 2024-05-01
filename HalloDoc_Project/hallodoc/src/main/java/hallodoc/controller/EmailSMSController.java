package hallodoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hallodoc.dto.ResetPasswordDto;
import hallodoc.service.EmailSMSService;


@Controller
public class EmailSMSController {
	

	@Autowired
	private EmailSMSService emailsmsservice;
	
	@RequestMapping(value = "/patientSendResetEmail", method = RequestMethod.POST)
	public String resetPasswordEmail(@ModelAttribute ResetPasswordDto resetpassdto, Model m) {
		 ;
		String email = resetpassdto.getEmail();
		int response = emailsmsservice.resetPassword(email);
		if(response == -1) {
			m.addAttribute("NoEmail","No such email exisits!");
		return "/patient/patient-forget-password";
		}
		else {
			return "/patient/patient-login";
		}
	}
}
