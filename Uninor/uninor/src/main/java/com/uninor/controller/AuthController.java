package com.uninor.controller;

import com.uninor.dto.*;
import com.uninor.model.Companies;
import com.uninor.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "registration/login";
    }

    @RequestMapping(value = "/admin-login", method = RequestMethod.GET)
    public String adminLoginPage() {
        return "registration/admin-login";
    }


    @RequestMapping(value = "/document-under-verification", method = RequestMethod.GET)
    public String getWaitingPage(){
        return "client/waiting-page";
    }


    @RequestMapping(value = "/registration-page", method = RequestMethod.GET)
    public String registrationPage(HttpServletRequest httpServletRequest, Model model, @ModelAttribute("registrationDataDto") RegistrationDataDto registrationDataDto) {
        List<Companies> companiesList = this.registrationService.getAllCompanyDetails();
        List<SimSuggestionsDto> simSuggestionsDtos = this.registrationService.getNumberSuggestions();
        model.addAttribute("simSuggestions", simSuggestionsDtos);
        model.addAttribute("companyList", companiesList);
        return "registration/registration";
    }

    @ResponseBody
    @RequestMapping(value = "/get-company-details", method = RequestMethod.GET)
    public List<Companies> getCompanyDetails() {
        return this.registrationService.getAllCompanyDetails();
    }


    @ResponseBody
    @RequestMapping(value = "/request-new-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> genereateNewOtp(@Valid @RequestBody SignupRequestDto signupRequestDto) throws MethodArgumentNotValidException, UnsupportedEncodingException {
        return this.registrationService.generateOtp(signupRequestDto);
    }

    @ResponseBody
    @RequestMapping(value = "/get-login-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> generateLoginOtp(@RequestParam("number") String number) {
        return this.registrationService.generateLoginOtp(number);
    }

    @ResponseBody
    @RequestMapping(value = "/get-admin-login-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> generateAdminLoginOtp(@RequestParam("email") String email) {
        return this.registrationService.generateAdminLoginOtp(email);
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

    @Transactional
    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> registerUser(@Valid @ModelAttribute("registrationDataDto") RegistrationDataDto registrationDataDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws MethodArgumentNotValidException {
        if(bindingResult.hasErrors()){
            Method method = ReflectionUtils.findMethod(getClass(), "registerUser", RegistrationDataDto.class, BindingResult.class, HttpServletRequest.class);
            MethodParameter methodParameter = new MethodParameter(method,0);
            throw new MethodArgumentNotValidException(methodParameter,bindingResult);
        }else {
            Map<String,String> responseMap = new HashMap<>();
            return this.registrationService.registerUserDetails(registrationDataDto);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/validate-login-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> validateLoginOtp(@Valid @RequestBody LoginDto loginDto,HttpServletRequest httpServletRequest){
        return this.registrationService.validateLoginOtp(loginDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/validate-admin-login-otp", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> validateAdminLoginOtp(@Valid @RequestBody AdminLoginDto adminloginDto, HttpServletRequest httpServletRequest){
        return this.registrationService.validateAdminLoginOtp(adminloginDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> logoutClient(HttpServletRequest httpServletRequest){
        Map<String,String> responseMap = new HashMap<>();
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        responseMap.put("message","Logout Successful");
        return new ResponseEntity<>(responseMap,new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/re-upload-documents/{token}", method = RequestMethod.GET)
    public String reUploadDocuments(@PathVariable("token") String token, HttpServletRequest httpServletRequest, Model model){
        boolean isExpired = this.registrationService.checkTokenValidity(token);
        if(isExpired){
            return "error-pages/expired-link";
        }else{
            model.addAttribute("token",token);
            return "registration/re-upload-documents";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/resubmit-client-documents", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> resubmitDocuments( @ModelAttribute("resubmitDocumentsDto") ResubmitDocumentsDto resubmitDocumentsDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws MethodArgumentNotValidException {

        BindingResult filteredBindingResult = new BeanPropertyBindingResult(resubmitDocumentsDto, "resubmitDocumentsDto");

        for (FieldError error : bindingResult.getFieldErrors()) {
            if (!isSpecificErrorToRemove(error)) {
                filteredBindingResult.addError(error);
            }
        }

        if(filteredBindingResult.hasErrors()){
            System.out.println("Error Found");
            Method method = ReflectionUtils.findMethod(getClass(), "resubmitDocuments", ResubmitDocumentsDto.class, BindingResult.class, HttpServletRequest.class);
            MethodParameter methodParameter = new MethodParameter(method,0);
            throw new MethodArgumentNotValidException(methodParameter,bindingResult);
        }else {
            return this.registrationService.resubmitClientUploadedDocuments(resubmitDocumentsDto, httpServletRequest);
        }
    }

    private boolean isSpecificErrorToRemove(FieldError error) {
        // Check for errors related to 'aadharCardUploadedFile' and 'panCardUploadedFile'
        String field = error.getField();
        return ("aadharCardUploadedFile".equals(field) || "panCardUploadedFile".equals(field)) &&
                error.getCode().contains("typeMismatch");
    }

    @ResponseBody
    @RequestMapping(value = "/get-resubmit-fields", method = RequestMethod.POST)
    public ResponseEntity<Map<String,ShowResubmitFieldsDto>> getResubmitFields(@RequestParam("token") String token, HttpServletRequest httpServletRequest){
        return this.registrationService.getRequiredResubmitFields(token,httpServletRequest);
    }


}
