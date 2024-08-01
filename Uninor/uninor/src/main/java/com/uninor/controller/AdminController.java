package com.uninor.controller;

import com.uninor.dto.*;
import com.uninor.model.CuponCategory;
import com.uninor.model.PlanCategories;
import com.uninor.service.AdminDashboardService;
import com.uninor.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getAdminDashboard() {
        return "admin/admin-dashboard";
    }

    @RequestMapping(value = "/admin-profile", method = RequestMethod.GET)
    public String getAdminProfile() {
        return "admin/admin-profile";
    }

    @RequestMapping(value = "/edit-coupon", method = RequestMethod.GET)
    public String showEditCouponPage(Model model){
        List<CuponCategory> getCouponCategories = this.adminService.getCouponCategories();
        model.addAttribute("cuponCategories", getCouponCategories);
        return "admin/edit-coupon-page";
    }

    @RequestMapping(value = "/users-details", method = RequestMethod.GET)
    public String showUserDetailsPage(){
        return "admin/users-details";
    }

    @RequestMapping(value = "/edit-plan-page", method = RequestMethod.GET)
    public String showEditPlanPage(Model model){
        List<PlanCategoriesDto> planCategories = this.adminService.getPlanCategories();
        model.addAttribute("planCategories", planCategories);
        return "admin/edit-plan-page";
    }

    @ResponseBody
    @RequestMapping(value = "/update-admin-profile-photo", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> updateUserProfilePhoto(@RequestParam("profilePhoto") CommonsMultipartFile profilePhoto, HttpServletRequest httpServletRequest){
        return this.adminService.updateProfilePhoto(profilePhoto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-dashboard-data", method = RequestMethod.GET)
    public ResponseEntity<Map<String, AdminDashboardDataDto>> getGetDashboardData() {
        return this.adminDashboardService.getAdminDashboardData();
    }

    @ResponseBody
    @RequestMapping(value = "/get-filtered-user-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String, ClientRequestPaginatedDto>> getFilteredClientRequest(@Valid @RequestBody FilterUserRequest filterUserRequest){
        return this.adminDashboardService.getFilteredClientRequests(filterUserRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-filtered-users-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,ClientDetailsPaginatedDto>> getFilteredUsersDetails(@Valid @RequestBody FilterUserRequest filterUserRequest){
        return this.adminService.getFilteredUsersDetails(filterUserRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-request-data", method = RequestMethod.POST)
    public ResponseEntity<Map<String, ViewDocumentRequestDataDto>> getCurrentRequestData(@RequestParam("requestId") int requestId, HttpServletRequest httpServletRequest){
        return this.adminDashboardService.getViewDocumentsDetails(requestId, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/verified-complete-documents", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> verifySelectedDocument(@RequestParam("requestId") Integer requestedId, @RequestParam("aadharVerified") String aadharVerified, @RequestParam("panVerified") String panVerified, HttpServletRequest httpServletRequest){
        return this.adminDashboardService.verifyDocuments(requestedId,aadharVerified,panVerified,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/accept-block-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> acceptBlockRequest(@RequestParam("requestId") int requestId ){
        return this.adminDashboardService.acceptBlockRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/reject-block-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> rejectBlockRequest(@RequestParam("requestId") int requestId){
        return this.adminDashboardService.rejectBlockRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/accept-unblock-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> acceptUnblockRequest(@RequestParam("requestId") int requestId){
        return this.adminDashboardService.acceptUnblockRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/reject-unblock-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> rejectUnblockRequest(@RequestParam("requestId") int requestId){
        return this.adminDashboardService.rejectUnblockRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/reject-deactivation-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> rejectDeactivationRequest(@RequestParam("requestId") int requestId){
        return this.adminDashboardService.rejectDeactivationRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/accept-deactivation-request", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> acceptDeactivationRequest(@RequestParam("requestId") int requestId){
        return this.adminDashboardService.acceptDeactivationRequest(requestId);
    }

    @ResponseBody
    @RequestMapping(value = "/get-admin-profile-details", method = RequestMethod.GET)
    public ResponseEntity<Map<String, AdminProfileDto>> getAdminProfileDetails(HttpServletRequest httpServletRequest){
        return this.adminService.getProfileDetails(httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/update-admin-profile", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> updateAdminProfile(@Valid @RequestBody UpdateAdminProfileDetailsDto updateAdminProfileDetailsDto, HttpServletRequest httpServletRequest){
        return  this.adminService.updateAdminProfile(updateAdminProfileDetailsDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-all-coupon-data", method = RequestMethod.POST)
    public ResponseEntity<Map<String, AdminPaginatedCouponsDetails>> getCouponsDetails(@Valid @RequestBody CouponsDetailsFilterDto couponsDetailsFilterDto){
        return this.adminService.getAllCouponsDetails(couponsDetailsFilterDto);
    }

    @ResponseBody
    @RequestMapping(value = "/create-new-coupon", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> createNewCoupon(@Valid @RequestBody CreateNewCouponDto createNewCouponDto, HttpServletRequest httpServletRequest){
        return this.adminService.createNewCoupon(createNewCouponDto, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-coupon-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,UpdateCouponDetailsDto>> getCouponDetails(@RequestParam("couponId") Integer couponId){
        return this.adminService.getCouponDetails(couponId);
    }

    @ResponseBody
    @RequestMapping(value = "/update-coupon-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> updateCouponDetalsDto(@Valid @RequestBody UpdatedCouponsDetails updatedCouponsDetails, HttpServletRequest httpServletRequest){
        return this.adminService.updateCouponDetails(updatedCouponsDetails,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-plan-data", method = RequestMethod.POST)
    public ResponseEntity<Map<String, ShowPlanDto>> getRechargePlanData(RechargePlanFilter rechargePlanFilter, HttpServletRequest httpServletRequest) {
        return this.adminService.getRechargeDetails(rechargePlanFilter, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-specific-plan-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String, PlanDetailsDto>> getRechargePlanDetails(@RequestParam("planId") int planId) {
        return this.adminService.getPlanDetails(planId);
    }

    @ResponseBody
    @RequestMapping(value = "/get-updating-plan-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,PreUpdatePlanDetailsDto>> getUpdatingPlanDetails(@RequestParam("planId") int planId) {
        return this.adminService.getUpdatingPlanDetails(planId);
    }

    @ResponseBody
    @RequestMapping(value = "/add-new-plan", method = RequestMethod.POST)
    public ResponseEntity<Map<String ,String >> saveNewPlanDetails(@Valid @RequestBody PreUpdatePlanDetailsDto preUpdatePlanDetailsDto, HttpServletRequest httpServletRequest){
        return this.adminService.saveNewPlanDetails(preUpdatePlanDetailsDto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/update-current-plan-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> updatePlanDetails(@Valid @RequestBody PreUpdatePlanDetailsDto preUpdatePlanDetailsDto, HttpServletRequest httpServletRequest){
        return this.adminService.updatePlanDetails(preUpdatePlanDetailsDto,httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/get-client-other-details", method = RequestMethod.POST)
    public ResponseEntity<Map<String,OtherClientDetailsDto>> getOtherClientDetails(@RequestParam("clientId") int clientId, HttpServletRequest httpServletRequest){
        return this.adminService.getClientOtherDetails(clientId, httpServletRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/send-contact-email", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> sendContactEmail(@RequestParam("description") String description,  @RequestParam("clientId") int clientId){
        return this.adminService.sendClientContactEmail(description,clientId);
    }

    @ResponseBody
    @RequestMapping(value = "/send-signup-email", method = RequestMethod.POST)
    public ResponseEntity<Map<String,String>> sendSignUpUserNotification(@RequestParam("clientId") int clientId){
        return this.adminService.sendSignUpUserNotificationEmail(clientId);
    }

}
