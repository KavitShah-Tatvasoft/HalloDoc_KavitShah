package com.uninor.interceptor;

import com.uninor.enumeration.RoleEnum;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.model.Client;
import com.uninor.model.Users;
import com.uninor.repository.ClientRepository;
import com.uninor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class ClientPreHandler extends HandlerInterceptorAdapter {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isDeactivation = false;
        try {
            Integer clientId = (Integer) request.getSession().getAttribute("clientId");
            Integer adminId = (Integer) request.getSession().getAttribute("adminId");
            if(clientId == null && adminId !=null){
                throw new AuthorizationServiceException("Invalid user");
            }else if(clientId != null && adminId == null){

            }
            else {
                throw new DataNotFoundException("Client not found");
            }

            Client client = this.clientRepository.getClientById(clientId);
            List<Users> usersList = this.userRepository.getUserByEmail(client.getEmail());
            if(!usersList.isEmpty()){
                Users user = usersList.get(0);
                if(user.getRoleId() != RoleEnum.CLIENT.getRoleId()){
                    throw new AuthorizationServiceException("Invalid user");
                }
            }

            if(!client.isDocValidated()){
                throw new InvalidDataFoundException("Docment verification left");
            }

            if(client.isDeactivationRequestCreated()){
                isDeactivation = true;
                throw new InvalidDataFoundException("Deactivation request created");
            }

        }
        catch (DataNotFoundException e){
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath()+"/error/session-expired");
        }
        catch (InvalidDataFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            if(isDeactivation){
                response.sendRedirect(request.getContextPath()+"/error/deactivation-request");
            }else{
                response.sendRedirect(request.getContextPath()+"/document-under-verification");
            }

        }
        catch (AuthorizationServiceException e){
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath()+"/error/authorization-error");
        }

        return true;
    }
}
