package com.uninor.service;

import com.uninor.Email.EmailService;
import com.uninor.dto.*;
import com.uninor.enumeration.RequestStatusEnum;
import com.uninor.enumeration.SimAccquireTypeEnum;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.helper.Helper;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminDashboardService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private ClientDocumentsRepository clientDocumentsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsService smsService;


    public ResponseEntity<Map<String, AdminDashboardDataDto>> getAdminDashboardData(){
        Map<String,AdminDashboardDataDto> responseMap = new HashMap<>();
        AdminDashboardDataDto adminDashboardDataDto = new AdminDashboardDataDto();

        List<String> planNameList = new ArrayList<>();
        List<Integer> planBoughtCountList = new ArrayList<>();
        List<String> datesList = new ArrayList<>();
        List<String> expenditureList = new ArrayList<>();
        List<Plan> planList = this.planRepository.getPopularActivePlans();
        for (Plan plan : planList) {
            planNameList.add(plan.getCategoryId().getPlanCategory() + " Rs." + plan.getRechargeAmount());
            planBoughtCountList.add(plan.getBoughtCount());
        }

        LocalDate currentDate = LocalDate.now();

        YearMonth currentYearMonth = YearMonth.of(currentDate.getYear(), currentDate.getMonth());
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth();

        List<Expenditure> currentMonthExpenditure = this.expenditureRepository.getLastMonthData(firstDayOfMonth,lastDayOfMonth);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = firstDayOfMonth;
        while (!date.isAfter(lastDayOfMonth)) {
            datesList.add(date.format(formatter));
            date = date.plusDays(1);
        }

        Map<String, Double> expenditureMap = currentMonthExpenditure.stream()
                .collect(Collectors.toMap(
                        exp -> exp.getDate().format(formatter),
                        Expenditure::getAmount
                ));

//        List<String> finalExpenditureList = datesList.stream()
//                .map(eachDate -> eachDate.format(formatter) + ": " + expenditureMap.getOrDefault(eachDate.format(formatter), 0.0))
//                .collect(Collectors.toList());

        List<Double> finalExpenditureList = new ArrayList<>();
        double totalExpenditure = 0;
        for (String dateStr : datesList) {
            double amount = expenditureMap.getOrDefault(dateStr, 0.0);
            totalExpenditure+=amount;
            finalExpenditureList.add(amount);
        }

        Map<String,Integer> map = this.clientRepository.getRegisteredSignedUpUserCount();
        adminDashboardDataDto.setTotalGeneratedRevenue(formatRevenueNumber(totalExpenditure));
        adminDashboardDataDto.setTotalRegisteredUsers(formatNumber(map.get("registeredUserCount")));
        adminDashboardDataDto.setTotalSignedUpUsers(formatNumber(map.get("signedUpUserCount")));
        adminDashboardDataDto.setTopCategoryBoughtCount(planBoughtCountList);
        adminDashboardDataDto.setTopCategoryName(planNameList);
        adminDashboardDataDto.setCurrentMonthDateList(datesList);
        adminDashboardDataDto.setCurrentMonthExpenditureList(finalExpenditureList);
        responseMap.put("adminDashboardData",adminDashboardDataDto);
        return new ResponseEntity<>(responseMap,new HttpHeaders(), HttpStatus.OK);
    }

    private static String formatNumber(int number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 1_000_000) {
            return String.format("%.1fk", number / 1000.0);
        } else{
            return String.format("%.1fM", number / 1_000_000.0);
        }
    }

    private static String formatRevenueNumber(double number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 1_000_000) {
            return String.format("%.1fk", number / 1000.0);
        } else{
            return String.format("%.1fM", number / 1_000_000.0);
        }
    }

    public ResponseEntity<Map<String, ClientRequestPaginatedDto>> getFilteredClientRequests(FilterUserRequest filterUserRequest){
        Map<String, ClientRequestPaginatedDto> responseMap = new HashMap<>();
        ClientRequestPaginatedDto clientRequestPaginatedDto = new ClientRequestPaginatedDto();
        int userPageSize = filterUserRequest.getPageSize();
        int currentPage = filterUserRequest.getCurrentPage();
        Pageable pageable = PageRequest.of(currentPage - 1, userPageSize);
        List<ClientFilteredRequests> clientFilteredRequestsList = new ArrayList<>();
        Page<ClientRequest> getPaginatedClientRequestData = this.clientRequestRepository.getPaginatedClientRequestData(pageable, filterUserRequest);
        clientRequestPaginatedDto.setDataAvailable(!getPaginatedClientRequestData.getContent().isEmpty());

        for (ClientRequest getClientRequest : getPaginatedClientRequestData.getContent()) {
            ClientFilteredRequests clientFilteredRequests = getClientFilteredRequests(getClientRequest);
            clientFilteredRequestsList.add(clientFilteredRequests);
        }

        clientRequestPaginatedDto.setClientFilteredRequests(clientFilteredRequestsList);
        clientRequestPaginatedDto.setCurrentPage(currentPage);
        clientRequestPaginatedDto.setRequestType(filterUserRequest.getRequestType());
        clientRequestPaginatedDto.setTotalPages(getPaginatedClientRequestData.getTotalPages());
        responseMap.put("clientRequestPaginatedDto",clientRequestPaginatedDto);
        return new ResponseEntity<>(responseMap,new HttpHeaders(), HttpStatus.OK);
    }

    private static ClientFilteredRequests getClientFilteredRequests(ClientRequest getClientRequest) {
        Client client = getClientRequest.getSimCard().getClient();
        ClientFilteredRequests clientFilteredRequests = new ClientFilteredRequests();
        clientFilteredRequests.setRequestId(getClientRequest.getRequestId());
        clientFilteredRequests.setClientName(client.getFirstName() + " " + getClientRequest.getSimCard().getClient().getLastName());
        clientFilteredRequests.setClientEmail(client.getEmail());
        clientFilteredRequests.setClientPhone(getClientRequest.getSimCard().getPhoneNumber());
        clientFilteredRequests.setCurrentRequestStatus(client.getValidationAttempts()==0?1:2);
        return clientFilteredRequests;
    }

    public ResponseEntity<Map<String, ViewDocumentRequestDataDto>> getViewDocumentsDetails(Integer requestId, HttpServletRequest httpServletRequest){

        if(requestId == 0 || requestId == null){
            throw new InvalidDataFoundException("Invalid Request Id");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);

        if(clientRequest == null){
            throw new InvalidDataFoundException("Invalid Request Id");
        }
        Map<String,ViewDocumentRequestDataDto> responseMap = new HashMap<>();
        Client client = clientRequest.getSimCard().getClient();
        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentDetails(client.getClientId());

        String aadharViewLink = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + client.getClientId() + File.separator + "AadharCard." + clientDocuments.getAadharCardExtension();
        String panViewLink = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + client.getClientId() + File.separator + "PANCard." + clientDocuments.getPanCardExtension();
        ViewDocumentRequestDataDto viewDocumentRequestDataDto = new ViewDocumentRequestDataDto();
        viewDocumentRequestDataDto.setRequestId(requestId);
        viewDocumentRequestDataDto.setBirthDate(Helper.formatBirthDate(client.getDateOfBirth()));
        viewDocumentRequestDataDto.setAadharCardVerified(clientDocuments.isAadharCardVerified());
        viewDocumentRequestDataDto.setPanCardVerified(clientDocuments.isPanCardVerified());
        viewDocumentRequestDataDto.setPanCardFilePath(panViewLink);
        viewDocumentRequestDataDto.setAadharCardFilePath(aadharViewLink);
        viewDocumentRequestDataDto.setUserName(client.getFirstName() + " " + client.getLastName());
        viewDocumentRequestDataDto.setAadharCardNumber(client.getAadharNumber());
        viewDocumentRequestDataDto.setPanCardNumber(client.getPanNumber());
        responseMap.put("viewDocumentRequestDataDto",viewDocumentRequestDataDto);

        return new ResponseEntity<>(responseMap,new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String ,String >> verifyDocuments(Integer requestId, String aadharVerified, String panVerified, HttpServletRequest httpServletRequest){

        if(requestId == 0 || requestId == null){
            throw new InvalidDataFoundException("Invalid Request Id");
        }

        if ((!aadharVerified.equals("accepted") && !aadharVerified.equals("rejected")) ||
                (!panVerified.equals("accepted") && !panVerified.equals("rejected"))){
            throw new InvalidDataFoundException("Invalid data found");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("Invalid Request Id");
        }

        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentDetails(clientRequest.getSimCard().getClient().getClientId());
        Client client = clientDocuments.getClient();
        Map<String,String> resposeMap = new HashMap<>();
        if(aadharVerified.equals("accepted")){
            clientDocuments.setAadharCardVerified(true);
        }else{
            clientDocuments.setAadharCardVerified(false);
        }

        if(panVerified.equals("accepted")){
            clientDocuments.setPanCardVerified(true);
        }else {
            clientDocuments.setPanCardVerified(false);
        }

        if(panVerified.equals("accepted") && aadharVerified.equals("accepted")){
//            Users user = this.userRepository.getUserByEmail(client.getEmail()).get(0); //check
//            user.setRegistered(true);
//            client.setUser(user);
            client.setDocValidated(true);
            client.setClientDocuments(clientDocuments);
            this.clientRepository.updateClient(client);
        }else {
            int validationAttempts = client.getValidationAttempts();
            if(validationAttempts == 3){
                this.emailService.unregistrtionEmail(client.getEmail(), client.getFirstName() + " " + client.getLastName());
                unregisterUser(client);
            }else{
                this.emailService.reuploadEmail(client.getEmail(), client.getFirstName() + " " + client.getLastName(), httpServletRequest);
                client.setValidationAttempts(validationAttempts+1);
                client.setClientDocuments(clientDocuments);
                this.clientRepository.updateClient(client);
            }

        }
        clientRequest.setRequestStatus(RequestStatusEnum.COMPLETED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        resposeMap.put("message","Process Completed!");
        return new ResponseEntity<>(resposeMap,new HttpHeaders(),HttpStatus.OK);
    }

    private void unregisterUser(Client client){
        SimCard simCard = this.simCardRepository.getClientSimCardDetailsByClientId(client.getClientId());

        if(simCard.getSimAcquiredType() == SimAccquireTypeEnum.PORT.getSimAccquireTypeId()){
            this.simCardRepository.deleteSimCardEntry(simCard.getSimCardId());
        }else{
            simCard.setClient(null);
            simCard.setAvailable(true);
            simCard.setBlocked(false);
            simCard.setRoamingActive(false);
            simCard.setActivationDate(null);
            this.simCardRepository.addOrUpdateSimCard(simCard);
        }
        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentDetails(client.getClientId());
        clientDocuments.setAadharCardVerified(false);
        clientDocuments.setPanCardVerified(false);
        client.setValidationAttempts(0);
        Users user = this.userRepository.getUserByEmail(client.getEmail()).get(0); //check
        user.setRegistered(false);
        client.setUser(user);
        client.setClientDocuments(clientDocuments);
        this.clientRepository.updateClient(client);

    }

    public ResponseEntity<Map<String,String>> rejectBlockRequest(int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }
        SimCard simCard = clientRequest.getSimCard();
        clientRequest.setRequestStatus(RequestStatusEnum.REJECTED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.smsService.sendRejectBlockRequestMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Block request rejected!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> rejectUnblockRequest(int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }
        SimCard simCard = clientRequest.getSimCard();
        clientRequest.setRequestStatus(RequestStatusEnum.REJECTED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.smsService.sendRejectUnblockRequestMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Unblock request rejected!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> rejectDeactivationRequest(int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }
        SimCard simCard = clientRequest.getSimCard();
        clientRequest.setRequestStatus(RequestStatusEnum.REJECTED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.smsService.sendRejectDeactivationRequestMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Deactivation request rejected!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> acceptBlockRequest(int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }

        SimCard simCard = clientRequest.getSimCard();
        simCard.setBlocked(true);
        clientRequest.setRequestStatus(RequestStatusEnum.COMPLETED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.simCardRepository.addOrUpdateSimCard(simCard);
        this.smsService.sendBlockMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Block request accepted!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> acceptUnblockRequest(int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }

        SimCard simCard = clientRequest.getSimCard();
        simCard.setBlocked(false);
        clientRequest.setRequestStatus(RequestStatusEnum.COMPLETED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.simCardRepository.addOrUpdateSimCard(simCard);
        this.smsService.sendUnblockMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Unblock request accepted!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> acceptDeactivationRequest (int requestId){
        Map<String,String> responseMap = new HashMap<>();
        if(requestId == 0){
            throw new InvalidDataFoundException("Invalid request ID!");
        }

        ClientRequest clientRequest = this.clientRequestRepository.getRequestById(requestId);
        if(clientRequest == null){
            throw new InvalidDataFoundException("No such request found!");
        }

        SimCard simCard = clientRequest.getSimCard();
        simCard.setPlanActive(false);
        simCard.setClient(null);
        simCard.setBlocked(false);
        simCard.setPlanActive(false);
        simCard.setActivationDate(null);
        simCard.setRoamingActive(false);
        simCard.setAvailable(true);
        clientRequest.setRequestStatus(RequestStatusEnum.COMPLETED.getRequestStatusId());
        this.clientRequestRepository.updateClientRequest(clientRequest);
        this.simCardRepository.addOrUpdateSimCard(simCard);
        this.smsService.sendAcceptDeactivationRequestMessage(simCard.getPhoneNumber());
        responseMap.put("messages", "Deactivation request accepted!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }
}


