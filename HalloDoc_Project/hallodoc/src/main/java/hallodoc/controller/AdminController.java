package hallodoc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.AdminAddressDto;
import hallodoc.dto.AdminContactDto;
import hallodoc.dto.AdminRegions;
import hallodoc.dto.AssignCaseDto;
import hallodoc.dto.BlockCaseDto;
import hallodoc.dto.CancelCaseDetailsDto;
import hallodoc.dto.CreatePatientRequestDto;
import hallodoc.dto.CreateRoleDataDto;
import hallodoc.dto.CreateShiftDto;
import hallodoc.dto.EditRoleDto;
import hallodoc.dto.EventsDto;
import hallodoc.dto.ExportDataDto;
import hallodoc.dto.GetRolesDto;
import hallodoc.dto.MenusDto;
import hallodoc.dto.NewProviderAccountDto;
import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.NewStatePageDataDto;
import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.dto.PhysicianResources;
import hallodoc.dto.ProviderMailingDto;
import hallodoc.dto.ProviderMenuDto;
import hallodoc.dto.ProviderUpdatedInfoDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.RolesDto;
import hallodoc.dto.SendAgreementDto;
import hallodoc.dto.SendLinkDto;
import hallodoc.dto.ShowProviderDetailsDto;
import hallodoc.dto.UpdateCaseDto;
import hallodoc.dto.UserAccessDto;
import hallodoc.dto.ViewNotesDto;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.CaseTag;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.repository.AspNetUserDao;
import hallodoc.service.AdminNewPatientRequestService;
import hallodoc.service.AdminService;
import hallodoc.service.UserService;
import hallodoc.sms.SmsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService aService;

	@Autowired
	private UserService uService;

	@Autowired
	private AdminNewPatientRequestService adminRequestService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private AspNetUserDao aspNetUserDao;

	Logger logger = Logger.getLogger(AdminController.class.getName());

	@RequestMapping("/errorPage")
	public String showErrorPage(HttpServletRequest request) {
		request.setAttribute("errorMessage", "Session expired! Please login again to continue.");

		return "admin/authorization-error";
	}

	@ResponseBody
	@PostMapping("/sendLinkByEmail")
	public String submitForm(HttpServletRequest request, SendLinkDto sendLinkDto) {
		Boolean check = aService.sendRequestLink(request, sendLinkDto);
		return "Success";
	}

	@RequestMapping("/adminDashboard")
	public ModelAndView adminDashboard(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("admin/admin-dashboard");
		List<CaseTag> caseTags = uService.getAllCancellationReasons();
		modelAndView.addObject("cancelReasons", caseTags);

		if (inputFlashMap != null) {

			String message = (String) inputFlashMap.get("message");
			String showAlertType = (String) inputFlashMap.get("alertType");
			modelAndView.addObject("msg", message);
			modelAndView.addObject("showalert", true);
			modelAndView.addObject("showAlertTypeJsp", showAlertType);

		} else {
			modelAndView.addObject("showalert", false);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/getRequestData", method = RequestMethod.POST)
	@ResponseBody
	public NewStatePageDataDto getNewRequestData(@RequestParam("status") String status,
			@RequestParam("pageNo") int pageNo) {

		NewStatePageDataDto requestsList = aService.getStatusCorrespondingRequests(status, pageNo);

		return requestsList;

	}

	@RequestMapping(value = "/getStatusWiseCount", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> getStatusRequestCount() {

		return this.aService.getStatusWiseRequestCount();

	}

	@ResponseBody
	@RequestMapping(value = "/searchRequestFilter", method = RequestMethod.POST)
	public NewStatePageDataDto searchRequestFilter(RequestFiltersDto requestFiltersDto) {
		NewStatePageDataDto filteredList = uService.getFilteredRequest(requestFiltersDto);
		return filteredList;
	}

	@RequestMapping("/adminCreatePatientRequest")
	public ModelAndView adminCreateRequest(HttpServletRequest request) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		ModelAndView modelAndView = new ModelAndView("admin/admin-create-patient-request");
		if (inputFlashMap != null) {
			String message = (String) inputFlashMap.get("message");
			String showAlertType = (String) inputFlashMap.get("alertType");
			modelAndView.addObject("msg", message);
			modelAndView.addObject("showalert", true);
			modelAndView.addObject("showAlertTypeJsp", showAlertType);

		} else {
			modelAndView.addObject("showalert", false);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/adminCreatePtRequest", method = RequestMethod.POST)
	public RedirectView adminCreatePtRequest(RedirectAttributes attributes,
			@ModelAttribute("createPatientRequestAdmin") CreatePatientRequestDto createPatientRequestDto,
			HttpServletRequest request) throws ParseException {
		int roleId = ((AspNetUsers) request.getSession().getAttribute("aspUser")).getUser().getAspNetRoles().getId();
		String status = adminRequestService.createNewRequest(request, createPatientRequestDto);
		RedirectView adminDashboard = new RedirectView("adminDashboard", true);
		RedirectView providerDashboard = new RedirectView("../provider/provider-dashboard", true);
		if (status.equalsIgnoreCase("UserExsist")) {
			attributes.addFlashAttribute("message", "User already exisits");
			attributes.addFlashAttribute("alertType", "faliure");
		} else {
			attributes.addFlashAttribute("message", "Request Created");
			attributes.addFlashAttribute("alertType", "success");
		}
		if (roleId == AspNetRolesEnum.ADMIN.getAspNetRolesId()) {
			return adminDashboard;
		} else {
			return providerDashboard;
		}
	}

	@RequestMapping("/viewCase/{requestId}")
	public String viewCasePatient(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		Request requestOb = uService.getRequestObject(id);
		m.addAttribute("requestOb", requestOb);
		return "common/view-case";
	}

	@ResponseBody
	@RequestMapping(value = "viewCase/updateCaseDetails", method = RequestMethod.POST)
	public String updateCase(UpdateCaseDto updateCaseDto) {
		String status = uService.updateViewCaseDetails(updateCaseDto);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/cancelRequestedCase")
	public String cancelCase(CancelCaseDetailsDto cancelCaseDetailsDto, HttpServletRequest httpServletRequest) {
		Boolean status = uService.cancelRequestedCase(cancelCaseDetailsDto, httpServletRequest);
		return "Cancelled Case";
	}

	@ResponseBody
	@RequestMapping(value = "/blockRequestedCase")
	public String blockCase(BlockCaseDto blockCaseDto, HttpServletRequest httpServletRequest) {
		Boolean status = uService.blockRequestedCase(blockCaseDto, httpServletRequest);
		return "Blocked Case";
	}

	@RequestMapping(value = "/viewNotes/{requestId}")
	public String viewNotes(@PathVariable("requestId") int id, HttpServletRequest request, Model m) {
		m.addAttribute("reqId", id);
		return "common/view-notes";
	}

	@ResponseBody
	@RequestMapping(value = "/getViewNotesData", method = RequestMethod.POST)
	public List<ViewNotesDto> getViewNotesData(@RequestParam("reqId") int id) {
		List<ViewNotesDto> viewNotesDtos = uService.getRequestSpecificLogs(id);
		return viewNotesDtos;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAdminNote", method = RequestMethod.POST)
	public String updateAdminNote(@RequestParam("adminNote") String adminNote, @RequestParam("reqId") int id,
			HttpServletRequest httpServletRequest) {
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		uService.updateAdminNote(adminNote, id, aspNetUsers);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/getPhysiciansByRegion", method = RequestMethod.POST)
	public List<PhysicianAssignCaseDto> getPhysicianByRegion(@RequestParam("regionId") int regionId) {
		List<PhysicianAssignCaseDto> physicians = aService.getPhysicianByRegion(regionId);
		return physicians;
	}

	@ResponseBody
	@RequestMapping(value = "/assignPhysician", method = RequestMethod.POST)
	public String assignPhysicianToRequest(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {

		aService.assignPhysicianToRequest(assignCaseDto, httpServletRequest);
		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/exportStatusWiseData", method = RequestMethod.POST)
	public ResponseEntity<Resource> exportDataToExcel(ExportDataDto exportDataDto) throws IOException {

		RequestFiltersDto requestFiltersDto = new RequestFiltersDto();
		requestFiltersDto.setPatientName(exportDataDto.getPatientName());
		requestFiltersDto.setRequestType(exportDataDto.getRequestType());
		requestFiltersDto.setStateName(exportDataDto.getStateName());
		requestFiltersDto.setStatusType(exportDataDto.getStatusType());

		List<NewRequestDataDto> requestsList = uService.getExportFilteredRequest(requestFiltersDto);
		ResponseEntity<Resource> resource = uService.exportDataToExcelSheet(requestsList,
				exportDataDto.getCurrentStatus());
		return resource;
	}

	@ResponseBody
	@RequestMapping(value = "/clearCase", method = RequestMethod.POST)
	public String cancelCase(@RequestParam("reqId") int reqId, HttpServletRequest httpServletRequest) {

		uService.clearRequestedCase(reqId, httpServletRequest);

		return "cleared";
	}

	@ResponseBody
	@RequestMapping(value = "/transferPhysician", method = RequestMethod.POST)
	public String transferRequestedCase(AssignCaseDto assignCaseDto, HttpServletRequest httpServletRequest) {

		aService.transferRequestedCase(assignCaseDto, httpServletRequest);

		return "Transferred";
	}

	@ResponseBody
	@RequestMapping(value = "/getSendAgreementData", method = RequestMethod.POST)
	public SendAgreementDto getSendAgreementDetails(@RequestParam("reqId") int reqId,
			HttpServletRequest httpServletRequest) {

		SendAgreementDto sendAgreementDto = uService.getRequiredSendAgreementDetails(reqId, httpServletRequest);
		return sendAgreementDto;

	}

//	@RequestMapping(value = "/sendAgreement")
//	public String sendAgreement() {
//		smsService.sendAgreementSMS();
//		return "Sent";
//	}
//	
	@ResponseBody
	@RequestMapping(value = "/sendAgreementToPatient", method = RequestMethod.POST)
	public String sendAgreementToPatient(SendAgreementDto sendAgreementDto, HttpServletRequest httpServletRequest) {

		String status = uService.sendAgreementToPatient(sendAgreementDto, httpServletRequest);
		return status;
	}

	@Transactional
	@RequestMapping(value = "/adminProfile")
	public String viewAdminProfile(HttpServletRequest httpServletRequest, Model m) {
		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		Admin admin = aspNetUsers.getAdmin();
//		List<Region> regions = admin.getRegions();
		List<AdminRegions> adminRegions = aService.getAdminRegions(admin);
		m.addAttribute("username", aspNetUsers.getUser_name());
		m.addAttribute("adminEmail", aspNetUsers.getEmail());
		m.addAttribute("phoneNumber", aspNetUsers.getPhone_number());
		m.addAttribute("adminOb", admin);
		m.addAttribute("regions", adminRegions);
		return "admin/admin-profile";
	}

	@ResponseBody
	@RequestMapping(value = "/changeAdminPassword", method = RequestMethod.POST)
	public String changeAdminPassword(@RequestParam("pass") String password, HttpServletRequest httpServletRequest) {

		AspNetUsers aspNetUsers = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");

		return aService.updateAspUserPassword(aspNetUsers, password, httpServletRequest);

	}

	@ResponseBody
	@RequestMapping(value = "/changeAdminAddress", method = RequestMethod.POST)
	public String changeAdminAddress(AdminAddressDto adminAddressDto, HttpServletRequest httpServletRequest) {

		return this.aService.updateAspUserAddress(httpServletRequest, adminAddressDto);

	}

	@ResponseBody
	@RequestMapping(value = "/updateAdminContactDetails", method = RequestMethod.POST)
	public String updateAdminContactDetails(AdminContactDto adminContactDto, HttpServletRequest httpServletRequest) {

		return this.aService.updateAdminContactDetails(adminContactDto, httpServletRequest);

	}

	@RequestMapping(value = "/physicianMenu")
	public String showProviderMenu() {
		return "admin/provider-menu";
	}

	@ResponseBody
	@RequestMapping(value = "/getFilteredProviderMenuData", method = RequestMethod.POST)
	public List<ProviderMenuDto> getProviderMenuData(@RequestParam("region") Integer regionId) {
		return this.aService.getProviderMenuDetails(regionId);
	}

	@RequestMapping(value = "/createProviderAccount")
	public String createPhysicianAccount(Model m) {
		List<GetRolesDto> rolesDto = this.aService.getPhysicianRoles();
		m.addAttribute("rolesDto", rolesDto);
		return "admin/create-provider-account";
	}

	@ResponseBody
	@RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
	public String checkUsername(@RequestParam("name") String name) {
		return this.uService.checkUserNameValidation(name);
	}

	@RequestMapping(value = "/createNewProviderAccount", method = RequestMethod.POST)
	public RedirectView createNewProviderAccount(NewProviderAccountDto newProviderAccountDto,
			HttpServletRequest request) {

		String status = this.aService.createNewProvider(newProviderAccountDto, request);

		return new RedirectView("physicianMenu", true);
	}

	@RequestMapping("/updatePhysicianAccount/{physicianId}")
	public String updatePhysicianAccount(@PathVariable("physicianId") Integer physicianId, Model m) {
		List<Region> regionList = this.aService.getRegionList();
		ShowProviderDetailsDto providerData = this.aService.getProviderDetails(physicianId);
		List<GetRolesDto> rolesDto = this.aService.getPhysicianRoles();
		m.addAttribute("rolesDto", rolesDto);
		m.addAttribute("listRegions", regionList);
		m.addAttribute("providerData", providerData);
		return "admin/update-provider-account";
	}

	@ResponseBody
	@RequestMapping(value = "/updateProviderRoleStatus", method = RequestMethod.POST)
	public String updateProviderRoleStatus(@RequestParam("id") Integer id, @RequestParam("role") Integer role,
			@RequestParam("status") Integer status, HttpServletRequest http) {

		return this.aService.updateProviderRoleStatus(id, role, status, http);

	}

	@ResponseBody
	@RequestMapping(value = "/updateProviderPassword", method = RequestMethod.POST)
	public String updateProviderPassword(@RequestParam("id") Integer id, @RequestParam("password") String password,
			HttpServletRequest httpServletRequest) {

		return this.aService.updatePhysicianPassword(id, password, httpServletRequest);

	}

	@ResponseBody
	@RequestMapping(value = "/updateProviderMailingDetails", method = RequestMethod.POST)
	public String updateProviderMailingDetails(ProviderMailingDto providerMailingDto,
			HttpServletRequest httpServletRequest) {
		return this.aService.updateProviderMailingDetails(providerMailingDto, httpServletRequest);
	}

	@ResponseBody
	@RequestMapping(value = "/updateProviderInfoDetails", method = RequestMethod.POST)
	public String updateProviderInfoDetails(ProviderUpdatedInfoDto providerUpdatedInfoDto,
			HttpServletRequest httpServletRequest) {
		return this.aService.updateProviderInfo(providerUpdatedInfoDto, httpServletRequest);
	}

	@RequestMapping("/deleteProviderAccount/{phyId}")
	public RedirectView deleteProviderAccount(@PathVariable("phyId") Integer phyId) {
		this.aService.deleteProviderAccount(phyId);
		return new RedirectView("../physicianMenu", true);
	}

	@ResponseBody
	@RequestMapping(value = "/toggleNotification", method = RequestMethod.POST)
	public String toggleNotification(@RequestParam("id") Integer id) {
		return this.aService.changeProviderNotification(id);
	}

	@ResponseBody
	@RequestMapping(value = "/contactProvider", method = RequestMethod.POST)
	public String contactProvider(@RequestParam("id") Integer id, @RequestParam("method") String method,
			@RequestParam("message") String message, HttpServletRequest httpServletRequest) {
		return this.aService.contactProvider(id, method, message, httpServletRequest);
	}

	@RequestMapping(value = "/createRoleAccess", method = RequestMethod.GET)
	public String createRoleAccess() {

		return "admin/create-role";
	}

	@ResponseBody
	@RequestMapping(value = "/getMenus", method = RequestMethod.POST)
	public List<MenusDto> getMenus(@RequestParam("role") Integer role) {
		return this.aService.getRoleMenus(role);
	}

	@RequestMapping(value = "/createNewRole", method = RequestMethod.POST)
	public RedirectView createNewRole(CreateRoleDataDto createRoleDataDto, HttpServletRequest httpServletRequest) {

		this.aService.createNewRoleAccess(createRoleDataDto, httpServletRequest);

		return new RedirectView("accountAccess", true);
	}

	@RequestMapping(value = "/accountAccess", method = RequestMethod.GET)
	public String viewAccountAccess() {
		return "admin/account-access";
	}

	@ResponseBody
	@RequestMapping(value = "/getRoleDetails", method = RequestMethod.GET)
	public List<RolesDto> getRoleDetails() {
		return this.aService.getRolesDataForAccountAccess();
	}

	@RequestMapping(value = "/editRoleAccess/{roleId}", method = RequestMethod.GET)
	public String editRoleAccess(@PathVariable("roleId") Integer roleId, Model m) {

		EditRoleDto editRoleDto = this.aService.getEditRolesDetails(roleId);
		m.addAttribute("editDetails", editRoleDto);
		m.addAttribute("roleId", roleId);

		return "admin/edit-role";
	}

	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public RedirectView updateRole(@RequestParam("selectedMenu") String menuString,
			@RequestParam("roleId") Integer roleId) {

		this.aService.updateRoleDetails(menuString, roleId);

		return new RedirectView("accountAccess", true);
	}

	@ResponseBody
	@RequestMapping("/deleteRole")
	public String deleteRole(@RequestParam("roleId") Integer roleId) {

		this.aService.deleteRole(roleId);
		return "Deleted Role";
	}

	@RequestMapping(value = "/user-access", method = RequestMethod.GET)
	public String accountAccess() {
		return "admin/user-access";
	}

	@ResponseBody
	@RequestMapping(value = "/get-user-access-data", method = RequestMethod.POST)
	public List<UserAccessDto> getUserAccessData(@RequestParam("typeId") Integer typeId) {
		List<UserAccessDto> list = this.aService.getUserAccessData(typeId);
		return list;
	}

	@RequestMapping(value = "/provider-location", method = RequestMethod.GET)
	public String seeProviderLocation() {
		return "admin/provider-location";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public RedirectView logoutUser(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		RedirectView redirectView = new RedirectView(request.getServletContext().getContextPath() + "/patient_login",
				false);
		return redirectView;

	}
	
	@RequestMapping(value = "/scheduling-day", method = RequestMethod.GET)
	public String dayScheduling(Model m) {
		return "admin/scheduling-day";
	}
	
	@ResponseBody
	@RequestMapping(value="/create-shift", method=RequestMethod.POST)
	public boolean createShift(CreateShiftDto createShiftDto, HttpServletRequest httpServletRequest) {
		System.out.println(createShiftDto.getIsRepeated());
		
		return this.aService.createNewShift(createShiftDto,httpServletRequest );
	}
	
	@ResponseBody
	@RequestMapping(value="/get-physician-details-scheduling", method = RequestMethod.GET)
	public List<PhysicianResources> getPhysicianDetails(HttpServletRequest httpServletRequest){
		return this.aService.getAllPhysicianDetails(httpServletRequest);
	}
	
	@ResponseBody
	@RequestMapping(value="get-physician-events", method = RequestMethod.GET)
	public List<EventsDto> getEventsData(){
		return this.aService.getAllActiveEvents();
	}
}
