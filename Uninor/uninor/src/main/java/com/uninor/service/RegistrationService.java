package com.uninor.service;

import com.uninor.Email.EmailService;
import com.uninor.dto.SignUpDataDto;
import com.uninor.dto.SignupRequestDto;
import com.uninor.dto.SimSuggestionsDto;
import com.uninor.enumeration.RoleEnum;
import com.uninor.helper.EntityDtoMappers;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.utilities.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OTPLogsRepository otpLogsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CompaniesRepository companiesRepository;

    @Autowired
    private SimCardRepository simCardRepository;

    public ResponseEntity<Map<String,String>> generateOtp(SignupRequestDto signupRequestDto) throws UnsupportedEncodingException {

        signupRequestDto.setEmail(signupRequestDto.getEmail().toLowerCase());
        signupRequestDto.setFname(Helper.capitalize(signupRequestDto.getFname()));
        signupRequestDto.setLname(Helper.capitalize(signupRequestDto.getLname()));
        Users user = null;

        if(!userRepository.getUserByEmail(signupRequestDto.getEmail()).isEmpty()){
            user = userRepository.getUserByEmail(signupRequestDto.getEmail()).get(0);
        }

        OtpLogs otpLogs = new OtpLogs();
        Map<String, String > responseMap = new HashMap<>();
        String randomPin = String.valueOf((int)(Math.random() * 900000) + 100000);

        if( user != null && user.isRegistered()){
            responseMap.put("errors","User already registered");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.CONFLICT);
        }

        if(user != null && !user.isRegistered()) {


            Client client = clientRepository.getUserByEmail(signupRequestDto.getEmail()).get(0);
            user.setDeleted(false);
            user.setRegistered(false);
            user.setEmailAddress(signupRequestDto.getEmail());
            user.setRoleId(RoleEnum.CLIENT.getRoleId());

            client.setFirstName(signupRequestDto.getFname());
            client.setLastName(signupRequestDto.getLname());
            client.setEmail(signupRequestDto.getEmail());
            client.setUser(user);

            clientRepository.updateClient(client);

            otpLogs.setEmail(signupRequestDto.getEmail());
            otpLogs.setOtpCode(randomPin);

            otpLogsRepository.addOTPLog(otpLogs);

            this.emailService.sendOTP(signupRequestDto,randomPin);

            String clientId = String.valueOf(client.getClientId());
            String encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes("utf-8"));

            responseMap.put("messages","An OTP has been sent on your email");
            responseMap.put("clientId", encodedClientId);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }else {

            Users newUser = new Users();
            Client newClient = new Client();

            newUser.setDeleted(false);
            newUser.setRegistered(false);
            newUser.setEmailAddress(signupRequestDto.getEmail());
            newUser.setRoleId(RoleEnum.CLIENT.getRoleId());

            newClient.setFirstName(signupRequestDto.getFname());
            newClient.setLastName(signupRequestDto.getLname());
            newClient.setEmail(signupRequestDto.getEmail());
            newClient.setUser(newUser);

            clientRepository.saveClient(newClient);

            otpLogs.setEmail(signupRequestDto.getEmail());
            otpLogs.setOtpCode(randomPin);

            otpLogsRepository.addOTPLog(otpLogs);
            this.emailService.sendOTP(signupRequestDto,randomPin);

            String clientId = String.valueOf(newClient.getClientId());
            String encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes("utf-8"));

            responseMap.put("messages","An OTP has been sent on your email");
            responseMap.put("clientId", encodedClientId);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }


    }

    public ResponseEntity<Map<String,String>> validateOtp(SignUpDataDto signUpDataDto, HttpServletRequest httpServletRequest){
        List<OtpLogs> otpLogsList = this.otpLogsRepository.getOtpForClient(signUpDataDto.getEmail(), signUpDataDto.getOtp());
        Map<String, String > responseMap = new HashMap<>();
        try {
            OtpLogs otpLogs = otpLogsList.get(0);

            if(LocalDateTime.now().isBefore(otpLogs.getSentDateTime().plusMinutes(5))){
                responseMap.put("messages", "Valid OTP!");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            }else {
                responseMap.put("errors","OTP Expired. Please try again");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            responseMap.put("errors","OTP is not valid.");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    public List<Companies> getAllCompanyDetails(){
        return this.companiesRepository.getCompanyDetails();
    }

    public String decodeClientId(String encodedClientId){
        byte[] decodedClientId = Base64.getDecoder().decode(encodedClientId);
        return new String(decodedClientId);
    }

    public ResponseEntity<Map<String,String>> getClientData(String clientId){

        Map<String, String > responseMap = new HashMap<>();
        try {
            Client client = this.clientRepository.getClientById(Integer.parseInt(decodeClientId(clientId)));
            responseMap.put("fname",client.getFirstName());
            responseMap.put("lname",client.getLastName());
            responseMap.put("email",client.getEmail());
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }catch (Exception e){
            responseMap.put("errors","No such client found. Please signup again");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }


    public List<SimSuggestionsDto> getNumberSuggestions(){

        List<SimCard> simCardSuggestions = this.simCardRepository.getAvailableSimCardSuggestions();
        return EntityDtoMappers.simSuggestionsDtoMapper(simCardSuggestions);

    }

}
