package hallodoc.controller;

import java.awt.PageAttributes.MediaType;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.CommonRequestDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.SomeoneElseRequestDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.User;
import hallodoc.service.BusinessRequestService;
import hallodoc.service.ConciergeRequestService;
import hallodoc.service.FamilyFriendRequestService;
import hallodoc.service.PatientRequestsService;
import hallodoc.service.PatientService;
import hallodoc.service.RegisteredPatientOthersRequestService;
import hallodoc.service.RegisteredPatientSelfRequestService;

@Controller
public class PatientRequestsController {

	@Autowired
	private PatientRequestsService patientRequestsService;

	@Autowired
	private PatientService pService;

	@Autowired
	private FamilyFriendRequestService familyFriendRequestService;

	@Autowired
	private ConciergeRequestService conciergeRequestService;

	@Autowired
	private BusinessRequestService businessRequestService;

	@Autowired
	private RegisteredPatientSelfRequestService registeredPatientSelfRequestService;

	@Autowired
	private RegisteredPatientOthersRequestService registeredPatientOthersRequestService;

	@RequestMapping("/createNewPatientRequest")
	public String createNewPatientRequest() {
		return "/patient/create-patient-request";
	}

	@PostMapping(path = "/addPatientRequest")
	public RedirectView addPatientRequest(
			@ModelAttribute("createPatientRequest") CreatePatientRequestDto createPatientRequestDto,
			HttpSession session, HttpServletRequest httpServletRequest) throws Exception {
		 ;
		String isExistingPatient = createPatientRequestDto.getIsExsistingPatient();
		 ;

		if (isExistingPatient.equals("true")) {

			boolean status = patientRequestsService.addRequestForExsitingPatient(createPatientRequestDto, session);
			 ;
		} else {
			 ;
			boolean status = patientRequestsService.addRequestForNewPatient(createPatientRequestDto, session);
			 ;
		}

		RedirectView redirectView = new RedirectView("/patientHome", true);
		return redirectView;
	}

	@RequestMapping(value = "/isPatientValidByEmail", method = RequestMethod.POST)
	@ResponseBody
	public String isPatientValidByEmail(@RequestParam("name") String email) {

		String isPresent = patientRequestsService.isPatientAvailable(email);
		return isPresent;
	}

	@RequestMapping(value = "/isPatientStateValid", method = RequestMethod.POST)
	@ResponseBody
	public String isPatientStateValid(@RequestParam("state") String state) {
		String formattedState = state.substring(0, 1).toUpperCase() + state.substring(1);
		String isPresent = patientRequestsService.isStateAvailable(state);
		return isPresent;
	}

	@RequestMapping(value = "/createNewFamilyFriendRequest", method = RequestMethod.GET)
	public String createNewFamilyRequest() {

		return "/patient/familyfriend-request";
	}

	@RequestMapping(value = "/createNewFamilyRequest", method = RequestMethod.POST)
	public RedirectView createNewFamilyRequest(@ModelAttribute("createFamilyRequest") CommonRequestDto commonRequestDto,
			HttpSession session, HttpServletRequest httpServletRequest) throws Exception {
		 ;
		boolean status = familyFriendRequestService.createNewFamilyFriendRequest(commonRequestDto, session,
				httpServletRequest);
		 ;
		RedirectView redirectView = new RedirectView("/patientHome", true);
		return redirectView;
	}

	@RequestMapping("/createNewConciergeRequest")
	public String createNewConciergeRequest() {

		return "/patient/concierge-request";
	}

	@RequestMapping(value = "/addNewConciergeRequest", method = RequestMethod.POST)
	public RedirectView addNewConciergeRequest(
			@ModelAttribute("createConciergeRequest") CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws Exception {
		 ;
		boolean status = conciergeRequestService.createConciregeRequest(commonRequestDto, session, httpServletRequest);
		 ;
		RedirectView redirectView = new RedirectView("/patientHome", true);
		return redirectView;
	}

	@RequestMapping("/createNewBusinessRequest")
	public String createNewBusinessRequest() {
		return "/patient/business-request";
	}

	@RequestMapping(value = "/addBusinessRequest", method = RequestMethod.POST)
	public RedirectView addNewBusinessRequest(
			@ModelAttribute("createConciergeRequest") CommonRequestDto commonRequestDto, HttpSession session,
			HttpServletRequest httpServletRequest) throws Exception {
		 ;
		boolean status = businessRequestService.createBusinessRequest(commonRequestDto, session, httpServletRequest);
		 ;
		RedirectView redirectView = new RedirectView("/patientHome", true);
		return redirectView;
	}

	@RequestMapping(value = "/registeredPatientMeRequest")
	public String registeredPatientMeRequest(HttpServletRequest request, Model m) {

		List<Region> regionList = pService.getAllRegions();
		m.addAttribute("regions", regionList);
		try {
			AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
			User user = aspNetUsers.getUser();
			m.addAttribute("userOb", user);
		} catch (Exception e) {
			 ;
		}

		return "/patient/submit-information-me";
	}

	@RequestMapping(value = "/createReqisteredPatientRequestForMe", method = RequestMethod.POST)
	public RedirectView createReqisteredPatientRequestForMe(RedirectAttributes attributes,
			@ModelAttribute("creteRequestMe") CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest request, Model m) throws ParseException {
		 ;
		String createStatus = registeredPatientSelfRequestService.createNewSelfRequest(createPatientRequestDto,
				request);
		 ;
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		RedirectView redirectView = new RedirectView("/patientDashboard", true);

		attributes.addFlashAttribute("message", "Request Succesfully Created");
		attributes.addFlashAttribute("alertType", "success");
		return redirectView;
	}

	@RequestMapping(value = "/registeredPatientOthersRequest")
	public String registeredPatientOthersRequest(HttpServletRequest request, Model m) {

		List<Region> regionList = pService.getAllRegions();
		m.addAttribute("regions", regionList);

		try {
			AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
			User user = aspNetUsers.getUser();
			m.addAttribute("userOb", user);
		} catch (Exception e) {
			 ;
		}

		return "/patient/submit-information-others";
	}

	@RequestMapping(value = "/registeredPatientCreateOthersRequest", method = RequestMethod.POST)
	public RedirectView createReqisteredPatientRequestForMe(RedirectAttributes attributes,
			@ModelAttribute("creteRequestOthers") SomeoneElseRequestDto someoneElseRequestDto,
			HttpServletRequest request, Model m) throws ParseException {
		 ;
		String createStatus = registeredPatientOthersRequestService.createOthersRequest(someoneElseRequestDto, request);
		 ;
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		RedirectView redirectView = new RedirectView("/patientDashboard", true);
		attributes.addFlashAttribute("message", "Request Succesfully Created");
		attributes.addFlashAttribute("alertType", "success");
		return redirectView;

	}
}
