package hallodoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hallodoc.dto.CreatePatientDto;
import hallodoc.model.AspNetUsers;
import hallodoc.service.PatientService;

@Controller
public class PatientLoginController {
	
	@Autowired
	private PatientService pService;
	
	@RequestMapping("/patientHome")
	public String patientHomePage(){
		return "patient/patient-site";
	}

	@RequestMapping("/createPatient")
	public String createPatientAccount() {
		return "patient/create-patient-account";
	}
	
//	@RequestMapping(value="/createPatientValidator", method = RequestMethod.POST)
//	public String createPatientValidator(@ModelAttribute("createPatientAccount") AspNetUsers user, Model m) {
//		
//		System.out.println("Before Service");
//		System.out.println(user.toString());
//		int id = this.pService.createPatient(user);
//		if(id!= -1) {
//			System.out.println("Patient Created");
//			return "/patient/patient-login";
//
//		}
//		else {
//			m.addAttribute("EmailExists","Email Already Exsists");
//			System.out.println("Patient Already Exisit");
//			return "/patient/create-patient-account";
//		}
//		
//	}
// remove this when dto works
	
	@RequestMapping(value="/createPatientValidator", method = RequestMethod.POST)
	public String createPatientValidator(@ModelAttribute("createPatientAccount") CreatePatientDto userdto, Model m) {
		
//		System.out.println("Before Service");
		System.out.println(userdto.toString());
		int id = this.pService.createPatient(userdto);
		if(id== -1) {
			m.addAttribute("EmailExists","Email Already Exsists");
			System.out.println("Patient Already Exisit");
			return "/patient/create-patient-account";
		}
		if(id == -2) {
			m.addAttribute("EmailExists","Password don't match");
			System.out.println("Password don't match");
			return "/patient/create-patient-account";

		}
		else {
			System.out.println("Patient Created");
			return "/patient/patient-login";
		}
		
	}
	
	
	@RequestMapping("/patient_submit_request")
	public String patientSubmitRequest() {
		return "patient/patient_submit_request";
	}
	
	@RequestMapping("/patient_login")
	public String RegisteredPatientLogin() {
		return "patient/patient-login";
	}
	
	@RequestMapping(value = "/patientloginValidator", method = RequestMethod.POST)
	public String PatientLoginValidator(@RequestParam("u_username") String username, @RequestParam("u_password") String password, Model m ){
		boolean isValid = this.pService.validatePatient(username, password);
		if(isValid==true) {
		return "patient/patient-dashboard";
		}
		else {
			m.addAttribute("Error","Login Failed! Wrong Username or Password!");
			return "patient/patient-login";
			}	
	}
}
