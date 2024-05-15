package hallodoc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/authorization-error")
	public String showErrorPage(HttpServletRequest request, Model m) {
		return "admin/authorization-error";
	}
	
	
	@RequestMapping("/session-expired")
	public String showSessionError(HttpServletRequest request, Model m) {
		return "admin/session-error";
	}
}
