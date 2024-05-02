package hallodoc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.exceptions.FailedAuthorizationException;
import hallodoc.model.AspNetUsers;

public class PhysicianInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
			if (aspNetUsers == null) {
				throw new FailedAuthorizationException("Session expired! Please log in again to continue");	
			}
			else if(aspNetUsers.getUser().getAspNetRoles().getId() != AspNetRolesEnum.PROVIDER.getAspNetRolesId()) {
				throw new FailedAuthorizationException("You are not authorized to access this page");	
			}
			else{
				return true;
			}
			
		} catch (FailedAuthorizationException e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/error/error-page");
			return false;
		}
		
	}
}
