package com.uninor.service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.text.DocumentException;
import com.uninor.dto.*;
import com.uninor.enumeration.*;
import com.uninor.exceptions.*;
import com.uninor.helper.*;
import com.uninor.model.*;
import com.uninor.repository.*;
import com.uninor.sms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ClientDashboardService {

    @Autowired
    private SimCardRepository simCardRepository;

    @Autowired
    private PlanActivationRepository planActivationRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    @Autowired
    private RoamingActivationRepository roamingActivationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CuponRepository couponRepository;

    @Autowired
    private InvoiceTableRepository invoiceTableRepository;

    @Autowired
    private ClientRequestRepository clientRequestRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private ClientDocumentsRepository clientDocumentsRepository;

    private String getAvailableData(double data) {
        if (data < 1000) {
            return String.format("%.2f MB", data);
        } else {
            double dataInGB = data / 1000;
            return String.format("%.2f GB", dataInGB);
        }
    }

    public ResponseEntity<Map<String, DashboardDataDto>> getDashboardData(HttpServletRequest httpServletRequest) {
        Map<String, DashboardDataDto> responseMap = new HashMap<>();
        HttpSession session = httpServletRequest.getSession();
        String mobileNumber = (String) session.getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        List<String> planNameList = new ArrayList<>();
        List<Integer> planBoughtCountList = new ArrayList<>();
        List<Plan> planList = this.planRepository.getPopularActivePlans();
        List<String> dateLabel = new ArrayList<>();
        List<Double> dataUsage = new ArrayList<>();

        for (Plan plan : planList) {
            planNameList.add(plan.getCategoryId().getPlanCategory() + " Rs." + plan.getRechargeAmount());
            planBoughtCountList.add(plan.getBoughtCount());
        }

        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }
        SimCard simCard = simCardList.get(0);
        List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPlan(simCard);
        List<RoamingActivation> roamingActivationList = this.roamingActivationRepository.getCurrentSimRoamingPlan(simCard.getSimCardId());
        DashboardDataDto dashboardDataDto = new DashboardDataDto();
        Client client = simCard.getClient();
        List<ClientDailyDataUsage> clientDailyDataUsageList = this.dailyUsageRepository.getLastMonthData(client.getClientId());
        Collections.reverse(clientDailyDataUsageList);
        for (ClientDailyDataUsage clientDailyDataUsage : clientDailyDataUsageList) {
            dateLabel.add(clientDailyDataUsage.getUsageDate().toString());
            dataUsage.add(clientDailyDataUsage.getDailyUsage());
        }
        dashboardDataDto.setSimBlocked(simCard.isBlocked());
        dashboardDataDto.setUsageDateList(dateLabel);
        dashboardDataDto.setUsageAmountList(dataUsage);
        dashboardDataDto.setClientId(client.getClientId());
        dashboardDataDto.setClientName(client.getFirstName() + " " + client.getLastName());
        dashboardDataDto.setMobileType(simCard.getSimType() == 1 ? "Prepaid Mobile" : "Postpaid Mobile");
        dashboardDataDto.setMobileNumber(simCard.getPhoneNumber());
        dashboardDataDto.setWalletAmount(client.getWalletAmount().toString());
        dashboardDataDto.setRoamingActive(simCard.isRoamingActive());
        if (simCard.getSimType() == 1) {
            dashboardDataDto.setSimPrepaid(true);
        } else {
            dashboardDataDto.setSimPrepaid(false);
        }


        if (simCard.isRoamingActive()) {
            if (roamingActivationList.isEmpty()) {
                dashboardDataDto.setPlanExpired(true);
            } else {
                dashboardDataDto.setPlanExpired(false);
                RoamingActivation planActivation = roamingActivationList.get(0);
                Plan plan = planActivation.getRoamingPlan();

                String renewsIn = "";
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
                String expirationDate = planActivation.getExpirationDate().format(outputFormatter);
                dashboardDataDto.setPostpaidExtraDataUsed(false);

                double remainingDataAmount = planActivation.getRoamingDataUsage();
                double totalDataAmount = plan.getDataAllowance();
                double availableDataPercentage = (remainingDataAmount / totalDataAmount) * 100;
                dashboardDataDto.setRemainingDataAmount(getAvailableData(remainingDataAmount));
                dashboardDataDto.setTotalDataAmount(getAvailableData(totalDataAmount));
                dashboardDataDto.setAvailableDataPercentage(String.format("%.2f", availableDataPercentage));
                dashboardDataDto.setPlanExpiryDate(expirationDate);
                double voiceAllowance = Integer.parseInt(plan.getVoiceAllowance().split("\\s+")[0]) * 60;
                dashboardDataDto.setVoiceUsage(String.format("%.2f", (voiceAllowance - planActivation.getVoiceUsage()) / 60) + " min left");
                if (plan.getIsRefreshing()) {
                    LocalTime now = LocalTime.now();
                    LocalTime midnight = LocalTime.of(23, 59, 59);
                    Duration duration = Duration.between(now, midnight);
                    renewsIn = "Renews in " + String.format("%.1f", (duration.toMinutes() / 60.0)) + " hours";
                } else {
                    renewsIn = "Expires on " + expirationDate;
                }
                dashboardDataDto.setRenewTime(renewsIn);
                dashboardDataDto.setPlanAmount(plan.getRechargeAmount().toString());
                dashboardDataDto.setPlanId(plan.getPlanId());
            }
        } else {
            if (planActivationList.isEmpty()) {
                dashboardDataDto.setPlanExpired(true);
            } else {
                dashboardDataDto.setPlanExpired(false);
                PlanActivation planActivation = planActivationList.get(0);
                Plan plan = planActivation.getPlan();
                PlanUsage planUsage = planActivation.getPlanUsage();
                String renewsIn = "";
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
                String expirationDate = planActivation.getExpirationDate().format(outputFormatter);

                if (planUsage.isPostpaidDataOver() && simCard.getSimType() == 2) {
                    dashboardDataDto.setPostpaidExtraDataUsed(true);
                }

                double remainingDataAmount = planUsage.getDataUsage();
                double totalDataAmount = plan.getDataAllowance();
                double availableDataPercentage = (remainingDataAmount / totalDataAmount) * 100;
                dashboardDataDto.setRemainingDataAmount(getAvailableData(remainingDataAmount));
                dashboardDataDto.setTotalDataAmount(getAvailableData(totalDataAmount));
                dashboardDataDto.setAvailableDataPercentage(String.format("%.2f", availableDataPercentage));
                dashboardDataDto.setPlanExpiryDate(expirationDate);

                if (plan.getIsRefreshing()) {
                    LocalTime now = LocalTime.now();
                    LocalTime midnight = LocalTime.of(23, 59, 59);
                    Duration duration = Duration.between(now, midnight);
                    renewsIn = "Renews in " + String.format("%.1f", (duration.toMinutes() / 60.0)) + " hours";
                } else {
                    renewsIn = "Expires on " + expirationDate;
                }

                dashboardDataDto.setRenewTime(renewsIn);
                dashboardDataDto.setPlanAmount(plan.getRechargeAmount().toString());
                dashboardDataDto.setPlanId(plan.getPlanId());
            }
        }


        dashboardDataDto.setTopCategoryBoughtCount(planBoughtCountList);
        dashboardDataDto.setTopCategoryName(planNameList);
        responseMap.put("DashboardDetails", dashboardDataDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, PlanDetailsDto>> getActivePlanDetials(HttpServletRequest httpServletRequestRequest) {
        HttpSession session = httpServletRequestRequest.getSession();
        String mobileNumber = (String) session.getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }
        Map<String, PlanDetailsDto> responseMap = new HashMap<>();
        SimCard simCard = simCardList.get(0);
        List<PlanActivation> planActivation = this.planActivationRepository.getActiveSimPlan(simCard);
        List<RoamingActivation> roamingActivationList = this.roamingActivationRepository.getCurrentSimRoamingPlan(simCard.getSimCardId());
        if (simCard.isRoamingActive()) {
            if (roamingActivationList.isEmpty()) {
                throw new DataNotFoundException("No Plan Found! Please try again");
            } else {
                Plan plan = roamingActivationList.get(0).getRoamingPlan();
                PlanDetailsDto planDetailsDto = getPlanDetials(plan);
                responseMap.put("Plan", planDetailsDto);
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            }
        } else {
            try {
                Plan plan = planActivation.get(0).getPlan();
                PlanDetailsDto planDetailsDto = getPlanDetials(plan);
                responseMap.put("Plan", planDetailsDto);
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            } catch (IndexOutOfBoundsException e) {
                throw new DataNotFoundException("No Plan Found! Please try again");
            }
        }
    }

    private PlanDetailsDto getPlanDetials(Plan plan) {
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
        if (plan.getIsRefreshing()) {
            totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
        } else {
            totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
        }
        planDetails.setTotalData(totalData);
        return planDetails;
    }

    public ResponseEntity<Map<String, String>> toggleSimType(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String mobileNumber = (String) session.getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        Map<String, String> responseMap = new HashMap<>();
        SimCard simCard = simCardList.get(0);
        if (simCard.getSimType() == SimType.PREPAID.getSimCardTypeId()) {
            simCard.setRoamingActive(false);
            simCard.setSimType(2);
            simCard.setPlanActive(false);
            this.planActivationRepository.expireAllUnexpiredSimPlan(simCard.getSimCardId());
            this.roamingActivationRepository.expireAllUnexpiredRoamingSimPlan(simCard.getSimCardId());
            responseMap.put("messages", "Switched to postpaid");
            responseMap.put("serviceType", "postpaid");
            responseMap.put("payment", "false");

        } else {
            List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPostpaidPlan(simCard);
            if (planActivationList.isEmpty()) {
                simCard.setSimType(1);
                responseMap.put("messages", "Switched to prepaid");
                responseMap.put("serviceType", "prepaid");
                responseMap.put("payment", "false");
            } else {
                responseMap.put("serviceType", "prepaid");
                responseMap.put("messages", "Proceed towards payment");
                responseMap.put("payment", "true");
            }
        }
        simCardRepository.addOrUpdateSimCard(simCard);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, PostPaidPlanDetailsPaymentDto>> getPostPaidPaymentDetails(HttpServletRequest httpServletRequest) {
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client Data not found! Login again!");
        }
        SimCard simCard = simCardList.get(0);
        Client client = simCard.getClient();
        Map<String, PostPaidPlanDetailsPaymentDto> responseMap = new HashMap<>();
        List<PlanActivation> listActivePlan = this.planActivationRepository.getActiveSimPostpaidPlan(simCard);
        if (listActivePlan.isEmpty()) {
            throw new DataNotFoundException("No Active Plan! Please try again");
        }

        Plan plan = listActivePlan.get(0).getPlan();
        PlanUsage planUsage = listActivePlan.get(0).getPlanUsage();
        PostPaidPlanDetailsPaymentDto planDetails = new PostPaidPlanDetailsPaymentDto();
        planDetails.setPlanId(plan.getPlanId());
        planDetails.setPlanAmount(plan.getRechargeAmount());
        planDetails.setPlanValidity(plan.getValidity());
        planDetails.setSmsAllowance(plan.getSmsAllowance());
        planDetails.setDataAmount((plan.getDataAllowance() / 1000));
        planDetails.setAdditionalData((plan.getExtraData() / 1000));
        planDetails.setDailyRefreshing(plan.getIsRefreshing());
        planDetails.setVoiceAllowance(plan.getVoiceAllowance());
        Double totalData;
        if (plan.getIsRefreshing()) {
            totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
        } else {
            totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
        }

        planDetails.setTotalData(totalData);
        planDetails.setWalletAmount(client.getWalletAmount());
        Double taxApplied = (Helper.CGST + Helper.SGST) / 100;
        Double planPrice = plan.getRechargeAmount() / (1 + taxApplied);
        planPrice = Double.valueOf(String.format("%.2f", planPrice));

        Double taxAmount = planPrice * taxApplied;
        taxAmount = Double.valueOf(String.format("%.2f", taxAmount));

        planDetails.setTaxAmount(taxAmount);
        planDetails.setPlanPriceWithoutTax(planPrice);
        if (planUsage.isPostpaidDataOver()) {
            planDetails.setSmsUsed(planUsage.getSmsUsage() + " SMS");
            planDetails.setSmsCharge(planUsage.getSmsUsage() * Helper.POSTPAID_SMS_CHARGES);
            planDetails.setDataUsed(getAvailableData(planUsage.getDataUsage()));
            planDetails.setDataCharges(planUsage.getDataUsage() / 1000 * Helper.POSTPAID_DATA_CHARGES);
            planDetails.setTotalAmount(plan.getRechargeAmount() + planUsage.getSmsUsage() * Helper.POSTPAID_SMS_CHARGES + planUsage.getDataUsage() / 1000 * Helper.POSTPAID_DATA_CHARGES);
            planDetails.setPayableAmount(plan.getRechargeAmount() + planUsage.getSmsUsage() * Helper.POSTPAID_SMS_CHARGES + planUsage.getDataUsage() / 1000 * Helper.POSTPAID_DATA_CHARGES);
        } else {
            planDetails.setSmsUsed("0 SMS");
            planDetails.setSmsCharge(0.0);
            planDetails.setDataUsed("0.00 GB");
            planDetails.setDataCharges(0.0);
            planDetails.setTotalAmount(plan.getRechargeAmount());
            planDetails.setPayableAmount(plan.getRechargeAmount());
        }
        planDetails.setExtraDataCharges("Rs." + Helper.POSTPAID_DATA_CHARGES + "/GB");
        planDetails.setExtraSmsCharges("Rs." + Helper.POSTPAID_SMS_CHARGES + "/SMS");

        responseMap.put("Plan", planDetails);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);

    }

    public ResponseEntity<Map<String, UpdatePostpaidPaymentDetailsDto>> verifyPostPaidBillingDetails(CuponWalletDto cuponWalletDto, HttpServletRequest httpServletRequest) {

        double extraSmsCharges = 0.00;
        double extraDataCharges = 0.00;
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("No SimCard Found");
        }
        List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPostpaidPlan(simCardList.get(0));
        Plan plan = planActivationList.get(0).getPlan();
        PlanUsage planUsage = planActivationList.get(0).getPlanUsage();
        Map<String, UpdatePostpaidPaymentDetailsDto> responseMap = new HashMap<>();
        if (cuponWalletDto.getEnteredWalletAmount().isEmpty()) {
            cuponWalletDto.setEnteredWalletAmount("0");
        }
        UpdatePostpaidPaymentDetailsDto updateBuyPlanDetailsDto = new UpdatePostpaidPaymentDetailsDto();
        Cupon validCupon;
        boolean isWalletAmountEmpty = false;
        boolean isCuponEmpty = false;
        Client client = simCardList.get(0).getClient();
        if (client == null) {
            throw new DataNotFoundException("No such client found!");
        }

        if (plan == null) {
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
        updateBuyPlanDetailsDto.setExtraSmsCharges("Rs." + Helper.POSTPAID_SMS_CHARGES + "/SMS");
        updateBuyPlanDetailsDto.setExtraDataCharges("Rs." + Helper.POSTPAID_DATA_CHARGES + "/GB");

        if (planUsage.isPostpaidDataOver()) {
            updateBuyPlanDetailsDto.setSmsUsed(planUsage.getSmsUsage() + " SMS");
            extraSmsCharges = planUsage.getSmsUsage() * Helper.POSTPAID_SMS_CHARGES;
            updateBuyPlanDetailsDto.setSmsCharge(extraSmsCharges);
            extraDataCharges = (planUsage.getDataUsage() / 1000) * Helper.POSTPAID_DATA_CHARGES;
            updateBuyPlanDetailsDto.setDataCharges(extraDataCharges);
            updateBuyPlanDetailsDto.setDataUsed(getAvailableData(planUsage.getDataUsage()));
        } else {
            updateBuyPlanDetailsDto.setSmsUsed("0 SMS");
            updateBuyPlanDetailsDto.setSmsCharge(extraSmsCharges);
            updateBuyPlanDetailsDto.setDataCharges(extraDataCharges);
            updateBuyPlanDetailsDto.setDataUsed("0 GB");
        }

        double totalData;
        if (plan.getIsRefreshing()) {
            totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
        } else {
            totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
        }

        updateBuyPlanDetailsDto.setTotalData(totalData);
        updateBuyPlanDetailsDto.setWalletAmount(client.getWalletAmount());


        List<ClientCupons> cuponList = this.couponRepository.getClientCuponByCuponCode(client.getClientId(), cuponWalletDto.getCuponCode());

        if (cuponWalletDto.getCuponCode().isEmpty()) {
            isCuponEmpty = true;
            updateBuyPlanDetailsDto.setCuponApplied(false);
        }

        if (cuponList.isEmpty()) {
            updateBuyPlanDetailsDto.setCuponErrorMessage("Invalid Cupon Code!");
            updateBuyPlanDetailsDto.setCuponError(true);
        } else {
            ClientCupons clientCupons = cuponList.get(0);
            if (clientCupons.isExpired()) {
                updateBuyPlanDetailsDto.setCuponErrorMessage("Cupon code Expired!");
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }
            if (clientCupons.isUsed()) {
                updateBuyPlanDetailsDto.setCuponErrorMessage("Cupon code already used");
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }


            if (!clientCupons.isExpired() && !clientCupons.isUsed()) {
                validCupon = clientCupons.getCupon();
                updateBuyPlanDetailsDto.setCuponError(false);
            }
        }

        if (!cuponWalletDto.getEnteredWalletAmount().isEmpty() && !cuponWalletDto.getEnteredWalletAmount().equals("0")) {
            if (client.getWalletAmount() >= Double.parseDouble(cuponWalletDto.getEnteredWalletAmount())) {
                updateBuyPlanDetailsDto.setWalletError(false);
            } else {
                cuponWalletDto.setEnteredWalletAmount("0");
                updateBuyPlanDetailsDto.setWalletError(true);
                updateBuyPlanDetailsDto.setWalletErrorMessage("Insufficient Wallet Balance");
            }
        } else {
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
        double taxAmount = (Helper.CGST + Helper.SGST) / 100;

        double planPriceWithoutTax = planAmountWithTax / (1 + taxAmount);
        planPriceWithoutTax = Double.valueOf(String.format("%.2f", planPriceWithoutTax));
        finalPlanPriceWithoutTax = planPriceWithoutTax;
        double discountAmount = 0.00;

        double planTax = planPriceWithoutTax * taxAmount;
        planTax = Double.parseDouble(String.format("%.2f", planTax));
        finalTaxApplied = planTax;
        finalPriceWithoutWallet = plan.getRechargeAmount();
        finalpayableAmount = plan.getRechargeAmount();

        double priceWithoutWallet = planAmountWithTax;
        double payableAmount = planAmountWithTax;

        if (!updateBuyPlanDetailsDto.isCuponError()) {
            Cupon cupon = cuponList.get(0).getCupon();
            if (cupon.isDeductable()) {
                double discountPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                discountAmount = planPriceWithoutTax * discountPercent <= cupon.getMaxRewardAmount() + 0.99 ? planPriceWithoutTax * discountPercent : cupon.getMaxRewardAmount();
                discountAmount = Double.parseDouble(String.format("%.2f", discountAmount));
                finalDiscountApplied = discountAmount;

                planTax = (planPriceWithoutTax - discountAmount) * taxAmount;
                planTax = Double.parseDouble(String.format("%.2f", planTax));
                finalTaxApplied = planTax;

                priceWithoutWallet = planPriceWithoutTax - discountAmount + planTax;
                payableAmount = priceWithoutWallet;
                finalPriceWithoutWallet = priceWithoutWallet;
                finalpayableAmount = priceWithoutWallet;
                updateBuyPlanDetailsDto.setCuponApplied(true);
            }

            if (!cupon.isCashback() && !cupon.isDeductable()) {
                updateBuyPlanDetailsDto.setCuponError(true);
                updateBuyPlanDetailsDto.setCuponErrorMessage("Coupon invalid here. Redeem from rewards.");
                updateBuyPlanDetailsDto.setCuponApplied(false);
            }
        }

        if (priceWithoutWallet < Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) + 10.0) {
            double minWalletAmount = priceWithoutWallet <= client.getWalletAmount() ? priceWithoutWallet : client.getWalletAmount();
            cuponWalletDto.setEnteredWalletAmount(String.valueOf(minWalletAmount));
        }
        updateBuyPlanDetailsDto.setUsedWalletAmount(Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()));
        if (!updateBuyPlanDetailsDto.isWalletError()) {
            payableAmount = payableAmount - Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            payableAmount = Double.parseDouble(String.format("%.2f", payableAmount));

            if (payableAmount == 0) {
                payableAmount += 10.00;
                double walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) - 10.00;
                updateBuyPlanDetailsDto.setUsedWalletAmount(Double.parseDouble(String.format("%.2f", walletAmountUsed)));
            }

            finalpayableAmount = payableAmount;

        }

        if (isWalletAmountEmpty) {
            updateBuyPlanDetailsDto.setWalletError(false);
        }

        if (isCuponEmpty) {
            updateBuyPlanDetailsDto.setCuponError(false);
        }

        finalPriceWithoutWallet = finalPriceWithoutWallet + extraSmsCharges + extraDataCharges;
        finalpayableAmount = finalpayableAmount + extraSmsCharges + extraDataCharges;

        updateBuyPlanDetailsDto.setDiscountApplied(finalDiscountApplied);
        updateBuyPlanDetailsDto.setFinalPayableAmount(finalpayableAmount);
        updateBuyPlanDetailsDto.setPlanAmount(plan.getRechargeAmount());
        updateBuyPlanDetailsDto.setPlanPriceWithoutWallet(finalPriceWithoutWallet);
        updateBuyPlanDetailsDto.setPlanPriceWithoutTax(finalPlanPriceWithoutTax);
        updateBuyPlanDetailsDto.setTaxAmount(finalTaxApplied);
        responseMap.put("updatedDetails", updateBuyPlanDetailsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> payPostpaidRechargeBill(CuponWalletDto cuponWalletDto, HttpServletRequest httpServletRequest) throws DocumentException, IOException {

        double extraSmsCharges = 0.00;
        double extraDataCharges = 0.00;
        boolean cuponError = false;
        boolean walletError = false;
        String cuponErrorMessage = "";
        String walletErrorMessage = "";
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("No SimCard Found");
        }
        SimCard simCard = simCardList.get(0);
        List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPostpaidPlan(simCard);
        PlanActivation planActivation = planActivationList.get(0);
        Plan plan = planActivation.getPlan();
        PlanUsage planUsage = planActivationList.get(0).getPlanUsage();
        Map<String, String> responseMap = new HashMap<>();
        if (cuponWalletDto.getEnteredWalletAmount().isEmpty()) {
            cuponWalletDto.setEnteredWalletAmount("0");
        }

        boolean isWalletAmountEmpty = false;
        boolean isCuponEmpty = false;
        Client client = simCardList.get(0).getClient();
        if (client == null) {
            throw new DataNotFoundException("No such client found!");
        }

        if (plan == null) {
            throw new DataNotFoundException("No such Plan found!");
        }

        if (planUsage.isPostpaidDataOver()) {

            extraSmsCharges = planUsage.getSmsUsage() * Helper.POSTPAID_SMS_CHARGES;
            extraDataCharges = (planUsage.getDataUsage() / 1000) * Helper.POSTPAID_DATA_CHARGES;

        } else {
            extraSmsCharges = 0.00;
            extraDataCharges = 0.00;
        }

        List<ClientCupons> cuponList = this.couponRepository.getClientCuponByCuponCode(client.getClientId(), cuponWalletDto.getCuponCode());

        if (cuponWalletDto.getCuponCode().isEmpty()) {
            isCuponEmpty = true;
        }

        if (cuponList.isEmpty()) {
            cuponError = true;
            cuponErrorMessage = "Invalid Cupon Code. ";
        } else {
            ClientCupons clientCupons = cuponList.get(0);
            if (clientCupons.isExpired()) {
                cuponError = true;
                cuponErrorMessage = "Cupon Code Expired. ";
            }
            if (clientCupons.isUsed()) {
                cuponError = true;
                cuponErrorMessage = "Cupon Code already used. ";
            }

            if (!clientCupons.isExpired() && !clientCupons.isUsed()) {
                cuponError = false;
            }
        }

        if (!cuponWalletDto.getEnteredWalletAmount().isEmpty() && !cuponWalletDto.getEnteredWalletAmount().equals("0")) {
            if (!(client.getWalletAmount() >= Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()))) {
                cuponWalletDto.setEnteredWalletAmount("0");
                walletError = true;
                walletErrorMessage = "Insufficient Wallet Amount";
            }
        } else {
            walletError = true;
            isWalletAmountEmpty = true;
        }


        //Main Calculations
        double cashbackAmount = 0;
        double walletAmountUsed = 0;
        double finalPlanPriceWithoutTax = 0;
        double finalTaxApplied = 0;
        double finalPriceWithoutWallet = 0;
        double finalDiscountApplied = 0;
        double finalpayableAmount = 0;
        double finalTaxableAmount = 0;

        double planAmountWithTax = plan.getRechargeAmount();
        double taxAmount = (Helper.CGST + Helper.SGST) / 100;

        double planPriceWithoutTax = planAmountWithTax / (1 + taxAmount);
        planPriceWithoutTax = Double.parseDouble(String.format("%.2f", planPriceWithoutTax));
        finalPlanPriceWithoutTax = planPriceWithoutTax;
        finalTaxableAmount = finalPlanPriceWithoutTax;
        double discountAmount = 0.00;

        double planTax = planPriceWithoutTax * taxAmount;
        planTax = Double.parseDouble(String.format("%.2f", planTax));
        finalTaxApplied = planTax;
        finalPriceWithoutWallet = plan.getRechargeAmount();
        finalpayableAmount = plan.getRechargeAmount();

        double priceWithoutWallet = planAmountWithTax;
        double payableAmount = planAmountWithTax;

        if (!cuponError) {
            Cupon cupon = cuponList.get(0).getCupon();
            if (cupon.isDeductable()) {
                double discountPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                discountAmount = planPriceWithoutTax * discountPercent <= cupon.getMaxRewardAmount() + 0.99 ? planPriceWithoutTax * discountPercent : cupon.getMaxRewardAmount();
                discountAmount = Double.parseDouble(String.format("%.2f", discountAmount));
                finalDiscountApplied = discountAmount;
                finalTaxableAmount = finalTaxableAmount - finalDiscountApplied;
                planTax = (planPriceWithoutTax - discountAmount) * taxAmount;
                planTax = Double.parseDouble(String.format("%.2f", planTax));
                finalTaxApplied = planTax;

                priceWithoutWallet = planPriceWithoutTax - discountAmount + planTax;
                payableAmount = priceWithoutWallet;
                finalPriceWithoutWallet = priceWithoutWallet;
                finalpayableAmount = priceWithoutWallet;
            }

            if (cupon.isCashback()) {
                double cashbackPercent = Double.parseDouble(cupon.getRewardAmount().split("%")[0]) / 100;
                cashbackAmount = finalTaxableAmount * cashbackPercent <= cupon.getMaxRewardAmount() ? finalTaxableAmount * cashbackPercent : cupon.getMaxRewardAmount();
                cashbackAmount = Double.parseDouble(String.format("%.2f", cashbackAmount));
            }

            if (!cupon.isCashback() && !cupon.isDeductable()) {
                cuponError = true;
                cuponErrorMessage = "Coupon invalid here. Redeem from rewards. ";
            }
        }

        if (priceWithoutWallet < Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) + 10.0) {
            double minWalletAmount = priceWithoutWallet <= client.getWalletAmount() ? priceWithoutWallet : client.getWalletAmount();
            cuponWalletDto.setEnteredWalletAmount(String.valueOf(minWalletAmount));
        }

        if (!walletError) {
            payableAmount = payableAmount - Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            payableAmount = Double.parseDouble(String.format("%.2f", payableAmount));

            if (payableAmount == 0) {
                payableAmount += 10.00;
                walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount()) - 10.00;
            } else {
                walletAmountUsed = Double.parseDouble(cuponWalletDto.getEnteredWalletAmount());
            }

            finalpayableAmount = payableAmount;

        }

        if (isWalletAmountEmpty) {
            walletError = false;
        }

        if (isCuponEmpty) {
            cuponError = false;
        }

        finalPriceWithoutWallet = finalPriceWithoutWallet + extraSmsCharges + extraDataCharges;
        finalpayableAmount = finalpayableAmount + extraSmsCharges + extraDataCharges;

        if ((!cuponList.isEmpty() && cuponError) || walletError) {
            throw new InvalidDataFoundException(cuponErrorMessage + walletErrorMessage);
        } else {
            OrderTable orderTable = saveOrderDetails(client, plan, simCard, finalpayableAmount, finalDiscountApplied);
            InvoiceTable invoiceTable = saveInvoiceTableDetails(client, orderTable, mobileNumber, finalDiscountApplied, finalTaxApplied, finalpayableAmount, finalTaxableAmount, walletAmountUsed, planUsage, extraDataCharges, extraSmsCharges, planActivation);

            if (!cuponError && !cuponList.isEmpty()) {
                ClientCupons updateClientCupon = cuponList.get(0);
                Cupon cupon = updateClientCupon.getCupon();
                if (cupon.isDeductable() || cupon.isCashback()) {
                    this.couponRepository.updateClientCupons(updateClientCupon);
                }

            }
            client.setWalletAmount(client.getWalletAmount() - walletAmountUsed + cashbackAmount);
            this.clientRepository.updateClient(client);
            this.invoiceTableRepository.saveInvoiceTableDetails(invoiceTable);
            String filePath = generatePostPaidPlanInvoice(client, simCard, invoiceTable, orderTable, plan);
            responseMap.put("filePath", filePath);
        }
        planActivation.setActive(false);
        planActivation.setExpired(true);
        planActivation.setServiceChange(true);
        this.planActivationRepository.updatePlanActivation(planActivation);

        simCard.setSimType(1);
        simCard.setPlanActive(false);
        this.simCardRepository.addOrUpdateSimCard(simCard);
        responseMap.put("messages", "Bill paid successfully!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public String generatePostPaidPlanInvoice(Client client, SimCard simCard, InvoiceTable invoice, OrderTable order, Plan plan) throws IOException, DocumentException {

        String folderPath = System.getenv("UninorUploadPath") + client.getClientId() + File.separator + "invoices";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = folderPath + File.separator + "Invoice-" + invoice.getInvoiceNumber() + ".pdf";
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
        if (client.getGstNumber() != null) {
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

        float[] itemColumnWidths = {1, 2, 3, 3, 3, 3, 3, 3};
        Table itemTable = new Table(UnitValue.createPercentArray(itemColumnWidths));
        itemTable.addHeaderCell("Sr. No.").setFont(bold).setTextAlignment(TextAlignment.CENTER);
        itemTable.addHeaderCell("Plan Code");
        itemTable.addHeaderCell("Plan Details");
        itemTable.addHeaderCell("SAC");
        itemTable.addHeaderCell("MRP");
        itemTable.addHeaderCell("Discount");
        itemTable.addHeaderCell("Wallet Amount");
        itemTable.addHeaderCell("Taxable Amount");
        document.add(itemTable);

        float[] itemColumnRowWidths = {1, 2, 3, 3, 3, 3, 3, 3};
        Table itemRowTable = new Table(UnitValue.createPercentArray(itemColumnRowWidths)).useAllAvailableWidth();
        itemRowTable.addCell("1").setTextAlignment(TextAlignment.CENTER);
        itemRowTable.addCell(plan.getPlanCode()).setTextAlignment(TextAlignment.CENTER);
        itemRowTable.addCell("MRP" + " " + plan.getRechargeAmount());
        itemRowTable.addCell(plan.getCategoryId().getSacCode());
        itemRowTable.addCell(plan.getRechargeAmount().toString());
        itemRowTable.addCell(String.valueOf(invoice.getDiscountAmount()));
        itemRowTable.addCell(String.valueOf(invoice.getWalletAmountUsed()));
        itemRowTable.addCell(String.valueOf(invoice.getTaxableAmount()));
        document.add(itemRowTable);

        float[] taxColumnWidths = {6, 1};
        Table taxTable = new Table(UnitValue.createPercentArray(taxColumnWidths)).useAllAvailableWidth();
        taxTable.addCell(new Cell().add(new Paragraph("Total Taxable Amount")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxableAmount()))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph("CGST (9%)")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxAmount() / 2))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph("SGST (9%)")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        taxTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTaxAmount() / 2))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        document.add(taxTable);

        float[] postPaidDataWidths = {4, 1, 1, 1};
        Table postPaidDataTable = new Table(UnitValue.createPercentArray(postPaidDataWidths)).useAllAvailableWidth();
        postPaidDataTable.addCell(new Cell().add(new Paragraph("Data Charges")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraDataUsed() + " GB")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraDataUnitCharges() + " Rs/GB")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraDataCharges() + " Rs")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph("SMS Charges")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraSmsUsed() + " SMS")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraSmsUnitCharges() + " Rs/SMS")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        postPaidDataTable.addCell(new Cell().add(new Paragraph(invoice.getExtraSmsCharges() + " Rs")).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        document.add(postPaidDataTable);


        float[] finalColumnWidths = {6, 1};
        Table finalAmountTable = new Table(UnitValue.createPercentArray(taxColumnWidths)).useAllAvailableWidth();
        finalAmountTable.addCell(new Cell().add(new Paragraph("Total Paid Amount")).setFont(bold).setPaddingRight(5).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        finalAmountTable.addCell(new Cell().add(new Paragraph(String.valueOf(invoice.getTotalAmount()))).setFont(font).setPaddingLeft(5).setTextAlignment(TextAlignment.CENTER).setFontSize(12));
        document.add(finalAmountTable);


        document.add(new Paragraph("\nTelecommunication services to be provided by Uninor Infocomm Limited\n" +
                "All disputes are subjected to Gujarat Jurisdiction\n" +
                "Tax is not payable under Reverse Charge basis for this supply.\n")
                .setFont(font)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(30));

        document.close();
        return filePath;
    }

    public InvoiceTable saveInvoiceTableDetails(Client client, OrderTable orderTable, String mobileNumber, double discountAmount, double taxAmount, double totalAmount, double taxableAmount, double walletAmountUsed, PlanUsage planUsage, double extraDataCharges, double extraSmsCharges, PlanActivation planActivation) {

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
        if (planUsage.isPostpaidDataOver()) {
            invoiceTable.setExtraDataUsed(planUsage.getDataUsage() / 1000);
            invoiceTable.setExtraSmsUsed(planUsage.getSmsUsage());
        } else {
            invoiceTable.setExtraDataUsed(0);
            invoiceTable.setExtraSmsUsed(0);
        }
        invoiceTable.setExtraDataCharges(extraDataCharges);
        invoiceTable.setExtraSmsCharges(extraSmsCharges);
        invoiceTable.setExtraDataUnitCharges(Helper.POSTPAID_DATA_CHARGES);
        invoiceTable.setExtraSmsUnitCharges(Helper.POSTPAID_SMS_CHARGES);
        invoiceTable.setPlanBoughtDate(planActivation.getBoughtDate());
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

    public ResponseEntity<Map<String, List<ClientCuponDetailsDto>>> getClientCuponDetails(HttpServletRequest httpServletRequest) {
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");

        List<ClientCupons> clientCuponsList = this.couponRepository.getClientCupons(clientId);

        List<ClientCuponDetailsDto> clientCuponDetailsDtos = clientCuponsList.stream().map(clientCupons -> new ClientCuponDetailsDto(clientCupons.getClientCuponId(), clientCupons.getCuponCode(), clientCupons.getCupon().getDescription(),
                clientCupons.getCupon().getRewardAmount(), clientCupons.getExpirationDate().toString(), clientCupons.isExpired(), clientCupons.isUsed(), clientCupons.getCupon().isCashback(), clientCupons.getCupon().isDeductable(), clientCupons.getCupon().getMaxRewardAmount())).collect(Collectors.toList());

        Map<String, List<ClientCuponDetailsDto>> responseMap = new HashMap<>();
        responseMap.put("cuponList", clientCuponDetailsDtos);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> redeemClientCupon(String coupon, HttpServletRequest httpServletRequest) {
        String cuponCode = coupon.toUpperCase();
        String cuponRegex = "^[A-Z0-9]{6}$";
        Pattern pattern = Pattern.compile(cuponRegex);
        Matcher matcher = pattern.matcher(cuponCode);

        if (!matcher.matches()) {
            throw new InvalidDataFoundException("Invalid Cupon Code");
        }

        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        Map<String, String> responseMap = new HashMap<>();
        List<SimCard> currentSimCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (currentSimCardList.isEmpty()) {
            throw new InvalidDataFoundException("Client details not found! Log in again.");
        }

        SimCard validSimCard = currentSimCardList.get(0);
        if (validSimCard.isRoamingActive()) {
            throw new InvalidDataFoundException("Coupon cannot be redeemed for current roaming plan!");
        }

        List<ClientCupons> clientCuponsList = this.couponRepository.getClientCuponByCuponCode(clientId, coupon);

        if (clientCuponsList.isEmpty()) {
            throw new DataNotFoundException("Invalid Cupon Code");
        }

        ClientCupons clientCupons = clientCuponsList.get(0);
        if (clientCupons.isUsed() || clientCupons.isExpired()) {
            throw new InvalidDataFoundException("Invalid Coupon Code!");
        }

        if (clientCupons.getCupon().isDeductable() || clientCupons.getCupon().isCashback()) {
            throw new InvalidDataFoundException("Coupon cannot be redeemed here! Use it during recharge payment");
        }

        List<PlanActivation> planActivationList = this.planActivationRepository.getActivePlanByMobile(mobileNumber);
        if (planActivationList.isEmpty()) {
            throw new InvalidDataFoundException("You need an active plan to redeem this reward!");
        }

        PlanActivation planActivation = planActivationList.get(0);
        PlanUsage planUsage = planActivation.getPlanUsage();
        SimCard simCard = planActivation.getSimCard();


        double currentAdditionalUsage = planUsage.getAdditionalData();
        double couponReward = Double.parseDouble(clientCupons.getCupon().getRewardAmount().split("\\s+")[0]);
        if (simCard.getSimType() == SimType.PREPAID.getSimCardTypeId()) {
            planUsage.setAdditionalData(currentAdditionalUsage + couponReward);
        } else {
            boolean isPostpaidDataOver = planUsage.isPostpaidDataOver();
            if (isPostpaidDataOver) {
                double currentUsage = planUsage.getDataUsage();
                if (currentUsage >= couponReward) {
                    planUsage.setDataUsage(currentUsage - couponReward);
                } else {
                    planUsage.setPostpaidDataOver(false);
                    planUsage.setDataUsage(couponReward - currentUsage);
                }
            } else {
                double currentUsage = planUsage.getDataUsage();
                planUsage.setDataUsage(currentUsage + couponReward);
            }
        }
        clientCupons.setUsed(true);
        this.couponRepository.updateClientCupons(clientCupons);
        planActivation.setPlanUsage(planUsage);
        this.planActivationRepository.updatePlanActivation(planActivation);
        responseMap.put("success", "Coupon redeemed successfully!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> toggleRoamingStatus(HttpServletRequest httpServletRequest) {
        Map<String, String> responseMap = new HashMap<>();
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("User data not found! Login again!");
        }

        SimCard simCard = simCardList.get(0);
        if (simCard.isRoamingActive()) {
            List<RoamingActivation> roamingActivationList = this.roamingActivationRepository.getCurrentSimRoamingPlan(simCard.getSimCardId());
            RoamingActivation roamingActivation;
            PlanActivation planActivation;
            simCard.setRoamingActive(false);
            if (!roamingActivationList.isEmpty()) {
                roamingActivation = roamingActivationList.get(0);
                roamingActivation.setActive(false);
                roamingActivation.setReactiveAvailable(true);
                this.roamingActivationRepository.updateRoamingActivationDetails(roamingActivation);
            }

            List<PlanActivation> planActivationList = this.planActivationRepository.getAvailableActivationPlansByMobile(mobileNumber);
            List<PlanActivation> nextAvailablePlanList = this.planActivationRepository.getNextAvailablePlan(simCard);
            if (!planActivationList.isEmpty()) {
                planActivation = planActivationList.get(0);
                planActivation.setActive(true);
                planActivation.setReactiveAvailable(false);
                this.planActivationRepository.updatePlanActivation(planActivation);
            } else {
                if (!nextAvailablePlanList.isEmpty()) {
                    planActivation = nextAvailablePlanList.get(0);
                    planActivation.setActive(true);
                    planActivation.setReactiveAvailable(false);
                    planActivation.setActivationDate(LocalDateTime.now());
                    planActivation.setExpirationDate(LocalDateTime.now().plusDays(planActivation.getPlan().getValidity()));
                    planActivation.setExpired(false);
                    planActivation.setServiceChange(false);
                    this.planActivationRepository.updatePlanActivation(planActivation);
                } else {
                    responseMap.put("planStatus", "No plan available, buy new plans to activate sim usage");
                }
            }

            this.simCardRepository.addOrUpdateSimCard(simCard);
            responseMap.put("toggleTo", "Deactivate");
            responseMap.put("message", "Roaming deactivated successfully!");

        } else {
            simCard.setRoamingActive(true);
            List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPlan(simCard);
            if (!planActivationList.isEmpty()) {
                PlanActivation planActivation = planActivationList.get(0);
                planActivation.setActive(false);
                planActivation.setReactiveAvailable(true);
                this.planActivationRepository.updatePlanActivation(planActivation);
            }

            List<RoamingActivation> activableRoamingPlanList = this.roamingActivationRepository.getActivableRoamingPlanByMobile(mobileNumber);
            List<RoamingActivation> nextActivablePlan = this.roamingActivationRepository.getNextActivablePlan(mobileNumber);
            if (!activableRoamingPlanList.isEmpty()) {
                RoamingActivation roamingActivation = activableRoamingPlanList.get(0);
                roamingActivation.setActive(true);
                roamingActivation.setReactiveAvailable(false);
                this.roamingActivationRepository.updateRoamingActivationDetails(roamingActivation);
            } else {
                if (!nextActivablePlan.isEmpty()) {
                    RoamingActivation roamingActivation = nextActivablePlan.get(0);
                    roamingActivation.setActive(true);
                    roamingActivation.setReactiveAvailable(false);
                    roamingActivation.setActivationDate(LocalDateTime.now());
                    roamingActivation.setExpirationDate(LocalDateTime.now().plusDays(roamingActivation.getRoamingPlan().getValidity()));
                    roamingActivation.setExpired(false);
                    roamingActivation.setServiceChange(false);
                    this.roamingActivationRepository.updateRoamingActivationDetails(roamingActivation);
                } else {
                    responseMap.put("planStatus", "No roaming plan available, buy new plans to activate sim usage");
                }
            }

            this.simCardRepository.addOrUpdateSimCard(simCard);
            responseMap.put("toggleTo", "Activate");
            responseMap.put("message", "Roaming activated successfully!");
        }

        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, RechargeHistoryDataDto>> getRechargeHistoryData(RechargeHistoryPaginationDetails rechargeHistoryPaginationDetails, HttpServletRequest httpServletRequest) {
        RechargeHistoryDataDto rechargeHistoryDataDto = new RechargeHistoryDataDto();
        Map<String, RechargeHistoryDataDto> responseMap = new HashMap<>();
        int userPageSize = Integer.parseInt(rechargeHistoryPaginationDetails.getPageSize());
        int currentPage = Integer.parseInt(rechargeHistoryPaginationDetails.getCurrentPage());
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);

        Pageable pageable = PageRequest.of(currentPage - 1, userPageSize);
        Page<InvoiceTable> getPaginatedRechargeHistoryData = this.invoiceTableRepository.getPaginatedInvoiceData(pageable, clientId, mobileNumber);
        List<RechargeHistoryDashboardDataDto> paidList = new ArrayList<>();

        if (!getPaginatedRechargeHistoryData.getContent().isEmpty()) {
            rechargeHistoryDataDto.setPaidDataAvailable(true);
            for (InvoiceTable invoiceTable : getPaginatedRechargeHistoryData.getContent()) {
                RechargeHistoryDashboardDataDto rechargeHistoryDtoOb = getRechargeHistoryDashboardDataDto(invoiceTable);
                paidList.add(rechargeHistoryDtoOb);
            }
            rechargeHistoryDataDto.setPaidList(paidList);
        } else {
            rechargeHistoryDataDto.setPaidDataAvailable(false);
        }

        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        SimCard simCard = simCardList.get(0);
        if (simCard.isRoamingActive()) {
            List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPlan(simCard);
            if (!planActivationList.isEmpty()) {
                rechargeHistoryDataDto.setUnpaidDataAvailable(true);
                PlanActivation planActivation = planActivationList.get(0);
                RechargeHistoryDashboardDataDto unpaid = new RechargeHistoryDashboardDataDto();
                unpaid.setPlanAmount(planActivation.getPlan().getRechargeAmount().toString());
                unpaid.setPlanBoughtDate(Helper.formatLocalDateTime(planActivation.getBoughtDate()));
                rechargeHistoryDataDto.setUnpaidPlan(unpaid);
            } else {
                rechargeHistoryDataDto.setUnpaidDataAvailable(false);
            }
        }

        rechargeHistoryDataDto.setCurrentPage(currentPage);
        rechargeHistoryDataDto.setTotalPages(getPaginatedRechargeHistoryData.getTotalPages());
        responseMap.put("message", rechargeHistoryDataDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private RechargeHistoryDashboardDataDto getRechargeHistoryDashboardDataDto(InvoiceTable invoiceTable) {
        RechargeHistoryDashboardDataDto rechargeHistoryDtoOb = new RechargeHistoryDashboardDataDto();
        rechargeHistoryDtoOb.setPaymentMethod(invoiceTable.getOrderTable().getPaymentMethod());
        rechargeHistoryDtoOb.setInvoiceNumber(invoiceTable.getInvoiceNumber());
        rechargeHistoryDtoOb.setInvoiceId(invoiceTable.getInvoiceId());
        rechargeHistoryDtoOb.setPaymentReference(invoiceTable.getOrderTable().getPaymentRefrence());
        rechargeHistoryDtoOb.setPlanBoughtDate(Helper.formatLocalDateTime(invoiceTable.getPlanBoughtDate()));
        rechargeHistoryDtoOb.setPlanAmount(invoiceTable.getOrderTable().getTotalAmount().toString());
        return rechargeHistoryDtoOb;
    }

    public ResponseEntity<Map<String, String>> getInvoiceDetails(String invoiceId, HttpServletRequest httpServletRequest) {
        String invoiceIdPattern = "^\\d+$";
        Pattern pattern = Pattern.compile(invoiceIdPattern);
        Matcher matcher = pattern.matcher(invoiceId);
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        List<InvoiceTable> invoiceTableList = this.invoiceTableRepository.getInvoiceDetails(clientId, Integer.parseInt(invoiceId));
        Map<String, String> responseMap = new HashMap<>();
        if (matcher.matches() && !invoiceTableList.isEmpty()) {
            InvoiceTable invoiceTable = invoiceTableList.get(0);
            String invoiceDownloadLink = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + clientId + File.separator + "invoices" + File.separator + "Invoice-" + invoiceTable.getInvoiceNumber() + ".pdf";
            responseMap.put("invoiceLink", invoiceDownloadLink);
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        } else {
            throw new InvalidDataFoundException("Invalid invoice ID");
        }
    }

    public ResponseEntity<Map<String, String>> createBlockRequest(HttpServletRequest httpServletRequest) {
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        Map<String, String> responseMap = new HashMap<>();
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        SimCard simCard = simCardList.get(0);
        if (simCard.isBlocked()) {
            responseMap.put("message", "Sim Card already blocked");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

        List<ClientRequest> clientRequests = this.clientRequestRepository.getClientBlockRequests(simCard);
        if (!clientRequests.isEmpty()) {
            responseMap.put("message", "Sim Card block request already created!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestType(ClientRequestTypeEnum.BLOCK.getRequestTypeId());
        clientRequest.setRequestStatus(RequestStatusEnum.PENDING.getRequestStatusId());
        clientRequest.setSimCard(simCard);
        this.clientRequestRepository.saveClientRequest(clientRequest);
        this.smsService.sendNewRequestSms(simCard.getPhoneNumber(), "block");

        responseMap.put("message", "Sim block request created!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> createUnblockRequest(HttpServletRequest httpServletRequest, String pukCode) {
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        Map<String, String> responseMap = new HashMap<>();
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        SimCard simCard = simCardList.get(0);
        if (!simCard.isBlocked()) {
            responseMap.put("message", "Sim Card already unblocked");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

        List<ClientRequest> clientRequests = this.clientRequestRepository.getClientUnblockRequests(simCard);
        if (!clientRequests.isEmpty()) {
            responseMap.put("message", "Sim Card unblock request already created!");
            return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
        }

        String regex = "^\\d{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pukCode);
        if (!matcher.matches() || !simCard.getPukNumber().equals(pukCode)) {
            throw new InvalidDataFoundException("Invalid PUK code");
        }

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setRequestType(ClientRequestTypeEnum.UNBLOCK.getRequestTypeId());
        clientRequest.setRequestStatus(RequestStatusEnum.PENDING.getRequestStatusId());
        clientRequest.setSimCard(simCard);
        this.clientRequestRepository.saveClientRequest(clientRequest);
        this.smsService.sendNewRequestSms(simCard.getPhoneNumber(), "unblock");

        responseMap.put("message", "Sim unblock request created!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, ClientProfileDto>> getClientDashboardDetails(HttpServletRequest httpServletRequest) {
        int clientId = (Integer) httpServletRequest.getSession().getAttribute("clientId");
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        Client client = this.clientRepository.getClientById(clientId);
        if (client == null) {
            throw new DataNotFoundException("Client data not found!");
        }
        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentDetails(clientId);
        ClientProfileDto clientProfileDto = getClientProfileDetails(client, mobileNumber, httpServletRequest, clientDocuments);
        Map<String, ClientProfileDto> responseMap = new HashMap<>();
        responseMap.put("clientProfileDto", clientProfileDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private ClientProfileDto getClientProfileDetails(Client client, String mobileNumber, HttpServletRequest httpServletRequest, ClientDocuments clientDocuments) {

        String profilePicPath = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + client.getClientId() + File.separator + "ProfilePhoto." + clientDocuments.getProfilePhotoExtension();

        ClientProfileDto clientProfileDto = new ClientProfileDto();
        clientProfileDto.setFirstName(client.getFirstName());
        clientProfileDto.setLastName(client.getLastName());
        clientProfileDto.setEmail(client.getEmail());
        clientProfileDto.setPhone(mobileNumber);
        clientProfileDto.setCity(client.getCity());
        clientProfileDto.setState(client.getState());
        clientProfileDto.setStreet(client.getStreet());
        clientProfileDto.setZipcode(client.getZipcode());
        clientProfileDto.setAadharCardNumber(client.getAadharNumber());
        clientProfileDto.setBirthDate(client.getDateOfBirth().toString());
        clientProfileDto.setGstNumber(client.getGstNumber());
        clientProfileDto.setPanNumber(client.getPanNumber());
        clientProfileDto.setProfilePicPath(profilePicPath);
        return clientProfileDto;
    }

    private SimDetailsDto getCurrentClientSimDetails(SimCard simCard) {
        SimDetailsDto simDetailsDto = new SimDetailsDto();
        Client client = simCard.getClient();
        simDetailsDto.setSimType(simCard.getSimType() == 1 ? "Prepaid" : "Postpaid");
        simDetailsDto.setFirstName(client.getFirstName());
        simDetailsDto.setLastName(client.getLastName());
        simDetailsDto.setMobileNumber(simCard.getPhoneNumber());
        simDetailsDto.setIccidNumber(simCard.getIccidNumber());
        simDetailsDto.setImsiNumber(simCard.getImsiNumber());
        simDetailsDto.setImeiNumber(simCard.getImeiNumber());
        simDetailsDto.setPukNumber(simCard.getPukNumber());
        simDetailsDto.setBlockStatus(simCard.isBlocked() ? "Blocked" : "Unblocked");
        simDetailsDto.setRoamingStatus(simCard.isRoamingActive() ? "Active" : "Inactive");
        return simDetailsDto;
    }

    public ResponseEntity<Map<String, SimDetailsDto>> getCurrentClientSimDetails(HttpServletRequest httpServletRequest) {
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        SimCard simCard = simCardList.get(0);
        SimDetailsDto simDetailsDto = getCurrentClientSimDetails(simCard);
        Map<String, SimDetailsDto> responseMap = new HashMap<>();
        responseMap.put("simDetailsDto", simDetailsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> checkPostpaidBillDues(HttpServletRequest httpServletRequest) {
        Map<String, String> responseMap = new HashMap<>();
        String mobileNumber = (String) httpServletRequest.getSession().getAttribute("loggedInMobile");
        List<SimCard> simCardList = this.simCardRepository.getSimCardDetailsByNumber(mobileNumber);
        if (simCardList.isEmpty()) {
            throw new DataNotFoundException("Client data not found!");
        }

        SimCard simCard = simCardList.get(0);
        if (simCard.getSimType() == SimType.POSTPAID.getSimCardTypeId()) {
            List<PlanActivation> planActivationList = this.planActivationRepository.getActiveSimPlan(simCard);
            List<PlanActivation> postpaidPlanActivationList = this.planActivationRepository.getActiveSimPostpaidPlan(simCard);

//            if(!postpaidPlanActivationList.isEmpty()){
//                responseMap.put("isPlanAvailable","true");
//            }else {
//                responseMap.put("isPlanAvailable","false");
//            }

            if(postpaidPlanActivationList.isEmpty()){
                throw new InvalidDataFoundException("No dues left to pay!");
            }

            if (!planActivationList.isEmpty()) {
                responseMap.put("expirationStatus", "false");
                return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
            }else {
                responseMap.put("expirationStatus", "true");
            }
        }else {
            throw new InvalidDataFoundException("Can only proceed with Postpaid Sim");
        }

        responseMap.put("expirationStatus", "true");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);

    }

}

