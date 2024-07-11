package com.uninor.interceptor;

import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.model.Client;
import com.uninor.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginPostHandler extends HandlerInterceptorAdapter {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        try {
            int clientId = (Integer) request.getSession().getAttribute("clientId");
            Client client = this.clientRepository.getClientById(clientId);
            if(!client.isDocValidated()){
                throw new InvalidDataFoundException("Docment verification left");
            }
        }
        catch (InvalidDataFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath()+"/document-under-verification");
        }
    }

}
