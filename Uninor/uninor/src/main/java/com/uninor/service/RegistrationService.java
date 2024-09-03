package com.uninor.service;

import com.uninor.Email.EmailService;
import com.uninor.dto.*;
import com.uninor.enumeration.*;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.exceptions.InvalidFileException;
import com.uninor.exceptions.SimNotAvailableException;
import com.uninor.helper.EntityDtoMappers;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.helper.Helper;
import com.uninor.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private SmsService smsService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ReuploadEmailLogRepository reuploadEmailLogRepository;

    @Autowired
    private ClientDocumentsRepository clientDocumentsRepository;

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private DailyUsageRepository dailyUsageRepository;


    public ResponseEntity<Map<String, String>> generateOtp(SignupRequestDto signupRequestDto) throws UnsupportedEncodingException {

        signupRequestDto.setEmail(signupRequestDto.getEmail());
        signupRequestDto.setFname(signupRequestDto.getFname());
        signupRequestDto.setLname(signupRequestDto.getLname());
        Users user = null;
        List<Users> usersList = null;
        if (!userRepository.getUserByEmail(signupRequestDto.getEmail()).isEmpty()) {
            usersList = userRepository.getUserByEmail(signupRequestDto.getEmail());
            if (usersList.isEmpty()) {
                throw new DataNotFoundException("User Data not found");
            }
            user = usersList.get(0);
        }


        OtpLogs otpLogs = new OtpLogs();
        Map<String, String> responseMap = new HashMap<>();
        String randomPin = String.valueOf((int) (Math.random() * 900000) + 100000);

        if (user != null && user.isRegistered()) {
            responseMap.put("errors", "User already registered");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.CONFLICT);
        }

//        Client client = this.clientRepository.getClientByUserId(user.getUserId());
//        if(!client.isDocValidated()){
//            responseMap.put("docError", "User document not yet verified");
//            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.CONFLICT);
//        }

        if (user != null && !user.isRegistered()) {

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
            otpLogs.setExpired(false);
            otpLogsRepository.addOTPLog(otpLogs);

            this.emailService.sendOTP(signupRequestDto, randomPin);

            String clientId = String.valueOf(client.getClientId());
            String encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes("utf-8"));

            responseMap.put("messages", "An OTP has been sent on your email");
            responseMap.put("clientId", encodedClientId);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        } else {

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
            newClient.setValidationAttemptsOver(false);
            newClient.setDeactivationRequestCreated(false);
            clientRepository.saveClient(newClient);

            otpLogs.setEmail(signupRequestDto.getEmail());
            otpLogs.setOtpCode(randomPin);

            otpLogsRepository.addOTPLog(otpLogs);
            this.emailService.sendOTP(signupRequestDto, randomPin);

            String clientId = String.valueOf(newClient.getClientId());
            String encodedClientId = Base64.getEncoder().encodeToString(clientId.getBytes("utf-8"));

            responseMap.put("messages", "An OTP has been sent on your email");
            responseMap.put("clientId", encodedClientId);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }


    }

    public ResponseEntity<Map<String, String>> validateOtp(SignUpDataDto signUpDataDto, HttpServletRequest httpServletRequest) {
        List<OtpLogs> otpLogsList = this.otpLogsRepository.getOtpForClient(signUpDataDto.getEmail());
        Map<String, String> responseMap = new HashMap<>();
        try {
            OtpLogs otpLogs = otpLogsList.get(0);

            if (LocalDateTime.now().isBefore(otpLogs.getSentDateTime().plusMinutes(5)) ) {
                if (!otpLogs.getOtpCode().equals(signUpDataDto.getOtp())) {
                    responseMap.put("errors", "OTP is not valid.");
                    return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
                responseMap.put("messages", "Valid OTP!");
                otpLogs.setExpired(true);
                this.otpLogsRepository.updateOtpLog(otpLogs);
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            } else {
                responseMap.put("errors", "OTP Expired. Please try again");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
            }

        } catch (IndexOutOfBoundsException ex) {
            responseMap.put("errors", "OTP is not valid!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    public List<Companies> getAllCompanyDetails() {
        return this.companiesRepository.getCompanyDetails();
    }

    public String decodeClientId(String encodedClientId) {
        byte[] decodedClientId = Base64.getDecoder().decode(encodedClientId);
        return new String(decodedClientId);
    }

    public ResponseEntity<Map<String, String>> getClientData(String clientId) {

        Map<String, String> responseMap = new HashMap<>();
        if (clientId.isEmpty()) {
            throw new DataNotFoundException("Client not found! Please signup and try again!");
        }
        try {
            Client client = this.clientRepository.getClientById(Integer.parseInt(decodeClientId(clientId)));
            if (client == null) {
                throw new DataNotFoundException("Client not found! Please signup and try again!");
            }
            responseMap.put("fname", client.getFirstName());
            responseMap.put("lname", client.getLastName());
            responseMap.put("email", client.getEmail());
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        } catch (DataNotFoundException ex) {
            responseMap.put("errors", ex.getMessage());
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }


    public List<SimSuggestionsDto> getNumberSuggestions() {

        List<SimCard> simCardSuggestions = this.simCardRepository.getAvailableSimCardSuggestions();
        return EntityDtoMappers.simSuggestionsDtoMapper(simCardSuggestions);

    }

    private Client updateUserDetails(RegistrationDataDto registrationDataDto) {

        try {
            Client client = this.clientRepository.getClientById(Integer.parseInt(decodeClientId(registrationDataDto.getClientId())));
            if (client == null) {
                throw new DataNotFoundException("No such client found. Please signup and try again!");
            }
            Users user = client.getUser();
            user.setRegistered(true);

            client.setUser(user);
            client.setCity(registrationDataDto.getCity());
            client.setEmail(registrationDataDto.getEmail());
            client.setDeleted(false);
            client.setFirstName(registrationDataDto.getFname());
            client.setLastName(registrationDataDto.getLname());
            LocalDate dob = Helper.parseToLocalDate(registrationDataDto.getDob());
            client.setDateOfBirth(dob);
            client.setDocValidated(false);
            if (!registrationDataDto.getGSTNumber().isEmpty()) {
                client.setGstNumber(registrationDataDto.getGSTNumber());
            }
            client.setAadharNumber(registrationDataDto.getAadharCardNumber());
            client.setPanNumber(registrationDataDto.getPanNumber());
            client.setStreet(registrationDataDto.getStreet());
            client.setState(registrationDataDto.getState());
            client.setCity(registrationDataDto.getCity());
            client.setZipcode(registrationDataDto.getZipcode());
            client.setValidationAttempts(0);
            client.setWalletAmount(0.0);

            return client;
        } catch (DataNotFoundException ex) {
            throw new DataNotFoundException(ex.getMessage());
        }

    }

    private static String calculateCheckDigit(String imei) {
        int sum = 0;
        for (int i = imei.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(imei.charAt(i)));
            if ((i % 2) == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return String.valueOf(checkDigit);
    }

    private String getImeiNumber() {
        Random random = new Random();
        String imei = "350091"; // India's MCC (Mobile Country Code)
        imei += String.format("%02d", random.nextInt(10));
        imei += String.format("%06d", random.nextInt(1000000));
        imei += calculateCheckDigit(imei);
        return imei;
    }

    private String getPUKCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    private SimCard addNewSimDetails(RegistrationDataDto registrationDataDto, Client client) {
        List<SimCard> availableSimCard = this.simCardRepository.getSimCardDetailsByNumber(registrationDataDto.getMobileNumber());
        if (!availableSimCard.isEmpty()) {
            throw new SimNotAvailableException("Number not available for port! Please contact admin for further queries!");
        }
        SimCard simCard = new SimCard();
        simCard.setClient(client);
        simCard.setSimAcquiredType(SimAccquireTypeEnum.PORT.getSimAccquireTypeId());
        simCard.setPhoneNumber(registrationDataDto.getMobileNumber());
        simCard.setIccidNumber(getIccidNumber());
        simCard.setImsiNumber(getImsiNumber());
        simCard.setImeiNumber(getImeiNumber());
        if (registrationDataDto.getSimType() == 1) {
            simCard.setSimType(SimType.PREPAID.getSimCardTypeId());
        } else {
            simCard.setSimType(SimType.POSTPAID.getSimCardTypeId());
        }

        simCard.setPukNumber(getPUKCode());
        simCard.setAvailable(false);
        simCard.setStatus(false);
        simCard.setBlocked(false);
        simCard.setRoamingActive(false);
        simCard.setPlanActive(false);
        return simCard;
    }

    private static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    private String getIccidNumber() {
        String random = generateRandomNumber(14);
        return "8991" + random + "41";
    }

    private String getImsiNumber() {
        String random = generateRandomNumber(10);
        return "08991" + random;
    }

    private String uploadDocuments(CommonsMultipartFile file, int clientId, String filename) {
        try {
            String path = System.getenv("UninorUploadPath");
            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
            String fullPath = path + clientId + "/" + filename + "." + fileExtension;

            byte[] data = file.getBytes();
            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(data);
            fos.close();
            return fileExtension;
        } catch (Exception e) {
            throw new InvalidFileException("File Upload Failed. Try again!");
        }
    }


    public ResponseEntity<Map<String, String>> registerUserDetails(RegistrationDataDto registrationDataDto) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            int requestType = registrationDataDto.getSelector();
            Client client = updateUserDetails(registrationDataDto);
            SimCard simCard;
            if (requestType == 1) {
                Companies companies = this.companiesRepository.getCompanyDetails(registrationDataDto.getCompanyId());
                this.emailService.portNumberEmail(companies.getCompanyEmail(), companies.getCompanyName(), registrationDataDto.getMobileNumber());
                simCard = addNewSimDetails(registrationDataDto, client);
            } else {
                simCard = this.simCardRepository.getSimCardById(registrationDataDto.getMobileId());
                if (simCard == null) {
                    throw new DataNotFoundException("No SIM Card found! Try again.");
                }
                simCard.setSimAcquiredType(SimAccquireTypeEnum.EXISTING.getSimAccquireTypeId());
                simCard.setClient(client);
                simCard.setAvailable(false);
                if (registrationDataDto.getSimType() == 1) {
                    simCard.setSimType(SimType.PREPAID.getSimCardTypeId());
                } else {
                    simCard.setSimType(SimType.POSTPAID.getSimCardTypeId());
                }

            }

            String createDirectoryPath = System.getenv("UninorUploadPath") + client.getClientId();

            File f1 = new File(createDirectoryPath);
            if (!f1.exists()) {
                f1.mkdirs();
            }


            CommonsMultipartFile photo = registrationDataDto.getProfilePicUploadedFile();
            CommonsMultipartFile panCardFile = registrationDataDto.getPanCardUploadedFile();
            CommonsMultipartFile aadharCardFile = registrationDataDto.getAadharCardUploadedFile();

            String photoExtension = uploadDocuments(photo, client.getClientId(), "ProfilePhoto");
            String panCardExtension = uploadDocuments(panCardFile, client.getClientId(), "PANCard");
            String aadharCardExtension = uploadDocuments(aadharCardFile, client.getClientId(), "AadharCard");

            if(client.isValidationAttemptsOver()){
                this.clientDocumentsRepository.deleteClientDocuments(client.getClientId());
                client.setValidationAttemptsOver(false);
            }

            ClientDocuments clientDocuments = new ClientDocuments();
            clientDocuments.setClient(client);
            clientDocuments.setAadharCardExtension(aadharCardExtension);
            clientDocuments.setPanCardExtension(panCardExtension);
            clientDocuments.setProfilePhotoExtension(photoExtension);
            clientDocuments.setAadharCardVerified(false);
            clientDocuments.setPanCardVerified(false);
            client.setClientDocuments(clientDocuments);

            ClientRequest clientRequest = new ClientRequest();
            clientRequest.setRequestStatus(RequestStatusEnum.PENDING.getRequestStatusId());
            clientRequest.setRequestType(ClientRequestTypeEnum.ACTIVATION.getRequestTypeId());
            clientRequest.setModifiedByAdmin(0);
            clientRequest.setSimCard(simCard);

            this.clientRequestRepository.saveClientRequest(clientRequest);
            this.clientRepository.updateClient(client);
            this.simCardRepository.addOrUpdateSimCard(simCard);


        } catch (DataNotFoundException ex) {
            responseMap.put("errors", ex.getMessage());
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        this.emailService.registrationEmail(registrationDataDto.getEmail(), registrationDataDto.getFname() + " " + registrationDataDto.getLname(), registrationDataDto.getMobileNumber());
        responseMap.put("messages", "User registered successfully!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> generateAdminLoginOtp(String email) {
        Map<String, String> responseMap = new HashMap<>();
        String pattern = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";

        if (!email.matches(pattern)) {
            responseMap.put("errors", "Please enter a valid email address!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<Users> usersList = this.userRepository.getUserByEmail(email.toLowerCase());
        if (usersList.isEmpty()) {
            responseMap.put("errors", "Email Address not registered! Please enter a valid email address!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        String randomPin = String.valueOf((int) (Math.random() * 900000) + 100000);

        OtpLogs otpLogs = new OtpLogs();
        otpLogs.setEmail(email);
        otpLogs.setOtpCode(randomPin);
        otpLogs.setExpired(false);
        this.otpLogsRepository.addOTPLog(otpLogs);
        this.emailService.sendLoginOTP(randomPin, email);

        responseMap.put("messages", "An OTP has been sent on your registered email!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);

    }

    public ResponseEntity<Map<String, String>> generateLoginOtp(String number) {

        Map<String, String> responseMap = new HashMap<>();
        String pattern = "^[1-9][0-9]{9}$";

//        if (!client.isDocValidated()) {
//            responseMap.put("docError", "User document not yet verified");
//            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.CONFLICT);
//        }

        if (!number.matches(pattern)) {
            responseMap.put("errors", "Invalid Mobile Number!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<SimCard> simCard = this.simCardRepository.getClientSimCardDetailsByNumber(number);
        if (simCard.isEmpty()) {
            responseMap.put("errors", "Mobile Number not registered! Please use a valid number!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        String randomPin = String.valueOf((int) (Math.random() * 900000) + 100000);

        OtpLogs otpLogs = new OtpLogs();
        otpLogs.setMobileNumber(number);
        otpLogs.setOtpCode(randomPin);
        otpLogs.setExpired(false);
        this.otpLogsRepository.addOTPLog(otpLogs);
        this.smsService.sendLoginOtpSms(number, randomPin);

        responseMap.put("messages", "An OTP has been sent on your mobile number!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Map<String, String>> validateLoginOtp(LoginDto loginDto, HttpServletRequest httpServletRequest) {

        List<OtpLogs> otpLogsList = this.otpLogsRepository.getLatestOtpByNumber(loginDto.getNumber());
        Map<String, String> responseMap = new HashMap<>();
        try {
            OtpLogs otpLogs = otpLogsList.get(0);

            if (LocalDateTime.now().isBefore(otpLogs.getSentDateTime().plusMinutes(5))) {
                if (!otpLogs.getOtpCode().equals(loginDto.getOtp())) {
                    responseMap.put("errors", "OTP is not valid.");
                    return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
                }
                responseMap.put("messages", "Valid OTP!");
                otpLogs.setExpired(true);
                this.otpLogsRepository.updateOtpLog(otpLogs);

                Random random = new Random();
                int randomUsage = 10 + random.nextInt(91);

                Client client = this.clientRepository.getClientBYNumber(loginDto.getNumber());
                this.dailyUsageRepository.insertOrUpdateDailyUsage(randomUsage,client);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("loggedInMobile", loginDto.getNumber());
                session.setAttribute("clientId", client.getClientId());
                session.setAttribute("clientEmail", client.getEmail());
                session.setAttribute("isRechargeForOthers", "false");
                responseMap.put("docValidation", String.valueOf(client.isDocValidated()));
                responseMap.put("clientId", String.valueOf(client.getClientId()));
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            } else {
                responseMap.put("errors", "OTP Expired. Please try again");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
            }

        } catch (IndexOutOfBoundsException ex) {
            responseMap.put("errors", "OTP is not valid.");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<Map<String, String>> validateAdminLoginOtp(AdminLoginDto loginDto, HttpServletRequest httpServletRequest) {

        List<OtpLogs> otpLogsList = this.otpLogsRepository.getLatestOtpByEmail(loginDto.getEmail());
        Map<String, String> responseMap = new HashMap<>();
        if (otpLogsList.isEmpty()) {
            responseMap.put("errors", "OTP is not valid.");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        OtpLogs otpLogs = otpLogsList.get(0);

        if (LocalDateTime.now().isBefore(otpLogs.getSentDateTime().plusMinutes(5))) {
            if (!otpLogs.getOtpCode().equals(loginDto.getOtp())) {
                responseMap.put("errors", "OTP is not valid.");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            responseMap.put("messages", "Valid OTP!");
            otpLogs.setExpired(true);
            this.otpLogsRepository.updateOtpLog(otpLogs);
            Admin admin = this.adminRepository.getAdminByEmail(loginDto.getEmail().toLowerCase());
            HttpSession session = httpServletRequest.getSession();
            System.out.println("AdminId :" + admin.getAdminId());
            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("adminEmail",admin.getEmail());
            responseMap.put("adminId", String.valueOf(admin.getAdminId()));
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        } else {
            responseMap.put("errors", "OTP Expired. Please try again");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }

    }

    public boolean checkTokenValidity(String token){
        ReuploadEmailLog reuploadEmailLog = this.reuploadEmailLogRepository.getLogByToken(token);
        return reuploadEmailLog == null || reuploadEmailLog.isExpired();
    }

    public ResponseEntity<Map<String,String>> resubmitClientUploadedDocuments(ResubmitDocumentsDto resubmitDocumentsDto, HttpServletRequest httpServletRequest) {
        Map<String,String> responseMap = new HashMap<>();
        ReuploadEmailLog reuploadEmailLog = this.reuploadEmailLogRepository.getLogByToken(resubmitDocumentsDto.getToken());

        if(reuploadEmailLog == null){
            throw new InvalidDataFoundException("Please refresh the page and try again!");
        }

        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentByEmail(reuploadEmailLog.getEmail());
        if (clientDocuments == null) {
            throw new InvalidDataFoundException("Client Data not found!");
        }

        Client client = clientDocuments.getClient();
        client.setDateOfBirth(Helper.parseToLocalDate(resubmitDocumentsDto.getDob()));


        CommonsMultipartFile aadharCardFile = resubmitDocumentsDto.getAadharCardUploadedFile();
        CommonsMultipartFile panCardFile = resubmitDocumentsDto.getPanCardUploadedFile();

        if(panCardFile!=null){
            String panCardExtension = uploadDocuments(panCardFile, client.getClientId(), "PANCard");
            clientDocuments.setPanCardExtension(panCardExtension);
            clientDocuments.setPanCardVerified(false);
            client.setPanNumber(resubmitDocumentsDto.getPanNumber());
        }

        if(aadharCardFile!=null){
            String aadharCardExtension = uploadDocuments(aadharCardFile, client.getClientId(), "AadharCard");
            clientDocuments.setAadharCardExtension(aadharCardExtension);
            clientDocuments.setAadharCardVerified(false);
            client.setAadharNumber(resubmitDocumentsDto.getAadharCardNumber());
        }

        client.setClientDocuments(clientDocuments);
        this.clientRepository.updateClient(client);

        SimCard simCard = this.simCardRepository.getClientSimCardDetailsByClientId(client.getClientId());
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestStatus(RequestStatusEnum.PENDING.getRequestStatusId());
        clientRequest.setRequestType(ClientRequestTypeEnum.ACTIVATION.getRequestTypeId());
        clientRequest.setModifiedByAdmin(0);
        clientRequest.setSimCard(simCard);
        this.clientRequestRepository.saveClientRequest(clientRequest);

        reuploadEmailLog.setExpired(true);
        this.reuploadEmailLogRepository.updateReuploadEmailLog(reuploadEmailLog);
        responseMap.put("messages", "Client successfully uploaded!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,ShowResubmitFieldsDto>> getRequiredResubmitFields(String token, HttpServletRequest httpServletRequest){
        Map<String,ShowResubmitFieldsDto> responseMap = new HashMap<>();
        ReuploadEmailLog reuploadEmailLog = this.reuploadEmailLogRepository.getLogByToken(token);
        if(reuploadEmailLog == null){
            throw new InvalidDataFoundException("Data not found! Try again");
        }

        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentByEmail(reuploadEmailLog.getEmail());
        if(clientDocuments == null){
            throw new InvalidDataFoundException("Client Data not found!");
        }

        ShowResubmitFieldsDto showResubmitFieldsDto = new ShowResubmitFieldsDto();
        showResubmitFieldsDto.setShowAadharCardField(!clientDocuments.isAadharCardVerified());
        showResubmitFieldsDto.setShowPanCardField(!clientDocuments.isPanCardVerified());
        responseMap.put("showResubmitFields", showResubmitFieldsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }
}
