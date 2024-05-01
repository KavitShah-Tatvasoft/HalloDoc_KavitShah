package hallodoc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.CreateNewPasswordDto;
import hallodoc.dto.CreatePatientDto;
import hallodoc.dto.DashboardDataDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.UserProfileDto;
import hallodoc.email.EmailService;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EmailToken;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.User;
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
//		 ;
		boolean isExpired = emailService.isTokenExpired(token);
		 ;
		if (isExpired) {
//			 ;
			return "patient/link-expired";
		} else {
//			 ;
			String email = emailService.getTokenCorrespondingEmail(token);
			 ;
			model.addAttribute("email", email);
			model.addAttribute("token", token);
			return "patient/create-patient-account";
		}

	}

	@RequestMapping("/resetPasswordScreen/{token}")
	public String resetAccountPass(@PathVariable("token") String token, Model model) {
//		 ;
		boolean isExpired = emailService.isForgetPassTokenExpired(token);
//		 ;
		if (isExpired) {
//			 ;
			return "patient/link-expired";
		} else {
//			 ;
			String email = emailService.getTokenCorrespondingEmail(token);
			 ;
			model.addAttribute("email", email);
			model.addAttribute("token", token);
			return "patient/account-password-reset-page";
		}

	}

	@RequestMapping(value = "/createPatientValidator", method = RequestMethod.POST)
	public String createPatientValidator(
			@ModelAttribute("createPatientAccount") CreateNewPasswordDto createNewPasswordDto, Model m) {

		String email = createNewPasswordDto.getEmail();
		String pass = createNewPasswordDto.getPassword();
		String pass1 = createNewPasswordDto.getConfPassword();
		String token = createNewPasswordDto.getToken();

		if (pass.equals(pass1)) {
			String status = pService.updateAspNetUserPassword(email, pass1);
			String status2 = emailService.updateIsResetWithMail(token);

			return "patient/patient-login";
		} else {
			return ""; // error page
		}

	}

	@RequestMapping(value = "sendResetPasswordRequest", method = RequestMethod.POST)
	public RedirectView resetPasswordRequest(@RequestParam("email") String email, HttpServletRequest httpServletRequest,
			RedirectAttributes attributes) {
		String emailStatus = emailService.sendForgetPasswordMail(httpServletRequest, email);
		 ;
		attributes.addFlashAttribute("message", "Reset Email sent Successfully");
		attributes.addFlashAttribute("alertType", "success");
		return new RedirectView("patient_login");
	}

	@RequestMapping("/patient_submit_request")
	public String patientSubmitRequest() {
		return "patient/patient_submit_request";
	}

	@RequestMapping("/patient_login")
	public ModelAndView RegisteredPatientLogin(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("patient/patient-login");

		if (inputFlashMap != null) {
			String message = (String) inputFlashMap.get("message");
			String showAlertType = (String) inputFlashMap.get("alertType");
			modelAndView.addObject("msg", message);
			modelAndView.addObject("showalert", true);
			modelAndView.addObject("showAlertTypeJsp", showAlertType);
		} else {
			modelAndView.addObject("showalert", false);
		}
		return modelAndView;
	}

	@RequestMapping("/patientDashboard")
	public ModelAndView PatientDashboard(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("patient/patient-dashboard");
		if (inputFlashMap != null) {
			 ;
			String message = (String) inputFlashMap.get("message");
			String showAlertType = (String) inputFlashMap.get("alertType");
			modelAndView.addObject("msg", message);
			modelAndView.addObject("showalert", true);
			modelAndView.addObject("showAlertTypeJsp", showAlertType);
		} else {
			 ;
			modelAndView.addObject("showalert", false);
		}

		AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
		List<DashboardDataDto> data = pService.getDashboardData(aspNetUsers);
		modelAndView.addObject("requestData", data);
		return modelAndView;
	}

	@RequestMapping("/patientProfile")
	public String patientProfile(HttpServletRequest request, Model m) {

		try {
			HttpSession session = request.getSession(false);
			AspNetUsers aspNetUsers = (AspNetUsers) session.getAttribute("aspUser");
			User user = aspNetUsers.getUser();
			m.addAttribute("userOb", user);
		} catch (Exception e) {
			// TODO: handle exception
			 ;
		}

		List<Region> regionList = pService.getAllRegions();
		m.addAttribute("regions", regionList);
		return "/patient/patient-profile";
	}


	@RequestMapping(value = "/loginValidator", method = RequestMethod.POST)
	public RedirectView LoginValidator(@RequestParam("username") String username,
			@RequestParam("password") String password, Model m, RedirectAttributes attributes,
			HttpServletRequest request) {
		int roleId = this.uService.validateUser(username, password, request);
		if (roleId == -1) {
			attributes.addFlashAttribute("message", "Login Failed! No such user found");
			attributes.addFlashAttribute("alertType", "faliure");
			return new RedirectView("patient_login");
		} else if (roleId == -2) {
			attributes.addFlashAttribute("message",
					"Login Failed! Password not created! Please check the registered mail to create password");
			attributes.addFlashAttribute("alertType", "faliure");
			return new RedirectView("patient_login");
		} else if (roleId == -3) {
			attributes.addFlashAttribute("message", "Login Failed! Username or password dosen't match");
			attributes.addFlashAttribute("alertType", "faliure");
			return new RedirectView("patient_login");
		}

		else if (roleId == 3) {
			attributes.addFlashAttribute("message", "Logged in Succesfully");
			attributes.addFlashAttribute("alertType", "success");
			return new RedirectView("patientDashboard");
		} 
		else if (roleId == 1) {
			attributes.addFlashAttribute("message", "Logged in Succesfully");
			attributes.addFlashAttribute("alertType", "success");
			return new RedirectView("admin/adminDashboard");
		} 
		else {
			attributes.addFlashAttribute("message", "Logged in Succesfully");
			attributes.addFlashAttribute("alertType", "success");
			return new RedirectView("physician/physician-dashboard");
		}
	}

	@RequestMapping("/patientViewRequestDocuments/{reqId}")
	public String LoginValidator(@PathVariable("reqId") int id, HttpServletRequest request, Model m) {
		 ;
		m.addAttribute("reqId", id);
		List<RequestDocumentsDto> requests = pService.getRequestDocuments(id);
		m.addAttribute("docList", requests);
		AspNetUsers aspNetUsersOb = (AspNetUsers) request.getSession().getAttribute("aspUser");
		User userOb = aspNetUsersOb.getUser();

		m.addAttribute("userOb", userOb);
		 ;
		return "patient/patient-view-documents";
	}

	@RequestMapping(value = "/uploadRequestDocument", method = RequestMethod.POST)
	public RedirectView uploadRequestDocument(@RequestParam("documentFile") CommonsMultipartFile document,
			@RequestParam("uploaderName") String name, @RequestParam("requestId") int requestId,
			HttpServletRequest request) {
		 ;
		Date date = new Date();
		Request requestOb = uService.getRequestObject(requestId);
		try {
			HttpSession session = request.getSession(false);
			uService.uploadRequestDocument(document, name, requestOb, session, date);
		} catch (Exception e) {
			 ;
		}
		RedirectView redirectView = new RedirectView("/patientViewRequestDocuments/" + requestId, true);
		return redirectView;
	}

	@PostMapping(value = "/updateUserProfile")
	public RedirectView updateUserProfile(@ModelAttribute("updateUserProfile") UserProfileDto userProfileDto,
			HttpServletRequest request) {
		 ;
		pService.updateUserProfile(userProfileDto, request);
		 ;
		RedirectView redirectView = new RedirectView("/patientProfile", true);
		return redirectView;
	}

	@RequestMapping("/userLogout")
	public RedirectView userLogout(RedirectAttributes attributes, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		 ;
		RedirectView redirectView = new RedirectView("/patient_login", true);
		attributes.addFlashAttribute("message", "Logged out successfully!");
		attributes.addFlashAttribute("alertType", "success");
		return redirectView;
	}

}
