package hallodoc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.CreateNewPasswordDto;
import hallodoc.dto.CreatePatientDto;
import hallodoc.email.EmailService;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.service.PatientService;
import hallodoc.service.UserService;

@Controller
public class PatientLoginController {

	@Autowired
	private PatientService pService;

	@Autowired
	private UserService uService;

	@Autowired
	private EmailService emailService;

	@RequestMapping("/patientHome")
	public String patientHomePage() {
		return "patient/patient-site";
	}

	@RequestMapping("/forgetPasswordPatient")
	public String forgetPatientPassword() {
		return "patient/patient-forget-password";
	}

	@RequestMapping("/createPatient/{token}")
	public String createPatientAccount(@PathVariable("token") String token, Model model) {
//		System.out.println("In  condition");
		boolean isExpired = emailService.isTokenExpired(token);
		System.out.println("In condition 1");
		if (isExpired) {
//			System.out.println("In expired condition");
			return "patient/link-expired";
		} else {
//			System.out.println("In alive condition");
			String email = emailService.getTokenCorrespondingEmail(token);
			System.out.println("In alive condition 1");
			model.addAttribute("email", email);
			model.addAttribute("token", token);
			return "patient/create-patient-account";
		}

	}

	@RequestMapping("/resetPasswordScreen/{token}")
	public String resetAccountPass(@PathVariable("token") String token, Model model) {
//		System.out.println("In  condition");
		boolean isExpired = emailService.isForgetPassTokenExpired(token);
//		System.out.println("In condition 1");
		if (isExpired) {
//			System.out.println("In expired condition");
			return "patient/link-expired";
		} else {
//			System.out.println("In alive condition");
			String email = emailService.getTokenCorrespondingEmail(token);
			System.out.println("In alive condition 1");
			model.addAttribute("email", email);
			model.addAttribute("token", token);
			return "patient/account-password-reset-page";
		}

	}

	@RequestMapping(value = "/createPatientValidator", method = RequestMethod.POST)
	public String createPatientValidator(
			@ModelAttribute("createPatientAccount") CreateNewPasswordDto createNewPasswordDto, Model m) {
//		System.out.println("Hello");
		System.out.println(createNewPasswordDto);

		String email = createNewPasswordDto.getEmail();
		String pass = createNewPasswordDto.getPassword();
		String pass1 = createNewPasswordDto.getConfPassword();
		String token = createNewPasswordDto.getToken();

		if (pass.equals(pass1)) {
			String status = pService.updateAspNetUserPassword(email, pass1);
			String status2 = emailService.updateIsResetWithMail(token);
			System.out.println("Update Password: " + status);
			System.out.println("Update isRestEmail flag: " + status2);
			return "patient/patient-login";
		} else {
			return ""; // error page
		}

	}

	@RequestMapping(value = "sendResetPasswordRequest", method = RequestMethod.POST)
	public String resetPasswordRequest(@RequestParam("email") String email, HttpServletRequest httpServletRequest) {
		String emailStatus = emailService.sendForgetPasswordMail(httpServletRequest, email);
		System.out.println(emailStatus);
		return "patient/patient-login";
	}

	@RequestMapping("/patient_submit_request")
	public String patientSubmitRequest() {
		return "patient/patient_submit_request";
	}

	@RequestMapping("/patient_login")
	public String RegisteredPatientLogin() {
		return "patient/patient-login";
	}

	@RequestMapping(value = "/loginValidator", method = RequestMethod.POST)
	public String LoginValidator(@RequestParam("username") String username, @RequestParam("password") String password,
			Model m) {
		int roleId = this.uService.validateUser(username, password);
		if (roleId == -1) {
			m.addAttribute("Error", "Login Failed! No such user found");
			return "patient/patient-login";
		}
		else if(roleId == -2) {
			m.addAttribute("Error", "Login Failed! Password not created! Please check the registered mail to create password");
			return "patient/patient-login";
		}
		else if(roleId == -3) {
			m.addAttribute("Error", "Login Failed! Username or password dosen't match");
			return "patient/patient-login";
		}
		
		else if(roleId == 3) {
			return "patient/patient-dashboard";
		}
		else {
			return "patient/patient-dashboard";
		}
	}

}
