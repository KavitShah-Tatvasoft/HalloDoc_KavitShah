package hallodoc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import hallodoc.dto.PhysicianRequestDataDto;
import hallodoc.model.CaseTag;
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
	public List<PhysicianRequestDataDto> getRequestByPhysician(@RequestParam("status") String status, HttpServletRequest httpServletRequest) {
		return this.physicianService.getPhysicianRequests(status,httpServletRequest);
	}
	
	@RequestMapping(value="/create-provider-new-request")
	public String createNewRequestByProvider() {
		return "provider/provider-create-new-request";
	}
	
	
}
