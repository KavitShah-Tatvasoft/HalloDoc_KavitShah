package hallodoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hallodoc.dto.CreatePatientRequestDto;
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
	@ResponseBody
	public String addPatientRequest(@ModelAttribute("createPatientRequest") CreatePatientRequestDto createPatientRequestDto ) {
		System.out.println(createPatientRequestDto.toString());
		return "";
	}
	
	@RequestMapping(value= "/isPatientValidByEmail", method = RequestMethod.POST)
	@ResponseBody
	public String isPatientValidByEmail(@RequestParam("name") String email){
		
		String isPresent = patientRequestsService.isPatientAvailable(email);
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
