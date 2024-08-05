package com.uninor.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.text.DocumentException;
import com.uninor.dto.*;
import com.uninor.enumeration.*;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.exceptions.InvalidFileException;
import com.uninor.exceptions.SimNotAvailableException;
import com.uninor.helper.Helper;
import com.uninor.helper.InvoiceNumberGenerator;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import com.itextpdf.text.pdf.PdfPTable;
//import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private PlanActivationRepository planActivationRepository;

    @Autowired
    private RoamingActivationRepository roamingActivationRepository;

    @Autowired
    private InvoiceTableRepository invoiceTableRepository;

    @Qualifier("httpServletRequest")
    @Autowired
    private ServletRequest httpServletRequest;

    @Autowired
    private SmsService smsService;

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    private ShowPlanDto createPopularPlanDto(List<Plan> planList, String planCategory){
        ShowPlanDto popularPlans = new ShowPlanDto();
        List<PlanDetailsDto> displayList = new ArrayList<>();
        popularPlans.setPlanCategory(planCategory);
        popularPlans.setPlanCount(planList.size());
        for(Plan plan : planList){
            PlanDetailsDto planDetails = new PlanDetailsDto();
            planDetails.setPlanId(plan.getPlanId());
            planDetails.setPlanAmount(plan.getRechargeAmount());
            planDetails.setPlanValidity(plan.getValidity());
            planDetails.setSmsAllowance(plan.getSmsAllowance());
            planDetails.setDataAmount((plan.getDataAllowance() / 1000));
            planDetails.setAdditionalData((plan.getExtraData() / 1000));
            planDetails.setDailyRefreshing(plan.getIsRefreshing());

            Double totalData;
            if(plan.getIsRefreshing()){
                totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
            }else {
                totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
            }

            planDetails.setTotalData(totalData);
            displayList.add(planDetails);
        }
        popularPlans.setPlanList(displayList);
        return popularPlans;
    }

    private ShowPlanDto createAnnualPlanDto(List<Plan> planList, String planCategory){
        ShowPlanDto annualPlan = new ShowPlanDto();
        List<PlanDetailsDto> displayList = new ArrayList<>();
        annualPlan.setPlanCategory(planCategory);
        int count = 0;
        for(Plan plan : planList){
            if(plan.getValidity() > 300){
                count++;
                PlanDetailsDto planDetails = new PlanDetailsDto();
                planDetails.setPlanId(plan.getPlanId());
                planDetails.setPlanAmount(plan.getRechargeAmount());
                planDetails.setPlanValidity(plan.getValidity());
                planDetails.setSmsAllowance(plan.getSmsAllowance());
                planDetails.setDataAmount((plan.getDataAllowance() / 1000));
                planDetails.setAdditionalData((plan.getExtraData() / 1000));
                planDetails.setDailyRefreshing(plan.getIsRefreshing());

                Double totalData;
                if(plan.getIsRefreshing()){
                    totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
                }else {
                    totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
                }

                planDetails.setTotalData(totalData);
                displayList.add(planDetails);
            }
        }
        annualPlan.setPlanCount(count);
        annualPlan.setPlanList(displayList);
        return annualPlan;
    }

    public ResponseEntity<Map<String, ShowPlanDto>> getRechargeDetails(RechargePlanFilter rechargePlanFilter, HttpServletRequest httpServletRequest){
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if(simCardList.isEmpty()){
            throw new DataNotFoundException("Client Data not found");
        }
        SimCard simCard = simCardList.get(0);
        Map<String,ShowPlanDto> responseMap = new LinkedHashMap<>();
        List<PlanCategories> planCategories = this.categoryRepository.getAllAvailableCategory();
        List<Plan> availablePlans;
        List<Plan> popularPlans = this.planRepository.getPopularActivePlans();
        if(rechargePlanFilter.isIsfilterApplied()){
            availablePlans = this.planRepository.getActiveFilteredPlan(rechargePlanFilter);
        }else {
            availablePlans = this.planRepository.getActivePlan();
        }

        ShowPlanDto popularPlan = createPopularPlanDto(popularPlans,"Popular Plans");
        responseMap.put("Popular Plans", popularPlan);

        ShowPlanDto annualPlan = createAnnualPlanDto(availablePlans,"Annual Plans");
        responseMap.put("Annual Plans", annualPlan);

        for(PlanCategories planCategory : planCategories){
            ShowPlanDto displayPlan = new ShowPlanDto();
            List<PlanDetailsDto> displayList = new ArrayList<>();
            displayPlan.setPlanCategory(planCategory.getPlanCategory());
            displayPlan.setPrepaid(simCard.getSimType() == SimType.PREPAID.getSimCardTypeId());
            int planCount = 0;
           for(Plan plan : availablePlans){
               if(planCategory.getPlanId() == plan.getCategoryId().getPlanId()){
                   planCount++;
                   PlanDetailsDto planDetails = new PlanDetailsDto();
                   planDetails.setPlanId(plan.getPlanId());
                   planDetails.setPlanAmount(plan.getRechargeAmount());
                   planDetails.setPlanValidity(plan.getValidity());
                   planDetails.setSmsAllowance(plan.getSmsAllowance());
                   planDetails.setDataAmount((plan.getDataAllowance() / 1000));
                   planDetails.setAdditionalData((plan.getExtraData() / 1000));
                   planDetails.setDailyRefreshing(plan.getIsRefreshing());

                   Double totalData;
                   if(plan.getIsRefreshing()){
                       totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
                   }else {
                       totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
                   }

                   planDetails.setTotalData(totalData);
                   displayList.add(planDetails);

               }

           }
            displayPlan.setPlanCount(planCount);
            displayPlan.setPlanList(displayList);
            responseMap.put(planCategory.getPlanCategory(), displayPlan);
        }


        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);


    }

    public ResponseEntity<Map<String,PlanDetailsDto>> getPlanDetails(int planId){
        if(planId == 0){
            throw new DataNotFoundException("No such plan found!");
        }
        Map<String,PlanDetailsDto> responseMap = new HashMap<>();
        Plan plan = this.planRepository.getPlanById(planId);
        try {
            PlanDetailsDto planDetails = new PlanDetailsDto();
            planDetails.setPlanId(plan.getPlanId());
            planDetails.setPlanAmount(plan.getRechargeAmount());
            planDetails.setPlanValidity(plan.getValidity());
            planDetails.setSmsAllowance(plan.getSmsAllowance());
            planDetails.setDataAmount((plan.getDataAllowance() / 1000));
            planDetails.setAdditionalData((plan.getExtraData() / 1000));
            planDetails.setDailyRefreshing(plan.getIsRefreshing());
            planDetails.setVoiceAllowance(plan.getVoiceAllowance());
            Double totalData;
            if(plan.getIsRefreshing()){
                totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
            }else {
                totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
            }

            planDetails.setTotalData(totalData);
            responseMap.put("Plan", planDetails);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }catch (NullPointerException e){
            throw new DataNotFoundException("No Plan Found! Please try again");
        }
    }

    public  ResponseEntity<Map<String, UserPlanDetailsDto>> getUserPlanDetails(int planId, HttpServletRequest httpServletRequest){
        String clientEmail = (String) httpServletRequest.getSession().getAttribute("clientEmail");
        Client client = this.clientRepository.getUserByEmail(clientEmail).get(0);
        if(planId == 0){
            throw new DataNotFoundException("No such plan found!");
        }
        Map<String,UserPlanDetailsDto> responseMap = new HashMap<>();
        Plan plan = this.planRepository.getPlanById(planId);
        try {
            UserPlanDetailsDto planDetails = new UserPlanDetailsDto();
            planDetails.setPlanId(plan.getPlanId());
            planDetails.setPlanAmount(plan.getRechargeAmount());
            planDetails.setPlanValidity(plan.getValidity());
            planDetails.setSmsAllowance(plan.getSmsAllowance());
            planDetails.setDataAmount((plan.getDataAllowance() / 1000));
            planDetails.setAdditionalData((plan.getExtraData() / 1000));
            planDetails.setDailyRefreshing(plan.getIsRefreshing());
            planDetails.setVoiceAllowance(plan.getVoiceAllowance());
            Double totalData;
            if(plan.getIsRefreshing()){
                totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
            }else {
                totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
            }

            planDetails.setTotalData(totalData);
            planDetails.setWalletAmount(client.getWalletAmount());
            Double taxApplied = (Helper.CGST + Helper.SGST)/ 100;
            Double planPrice = plan.getRechargeAmount() / (1 + taxApplied);
            planPrice = Double.valueOf(String.format("%.2f", planPrice));

            Double taxAmount = planPrice * taxApplied;
            taxAmount = Double.valueOf(String.format("%.2f", taxAmount));



            planDetails.setTaxAmount(taxAmount);
            planDetails.setPlanPriceWithoutTax(planPrice);
            responseMap.put("Plan", planDetails);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }catch (NullPointerException e){
            throw new DataNotFoundException("No Plan Found! Please try again");
        }
    }

    public ResponseEntity<Map<String,String>> validateWalletAmount(ClientWalletDto walletDto){
        Map<String,String> responseMap = new HashMap<>();
        Client client = this.clientRepository.getClientById(Integer.parseInt(walletDto.getClientId()));
        if(client == null){
            throw new DataNotFoundException("No such client found!");
        }

        if(client.getWalletAmount() >= Double.parseDouble(walletDto.getAmount())){
            responseMap.put("messages","Sufficient Amount available");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }else {
            responseMap.put("errors","Insufficient Balance");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.FORBIDDEN);
        }
    }

    private CuponDto getCuponDto(Cupon cupon){
        CuponDto cuponDto = new CuponDto();
        cuponDto.setCuponId(cupon.getCuponId());
        cuponDto.setCuponName(cupon.getCuponName());
        cuponDto.setRewardAmount(cupon.getRewardAmount());
        cuponDto.setMaxRewardAmount(cupon.getMaxRewardAmount());
        return cuponDto;
    }

    public ResponseEntity<Map<String, CuponDto>> validateCuponCode(ClientCuponDto clientCuponDto){
        Map<String,CuponDto> responseMap = new HashMap<>();
        List<ClientCupons> cuponList = this.cuponRepository.getClientCuponByCuponCode(Integer.parseInt(clientCuponDto.getClientId()),clientCuponDto.getCuponCode());
        if(cuponList.isEmpty()){
            throw new DataNotFoundException("Invalid Cupon Code");
        }else {
            ClientCupons clientCupons = cuponList.get(0);
            if(clientCupons.isExpired() == true){
                throw new DataNotFoundException("Cupon Code Expired.");
            }
            if(clientCupons.isUsed() == true){
                throw new DataNotFoundException("Cupon Code already used.");
            }

            CuponDto cuponDto = getCuponDto(clientCupons.getCupon());
            responseMap.put("cuponDetails",cuponDto);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

    }

    public ResponseEntity<Map<String,String>> rechargePayment(CuponWalletDto cuponWalletDto, HttpServletRequest httpServletRequest) throws DocumentException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String mobileNumber = (String) session.getAttribute("loggedInMobile");
        SimCard simCard;

        try {
            simCard = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber).get(0);
        }catch (NullPointerException e){
            throw new SimNotAvailableException("Error while getting mobile number details");
        }

        Map<String,String> responseMap = new HashMap<>();
        boolean cuponError = false;
        boolean walletError = false;
        if(cuponWalletDto.getEnteredWalletAmount().isEmpty()){
            cuponWalletDto.setEnteredWalletAmount("0");
        }


        boolean isWalletAmountEmpty = false;
        boolean isCuponEmpty = false;
        Client client = this.clientRepository.getClientById(Integer.parseInt(cuponWalletDto.getClientId()));
        if(client == null){
            throw new DataNotFoundException("No such client found!");
        }

        Plan plan = this.planRepository.getPlanById(Integer.parseInt(cuponWalletDto.getPlanId()));
        if(plan == null){
            throw new DataNotFoundException("No such Plan found!");
        }

        List<ClientCupons> cuponList = this.cuponRepository.getClientCuponByCuponCode(client.getClientId(),cuponWalletDto.getCuponCode());

        if(cuponWalletDto.getCuponCode().isEmpty()){
            isCuponEmpty = true;
        }

        if(cuponList.isEmpty()){
            cuponError = true;
        }else {
            ClientCupons clientCupons = cuponList.get(0);
            if(clientCupons.isExpired()){
                cuponError = true;
            }
            if(clientCupons.isUsed()){
                cuponError = true;
            }

            if(!clientCupons.isExpired() && !clientCupons.isUsed()) {
                cuponError = false;
            }
        }

        if(!cuponWalletDto.getEnteredWalletAmount().isEmpty() && !cuponWalletDto.getEnteredWalletAmount().equals("0")) {
            if(client.getWalletAmount() >= Double.parseDouble(cuponWalletDto.getEnteredWalletAmount())){
                walletError = false;
            }else {
                walletError = true;
            }
        }else {
            walletError = true;
            isWalletAmountEmpty = true;
        }

        //Main Calculations

        double finalPlanPriceWithoutTax = 0;
        double finalTaxApplied = 0;
        double finalPriceWithoutWallet = 0;
        double finalDiscountApplied = 0;
        double finalpayableAmount = 0;
        double finalTaxableAmount = 0;
        double cashbackAmount = 0;
        double planAmountWithTax = plan.getRechargeAmount();
        double taxAmount = (Helper.CGST + Helper.SGST)/ 100;

        double planPriceWithoutTax = planAmountWithTax / (1 + taxAmount);
        planPriceWithoutTax = Double.parseDouble(String.format("%.2f", planPriceWithoutTax));
        finalPlanPriceWithoutTax = planPriceWithoutTax;
        finalTaxableAmount = finalPlanPriceWithoutTax;
        double discountAmount = 0.00;
        double walletAmountUsed = 0; //used wallet amount
        double planTax = planPriceWithoutTax * taxAmount;
        planTax = Double.parseDouble(String.format("%.2f", planTax));
        finalTaxApplied = planTax;
        finalPriceWithoutWallet = plan.getRechargeAmount();
        finalpayableAmount = plan.getRechargeAmount();

        Double priceWithoutWallet = planAmountWithTax;
        Double payableAmount = planAmountWithTax;
        String path;
        if(!cuponError){
            ClientCupons updateClientCupon = cuponList.get(0);
            Cupon cupon = updateClientCupon.getCupon();
            if(cupon.isDeductable()){
                double discountPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                discountAmount = planPriceWithoutTax * discountPercent <= cupon.getMaxRewardAmount() + 0.99 ? planPriceWithoutTax * discountPercent : cupon.getMaxRewardAmount();
                discountAmount = Double.parseDouble(String.format("%.2f", discountAmount));
                finalDiscountApplied = discountAmount;
                finalTaxableAmount = finalTaxableAmount - discountAmount;
                planTax = (planPriceWithoutTax-discountAmount) * taxAmount;
                planTax = Double.parseDouble(String.format("%.2f", planTax));
                finalTaxApplied = planTax;

                priceWithoutWallet = planPriceWithoutTax - discountAmount + planTax;
                payableAmount = priceWithoutWallet;
                finalPriceWithoutWallet = priceWithoutWallet;
                finalpayableAmount = priceWithoutWallet;
            }

            if(cupon.isCashback()){
                double cashbackPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                cashbackAmount = finalTaxableAmount * cashbackPercent <= cupon.getMaxRewardAmount()?finalTaxableAmount * cashbackPercent:cupon.getMaxRewardAmount();
                cashbackAmount = Double.parseDouble(String.format("%.2f", cashbackAmount));
            }

            if(!cupon.isCashback() && !cupon.isDeductable()){
                cuponError = true;
            }
        }

        if(priceWithoutWallet < Double.parseDouble(cuponWalletDto.getEnteredWalletAmount())+10.0){
            double minWalletAmount =  priceWithoutWallet <= client.getWalletAmount() ? priceWithoutWallet : client.getWalletAmount();
            cuponWalletDto.setEnteredWalletAmount(String.valueOf(minWalletAmount));

        }

        if(!walletError){
            payableAmount = payableAmount - Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            payableAmount = Double.valueOf(String.format("%.2f", payableAmount));

            if(payableAmount == 0){
                payableAmount += 10.00;
                walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) - 10.00;
            }else {
                walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            }

            finalpayableAmount = payableAmount;
        }

        if(isWalletAmountEmpty){
            walletError = false;
        }

        if (isCuponEmpty){
            cuponError = false;
        }

        if((!cuponList.isEmpty() && cuponError) || walletError){
            throw new InvalidDataFoundException("Please check cupon code and wallet amount!");
        }else {
            InvoiceTable invoiceTable = null;
            OrderTable orderTable = null;
            List<PlanActivation> activePlanList = this.planActivationRepository.getActiveSimPlan(simCard);
            if(plan.getCategoryId().isRoamingAvailable() && isPlanValid(plan,simCard,client,activePlanList)){
                RoamingActivation roamingActivation = saveRoamingActivationPlan(plan,simCard);
                orderTable = saveOrderDetails(client,plan,simCard,finalpayableAmount,finalDiscountApplied);
                invoiceTable = saveInvoiceTableDetails(client,orderTable,mobileNumber,finalDiscountApplied,taxAmount,finalpayableAmount,finalTaxableAmount,walletAmountUsed);
                this.roamingActivationRepository.saveRoamingActivationDetails(roamingActivation);
                this.invoiceTableRepository.saveInvoiceTableDetails(invoiceTable);
            }

            if(isPlanValid(plan,simCard,client,activePlanList) && !plan.getCategoryId().isRoamingAvailable()){
                orderTable = saveOrderDetails(client,plan,simCard,finalpayableAmount,finalDiscountApplied);
                invoiceTable = saveInvoiceTableDetails(client,orderTable,mobileNumber,finalDiscountApplied,finalTaxApplied,finalpayableAmount,finalTaxableAmount,walletAmountUsed);
                this.invoiceTableRepository.saveInvoiceTableDetails(invoiceTable);

                if( plan.getCategoryId().getPlanId() != PlanCategoryEnum.ADD_ON_DATA.getCategoryId()){
                    PlanUsage planUsage = initiatePlanUsageDetails(plan);
                    PlanActivation planActivation = savePlanActivationDetails(simCard,plan,planUsage,activePlanList);
                    this.planActivationRepository.savePlanActivation(planActivation);
                }else {
                    PlanActivation currentActivatedPlan = activePlanList.get(0);
                    PlanUsage currentActivatedPlanPlanUsage = currentActivatedPlan.getPlanUsage();
                    double currentAdditionalData = currentActivatedPlanPlanUsage.getAdditionalData();
                    currentActivatedPlanPlanUsage.setAdditionalData(currentAdditionalData + plan.getDataAllowance());
                    this.planActivationRepository.updatePlanActivation(currentActivatedPlan);
                }

            }

            if(!cuponError && !cuponList.isEmpty()){
                ClientCupons updateClientCupon = cuponList.get(0);
                Cupon cupon = updateClientCupon.getCupon();
                if(cupon.isDeductable() || cupon.isCashback() ){
                    updateClientCupon.setUsed(true);
                    this.cuponRepository.updateClientCupons(updateClientCupon);
                }

            }
            client.setWalletAmount(client.getWalletAmount() - walletAmountUsed + cashbackAmount);
            this.clientRepository.updateClient(client);

            this.planRepository.updatePlanBoughtCount(plan);
            path = generatePdf(client,simCard,invoiceTable,orderTable,plan, httpServletRequest);
        }
        this.smsService.sendPlanBoughtSms(mobileNumber,plan);
        Cupon cupon = assignRandomCouponToUser();
        if(cupon != null){
            ClientCupons clientCupons = new ClientCupons();
            clientCupons.setCupon(cupon);
            clientCupons.setUsed(false);
            clientCupons.setClient(client);
            clientCupons.setExpired(false);
            clientCupons.setCuponCode(Helper.cuponCodeGenerator());
            clientCupons.setExpirationDate(LocalDate.now().plusDays(cupon.getValidityPeriod()));
            this.cuponRepository.saveClientCupons(clientCupons);
            responseMap.put("couponMessage","Coupon assigned! You can view it in the coupon tab.");
        }else {
            responseMap.put("couponMessage","No Coupon Assigned. Try again later");
        }
        responseMap.put("messages","Plan recharged successfully!");
        responseMap.put("downloadPath",path);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);

    }

    private Cupon assignRandomCouponToUser(){
        List<Cupon> cuponList = this.cuponRepository.getAllAvailableCoupons();
        if(!cuponList.isEmpty()){
            Random random = new Random();
            int randomIndex = random.nextInt(cuponList.size());
            return cuponList.get(randomIndex);
        }else {
            return null;
        }
    }

    public InvoiceTable saveInvoiceTableDetails(Client client, OrderTable orderTable, String mobileNumber, Double discountAmount, Double taxAmount, Double totalAmount, Double taxableAmount, Double walletAmountUsed){

        InvoiceNumberGenerator generator = InvoiceNumberGenerator.getInstance();

        InvoiceTable invoiceTable = new InvoiceTable();
        invoiceTable.setClient(client);
        invoiceTable.setInvoiceNumber(generator.generateInvoiceNumber());
        invoiceTable.setOrderTable(orderTable);
        invoiceTable.setTaxAmount(taxAmount);
        invoiceTable.setTotalAmount(totalAmount);
        invoiceTable.setBillingAddress(client.getStreet() + " ," + client.getCity() + " ," + client.getState() + " ," + client.getZipcode() + ".");
        invoiceTable.setDiscountAmount(discountAmount);
        invoiceTable.setMobileNumber(mobileNumber);
        invoiceTable.setPaymentStatus(PaymentStatusEnum.PAID.getPaymentStatusId());
        invoiceTable.setTaxableAmount(taxableAmount);
        invoiceTable.setWalletAmountUsed(walletAmountUsed);
        invoiceTable.setExtraDataUsed(0);
        invoiceTable.setExtraDataCharges(0);
        invoiceTable.setExtraSmsUsed(0);
        invoiceTable.setExtraSmsCharges(0);
        invoiceTable.setPlanBoughtDate(LocalDateTime.now());
        return invoiceTable;

    }

    public OrderTable saveOrderDetails(Client client, Plan plan, SimCard simCard, Double totalAmount, Double totalDiscount) {
        OrderTable orderTable = new OrderTable();
        orderTable.setClient(client);
        orderTable.setOrderRef(Helper.orderIdGenerator());
        orderTable.setOrderStatus(OrderStatusEnum.COMPLETED.getOrderStatusId());
        orderTable.setPlan(plan);
        orderTable.setSimCard(simCard);
        orderTable.setPaymentMethod("UPI");
        orderTable.setPaymentRefrence(Helper.paymentRefGenerator());
        orderTable.setTotalAmount(totalAmount);
        orderTable.setTotalDiscount(totalDiscount);
        return orderTable;
    }

    public RoamingActivation saveRoamingActivationPlan(Plan plan, SimCard simCard){
        RoamingActivation roamingActivation = new RoamingActivation();
        roamingActivation.setActivationDate(null);
        roamingActivation.setRoamingPlan(plan);
        roamingActivation.setActive(false);
        roamingActivation.setExpired(false);
        roamingActivation.setRoamingDataUsage(plan.getDataAllowance());
        roamingActivation.setSimCard(simCard);
        roamingActivation.setSmsUsage(plan.getSmsAllowance());
        int voiceAllowance = Integer.parseInt((plan.getVoiceAllowance().split(" ")[0]).trim())*60;
        roamingActivation.setVoiceUsage(voiceAllowance);
        roamingActivation.setExpirationDate(null);
        return roamingActivation;
    }

    public boolean isPlanValid(Plan plan, SimCard simCard, Client client, List<PlanActivation> listPlanActivation) {
        int planType = plan.getCategoryId().getPlanType();
        boolean isAddOn = plan.getCategoryId().isAddOn();

        if(simCard.getSimType() != planType){
            String message = planType == 1?"Prepaid":"Postpaid";
            throw new InvalidDataFoundException("This Plan is for " + message + " Sim");
        }

        if(isAddOn){
            if(listPlanActivation.isEmpty()){
                throw new InvalidDataFoundException("You need a active base plan to buy add on packs");
            }
        }

        return true;
    }

    public PlanUsage initiatePlanUsageDetails(Plan plan) {
        PlanUsage planUsage = new PlanUsage();
        planUsage.setDataUsage(plan.getDataAllowance());
        planUsage.setSmsUsage(plan.getSmsAllowance());
        planUsage.setPostpaidDataOver(false);

        if(plan.getIsExtraDataAvailable()){
            planUsage.setAdditionalData(plan.getExtraData());
        }else {
            planUsage.setAdditionalData(0.00);
        }
        return planUsage;
    }

    public PlanActivation savePlanActivationDetails(SimCard simCard,Plan plan, PlanUsage planUsage,  List<PlanActivation> listPlanActivation){

        PlanActivation planActivation = new PlanActivation();
        planActivation.setSimCard(simCard);
        planActivation.setPlan(plan);
        planActivation.setPlanUsage(planUsage);
        if(!listPlanActivation.isEmpty()){
            planActivation.setActivationDate(null);
            planActivation.setExpirationDate(null);
            planActivation.setActive(false);
        }else {
            planActivation.setActivationDate(LocalDateTime.now());
            planActivation.setExpirationDate(LocalDateTime.now().plusDays(plan.getValidity()));
            planActivation.setActive(true);
        }
        planActivation.setExpired(false);
        return planActivation;
    }

    public ResponseEntity<Map<String , UpdateBuyPlanDetailsDto>> validateCuponWalletDetails(CuponWalletDto cuponWalletDto){

        Map<String,UpdateBuyPlanDetailsDto> responseMap = new HashMap<>();
        if(cuponWalletDto.getEnteredWalletAmount().isEmpty()){
            cuponWalletDto.setEnteredWalletAmount("0");
        }
        UpdateBuyPlanDetailsDto updateBuyPlanDetailsDto = new UpdateBuyPlanDetailsDto();
        Cupon validCupon;
        boolean isWalletAmountEmpty = false;
        boolean isCuponEmpty = false;
        Client client = this.clientRepository.getClientById(Integer.parseInt(cuponWalletDto.getClientId()));
        if(client == null){
            throw new DataNotFoundException("No such client found!");
        }

        Plan plan = this.planRepository.getPlanById(Integer.parseInt(cuponWalletDto.getPlanId()));
        if(plan == null){
            throw new DataNotFoundException("No such Plan found!");
        }

        updateBuyPlanDetailsDto.setPlanId(plan.getPlanId());
        updateBuyPlanDetailsDto.setPlanAmount(plan.getRechargeAmount());
        updateBuyPlanDetailsDto.setPlanValidity(plan.getValidity());
        updateBuyPlanDetailsDto.setSmsAllowance(plan.getSmsAllowance());
        updateBuyPlanDetailsDto.setDataAmount((plan.getDataAllowance() / 1000));
        updateBuyPlanDetailsDto.setAdditionalData((plan.getExtraData() / 1000));
        updateBuyPlanDetailsDto.setDailyRefreshing(plan.getIsRefreshing());
        updateBuyPlanDetailsDto.setVoiceAllowance(plan.getVoiceAllowance());
        double totalData;
        if(plan.getIsRefreshing()){
            totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
        }else {
            totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
        }

        updateBuyPlanDetailsDto.setTotalData(totalData);
        updateBuyPlanDetailsDto.setWalletAmount(client.getWalletAmount());


        List<ClientCupons> cuponList = this.cuponRepository.getClientCuponByCuponCode(client.getClientId(),cuponWalletDto.getCuponCode());

        if(cuponWalletDto.getCuponCode().isEmpty()){
            isCuponEmpty = true;
            updateBuyPlanDetailsDto.setCuponApplied(false);
        }

        if(cuponList.isEmpty()){
            updateBuyPlanDetailsDto.setCuponErrorMessage("Invalid Cupon Code!");
            updateBuyPlanDetailsDto.setCuponError(true);
        }else {
            ClientCupons clientCupons = cuponList.get(0);
            if(clientCupons.isExpired()){
                updateBuyPlanDetailsDto.setCuponErrorMessage("Cupon code Expired!");
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }
            if(clientCupons.isUsed()){
                updateBuyPlanDetailsDto.setCuponErrorMessage("Cupon code already used");
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }


            if(!clientCupons.isExpired() && !clientCupons.isUsed()) {
                validCupon = clientCupons.getCupon();
                updateBuyPlanDetailsDto.setCuponError(false);
                updateBuyPlanDetailsDto.setCuponId(validCupon.getCuponId());
                updateBuyPlanDetailsDto.setCuponName(validCupon.getCuponName());
                updateBuyPlanDetailsDto.setRewardAmount(validCupon.getRewardAmount());
                updateBuyPlanDetailsDto.setMaxRewardAmount(validCupon.getMaxRewardAmount());
            }
        }

        if(!cuponWalletDto.getEnteredWalletAmount().isEmpty() && !cuponWalletDto.getEnteredWalletAmount().equals("0")) {
            if(client.getWalletAmount() >= Double.parseDouble(cuponWalletDto.getEnteredWalletAmount())){
                updateBuyPlanDetailsDto.setWalletError(false);
            }else {
                cuponWalletDto.setEnteredWalletAmount("0");
                updateBuyPlanDetailsDto.setWalletError(true);
                updateBuyPlanDetailsDto.setWalletErrorMessage("Insufficient Wallet Balance");
            }
        }else {
            updateBuyPlanDetailsDto.setWalletError(true);
            isWalletAmountEmpty = true;
        }


        //Main Calculations

        double finalPlanPriceWithoutTax = 0;
        double finalTaxApplied = 0;
        double finalPriceWithoutWallet = 0;
        double finalDiscountApplied = 0;
        double finalpayableAmount = 0;

        double planAmountWithTax = plan.getRechargeAmount();
        double taxAmount = (Helper.CGST + Helper.SGST)/ 100;

        double planPriceWithoutTax = planAmountWithTax / (1 + taxAmount);
        planPriceWithoutTax = Double.valueOf(String.format("%.2f", planPriceWithoutTax));
        finalPlanPriceWithoutTax = planPriceWithoutTax;
        double discountAmount = 0.00;

        double planTax = planPriceWithoutTax * taxAmount;
        planTax = Double.valueOf(String.format("%.2f", planTax));
        finalTaxApplied = planTax;
        finalPriceWithoutWallet = plan.getRechargeAmount();
        finalpayableAmount = plan.getRechargeAmount();

        double priceWithoutWallet = planAmountWithTax;
        double payableAmount = planAmountWithTax;

        if(!updateBuyPlanDetailsDto.isCuponError()){
            Cupon cupon = cuponList.get(0).getCupon();
            if(cupon.isDeductable()){
                double discountPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                discountAmount = planPriceWithoutTax * discountPercent <= cupon.getMaxRewardAmount() + 0.99 ? planPriceWithoutTax * discountPercent : cupon.getMaxRewardAmount();
                discountAmount = Double.valueOf(String.format("%.2f", discountAmount));
                finalDiscountApplied = discountAmount;

                planTax = (planPriceWithoutTax-discountAmount) * taxAmount;
                planTax = Double.valueOf(String.format("%.2f", planTax));
                finalTaxApplied = planTax;

                priceWithoutWallet = planPriceWithoutTax - discountAmount + planTax;
                payableAmount = priceWithoutWallet;
                finalPriceWithoutWallet = priceWithoutWallet;
                finalpayableAmount = priceWithoutWallet;
                updateBuyPlanDetailsDto.setCuponApplied(true);
            }

            if(!cupon.isCashback() && !cupon.isDeductable()){
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponErrorMessage("Coupon invalid here. Redeem from rewards.");
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }
        }

//        planTax = priceWithoutWallet * taxAmount;
//        planTax = Double.valueOf(String.format("%.2f", planTax));
//        priceWithoutWallet += planTax;
//        priceWithoutWallet = Double.valueOf(String.format("%.2f", priceWithoutWallet));
//        payableAmount = priceWithoutWallet;

        if(priceWithoutWallet < Double.parseDouble(cuponWalletDto.getEnteredWalletAmount())+10.0){
            double minWalletAmount =  priceWithoutWallet <= client.getWalletAmount() ? priceWithoutWallet : client.getWalletAmount();
            cuponWalletDto.setEnteredWalletAmount(String.valueOf(minWalletAmount));
        }
        updateBuyPlanDetailsDto.setUsedWalletAmount(Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()));
        if(!updateBuyPlanDetailsDto.isWalletError()){
            payableAmount = payableAmount - Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            payableAmount = Double.valueOf(String.format("%.2f", payableAmount));

            if(payableAmount == 0){
                payableAmount += 10.00;
                double walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) - 10.00;
                updateBuyPlanDetailsDto.setUsedWalletAmount(Double.valueOf(String.format("%.2f", walletAmountUsed)));
            }

            finalpayableAmount = payableAmount;

        }

        if(isWalletAmountEmpty){
            updateBuyPlanDetailsDto.setWalletError(false);
        }

        if (isCuponEmpty){
            updateBuyPlanDetailsDto.setCuponError(false);
        }

        updateBuyPlanDetailsDto.setDiscountApplied(finalDiscountApplied);
        updateBuyPlanDetailsDto.setFinalPayableAmount(finalpayableAmount);
        updateBuyPlanDetailsDto.setPlanAmount(plan.getRechargeAmount());
        updateBuyPlanDetailsDto.setPlanPriceWithoutWallet(finalPriceWithoutWallet);
        updateBuyPlanDetailsDto.setPlanPriceWithoutTax(finalPlanPriceWithoutTax);
        updateBuyPlanDetailsDto.setTaxAmount(finalTaxApplied);
        responseMap.put("updatedDetails",updateBuyPlanDetailsDto);
        return new ResponseEntity<>(responseMap,new HttpHeaders(), HttpStatus.OK);
    }

    public String generatePdf(Client client, SimCard simCard, InvoiceTable invoice, OrderTable order, Plan plan, HttpServletRequest httpServletRequest ) throws IOException, DocumentException {

        String folderPath = System.getenv("UninorUploadPath") + client.getClientId() + File.separator + "invoices";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = folderPath + File.separator + "Invoice-" +  invoice.getInvoiceNumber() + ".pdf";
        System.out.println(filePath);
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        pdf.addNewPage();
        Document document = new Document(pdf, PageSize.A4);

        PdfFont font = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
        float width = pdf.getDefaultPageSize().getWidth();
        float height = pdf.getDefaultPageSize().getHeight();
        PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
        canvas.rectangle(20, 20, width - 40, height - 40);
        canvas.stroke();

//        ImageData imageData = ImageDataFactory.create(
//                httpSession.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"	+ File.separator + "images" + File.separator + "uninor_logo_big_resize.jpg");
//        System.out.println(httpSession.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"	+ File.separator + "images/uninor-remove-bg.png");
//        Image image = new Image(imageData);
//        image.setFixedPosition(pdf.getDefaultPageSize().getWidth() / 2 - (image.getImageScaledWidth() / 2),
//                pdf.getDefaultPageSize().getHeight() / 2 - (image.getImageScaledHeight() / 2));
//        image.setOpacity(0.3f);
//        document.add(image);
        document.add(new Paragraph("Uninor Retail Limited")
                .setFont(bold)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18));

        document.add(new Paragraph("Tax Invoice")
                .setFont(bold)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16));

        document.add(new Paragraph("(Original for Recipient)")
                .setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12));

        document.add(new Paragraph(
                "")
                .setFont(font)
                .setFontSize(10)
                .setMarginTop(25));

        float[] columnWidths = {1.5f, 3.5f};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        table.addCell(new Cell().add(new Paragraph("Customer Name:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(client.getFirstName() + " " + client.getLastName())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Uninor Number.:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(simCard.getPhoneNumber())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Invoice No:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(invoice.getInvoiceNumber())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Order Ref. No.:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(order.getOrderRef())).setFont(font).setFontSize(11).setPaddingLeft(14));
        if(client.getGstNumber() != null){
            table.addCell(new Cell().add(new Paragraph("GST No:")).setFontSize(11).setPaddingLeft(14));
            table.addCell(new Cell().add(new Paragraph(client.getGstNumber())).setFont(font).setFontSize(11).setPaddingLeft(14));
        }
        table.addCell(new Cell().add(new Paragraph("PAN No:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(client.getPanNumber())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Payment Ref. No.:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(order.getPaymentRefrence())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Mode of Payment:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(order.getPaymentMethod())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Payment Date & Time:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(invoice.getInvoiceDate().toString())).setFont(font).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph("Billing Address:")).setFontSize(11).setPaddingLeft(14));
        table.addCell(new Cell().add(new Paragraph(client.getStreet() + ",\n" + client.getCity() + ",\n" + client.getState() + "-" + client.getZipcode())).setFont(font).setFontSize(11).setPaddingLeft(14));
        document.add(table);

        document.add(new Paragraph(" ")
                .setFont(font)
                .setFontSize(10)
                .setMarginTop(20));

        float[] itemColumnWidths = {1, 5, 3, 3, 3, 3, 3};
        Table itemTable = new Table(UnitValue.createPercentArray(itemColumnWidths));
        itemTable.addHeaderCell("Sr. No.").setFont(bold).setTextAlignment(TextAlignment.CENTER);
        itemTable.addHeaderCell("Plan Details");
        itemTable.addHeaderCell("SAC");
        itemTable.addHeaderCell("MRP");
        itemTable.addHeaderCell("Discount");
        itemTable.addHeaderCell("Wallet Amount");
        itemTable.addHeaderCell("Taxable Amount");
        document.add(itemTable);

        float[] itemColumnRowWidths = {1, 5, 3, 3, 3, 3, 3};
        Table itemRowTable = new Table(UnitValue.createPercentArray(itemColumnRowWidths)).useAllAvailableWidth();
        itemRowTable.addCell("1").setTextAlignment(TextAlignment.CENTER);
        itemRowTable.addCell("MRP" + " " + plan.getRechargeAmount());
        itemRowTable.addCell(plan.getCategoryId().getSacCode());
        itemRowTable.addCell(plan.getRechargeAmount().toString());
        itemRowTable.addCell(String.valueOf(invoice.getDiscountAmount()));
        itemRowTable.addCell(String.valueOf(invoice.getWalletAmountUsed()));
        itemRowTable.addCell(String.valueOf(invoice.getTaxableAmount()));
        document.add(itemRowTable);

        float[] taxColumnWidths = {6,1};
        Table taxTable = new Table(UnitValue.createPercentArray(taxColumnWidths)).useAllAvailableWidth();
        taxTable.addCell(new Cell().add(new Paragraph("Total Taxable Amount")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxableAmount()))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph("CGST (9%)")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxAmount()/2))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph("SGST (9%)")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxAmount()/2))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph("Total Paid Amount")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTotalAmount()))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        document.add(taxTable);

        document.add(new Paragraph("\nTelecommunication services to be provided by Uninor Infocomm Limited\n" +
                "All disputes are subjected to Gujarat Jurisdiction\n" +
                "Tax is not payable under Reverse Charge basis for this supply.\n")
                .setFont(font)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(30));

        document.close();

        return httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + File.separator + client.getClientId() + File.separator + "invoices" + File.separator + "Invoice-" +  invoice.getInvoiceNumber() + ".pdf";
    }

    public ResponseEntity<Map<String ,String>> buyPostPaidPlan(int planId, HttpServletRequest httpServletRequest){

        Map<String,String> responseMap = new HashMap<>();
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if(simCardList.isEmpty()){
            throw new DataNotFoundException("Client data not found");
        }

        SimCard simCard = simCardList.get(0);
        if(simCard.getSimType() == SimType.PREPAID.getSimCardTypeId()){
            throw new InvalidDataFoundException("Cannot buy postpaid plan for prepaid sim type.");
        }

        Plan plan = this.planRepository.getPlanById(planId);
        if(plan == null){
            throw new DataNotFoundException("Invalid Plan");
        }
        List<PlanActivation> planActivationList = this.planActivationRepository.getActivePlanByMobile(mobileNumber);
        if(planActivationList.isEmpty()){
            PlanActivation planActivation = new PlanActivation();
            PlanUsage planUsage = new PlanUsage();

            planUsage.setAdditionalData(0);
            planUsage.setSmsUsage(plan.getSmsAllowance());
            planUsage.setDataUsage(plan.getDataAllowance());
            planUsage.setPostpaidDataOver(false);

            planActivation.setActive(true);
            planActivation.setServiceChange(false);
            planActivation.setReactiveAvailable(false);
            planActivation.setActivationDate(LocalDateTime.now());
            planActivation.setExpired(false);
            planActivation.setPlan(plan);
            planActivation.setSimCard(simCard);
            planActivation.setPlanUsage(planUsage);
            planActivation.setExpirationDate(LocalDateTime.now().plusDays(plan.getValidity()));
            this.planActivationRepository.savePlanActivation(planActivation);
            this.planRepository.updatePlanBoughtCount(plan);
            responseMap.put("message","Plan Bought Successfully");
            return new ResponseEntity<>(responseMap,new HttpHeaders(),HttpStatus.OK);
        }else {
            throw new InvalidDataFoundException("You cannot buy plan as there is already a active plan");
        }




    }

    public ResponseEntity<Map<String,String>> simDeactivationRequest(HttpServletRequest httpServletRequest){

        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        Map<String, String> responseMap = new HashMap<>();
        if(simCardList.isEmpty()){
            throw new DataNotFoundException("Client data not found!");
        }
        SimCard simCard = simCardList.get(0);
        ClientRequest clientRequests = this.clientRequestRepository.getSimDeactivationRequest(simCard);
        if(clientRequests != null){
            responseMap.put("message", "Sim Card deactivation request already created!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

        if(simCard.getSimType() == SimType.POSTPAID.getSimCardTypeId()){
            List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPostpaidPlan(simCard);
            if(!planActivationList.isEmpty()){
                throw new InvalidDataFoundException("Pay all current dues then only you can deactivate the SIM");
            }
        }

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestType(ClientRequestTypeEnum.DEACTIVATION.getRequestTypeId());
        clientRequest.setRequestStatus(RequestStatusEnum.PENDING.getRequestStatusId());
        clientRequest.setSimCard(simCard);
        this.clientRequestRepository.saveClientRequest(clientRequest);
        this.smsService.sendNewRequestSms(simCard.getPhoneNumber(), "deactivation");
        this.clientRepository.setDeactivationFlag(simCard.getClient().getClientId(), true);
        responseMap.put("message","Sim deactivation request created!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);

    }

    public ResponseEntity<Map<String,String>> updateUserProfile(UpdateProfileDetailsDto updateProfileDetailsDto, HttpServletRequest httpServletRequest){
        Map<String,String> responseMap = new HashMap<>();
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }

        String gstPattern = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
        Pattern pattern = Pattern.compile(gstPattern);
        Matcher matcher = pattern.matcher(updateProfileDetailsDto.getGstNumber());

        if(!updateProfileDetailsDto.getGstNumber().isEmpty() && !matcher.matches()){
            throw new InvalidDataFoundException("Invalid GST number");
        }

        client.setFirstName(updateProfileDetailsDto.getFirstName());
        client.setLastName(updateProfileDetailsDto.getLastName());
        client.setEmail(updateProfileDetailsDto.getEmail());
        client.setStreet(updateProfileDetailsDto.getStreet());
        client.setCity(updateProfileDetailsDto.getCity());
        client.setState(updateProfileDetailsDto.getState());
        client.setZipcode(updateProfileDetailsDto.getZipcode());
        client.setGstNumber(updateProfileDetailsDto.getGstNumber());

        this.clientRepository.updateClient(client);
        responseMap.put("message","Profile Details Updated");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String ,String>> updateProfilePhoto(CommonsMultipartFile profilePhoto, HttpServletRequest httpServletRequest){

        Map<String,String> responseMap = new HashMap<>();
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }

        if((double)profilePhoto.getSize()/ (1024 * 1024) > 2){
            throw new InvalidDataFoundException("Invalid profile photo");
        }

        String fileExtension = profilePhoto.getOriginalFilename().substring(profilePhoto.getOriginalFilename().lastIndexOf('.') + 1);

        if(!fileExtension.equals("jpeg") && !fileExtension.equals("jpg") && !fileExtension.equals("png")){
            throw new InvalidDataFoundException("Invalid profile photo");
        }

        ClientDocuments clientDocuments = client.getClientDocuments();
        clientDocuments.setProfilePhotoExtension(fileExtension);

        client.setClientDocuments(clientDocuments);
        try {
            String path = System.getenv("UninorUploadPath");
            String fullPath = path + clientId + "/" + "ProfilePhoto" + "." + fileExtension;

            byte[] data = profilePhoto.getBytes();
            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(data);
            fos.close();

        } catch (Exception e) {
            throw new InvalidFileException("File Upload Failed. Try again!");
        }
        client.setClientDocuments(clientDocuments);
        clientRepository.updateClient(client);
        responseMap.put("profilePhotoUpdate","Profile Photo Successfully Updated");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,List<NotificationDto>>> getAllCurrentClientNotificaitons(HttpServletRequest httpServletRequest){
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }

        Map<String , List<NotificationDto>> responseMap = new HashMap<>();
        List<NotificationDto> notificationDtos = new ArrayList<>();
        Object[] getNotificationData = this.notificationRepository.getCurrentAvailableClientCoupons(clientId);
        List<Notification> notificationList = (List<Notification>) getNotificationData[0];
        int count = (Integer) getNotificationData[1];

        if(!notificationList.isEmpty()){
            for (Notification notification : notificationList) {
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setHeader(notification.getNotificationHeader());
                notificationDto.setMessage(notification.getMessage());
                notificationDto.setNotificationId(notification.getNotificationId());
                notificationDto.setNewCount(count);
                notificationDto.setNotificationType(notification.getNotificationType().getNotificationId());
                notificationDto.setSendDate(Helper.getNotificationTime(notification.getCreatedDate()));
                notificationDtos.add(notificationDto);
            }
        }

        responseMap.put("notificationDetails", !notificationDtos.isEmpty()?notificationDtos:null);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> removeClientNotification(int notificationId, HttpServletRequest httpServletRequest){

        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }

        String regex = "^[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(notificationId));
        Map<String,String> responseMap = new HashMap<>();
        if (!matcher.matches()){
            throw new InvalidDataFoundException("Invalid notification id");
        }
        this.notificationRepository.deleteNotification(notificationId,clientId);
        responseMap.put("message","Notification Deleted");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> removeAllClientNotification(HttpServletRequest httpServletRequest){

        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }
        Map<String,String> responseMap = new HashMap<>();
        this.notificationRepository.deleteAllClientNotification(clientId);
        responseMap.put("message","All Notification Deleted");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> updateReadReciepts(HttpServletRequest httpServletRequest){
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        Client client = this.clientRepository.getClientById(clientId);

        if(client == null){
            throw new DataNotFoundException("Client data not found");
        }
        Map<String,String> responseMap = new HashMap<>();
        this.notificationRepository.updateClientNotificatinReadReciepts(clientId);
        responseMap.put("message","All Notification Read Receipts updates");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }
}


