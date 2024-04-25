package hallodoc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import hallodoc.dto.AdminAddressDto;
import hallodoc.dto.AdminContactDto;
import hallodoc.dto.AdminRegions;
import hallodoc.dto.AssignCaseDto;
import hallodoc.dto.BlockCaseDto;
import hallodoc.dto.CancelCaseDetailsDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.ExportDataDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.UpdateCaseDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.CaseTag;
import hallodoc.model.EmailLog;
import hallodoc.model.Physician;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.service.AdminNewPatientRequestService;
import hallodoc.service.AdminService;
import hallodoc.service.PatientService;
import hallodoc.service.UserService;
import hallodoc.sms.SmsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private UserService uService;

	@Autowired
	private AdminNewPatientRequestService adminRequestService;

	@Autowired
	private SmsService smsService;

	@RequestMapping("/errorPage")
	public String showErrorPage(HttpServletRequest request) {
		request.setAttribute("errorMessage", "Session expired! Please login again to continue.");
		return "admin/authorization-error";
	}

	@ResponseBody
	@PostMapping("/sendLinkByEmail")
	public String submitForm(HttpServletRequest request, SendLinkDto sendLinkDto) {
		System.out.println(sendLinkDto);
		Boolean check = aService.sendRequestLink(request, sendLinkDto);
		return "Success";
	}

	@RequestMapping("/adminDashboard")
	public ModelAndView adminDashboard(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("admin/admin-dashboard");
		List<CaseTag> caseTags = uService.getAllCancellationReasons();
		modelAndView.addObject("cancelReasons", caseTags);

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

	@RequestMapping(value = "/getRequestData", method = RequestMethod.POST)
	@ResponseBody
	public List<NewRequestDataDto> getNewRequestData(@RequestParam("status") String status) {
		System.out.println(status);

		List<NewRequestDataDto> requestsList = aService.getStatusCorrespondingRequests(status);

		return requestsList;

	}

	@RequestMapping(value = "/getStatusWiseCount", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> getStatusRequestCount() {

		List<Integer> countLists = aService.getStatusWiseRequestCount();

		return countLists;

	}

	@ResponseBody
	@RequestMapping(value = "/searchRequestFilter", method = RequestMethod.POST)
	public List<NewRequestDataDto> searchRequestFilter(RequestFiltersDto requestFiltersDto) {

		System.out.println(requestFiltersDto);
		List<NewRequestDataDto> filteredList = uService.getFilteredRequest(requestFiltersDto);
		return filteredList;
	}

	@RequestMapping("/adminCreatePatientRequest")
	public ModelAndView adminCreateRequest(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("admin/admin-create-patient-request");
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

	@RequestMapping(value = "/adminCreatePtRequest", method = RequestMethod.POST)
	public RedirectView adminCreatePtRequest(RedirectAttributes attributes,
			@ModelAttribute("createPatientRequestAdmin") CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest request) throws ParseException {
		System.out.println(createPatientRequestDto);

		String status = adminRequestService.createNewRequest(request, createPatientRequestDto);
		RedirectView redirectView = new RedirectView("adminDashboard", true);

		if (status.equalsIgnoreCase("UserExsist")) {
			attributes.addFlashAttribute("message", "User already exisits");
			attributes.addFlashAttribute("alertType", "faliure");
		} else {
			attributes.addFlashAttribute("message", "Request Created");
			attributes.addFlashAttribute("alertType", "success");
		}

		return redirectView;
	}

	@RequestMapping("/viewCase/{requestId}")
	public String viewCasePatient(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		Request requestOb = uService.getRequestObject(id);
		m.addAttribute("requestOb", requestOb);
		return "common/view-case";
	}

	@ResponseBody
	@RequestMapping(value = "viewCase/updateCaseDetails", method = RequestMethod.POST)
	public String updateCase(UpdateCaseDto updateCaseDto) {
		String status = uService.updateViewCaseDetails(updateCaseDto);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/cancelRequestedCase")
	public String cancelCase(CancelCaseDetailsDto cancelCaseDetailsDto, HttpServletRequest httpServletRequest) {
		System.out.println(cancelCaseDetailsDto);
		Boolean status = uService.cancelRequestedCase(cancelCaseDetailsDto, httpServletRequest);
		return "Cancelled Case";
	}

	@ResponseBody
	@RequestMapping(value = "/blockRequestedCase")
	public String blockCase(BlockCaseDto blockCaseDto, HttpServletRequest httpServletRequest) {
		System.out.println(blockCaseDto);
		Boolean status = uService.blockRequestedCase(blockCaseDto, httpServletRequest);
		return "Blocked Case";
	}

	@RequestMapping(value = "/viewNotes/{requestId}")
	public String viewNotes(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		m.addAttribute("reqId", id);
		return "common/view-notes";
	}

	@ResponseBody
	@RequestMapping(value = "/getViewNotesData", method = RequestMethod.POST)
	public List<ViewNotesDto> getViewNotesData(@RequestParam("reqId") int id) {
		List<ViewNotesDto> viewNotesDtos = uService.getRequestSpecificLogs(id);
		return viewNotesDtos;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAdminNote", method = RequestMethod.POST)
	public String updateAdminNote(@RequestParam("adminNote") String adminNote, @RequestParam("reqId") int id,
			HttpServletRequest httpServletRequest) {
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		uService.updateAdminNote(adminNote, id, aspNetUsers);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/getPhysiciansByRegion", method = RequestMethod.POST)
	public List<PhysicianAssignCaseDto> getPhysicianByRegion(@RequestParam("regionId") int regionId) {
		List<PhysicianAssignCaseDto> physicians = aService.getPhysicianByRegion(regionId);
		return physicians;
	}

	@ResponseBody
	@RequestMapping(value = "/assignPhysician", method = RequestMethod.POST)
	public String assignPhysicianToRequest(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {
		System.out.println(assignCaseDto);

		aService.assignPhysicianToRequest(assignCaseDto, httpServletRequest);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/exportStatusWiseData", method = RequestMethod.POST)
	public ResponseEntity<Resource> exportDataToExcel(ExportDataDto exportDataDto) throws IOException {

		RequestFiltersDto requestFiltersDto = new RequestFiltersDto();
		requestFiltersDto.setPatientName(exportDataDto.getPatientName());
		requestFiltersDto.setRequestType(exportDataDto.getRequestType());
		requestFiltersDto.setStateName(exportDataDto.getStateName());
		requestFiltersDto.setStatusType(exportDataDto.getStatusType());

		List<NewRequestDataDto> requestsList = uService.getFilteredRequest(requestFiltersDto);
		ResponseEntity<Resource> resource = uService.exportDataToExcelSheet(requestsList,
				exportDataDto.getCurrentStatus());
		return resource;
	}

	@ResponseBody
	@RequestMapping(value = "/clearCase", method = RequestMethod.POST)
	public String cancelCase(@RequestParam("reqId") int reqId, HttpServletRequest httpServletRequest) {

		uService.clearRequestedCase(reqId, httpServletRequest);

		return "cleared";
	}

	@ResponseBody
	@RequestMapping(value = "/transferPhysician", method = RequestMethod.POST)
	public String transferRequestedCase(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {

		aService.transferRequestedCase(assignCaseDto, httpServletRequest);

		return "Transferred";
	}

	@ResponseBody
	@RequestMapping(value = "/getSendAgreementData", method = RequestMethod.POST)
	public SendAgreementDto getSendAgreementDetails(@RequestParam("reqId") int reqId,
			HttpServletRequest httpServletRequest) {

		SendAgreementDto sendAgreementDto = uService.getRequiredSendAgreementDetails(reqId, httpServletRequest);
		return sendAgreementDto;

	}

//	@RequestMapping(value = "/sendAgreement")
//	public String sendAgreement() {
//		smsService.sendAgreementSMS();
//		return "Sent";
//	}
//	
	@ResponseBody
	@RequestMapping(value = "/sendAgreementToPatient", method = RequestMethod.POST)
	public String sendAgreementToPatient(SendAgreementDto sendAgreementDto, HttpServletRequest httpServletRequest) {
		System.out.println(sendAgreementDto);
		String status = uService.sendAgreementToPatient(sendAgreementDto, httpServletRequest);
		return status;
	}
	
	@Transactional
	@RequestMapping(value="/adminProfile")
	public String viewAdminProfile(HttpServletRequest httpServletRequest, Model m) {
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
//		List<Region> regions = admin.getRegions();
		List<AdminRegions> adminRegions = aService.getAdminRegions(admin);
 		m.addAttribute("username", aspNetUsers.getUser_name());
		m.addAttribute("adminEmail",aspNetUsers.getEmail());
		m.addAttribute("phoneNumber",aspNetUsers.getPhone_number());
		m.addAttribute("adminOb",admin);
		m.addAttribute("regions",adminRegions);
		return "admin/admin-profile";
	}
	
	@ResponseBody
	@RequestMapping(value="/changeAdminPassword", method = RequestMethod.POST)
	public String changeAdminPassword(@RequestParam("pass") String password, HttpServletRequest httpServletRequest) {
		
		AspNetUsers aspNetUsers = (AspNetUsers)httpServletRequest.getSession().getAttribute("aspUser");
		
		return aService.updateAspUserPassword(aspNetUsers,password,httpServletRequest);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/changeAdminAddress", method = RequestMethod.POST)
	public String changeAdminAddress(AdminAddressDto adminAddressDto, HttpServletRequest httpServletRequest) {
		
		return this.aService.updateAspUserAddress(httpServletRequest,adminAddressDto);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAdminContactDetails",method = RequestMethod.POST )
	public String updateAdminContactDetails(AdminContactDto adminContactDto,HttpServletRequest httpServletRequest) {
		
		return this.aService.updateAdminContactDetails(adminContactDto,httpServletRequest);
		
	}
	

}
