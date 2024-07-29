package com.uninor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/error-page")
    public String errorPage() {
        return "error-pages/common-error-page";
    }

    @RequestMapping(value = "/error-404")
    public String error404Page() {
        return "error-pages/404";
    }

    @RequestMapping(value = "/session-expired", method = RequestMethod.GET)
    public String getSessionExpiredPage(){
        return "error-pages/session-expired";
    }

    @RequestMapping(value = "/authorization-error", method = RequestMethod.GET)
    public String authorzationErrorPage(){
        return "error-pages/authorization-error";
    }

}
