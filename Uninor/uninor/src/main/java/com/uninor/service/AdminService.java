package com.uninor.service;

import com.uninor.Email.EmailService;
import com.uninor.dto.*;
import com.uninor.enumeration.AdminUserStatusEnum;
import com.uninor.enumeration.SimType;
import com.uninor.exceptions.DataNotFoundException;
import com.uninor.exceptions.InvalidDataFoundException;
import com.uninor.exceptions.InvalidFileException;
import com.uninor.helper.Helper;
import com.uninor.model.*;
import com.uninor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientDocumentsRepository clientDocumentsRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<Map<String, String>> updateProfilePhoto(CommonsMultipartFile profilePhoto, HttpServletRequest httpServletRequest) {

        Map<String, String> responseMap = new HashMap<>();
        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);

        if (admin == null) {
            throw new DataNotFoundException("Admin data not found");
        }

        if ((double) profilePhoto.getSize() / (1024 * 1024) > 2) {
            throw new InvalidDataFoundException("Invalid profile photo");
        }

        String fileExtension = profilePhoto.getOriginalFilename().substring(profilePhoto.getOriginalFilename().lastIndexOf('.') + 1);

        if (!fileExtension.equals("jpeg") && !fileExtension.equals("jpg") && !fileExtension.equals("png")) {
            throw new InvalidDataFoundException("Invalid profile photo");
        }

        admin.setProfileExtension(fileExtension);
        try {
            String path = System.getenv("UninorAdminUploadPath");
            String fullPath = path + adminId + "/" + "AdminProfile" + "." + fileExtension;

            byte[] data = profilePhoto.getBytes();
            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(data);
            fos.close();

        } catch (Exception e) {
            throw new InvalidFileException("File Upload Failed. Try again!");
        }
        admin.setProfileExtension(fileExtension);
        adminRepository.updateAdmin(admin);
        responseMap.put("profilePhotoUpdate", "Profile Photo Successfully Updated");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, AdminProfileDto>> getProfileDetails(HttpServletRequest httpServletRequest) {
        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);
        if (admin == null) {
            throw new DataNotFoundException("Admin data not found!");
        }

        AdminProfileDto adminProfileDto = getAdminProfileDetails(admin, httpServletRequest);
        Map<String, AdminProfileDto> responseMap = new HashMap<>();
        responseMap.put("adminProfileDto", adminProfileDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private AdminProfileDto getAdminProfileDetails(Admin admin, HttpServletRequest httpServletRequest) {

        String profilePicPath = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorAdminDownloadPath") + admin.getAdminId() + File.separator + "AdminProfile." + admin.getProfileExtension();

        AdminProfileDto adminProfileDto = new AdminProfileDto();
        adminProfileDto.setFirstName(admin.getFirstName());
        adminProfileDto.setLastName(admin.getLastName());
        adminProfileDto.setEmail(admin.getEmail());
        adminProfileDto.setPhone(admin.getMobileNumber());
        adminProfileDto.setCity(admin.getCity());
        adminProfileDto.setState(admin.getState());
        adminProfileDto.setStreet(admin.getStreet());
        adminProfileDto.setZipcode(admin.getZipcode());
        adminProfileDto.setBirthDate(admin.getDateOfBirth().toString());
        adminProfileDto.setProfilePicPath(profilePicPath);
        return adminProfileDto;
    }

    public ResponseEntity<Map<String, String>> updateAdminProfile(UpdateAdminProfileDetailsDto updateAdminProfileDetailsDto, HttpServletRequest httpServletRequest) {
        Map<String, String> responseMap = new HashMap<>();
        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);

        if (admin == null) {
            throw new DataNotFoundException("Admin data not found");
        }

        admin.setFirstName(updateAdminProfileDetailsDto.getFirstName());
        admin.setLastName(updateAdminProfileDetailsDto.getLastName());
        admin.setMobileNumber(updateAdminProfileDetailsDto.getMobileNumber());
        admin.setStreet(updateAdminProfileDetailsDto.getStreet());
        admin.setCity(updateAdminProfileDetailsDto.getCity());
        admin.setState(updateAdminProfileDetailsDto.getState());
        admin.setZipcode(updateAdminProfileDetailsDto.getZipcode());

        this.adminRepository.updateAdmin(admin);
        responseMap.put("message", "Profile Details Updated");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, AdminPaginatedCouponsDetails>> getAllCouponsDetails(CouponsDetailsFilterDto couponsDetailsFilterDto) {
        Map<String, AdminPaginatedCouponsDetails> responseMap = new HashMap<>();
        List<AllCouponsDetailsDto> couponList = new ArrayList<>();
        AdminPaginatedCouponsDetails adminPaginatedCouponsDetails = new AdminPaginatedCouponsDetails();
        int userPageSize = couponsDetailsFilterDto.getPageSize();
        int currentPage = couponsDetailsFilterDto.getCurrentPage();
        Pageable pageable = PageRequest.of(currentPage - 1, userPageSize);

        Page<Cupon> coupons = this.cuponRepository.getFilteredCoupons(pageable, couponsDetailsFilterDto);
        for (Cupon coupon : coupons) {
            AllCouponsDetailsDto allCouponsDetailsDto = getAllCouponsDetailsDto(couponsDetailsFilterDto, coupon);
            couponList.add(allCouponsDetailsDto);
        }

        adminPaginatedCouponsDetails.setPages(coupons.getTotalPages());
        adminPaginatedCouponsDetails.setAllCouponsDetailsDtos(couponList);
        responseMap.put("paginatedCoupnData", adminPaginatedCouponsDetails);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private static AllCouponsDetailsDto getAllCouponsDetailsDto(CouponsDetailsFilterDto couponsDetailsFilterDto, Cupon coupon) {
        AllCouponsDetailsDto allCouponsDetailsDto = new AllCouponsDetailsDto();
        allCouponsDetailsDto.setCouponId(coupon.getCuponId());
        allCouponsDetailsDto.setCouponAvailable(coupon.isAvailable());
        allCouponsDetailsDto.setCouponDescription(coupon.getDescription());
        allCouponsDetailsDto.setMaxRewards(couponsDetailsFilterDto.getCouponType() != 3 ? coupon.getMaxRewardAmount() + " Rs" : coupon.getMaxRewardAmount() / 1000 + " GB");

        if (couponsDetailsFilterDto.getCouponType() != 3) {
            allCouponsDetailsDto.setCouponHeading(coupon.getRewardAmount() + " OFF");
        } else {
            String heading = Integer.parseInt(coupon.getRewardAmount().split(" ")[0]) / 1000 + " GB";
            allCouponsDetailsDto.setCouponHeading(heading);
        }
        allCouponsDetailsDto.setCouponType(coupon.getCuponCategory().getCuponCategoryId());
        return allCouponsDetailsDto;
    }

    public List<CuponCategory> getCouponCategories() {
        return this.cuponRepository.getAllCuponCategories();
    }

    public ResponseEntity<Map<String, String>> createNewCoupon(CreateNewCouponDto createNewCouponDto, HttpServletRequest httpServletRequest) {
        Cupon cupon = this.cuponRepository.checkAlreadyExistingCoupon(createNewCouponDto);
        if (cupon != null) {
            throw new InvalidDataFoundException("Coupon already exists");
        }
        Map<String, String> responseMap = new HashMap<>();
        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);
        CuponCategory cuponCategory = this.cuponRepository.getCouponCategoryById(createNewCouponDto.getCouponType());
        Cupon newCoupon = new Cupon();
        newCoupon.setCreatedBy(admin);
        if (createNewCouponDto.getCouponType() == 3) {
            newCoupon.setCashback(false);
            newCoupon.setDeductable(false);
            newCoupon.setMaxRewardAmount(0);
            newCoupon.setRewardAmount(createNewCouponDto.getRewardMB() + " MB");
        }
        if (createNewCouponDto.getCouponType() == 2) {
            newCoupon.setCashback(false);
            newCoupon.setDeductable(true);
            newCoupon.setMaxRewardAmount(createNewCouponDto.getMaxReward());
            newCoupon.setRewardAmount(createNewCouponDto.getRewardPercentage() + "%");
        }
        if (createNewCouponDto.getCouponType() == 1) {
            newCoupon.setCashback(true);
            newCoupon.setDeductable(false);
            newCoupon.setMaxRewardAmount(createNewCouponDto.getMaxReward());
            newCoupon.setRewardAmount(createNewCouponDto.getRewardPercentage() + "%");
        }
        newCoupon.setAvailable(true);
        newCoupon.setCuponCategory(cuponCategory);
        newCoupon.setDescription(createNewCouponDto.getCouponDescription());
        newCoupon.setCuponName(createNewCouponDto.getCouponName());
        newCoupon.setModifiedBy(admin);
        newCoupon.setValidityPeriod(createNewCouponDto.getValidity());
        this.cuponRepository.addNewCoupon(newCoupon);

        responseMap.put("message", "New Coupon Created");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, UpdateCouponDetailsDto>> getCouponDetails(int couponId) {
        Map<String, UpdateCouponDetailsDto> responseMap = new HashMap<>();
        Cupon coupon = this.cuponRepository.getCouponDetailsById(couponId);

        if (coupon == null) {
            throw new DataNotFoundException("Failed to fetch coupon data");
        }

        UpdateCouponDetailsDto updateCouponDetailsDto = new UpdateCouponDetailsDto();
        updateCouponDetailsDto.setCouponId(coupon.getCuponId());
        updateCouponDetailsDto.setCouponAvailable(coupon.isAvailable());
        updateCouponDetailsDto.setCouponDescription(coupon.getDescription());
        updateCouponDetailsDto.setCouponCategory(coupon.getCuponCategory().getCuponCategoryId());
        updateCouponDetailsDto.setCouponMaxReward(coupon.getMaxRewardAmount());
        updateCouponDetailsDto.setCouponName(coupon.getCuponName());
        updateCouponDetailsDto.setCouponValidity(coupon.getValidityPeriod());
        updateCouponDetailsDto.setCouponReward(extractNumber(coupon.getRewardAmount()));
        responseMap.put("couponDetials", updateCouponDetailsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> updateCouponDetails(UpdatedCouponsDetails updateCouponDetailsDto, HttpServletRequest httpServletRequest) {
        Map<String, String> responseMap = new HashMap<>();

        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);
        if (admin == null) {
            throw new DataNotFoundException("Failed to fetch admin data");
        }

        Cupon cupon = this.cuponRepository.getCouponDetailsById(updateCouponDetailsDto.getCouponId());
        if (cupon == null) {
            throw new DataNotFoundException("Failed to fetch coupon data");
        }

        if (this.cuponRepository.checkCouponAvailability(updateCouponDetailsDto.getCouponCategory(), updateCouponDetailsDto.getValidity(), updateCouponDetailsDto.getRewardMB(), updateCouponDetailsDto.getRewardPercentage(), updateCouponDetailsDto.getMaxReward(), updateCouponDetailsDto.getCouponId())) {
            throw new InvalidDataFoundException("Coupon already exists");
        }

        cupon.setModifiedBy(admin);
        cupon.setCuponName(updateCouponDetailsDto.getCouponName());
        cupon.setValidityPeriod(updateCouponDetailsDto.getValidity());
        cupon.setDescription(updateCouponDetailsDto.getCouponDescription());

        if (updateCouponDetailsDto.getCouponCategory() == 3) {
            cupon.setRewardAmount(updateCouponDetailsDto.getRewardMB() + " MB");
            cupon.setMaxRewardAmount(0);
        } else {
            cupon.setRewardAmount(updateCouponDetailsDto.getRewardPercentage() + " %");
            cupon.setMaxRewardAmount(updateCouponDetailsDto.getMaxReward());
        }
        cupon.setAvailable(updateCouponDetailsDto.isAvailable());
        this.cuponRepository.updateCouponDetails(cupon);
        responseMap.put("message", "Coupon Updated Successfully");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private static String extractNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public ResponseEntity<Map<String, ShowPlanDto>> getRechargeDetails(RechargePlanFilter rechargePlanFilter, HttpServletRequest httpServletRequest) {

        Map<String, ShowPlanDto> responseMap = new LinkedHashMap<>();
        List<PlanCategories> planCategories = this.categoryRepository.getAllAvailableCategory();
        List<Plan> availablePlans;
        List<Plan> popularPlans = this.planRepository.getPopularActivePlans();
        if (rechargePlanFilter.isIsfilterApplied()) {
            availablePlans = this.planRepository.getAllFilteredPlan(rechargePlanFilter);
        } else {
            availablePlans = this.planRepository.getAllPlan();
        }

        ShowPlanDto popularPlan = createPopularPlanDto(popularPlans, "Popular Plans");
        responseMap.put("Popular Plans", popularPlan);

        ShowPlanDto annualPlan = createAnnualPlanDto(availablePlans, "Annual Plans");
        responseMap.put("Annual Plans", annualPlan);

        for (PlanCategories planCategory : planCategories) {
            ShowPlanDto displayPlan = new ShowPlanDto();
            List<PlanDetailsDto> displayList = new ArrayList<>();
            displayPlan.setPlanCategory(planCategory.getPlanCategory());
            int planCount = 0;
            for (Plan plan : availablePlans) {
                if (planCategory.getPlanId() == plan.getCategoryId().getPlanId()) {
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
                    if (plan.getIsRefreshing()) {
                        totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
                    } else {
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

    private ShowPlanDto createPopularPlanDto(List<Plan> planList, String planCategory) {
        ShowPlanDto popularPlans = new ShowPlanDto();
        List<PlanDetailsDto> displayList = new ArrayList<>();
        popularPlans.setPlanCategory(planCategory);
        popularPlans.setPlanCount(planList.size());
        for (Plan plan : planList) {
            PlanDetailsDto planDetails = new PlanDetailsDto();
            planDetails.setPlanId(plan.getPlanId());
            planDetails.setPlanAmount(plan.getRechargeAmount());
            planDetails.setPlanValidity(plan.getValidity());
            planDetails.setSmsAllowance(plan.getSmsAllowance());
            planDetails.setDataAmount((plan.getDataAllowance() / 1000));
            planDetails.setAdditionalData((plan.getExtraData() / 1000));
            planDetails.setDailyRefreshing(plan.getIsRefreshing());

            Double totalData;
            if (plan.getIsRefreshing()) {
                totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
            } else {
                totalData = (plan.getDataAllowance() / 1000) + plan.getExtraData();
            }

            planDetails.setTotalData(totalData);
            displayList.add(planDetails);
        }
        popularPlans.setPlanList(displayList);
        return popularPlans;
    }

    private ShowPlanDto createAnnualPlanDto(List<Plan> planList, String planCategory) {
        ShowPlanDto annualPlan = new ShowPlanDto();
        List<PlanDetailsDto> displayList = new ArrayList<>();
        annualPlan.setPlanCategory(planCategory);
        int count = 0;
        for (Plan plan : planList) {
            if (plan.getValidity() > 300) {
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
                if (plan.getIsRefreshing()) {
                    totalData = (plan.getDataAllowance() / 1000) * plan.getValidity() + plan.getExtraData();
                } else {
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

    public ResponseEntity<Map<String, PreUpdatePlanDetailsDto>> getUpdatingPlanDetails(int planId) {
        if (planId == 0) {
            throw new DataNotFoundException("No such plan found!");
        }

        Map<String, PreUpdatePlanDetailsDto> responseMap = new HashMap<>();
        Plan plan = this.planRepository.getPlanById(planId);

        if (plan == null) {
            throw new DataNotFoundException("Plan Data not found");
        }

        PreUpdatePlanDetailsDto planDetailsDto = new PreUpdatePlanDetailsDto();
        planDetailsDto.setPlanId(planId);
        planDetailsDto.setPlanAmount(plan.getRechargeAmount());
        planDetailsDto.setIsNewPlan(0);
        planDetailsDto.setPlanCategoryId(plan.getCategoryId().getPlanId());
        planDetailsDto.setDataAllowance(plan.getDataAllowance());
        planDetailsDto.setValidityPeriod(plan.getValidity());
        planDetailsDto.setSmsAllowance(plan.getSmsAllowance());
        planDetailsDto.setDailyRefresh(plan.getIsRefreshing() ? 1 : 0);
        planDetailsDto.setExtraDataAllowance(plan.getExtraData());
        planDetailsDto.setExtraDataAvailable(plan.getIsExtraDataAvailable() ? 1 : 0);
        planDetailsDto.setIsAvailable(plan.getIsDeleted() ? 0 : 1);
        planDetailsDto.setVoiceAllowance(plan.getVoiceAllowance());

        responseMap.put("planData", planDetailsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, PlanDetailsDto>> getPlanDetails(int planId) {
        if (planId == 0) {
            throw new DataNotFoundException("No such plan found!");
        }
        Map<String, PlanDetailsDto> responseMap = new HashMap<>();
        Plan plan = this.planRepository.getPlanById(planId);
        if (plan == null) {
            throw new DataNotFoundException("Plan Data not found");
        }
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
        responseMap.put("Plan", planDetails);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public List<PlanCategoriesDto> getPlanCategories() {
        return this.planRepository.getAllPlanCategories();
    }

    public ResponseEntity<Map<String, String>> saveNewPlanDetails(PreUpdatePlanDetailsDto dtoOb, HttpServletRequest httpServletRequest) {

        if(dtoOb.getPlanCategoryId() != 7 && dtoOb.getValidityPeriod() == 0){
            throw new DataNotFoundException("Plan validity cannot be 0 days!");
        }

        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);
        if (admin == null) {
            throw new DataNotFoundException("Admin Data found!");
        }
        boolean isPlanAvailable = this.planRepository.isSamePlanAvailable(dtoOb.getIsNewPlan(), dtoOb.getPlanId(), dtoOb.getPlanCategoryId(), dtoOb.getPlanAmount(), dtoOb.getSmsAllowance(), dtoOb.getVoiceAllowance(), dtoOb.getDataAllowance(), dtoOb.getExtraDataAllowance(), dtoOb.getValidityPeriod(), dtoOb.getIsAvailable(), dtoOb.getExtraDataAvailable(), dtoOb.getDailyRefresh());

        if (isPlanAvailable) {
            throw new InvalidDataFoundException("Plan Already Available!");
        }
        Map<String, String> responseMap = new HashMap<>();
        PlanCategories planCategories = this.planRepository.getPlanCategoriesById(dtoOb.getPlanCategoryId());
        Plan plan = new Plan();
        plan.setPlanCode(Helper.planCodeGenerator());
        plan.setCategoryId(planCategories);
        plan.setBoughtCount(0);
        plan.setValidity(dtoOb.getValidityPeriod());
        plan.setSmsAllowance(dtoOb.getSmsAllowance());
        plan.setDataAllowance(dtoOb.getDataAllowance());
        plan.setExtraData(dtoOb.getExtraDataAllowance());
        plan.setCreatedBy(admin);
        plan.setModidfiedBy(admin);
        plan.setRechargeAmount(dtoOb.getPlanAmount());
        plan.setVoiceAllowance(dtoOb.getVoiceAllowance());
        plan.setIsRefreshing(dtoOb.getDailyRefresh() == 1);
        plan.setIsExtraDataAvailable(dtoOb.getExtraDataAvailable() == 1);
        plan.setIsDeleted(dtoOb.getIsAvailable() == 0);
        this.planRepository.savePlanDetails(plan);

        responseMap.put("message", "Plan saved successfully!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> updatePlanDetails(PreUpdatePlanDetailsDto dtoOb, HttpServletRequest httpServletRequest) {

        if(dtoOb.getPlanCategoryId() != 7 && dtoOb.getValidityPeriod() == 0){
            throw new DataNotFoundException("Plan validity cannot be 0 days!");
        }

        int adminId = (Integer) httpServletRequest.getSession().getAttribute("adminId");
        Admin admin = this.adminRepository.getAdminById(adminId);
        if (admin == null) {
            throw new DataNotFoundException("Admin Data found!");
        }

        Plan plan = this.planRepository.getPlanById(dtoOb.getPlanId());
        if (plan == null) {
            throw new DataNotFoundException("Plan Data found!");
        }

        boolean isPlanAvailable = this.planRepository.isSamePlanAvailable(dtoOb.getIsNewPlan(), dtoOb.getPlanId(), dtoOb.getPlanCategoryId(), dtoOb.getPlanAmount(), dtoOb.getSmsAllowance(), dtoOb.getVoiceAllowance(), dtoOb.getDataAllowance(), dtoOb.getExtraDataAllowance(), dtoOb.getValidityPeriod(), dtoOb.getIsAvailable(), dtoOb.getExtraDataAvailable(), dtoOb.getDailyRefresh());
        if (isPlanAvailable) {
            throw new InvalidDataFoundException("Plan Already Available!");
        }
        Map<String, String> responseMap = new HashMap<>();
        plan.setValidity(dtoOb.getValidityPeriod());
        plan.setSmsAllowance(dtoOb.getSmsAllowance());
        plan.setDataAllowance(dtoOb.getDataAllowance());
        plan.setExtraData(dtoOb.getExtraDataAllowance());
        plan.setModidfiedBy(admin);
        plan.setRechargeAmount(dtoOb.getPlanAmount());
        plan.setVoiceAllowance(dtoOb.getVoiceAllowance());
        plan.setIsRefreshing(dtoOb.getDailyRefresh() == 1);
        plan.setIsExtraDataAvailable(dtoOb.getExtraDataAvailable() == 1);
        plan.setIsDeleted(dtoOb.getIsAvailable() == 0);
        this.planRepository.updatePlanDetails(plan);
        responseMap.put("message", "Plan updated successfully!");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, ClientDetailsPaginatedDto>> getFilteredUsersDetails(FilterUserRequest filterUserRequest) {
        Map<String, ClientDetailsPaginatedDto> responseMap = new HashMap<>();
        ClientDetailsPaginatedDto clientDetailsPaginatedDto = new ClientDetailsPaginatedDto();
        int userPageSize = filterUserRequest.getPageSize();
        int currentPage = filterUserRequest.getCurrentPage();
        Pageable pageable = PageRequest.of(currentPage - 1, userPageSize);
        List<UsersDetailsDto> usersDetailsDtos = new ArrayList<>();
        Page<Client> getPaginatedClientData = this.clientRepository.getPaginatedClientData(pageable, filterUserRequest);

        clientDetailsPaginatedDto.setDataAvailable(!getPaginatedClientData.isEmpty());
        clientDetailsPaginatedDto.setTotalPages(getPaginatedClientData.getTotalPages());
        clientDetailsPaginatedDto.setRequestType(filterUserRequest.getRequestType());

        for (Client client : getPaginatedClientData.getContent() ){
            UsersDetailsDto usersDetailsDto = getUsersDetailsDto(filterUserRequest, client);
            usersDetailsDtos.add(usersDetailsDto);
        }
        clientDetailsPaginatedDto.setClientFilteredRequests(usersDetailsDtos);
        responseMap.put("clientDataDto", clientDetailsPaginatedDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    private static UsersDetailsDto getUsersDetailsDto(FilterUserRequest filterUserRequest, Client client) {
        UsersDetailsDto usersDetailsDto = new UsersDetailsDto();
        usersDetailsDto.setClientId(client.getClientId());
        usersDetailsDto.setClientEmail(client.getEmail());
        usersDetailsDto.setClientName(client.getFirstName() + " " + client.getLastName());

        int currentStatus;

        if(filterUserRequest.getRequestType() == 2){
            currentStatus = AdminUserStatusEnum.SIGNED_UP.getUserStatusId();
        }else {
            if(client.isDocValidated()){
                currentStatus = AdminUserStatusEnum.REGISTERED.getUserStatusId();
            }else {
                currentStatus = AdminUserStatusEnum.PENDING.getUserStatusId();
            }
        }

        usersDetailsDto.setCurrentRequestStatus(currentStatus);
        return usersDetailsDto;
    }

    public ResponseEntity<Map<String,OtherClientDetailsDto>> getClientOtherDetails(int clientId, HttpServletRequest httpServletRequest){
        Map<String,OtherClientDetailsDto> responseMap = new HashMap<>();
        if(clientId == 0){
            throw new DataNotFoundException("Client Id Not Found!");
        }

        Client client = this.clientRepository.getClientById(clientId);
        ClientDocuments clientDocuments = this.clientDocumentsRepository.getClientDocumentDetails(client.getClientId());
        String aadharViewLink = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + client.getClientId() + File.separator + "AadharCard." + clientDocuments.getAadharCardExtension();
        String panViewLink = httpServletRequest.getContextPath() + File.separator + System.getenv("UninorDownloadPath") + client.getClientId() + File.separator + "PANCard." + clientDocuments.getPanCardExtension();
        OtherClientDetailsDto otherClientDetailsDto = new OtherClientDetailsDto();
        otherClientDetailsDto.setClientId(client.getClientId());
        otherClientDetailsDto.setClientBDate(Helper.formatBirthDate(client.getDateOfBirth()));
        otherClientDetailsDto.setClientPanNumber(client.getPanNumber());
        otherClientDetailsDto.setClientAadharNumber(client.getAadharNumber());
        otherClientDetailsDto.setClientName(client.getFirstName() + " " + client.getLastName());
        otherClientDetailsDto.setAadharFilePath(aadharViewLink);
        otherClientDetailsDto.setPanFilePath(panViewLink);
        responseMap.put("clientDetails", otherClientDetailsDto);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> sendClientContactEmail(String description, int clientId){
        if(clientId == 0){
            throw new DataNotFoundException("Client Id Not Found!");
        }

        if(description == null){
            throw new DataNotFoundException("Please provide description");
        }

        Map<String,String> responseMap = new HashMap<>();
        Client client = this.clientRepository.getClientById(clientId);

        if(client==null){
            throw new DataNotFoundException("Client Data Not Found!");
        }

        this.emailService.sendContactMail(client.getEmail(),client.getFirstName() + " " + client.getLastName(), description);
        responseMap.put("message","Email send to user");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>> sendSignUpUserNotificationEmail(int clientId){
        if(clientId == 0){
            throw new DataNotFoundException("Client Data not found");
        }

        Map<String,String> responseMap = new HashMap<>();
        Client client = this.clientRepository.getClientById(clientId);
        if(client==null){
            throw new DataNotFoundException("Client Data Not Found");
        }

        this.emailService.signedUserNotificationMail(client.getEmail(),client.getFirstName() + " " + client.getLastName());
        responseMap.put("message","Email send to user");
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

}

