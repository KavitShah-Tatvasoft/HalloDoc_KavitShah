package com.uninor.controller;

import org.apache.commons.collections4.Get;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUpPage(){
        return "registration/signup";
    }

}
