package hallodoc.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.User;
import hallodoc.service.PatientRequestsService;

@Controller
public class PatientRequestsController {
	
	@Autowired
	private PatientRequestsService patientRequestsService;
	

	@RequestMapping("/createNewPatientRequest")
	public String createNewPatientRequest() {
		return "/patient/create-patient-request";
	}
	
	@PostMapping(path = "/addPatientRequest")
	public String addPatientRequest(@ModelAttribute("createPatientRequest") CreatePatientRequestDto createPatientRequestDto ) throws Exception {
		System.out.println(createPatientRequestDto.toString());
		String isExistingPatient = createPatientRequestDto.getIsExsistingPatient();
		System.out.println(isExistingPatient);
		
		if(isExistingPatient.equals("true")) {
			
			int id = patientRequestsService.addRequestForExsitingPatient(createPatientRequestDto);
			System.out.println("In existing patient");
			return "true";
		}else
		{
			System.out.println("In new patient");
			boolean status = patientRequestsService.addRequestForNewPatient(createPatientRequestDto);
			System.out.println(status);
			return "";
		}
		
		
		
	}
	
	
	@RequestMapping(value= "/isPatientValidByEmail", method = RequestMethod.POST)
	@ResponseBody
	public String isPatientValidByEmail(@RequestParam("name") String email){
		
		String isPresent = patientRequestsService.isPatientAvailable(email);
		return isPresent;
	}
	
	@RequestMapping(value= "/isPatientStateValid", method = RequestMethod.POST)
	@ResponseBody
	public String isPatientStateValid(@RequestParam("state") String state){
		String formattedState =  state.substring(0, 1).toUpperCase() + state.substring(1);
		String isPresent = patientRequestsService.isStateAvailable(state);
		return isPresent;
	}
	
	@RequestMapping("/createNewFamilyFriendRequest")
	public String createNewFamilyRequest() {
		
		return "/patient/familyfriend-request";
	}
	
	@RequestMapping("/createNewConciergeRequest")
	public String createNewConciergeRequest() {
		
		return "/patient/concierge-request";
	}
	
	@RequestMapping("/createNewBusinessRequest")
	public String createNewBusinessRequest() {
		
		return "/patient/create-patient-request";
	}
}
