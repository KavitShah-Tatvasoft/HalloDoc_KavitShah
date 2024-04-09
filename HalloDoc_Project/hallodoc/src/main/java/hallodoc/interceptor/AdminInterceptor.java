package hallodoc.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import hallodoc.exceptions.FailedAuthorizationException;
import hallodoc.model.AspNetUsers;

public class AdminInterceptor extends  HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			System.out.println("In interceptor----------------------------------");
			AspNetUsers aspNetUsers = (AspNetUsers) request.getSession().getAttribute("aspUser");
			if (aspNetUsers == null) {
				throw new FailedAuthorizationException("Session expired! Please log in again to continue");
				
			}else {
				return true;
			}
			
		} catch (FailedAuthorizationException e) {
			System.out.println("In catch of interceptor----------------------------------");
			response.sendRedirect(request.getContextPath()+"/admin/errorPage");
			return false;
		}
		
	}

}
