package hallodoc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/error-page")
	public String showErrorPage(HttpServletRequest request, Model m) {
		m.addAttribute("errorMessage",request.getAttribute("errorMessage"));
		return "admin/authorization-error";
	}
	
}
