package hallodoc.controller;

import java.awt.PageAttributes.MediaType;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.User;
import hallodoc.service.BusinessRequestService;
import hallodoc.service.ConciergeRequestService;
import hallodoc.service.FamilyFriendRequestService;
import hallodoc.service.PatientRequestsService;

@Controller
public class PatientRequestsController {
	
	@Autowired
	private PatientRequestsService patientRequestsService;
	
	@Autowired
	private FamilyFriendRequestService familyFriendRequestService;
	
	@Autowired
	private ConciergeRequestService conciergeRequestService;
	
	@Autowired
	private BusinessRequestService businessRequestService;
	

	@RequestMapping("/createNewPatientRequest")
	public String createNewPatientRequest() {
		return "/patient/create-patient-request";
	}
	
	@PostMapping(path = "/addPatientRequest")
	public String addPatientRequest(@ModelAttribute("createPatientRequest") CreatePatientRequestDto createPatientRequestDto, HttpSession session, HttpServletRequest httpServletRequest ) throws Exception {
		System.out.println(createPatientRequestDto.toString());
		String isExistingPatient = createPatientRequestDto.getIsExsistingPatient();
		System.out.println(isExistingPatient);
		
		if(isExistingPatient.equals("true")) {
			
			boolean status = patientRequestsService.addRequestForExsitingPatient(createPatientRequestDto, session);
			System.out.println("In existing patient");
			return "true";
		}else
		{
			System.out.println("In new patient");
			boolean status = patientRequestsService.addRequestForNewPatient(createPatientRequestDto, session);
			System.out.println(status);
			return "true";
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
	
	@RequestMapping(value="/createNewFamilyFriendRequest", method = RequestMethod.GET)
	public String createNewFamilyRequest() {
		
		return "/patient/familyfriend-request";
	}
	

	@RequestMapping(value = "/createNewFamilyRequest", method = RequestMethod.POST)
	public String createNewFamilyRequest(@ModelAttribute("createFamilyRequest") CommonRequestDto commonRequestDto, HttpSession session, HttpServletRequest httpServletRequest ) throws Exception{
		System.out.println(commonRequestDto);
		boolean status = familyFriendRequestService.createNewFamilyFriendRequest(commonRequestDto, session, httpServletRequest);
		System.out.println(status);
		return "";
	}
	
	@RequestMapping("/createNewConciergeRequest")
	public String createNewConciergeRequest() {
		
		return "/patient/concierge-request";
	}
	
	@RequestMapping(value="/addNewConciergeRequest", method = RequestMethod.POST)
	public String addNewConciergeRequest(@ModelAttribute("createConciergeRequest") CommonRequestDto commonRequestDto, HttpSession session, HttpServletRequest httpServletRequest ) throws Exception{
		System.out.println(commonRequestDto);
		boolean status = conciergeRequestService.createConciregeRequest(commonRequestDto, session, httpServletRequest);
		System.out.println(status);
		return "";
	}
	
	@RequestMapping("/createNewBusinessRequest")
	public String createNewBusinessRequest() {
		return "/patient/business-request";
	}
	
	@RequestMapping(value="/addBusinessRequest", method = RequestMethod.POST)
	public String addNewBusinessRequest(@ModelAttribute("createConciergeRequest") CommonRequestDto commonRequestDto, HttpSession session, HttpServletRequest httpServletRequest ) throws Exception{
		System.out.println(commonRequestDto);
		boolean status = businessRequestService.createBusinessRequest(commonRequestDto, session, httpServletRequest);
		System.out.println(status);
		return "";
	}
}
