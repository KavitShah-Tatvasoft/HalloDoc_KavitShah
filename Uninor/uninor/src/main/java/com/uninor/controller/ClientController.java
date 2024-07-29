package com.uninor.controller;

import com.itextpdf.text.DocumentException;
import com.uninor.dto.*;
import com.uninor.service.ClientDashboardService;
import com.uninor.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDashboardService clientDashboardService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String navbar() {
        return "client/client-dashboard";
    }

    @RequestMapping(value = "/recharge-tab", method = RequestMethod.GET)
    public String rechargeTab() {
        return "client/recharge-tab";
    }

    @RequestMapping(value = "/redeem-rewards", method = RequestMethod.GET)
    public String redeemRewards() {
        return "client/redeem-rewards";
    }

    @RequestMapping(value = "/recharge-history", method = RequestMethod.GET)
    public String rechargeHistory() {
        return "client/recharge-history";
    }

    @RequestMapping(value = "/client-profile", method = RequestMethod.GET)
    public String viewClientProfile() {
        return "client/client-profile";
    }

    @RequestMapping(value = "/sim-details", method = RequestMethod.GET)
    public String viewSimDetails() {
        return "client/client-sim";
    }

    @ResponseBody
    @RequestMapping(value = "/get-plan-data", method = RequestMethod.POST)
    public ResponseEntity<Map<String, ShowPlanDto>> getRechargePlanData(RechargePlanFilter rechargePlanFilter, HttpServletRequest httpServletRequest) {
        return this.clientService.getRechargeDetails(rechargePlanFilter, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-specific-plan-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String, PlanDetailsDto>> getRechargePlanDetails(@RequestParam("planId") int planId) {
        return this.clientService.getPlanDetails(planId);
    }

    @ResponseBody
    @RequestMapping(value = "/buy-plan-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String, UserPlanDetailsDto>> getUserPlanDetails(@RequestParam("planId") int planId, HttpServletRequest httpServletRequest){
        return this.clientService.getUserPlanDetails(planId,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/check-client-wallet-amount", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> checkClientWalletAmount(@Valid @RequestBody ClientWalletDto clientWalletDto, HttpServletRequest httpServletRequest) {
        return this.clientService.validateWalletAmount(clientWalletDto);
    }

    @ResponseBody
    @RequestMapping(value = "/check-cupon-validity", method = RequestMethod.POST)
    public ResponseEntity<Map<String, CuponDto>> checkCuponValidity(@Valid @RequestBody ClientCuponDto clientCuponDto) {
        return this.clientService.validateCuponCode(clientCuponDto);
    }

    @ResponseBody
    @RequestMapping(value = "/verify-cupon-wallet-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String , UpdateBuyPlanDetailsDto>> verifyCuponWalletDetails(@Valid @RequestBody CuponWalletDto cuponWalletDto) throws IOException, DocumentException {
        return this.clientService.validateCuponWalletDetails(cuponWalletDto);
    }

    @ResponseBody
    @RequestMapping(value = "/buy-recharge-plan", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> buyRechargePlan(@Valid @RequestBody CuponWalletDto cuponWalletDto, HttpServletRequest httpServletRequest) throws DocumentException, IOException {
        return this.clientService.rechargePayment(cuponWalletDto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-dashboard-data", method = RequestMethod.GET)
    public ResponseEntity<Map<String,DashboardDataDto>> getDashboardData(HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.getDashboardData(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-active-plan-details", method = RequestMethod.GET)
    public ResponseEntity<Map<String, PlanDetailsDto>> getActivePlanDetails(HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.getActivePlanDetials(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/toggle-sim-type", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String >> toogleSimType(HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.toggleSimType(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/postpaid-billing-details", method = RequestMethod.GET)
    public ResponseEntity<Map<String,PostPaidPlanDetailsPaymentDto>> postpaidBillingDetails(HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.getPostPaidPaymentDetails(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/verify-postpaid-payment-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,UpdatePostpaidPaymentDetailsDto>> verifyPostpaidBillingDetails(@Valid @RequestBody CuponWalletDto cuponWalletDto,HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.verifyPostPaidBillingDetails(cuponWalletDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/pay-postpaid-bill", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> payPostpaidBill(@Valid @RequestBody CuponWalletDto cuponWalletDto,HttpServletRequest httpServletRequest) throws DocumentException, IOException {
        return this.clientDashboardService.payPostpaidRechargeBill(cuponWalletDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-user-coupons", method = RequestMethod.GET)
    public ResponseEntity<Map<String , List<ClientCuponDetailsDto>>> getClientCupons(HttpServletRequest httpServletRequest){
        return this.clientDashboardService.getClientCuponDetails(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/redeem-coupon", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>>  redeemCupon(@RequestParam("couponCode") String couponCode, HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.redeemClientCupon(couponCode,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/toggle-roaming-status", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> toggleRoamingStatus(HttpServletRequest httpServletRequest) {
        return this.clientDashboardService.toggleRoamingStatus(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-recharge-history-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,RechargeHistoryDataDto>> getRechargeHistoryDetails(@Valid @RequestBody RechargeHistoryPaginationDetails rechargeHistoryPaginationDetails,HttpServletRequest httpServletRequest){
        return this.clientDashboardService.getRechargeHistoryData(rechargeHistoryPaginationDetails,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/download-invoice", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> getInvoice(@RequestParam("invoiceId") String invoiceId,HttpServletRequest httpServletRequest){
        return this.clientDashboardService.getInvoiceDetails(invoiceId,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/create-block-request", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> createBlockRequest(HttpServletRequest httpServletRequest){
        return this.clientDashboardService.createBlockRequest(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/create-unblock-request", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> createUnblockRequest(@RequestParam("pukCode") String pukCode, HttpServletRequest httpServletRequest){
        return this.clientDashboardService.createUnblockRequest(httpServletRequest,pukCode);
    }

    @ResponseBody
    @RequestMapping(value = "/buy-post-paid-plan", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String>> buyPostpaidPlan(@RequestParam("planId") int planId, HttpServletRequest httpServletRequest){
        return this.clientService.buyPostPaidPlan(planId,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "get-client-profile-details", method = RequestMethod.GET)
    public ResponseEntity<Map<String,ClientProfileDto>> getClientProfileDetails(HttpServletRequest httpServletRequest){
        return this.clientDashboardService.getClientDashboardDetails(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-current-sim-details", method = RequestMethod.GET)
    public ResponseEntity<Map<String,SimDetailsDto>> getClientSimDetails(HttpServletRequest httpServletRequest){
        return this.clientDashboardService.getCurrentClientSimDetails(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/create-sim-deactivation-request", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String >> cerateSimDeactivationRequest(HttpServletRequest httpServletRequest){
        return this.clientService.simDeactivationRequest(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/update-user-profile", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String>>  updateUserProfile(@Valid @RequestBody UpdateProfileDetailsDto updateProfileDetailsDto, HttpServletRequest httpServletRequest) {
            return this.clientService.updateUserProfile(updateProfileDetailsDto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/update-profile-photo", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> updateUserProfilePhoto(@RequestParam("profilePhoto")CommonsMultipartFile profilePhoto, HttpServletRequest httpServletRequest){
        return this.clientService.updateProfilePhoto(profilePhoto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/check-postpaid-dues", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> checkAvailablePostpaidDues(HttpServletRequest httpServletRequest){
        return this.clientDashboardService.checkPostpaidBillDues(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-client-notifications", method = RequestMethod.GET)
    public ResponseEntity<Map<String , List<NotificationDto>>> getRecentClientNotifications(HttpServletRequest httpServletRequest){
        return this.clientService.getAllCurrentClientNotificaitons(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/remove-notification", method = RequestMethod.POST)
    public ResponseEntity<Map<String , String>> deleteNotification(@RequestParam("notificationId") int notificationId, HttpServletRequest httpServletRequest){
        return this.clientService.removeClientNotification(notificationId,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/remove-all-notification", method = RequestMethod.GET)
    public ResponseEntity<Map<String , String>> removeAllNotifications(HttpServletRequest httpServletRequest){
        return this.clientService.removeAllClientNotification(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/update-read-receipts", method = RequestMethod.GET)
    public ResponseEntity<Map<String , String>> updateReadReceipts(HttpServletRequest httpServletRequest){
        return this.clientService.updateReadReciepts(httpServletRequest);
    }

}

