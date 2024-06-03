package com.uninor.controller;

import com.uninor.dto.SignUpDataDto;
import com.uninor.dto.SignupRequestDto;
import com.uninor.dto.SimSuggestionsDto;
import com.uninor.model.Companies;
import com.uninor.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUpPage() {
        return "registration/demo-signup";
    }


    @RequestMapping(value = "/registration-page", method = RequestMethod.GET)
    public String registrationPage(HttpServletRequest httpServletRequest, Model model) {
        List<Companies> companiesList = this.registrationService.getAllCompanyDetails();
        model.addAttribute("companies", companiesList);
        return "registration/registration";
    }


    @ResponseBody
    @RequestMapping(value = "/request-new-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> genereateNewOtp(@Valid @RequestBody SignupRequestDto signupRequestDto) throws MethodArgumentNotValidException, UnsupportedEncodingException {
        return this.registrationService.generateOtp(signupRequestDto);
    }

    @ResponseBody
    @RequestMapping(value = "/validate-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> validateOtpForUser(@Valid @RequestBody SignUpDataDto signUpDataDto, HttpServletRequest httpServletRequest) throws MethodArgumentNotValidException,UnsupportedEncodingException {
        return this.registrationService.validateOtp(signUpDataDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-client-data", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getUserData(@NotNull @RequestParam("clientId") String clientId) throws MethodArgumentNotValidException, UnsupportedEncodingException {
        return this.registrationService.getClientData(clientId);
    }

    @ResponseBody
    @RequestMapping(value = "/get-available-number-suggestions", method = RequestMethod.GET)
    public List<SimSuggestionsDto> getMobileNumberSuggestions(){
        return this.registrationService.getNumberSuggestions();
    }

}
