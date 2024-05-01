package hallodoc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import hallodoc.model.CaseTag;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/provider")
public class PhysicianController {
	
	@Autowired
	UserService uService;

	@RequestMapping("/provider-dashboard")
	public ModelAndView adminDashboard(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("provider/provider-dashboard");
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

	
}
