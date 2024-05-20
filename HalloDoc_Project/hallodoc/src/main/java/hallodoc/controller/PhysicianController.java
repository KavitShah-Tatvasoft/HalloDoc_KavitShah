package hallodoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.CreateShiftDto;
import hallodoc.dto.EditShiftDetailsDto;
import hallodoc.dto.EditShiftDto;
import hallodoc.dto.EventsDto;
import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.dto.PhysicianResources;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.ShowProviderDetailsDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.helper.Constants;
import hallodoc.model.AspNetUsers;
import hallodoc.model.CaseTag;
import hallodoc.model.Physician;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.User;
import hallodoc.service.AdminService;
import hallodoc.service.PhysicianService;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/provider")
public class PhysicianController {

	@Autowired
	private UserService userService;

	@Autowired
	private PhysicianService physicianService;

	@Autowired
	private AdminService adminService;

	@RequestMapping("/provider-dashboard")
	public ModelAndView adminDashboard(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("provider/provider-dashboard");
		List<CaseTag> caseTags = userService.getAllCancellationReasons();
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

	@ResponseBody
	@RequestMapping(value = "/get-request-by-physician", method = RequestMethod.POST)
	public List<PhysicianRequestDataDto> getRequestByPhysician(@RequestParam("status") String status,
			HttpServletRequest httpServletRequest) {
		return this.physicianService.getPhysicianRequests(status, httpServletRequest);
	}

	@RequestMapping(value = "/create-provider-new-request")
	public String createNewRequestByProvider() {
		return "provider/provider-create-new-request";
	}

	@ResponseBody
	@RequestMapping(value = "/get-status-wise-physician-request-count")
	public List<Integer> getPhysicianRequestWiseCount(HttpServletRequest httpServletRequest) {
		return this.physicianService.getStatusWiseCount(httpServletRequest);
	}

	@ResponseBody
	@RequestMapping(value = "/accept-case", method = RequestMethod.POST)
	public String acceptCase(@RequestParam("reqId") int reqId, HttpServletRequest httpServeltRequest) {
		return this.physicianService.acceptCase(reqId, httpServeltRequest);
	}

	@ResponseBody
	@RequestMapping(value = "/transfer-case-admin", method = RequestMethod.POST)
	public String transferCase(HttpServletRequest httpServletRequest, @RequestParam("description") String description,
			@RequestParam("reqId") Integer reqId) {
		return this.physicianService.transferCaseToAdmin(httpServletRequest, description, reqId);

	}

	@RequestMapping("/viewCase/{requestId}")
	public String viewCasePatient(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		Request requestOb = userService.getRequestObject(id);
		m.addAttribute("requestOb", requestOb);
		return "common/view-case";
	}

	@RequestMapping(value = "/viewNotes/{requestId}")
	public String viewNotes(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		m.addAttribute("reqId", id);
		return "common/view-notes";
	}

	@ResponseBody
	@RequestMapping(value = "/getViewNotesData", method = RequestMethod.POST)
	public List<ViewNotesDto> getViewNotesData(@RequestParam("reqId") int id) {
		List<ViewNotesDto> viewNotesDtos = userService.getRequestSpecificLogs(id);
		return viewNotesDtos;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAdminNote", method = RequestMethod.POST)
	public String updateAdminNote(@RequestParam("adminNote") String adminNote, @RequestParam("reqId") int id,
			HttpServletRequest httpServletRequest) {
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		userService.updateAdminNote(adminNote, id, aspNetUsers);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/getSendAgreementData", method = RequestMethod.POST)
	public SendAgreementDto getSendAgreementDetails(@RequestParam("reqId") int reqId,
			HttpServletRequest httpServletRequest) {

		SendAgreementDto sendAgreementDto = userService.getRequiredSendAgreementDetails(reqId, httpServletRequest);
		return sendAgreementDto;

	}

	@ResponseBody
	@RequestMapping(value = "/sendAgreementToPatient", method = RequestMethod.POST)
	public String sendAgreementToPatient(SendAgreementDto sendAgreementDto, HttpServletRequest httpServletRequest) {

		String status = userService.sendAgreementToPatient(sendAgreementDto, httpServletRequest);
		return status;
	}

	@ResponseBody
	@RequestMapping(value = "set-call-type", method = RequestMethod.POST)
	public String setCallType(@RequestParam("reqId") int reqId, @RequestParam("callType") int callType,
			HttpServletRequest httpServletRequest) {
		return this.physicianService.changeCallType(reqId, callType, httpServletRequest);

	}

	@ResponseBody
	@RequestMapping(value = "/conclude-requested-case", method = RequestMethod.POST)
	public String concludeCase(@RequestParam("reqId") int reqId, HttpServletRequest httpServletRequest) {
		return this.physicianService.concludeCaseByReqId(reqId, httpServletRequest);
	}

	@RequestMapping(value = "/conclude-care/{reqId}", method = RequestMethod.GET)
	public String concludeCare(@PathVariable("reqId") int reqId, Model m, HttpServletRequest httpServletRequest) {
		m.addAttribute("reqId", reqId);
		Request request = userService.getRequestById(reqId);

		if (request.getRequestNotes() == null) {
			m.addAttribute("physicianNotes", "-");
		} else {
			m.addAttribute("physicianNotes", request.getRequestNotes().getPhysicanNotes());
		}

		if (request.getEncounterForm() == null) {
			m.addAttribute("isFinalized", false);
		} else {
			m.addAttribute("isFinalized", request.getEncounterForm().isFinalized());
		}

		m.addAttribute("ptName",
				request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber());
		List<RequestDocumentsDto> requests = userService.getRequestDocuments(reqId);
		m.addAttribute("docList", requests);
		AspNetUsers aspNetUsersOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		User userOb = aspNetUsersOb.getUser();

		m.addAttribute("userOb", userOb);

		return "provider/conclude-care";
	}

	@RequestMapping(value = "/upload-files-conclude-care", method = RequestMethod.POST)
	public RedirectView uploadConludeCareFiles(@RequestParam("fileupload") CommonsMultipartFile uploadFile,
			@RequestParam("reqId") int reqId) {
		System.out.println(reqId);
		return new RedirectView("conclude-care/" + reqId);
	}

	@ResponseBody
	@RequestMapping(value = "/softDeleteUploadedFile", method = RequestMethod.POST)
	public String deleteUploadedFile(@RequestParam("fileId") int fileId) {

		userService.softDeleteRequestedFile(fileId);
		return "deleted";
	}

	@RequestMapping(value = "/close-case-by-physician/{reqId}", method = RequestMethod.GET)
	public RedirectView concludeCareByPhysician(@PathVariable("reqId") int reqId,
			HttpServletRequest httpServletRequest) {
		this.userService.concludeCare(reqId, httpServletRequest);
		return new RedirectView("../provider-dashboard");
	}

	@RequestMapping(value = "/provider-profile", method = RequestMethod.GET)
	public String viewProviderProfile(HttpServletRequest httpServletRequest, Model m) {

		int physicianId = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician()
				.getPhysicianId();

		String prefix = Constants.CONTEXT_PATH;
		String uploadPath = String.format("%s/%s/%s/%s/%d", prefix, "resources", "fileuploads", "provider",
				physicianId);

		ShowProviderDetailsDto providerData = this.adminService.getProviderDetails(physicianId);
		m.addAttribute("uploadPath", uploadPath);
		m.addAttribute("providerData", providerData);
		return "provider/provider-my-profile";
	}

	@ResponseBody
	@RequestMapping(value = "/change-provider-password", method = RequestMethod.POST)
	public String changeProviderPassword(@RequestParam("pass") String pass, HttpServletRequest httpServletRequest) {
		AspNetUsers physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser"));
		return this.physicianService.changePassword(physician, pass);

	}

	@ResponseBody
	@RequestMapping(value = "/send-request-to-admin", method = RequestMethod.POST)
	public String sendRequestToAdmin(@RequestParam("desc") String description, HttpServletRequest httpServletRequest) {
		return this.physicianService.sendRequestToAdmin(httpServletRequest, description);
	}

	@ResponseBody
	@RequestMapping(value = "/get-physician-request-filtered-data", method = RequestMethod.POST)
	public List<PhysicianRequestDataDto> getFilteredPhysicianRequest(RequestFiltersDto requestFiltersDto,
			HttpServletRequest httpServletRequest) {
		return this.physicianService.getFilteredPhysicianRequests(requestFiltersDto, httpServletRequest);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public RedirectView logoutUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		RedirectView redirectView = new RedirectView(request.getServletContext().getContextPath() + "/patient_login",
				false);
		return redirectView;

	}

	@RequestMapping(value = "/provider-schedule", method = RequestMethod.GET)
	public String providerSchedule(Model m, HttpServletRequest httpServletRequest) {
		Physician physician = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getPhysician();
		List<Region> physicianRegion = this.physicianService.getPhysicianRegion(physician.getPhysicianId());
		m.addAttribute("phyRegion", physicianRegion);
		m.addAttribute("physicianName", "Dr. " + physician.getFirstName() + " " + physician.getLastName());
		m.addAttribute("physicianId", physician.getPhysicianId());
		return "provider/provider-schedule";
	}

	@ResponseBody
	@RequestMapping(value = "/get-physician-details-scheduling", method = RequestMethod.POST)
	public List<PhysicianResources> getPhysicianDetails(HttpServletRequest httpServletRequest,
			@RequestParam("regionId") int regionId) {
		return this.adminService.getAllPhysicianDetails(httpServletRequest, regionId);
	}

	@ResponseBody
	@RequestMapping(value = "/get-physician-events", method = RequestMethod.POST)
	public List<EventsDto> getEventsData(@RequestParam("regionId") int regionId,
			HttpServletRequest httpServletRequest) {
		return this.physicianService.getAllActivePhysicianEvents(regionId, httpServletRequest);
	}

	@ResponseBody
	@RequestMapping(value = "/create-shift", method = RequestMethod.POST)
	public boolean createShift(CreateShiftDto createShiftDto, HttpServletRequest httpServletRequest) {
		return this.physicianService.createNewShift(createShiftDto, httpServletRequest);
	}

	@ResponseBody
	@RequestMapping(value = "/get-event-details", method = RequestMethod.POST)
	public EditShiftDto getEventDetails(@RequestParam("eventId") int eventId) {
		return this.physicianService.getEventDetails(eventId);
	}

	@ResponseBody
	@RequestMapping(value = "/edit-old-shift-details", method = RequestMethod.POST)
	public boolean editRequestedShift(EditShiftDetailsDto editShiftDetailsDto, HttpServletRequest httpServletRequest) {
		return this.physicianService.editShiftDetails(editShiftDetailsDto, httpServletRequest);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete-shift",method=RequestMethod.POST)
	public String deleteShift(@RequestParam("shiftDetailId") int shiftDetailId) {
		return this.physicianService.deleteShift(shiftDetailId);
	}
	
	@ResponseBody
	@RequestMapping(value="/download-encounter-form", method = RequestMethod.POST)
	public String downloadPdf(@RequestParam("reqId") int reqId, HttpServletRequest httpServletRequest) throws IOException{
		return this.physicianService.creatingPdf(reqId, httpServletRequest);
	}

}
