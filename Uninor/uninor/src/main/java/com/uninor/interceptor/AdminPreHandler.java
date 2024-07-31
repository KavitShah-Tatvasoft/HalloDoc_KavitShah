package com.uninor.interceptor;

import com.uninor.enumeration.RoleEnum;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.model.Admin;
import com.uninor.model.Users;
import com.uninor.repository.AdminRepository;
import com.uninor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminPreHandler extends HandlerInterceptorAdapter {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Integer clientId = (Integer) request.getSession().getAttribute("clientId");
            Integer adminId = (Integer) request.getSession().getAttribute("adminId");
            if(clientId != null && adminId ==null){
                throw new AuthorizationServiceException("Invalid user");
            }else if(clientId == null && adminId != null){

            }
            else {
                throw new DataNotFoundException("Client not found");
            }

            Admin admin = this.adminRepository.getAdminById(adminId);
            List<Users> usersList = this.userRepository.getUserByEmail(admin.getEmail());
            if(!usersList.isEmpty()){
                Users user = usersList.get(0);
                if(user.getRoleId() != RoleEnum.MASTER_ADMIN.getRoleId()){
                    throw new AuthorizationServiceException("Invalid user");
                }
            }

        }
        catch (DataNotFoundException e){
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath()+"/error/session-expired");
        }
        catch (AuthorizationServiceException e){
            request.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath()+"/error/authorization-error");
        }

        return true;
    }

}
