package hallodoc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.mapper.RequestNewDataDtoMapper;
import hallodoc.model.EmailLog;
import hallodoc.model.Request;
import hallodoc.service.AdminNewPatientRequestService;
import hallodoc.service.AdminService;
import hallodoc.service.PatientService;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private UserService uService;

	@Autowired
	private AdminNewPatientRequestService adminRequestService;

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
		}

		return redirectView;
	}

}
