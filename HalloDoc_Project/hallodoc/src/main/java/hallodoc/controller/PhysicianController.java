package hallodoc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.model.AspNetUsers;
import hallodoc.model.CaseTag;
import hallodoc.model.Request;
import hallodoc.service.PhysicianService;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/provider")
public class PhysicianController {

	@Autowired
	UserService userService;

	@Autowired
	PhysicianService physicianService;

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

}
