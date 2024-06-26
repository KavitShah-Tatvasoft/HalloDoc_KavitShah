package hallodoc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import hallodoc.dto.BlockHistoryFilterData;
import hallodoc.dto.BlockHistoryPaginatedDto;
import hallodoc.dto.BlockRequestsTableData;
import hallodoc.dto.ClearCaseDto;
import hallodoc.dto.CloseCaseEditDataDto;
import hallodoc.dto.EmailLogDashboardDto;
import hallodoc.dto.EmailLogFiltersDto;
import hallodoc.dto.EmailLogPaginatedDto;
import hallodoc.dto.EncounterFormDto;
import hallodoc.dto.EncounterFormUserDetailsDto;
import hallodoc.dto.HealthProfessionalDataDto;
import hallodoc.dto.NewBusinessDto;
import hallodoc.dto.OrderVendorDetailsDto;
import hallodoc.dto.OrdersDetailsDto;
import hallodoc.dto.PatientHistoryDto;
import hallodoc.dto.PatientHistoryPaginatedDto;
import hallodoc.dto.PatientRecordsDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.SMSLogDashboardDataDto;
import hallodoc.dto.SearchRecordsDashboardData;
import hallodoc.dto.SearchRecordsFilter;
import hallodoc.dto.SearchRecordsPaginationDto;
import hallodoc.dto.SmsLogPaginatedDto;
import hallodoc.dto.VendorDetailsDto;
import hallodoc.email.EmailService;
import hallodoc.helper.ExcelSheetHelper;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.BlockRequests;
import hallodoc.model.EmailLog;
import hallodoc.model.EncounterForm;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;
import hallodoc.model.Request;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;
import hallodoc.repository.HealthProfessionalsDao;
import hallodoc.repository.RequestDao;
import hallodoc.repository.RequestStatusLogDao;
import hallodoc.repository.RequestWiseFileDao;
import hallodoc.service.PatientService;
import hallodoc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private PatientService pService;

	@RequestMapping(value = "/sendReviewAgreement/{reqId}")
	public String showAgreementEmail(@PathVariable("reqId") int reqId, Model m) {

		boolean validateAgreement = userService.validateAgreementRequest(reqId);
		if (validateAgreement) {
			Request request = this.requestDao.getRequestOb(reqId);
			m.addAttribute("reqId", reqId);
			m.addAttribute("name", EmailService.capitalize(request.getRequestClient().getFirstName()) + " "
					+ EmailService.capitalize(request.getRequestClient().getLastName()));
			return "common/review-agreement";

		} else {
			return "common/agreement-error";
		}
	}

	@RequestMapping(value = "/acceptedReviewAgreement/{reqId}")
	public String acceptedReviewAgreement(@PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest) {

		userService.acceptAgreement(reqId, httpServletRequest, true, "");

		return "patient/patient-site";
	}

	@RequestMapping("/cancelAgreementDetails/{reqId}")
	public RedirectView cancelAgreementDetails(@RequestParam("cancel-details") String cancellationReasons,
			@PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest) {

		userService.acceptAgreement(reqId, httpServletRequest, false, cancellationReasons);

		return new RedirectView(httpServletRequest.getContextPath() + "/patientHome");
	}

	@RequestMapping("/viewRequestUploads/{reqId}")
	public String viewRequestUploads(@PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest, Model m) {
		m.addAttribute("reqId", reqId);
		Request request = userService.getRequestById(reqId);
		m.addAttribute("ptName",
				request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber());
		List<RequestDocumentsDto> requests = pService.getRequestDocuments(reqId);
		m.addAttribute("docList", requests);
		AspNetUsers aspNetUsersOb = (AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser");
		User userOb = aspNetUsersOb.getUser();

		m.addAttribute("userOb", userOb);

		return "common/requested-case-view-uploads";
	}

	@RequestMapping(value = "/uploadRequestDocument", method = RequestMethod.POST)
	public RedirectView uploadRequestDocument(@RequestParam("documentFile") CommonsMultipartFile document,
			@RequestParam("uploaderName") String name, @RequestParam("requestId") int requestId,
			HttpServletRequest request) {

		Date date = new Date();
		Request requestOb = userService.getRequestObject(requestId);
		try {
			HttpSession session = request.getSession(false);
			userService.uploadRequestDocument(document, name, requestOb, session, date);
		} catch (Exception e) {

		}
		RedirectView redirectView = new RedirectView("/user/viewRequestUploads/" + requestId, true);
		return redirectView;
	}

	@ResponseBody
	@RequestMapping(value = "/softDeleteUploadedFile", method = RequestMethod.POST)
	public String deleteUploadedFile(@RequestParam("fileId") int fileId) {

		userService.softDeleteRequestedFile(fileId);
		return "deleted";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteMultipleFiles", method = RequestMethod.POST)
	public String deleteMultipleFiles(@RequestParam("data") String list) {

		userService.softDeleteMultipleRequestedFile(list); // can you in query

		return "Success";
	}

	@ResponseBody
	@RequestMapping(value = "/sendFilesToPatient", method = RequestMethod.POST)
	public String sendFilesToPatient(@RequestParam("reqId") int reqId, @RequestParam("attachmentList") String list,
			HttpServletRequest httpServletRequest) {
		String status = userService.sendFilesToPatient(reqId, list, httpServletRequest);
		return status;
	}

	@RequestMapping(value = "/sendOrderDetails/{reqId}", method = RequestMethod.GET)
	public String sendOrderDetails(@PathVariable("reqId") int reqId, Model model) {

		List<HealthProfessionalTypes> professionList = userService.getActiveProfessions();

		model.addAttribute("reqId", reqId);
		model.addAttribute("professionList", professionList);

		return "common/order-details";
	}

	@ResponseBody
	@RequestMapping(value = "/getVendorDetails", method = RequestMethod.POST)
	public List<VendorDetailsDto> getVendorDetails(@RequestParam("professionTypeId") int professionTypeId) {
		List<VendorDetailsDto> vendorList = userService.getActiveVendors(professionTypeId);
		return vendorList;
	}

	@ResponseBody
	@RequestMapping(value = "/getSelectedVendorDetails", method = RequestMethod.POST)
	public OrderVendorDetailsDto getSelectedVendorDetails(@RequestParam("vendorId") int vendorId) {
		OrderVendorDetailsDto orderVendorDetailsDto = userService.getOrderVendorDetails(vendorId);
		return orderVendorDetailsDto;
	}

	@RequestMapping(value = "/sendOrderDetails", method = RequestMethod.POST)
	public RedirectView sendOrderDetails(@ModelAttribute("orderDetails") OrdersDetailsDto ordersDetailsDto,
			HttpServletRequest httpServletRequest) {
		String status = userService.sendOrderDetails(ordersDetailsDto, httpServletRequest);
		int roleId = ((AspNetUsers) httpServletRequest.getSession().getAttribute("aspUser")).getUser().getAspNetRoles()
				.getId();
		RedirectView adminView = new RedirectView("../admin/adminDashboard");
		RedirectView providerView = new RedirectView("../provider/provider-dashboard");
		if (roleId == 1) {
			return adminView;
		} else {
			return providerView;
		}
	}

	@RequestMapping(value = "/encounterForm/{reqId}")
	public String viewEncounterForm(@PathVariable("reqId") int reqId, Model m) {
		EncounterFormUserDetailsDto encounterFormUserDetailsDto = userService.getEncounterFormUserDetailsDto(reqId);
		EncounterFormDto encounterFormdto = userService.getEncounterFormDtoOb(reqId);
		m.addAttribute("reqId", reqId);
		m.addAttribute("userDetails", encounterFormUserDetailsDto);
		m.addAttribute("encounterForm", encounterFormdto);
		return "common/encounter-form";
	}

	@RequestMapping(value = "/editEncounterForm", method = RequestMethod.POST)
	public RedirectView editEncounterFormDetails(@ModelAttribute("editEncounterForm") EncounterFormDto encounterFormDto,
			HttpServletRequest httpServletRequest) {
//		 ;
		String status = userService.updateEncounterFormDetails(encounterFormDto, httpServletRequest);
		return new RedirectView("../user/encounterForm/" + encounterFormDto.getRequestId());
	}

	@RequestMapping(value = "/closeCaseForm/{reqId}")
	public String closeCasePage(@PathVariable("reqId") int reqId, Model m) {
		m.addAttribute("reqId", reqId);
		Request request = userService.getRequestById(reqId);
		ClearCaseDto clearCaseDto = userService.getClearCasePtDetails(request);
		m.addAttribute("userDetails", clearCaseDto);
		m.addAttribute("ptName",
				request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber());
		List<RequestDocumentsDto> requests = pService.getRequestDocuments(reqId);
		m.addAttribute("docList", requests);
		return "common/close-case";
	}

	@RequestMapping(value = "/closeCaseRequest/{reqId}")
	public RedirectView closeCaseRequest(@PathVariable("reqId") int reqId, HttpServletRequest request) {

		String changeStatus = userService.closeRequestedCase(reqId, request);

		RedirectView redirectView = new RedirectView(request.getContextPath() + "/admin/adminDashboard");
		return redirectView;
	}

	@ResponseBody
	@RequestMapping(value = "/updateCloseCaseDetails", method = RequestMethod.POST)
	public String updateCloseCaseDetails(CloseCaseEditDataDto closeCaseEditDataDto) throws ParseException {
		;
		String status = userService.editCloseCaseDetails(closeCaseEditDataDto);
		return "updated";
	}

	@RequestMapping(value = "/professionMenu")
	public String showProfessionMenu(Model m) {
		List<HealthProfessionalTypes> healthProfessionalTypes = this.userService.getActiveProfessions();
		m.addAttribute("healthProfessionalTypes", healthProfessionalTypes);
		return "common/profession-menu-vendor";
	}

	@ResponseBody
	@RequestMapping(value = "/getProfessionMenuData", method = RequestMethod.POST)
	public List<HealthProfessionalDataDto> getProfessionMenuData(@RequestParam("name") String name,
			@RequestParam("typeId") Integer typeId) {

		List<HealthProfessionalDataDto> list = this.userService.getHealthProfessionalsData(name, typeId);
		return list;
	}

	@RequestMapping(value = "/addNewBusiness")
	public String showNewBusinessPage(Model m) {
		List<HealthProfessionalTypes> healthProfessionalTypes = this.userService.getActiveProfessions();
		m.addAttribute("healthProfessionalTypes", healthProfessionalTypes);
		return "common/new-business";
	}

	@RequestMapping(value = "/addNewBusinessRequest", method = RequestMethod.POST)
	public RedirectView addNewBusinessRequest(NewBusinessDto newBusinessDto, HttpServletRequest request) {
		;

		this.userService.addNewBusiness(newBusinessDto);

		return new RedirectView(request.getContextPath() + "/user/professionMenu");
	}

	@RequestMapping(value = "/editExsistingBusiness/{businessId}")
	public String editExsistingBusiness(@PathVariable("businessId") Integer businessId, Model m) {
		HealthProfessionals healthProfessionals = this.userService.getHealthProfessionalData(businessId);
		List<HealthProfessionalTypes> healthProfessionalTypes = this.userService.getActiveProfessions();
		m.addAttribute("healthProfessional", healthProfessionals);
		m.addAttribute("healthProfessionalTypes", healthProfessionalTypes);
		m.addAttribute("businessId", businessId);
		return "common/edit-business";
	}

	@RequestMapping(value = "/editBusinessRequest", method = RequestMethod.POST)
	public RedirectView editBusinessRequest(NewBusinessDto newBusinessDto, HttpServletRequest request) {
		;
		this.userService.updateBusiness(newBusinessDto);
		return new RedirectView(request.getContextPath() + "/user/professionMenu");
	}

	@ResponseBody
	@RequestMapping(value = "/deleteExsistingBusiness", method = RequestMethod.POST)
	public String deleteExsistingBusiness(@RequestParam("businessId") Integer businessId) {
		return this.userService.deleteBusinessRequest(businessId);

	}

	@RequestMapping("/emailLogs")
	public String viewSmsLogs() {
		return "common/email-log";
	}

	@RequestMapping("/smsLogs")
	public String viewEmailLogs() {
		return "common/sms-logs";
	}

	@ResponseBody
	@RequestMapping(value = "/getFilteredEmailLogData", method = RequestMethod.POST)
	public EmailLogPaginatedDto getFilteredEmailLogData(EmailLogFiltersDto emailLogFiltersDto) {
		return this.userService.getEmailLogFilteredDate(emailLogFiltersDto);
	}

	@ResponseBody
	@RequestMapping(value = "/getFilteredSMSLogData", method = RequestMethod.POST)
	public SmsLogPaginatedDto getFilteredSMSLogData(EmailLogFiltersDto emailLogFiltersDto) {

		return this.userService.getSmsLogFilteredData(emailLogFiltersDto);
	}

	@RequestMapping("/patientHistory")
	public String viewPatientHistory() {
		return "common/patient-history";
	}

	@ResponseBody
	@RequestMapping(value = "/getPatientHistoryData", method = RequestMethod.POST)
	public PatientHistoryPaginatedDto getPatientHistoryData(PatientHistoryDto patientHistoryDto) {
		return this.userService.getPatientHistory(patientHistoryDto);
	}

	@RequestMapping(value = "/explore/{userId}")
	public String showUserCaseDetails(@PathVariable("userId") Integer userId, Model m) {
		List<PatientRecordsDto> reqLists = this.userService.getRequestByUserId(userId);
		m.addAttribute("dataLists", reqLists);
		return "common/patient-explore";
	}

	@RequestMapping("/blockHistory")
	public String viewBlockHistory() {
		return "common/block-history";
	}

	@ResponseBody
	@RequestMapping(value = "/getBlockHistoryData", method = RequestMethod.POST)
	public BlockHistoryPaginatedDto getBlockRequestsDetails(BlockHistoryFilterData blockHistoryFilterData) {
		return this.userService.getBlockRequestData(blockHistoryFilterData);
	}

	@ResponseBody
	@RequestMapping(value = "/unblockCase", method = RequestMethod.POST)
	public String unblockCase(@RequestParam("blockId") Integer blockId, HttpServletRequest httpServletRequest) {
		return this.userService.unblockRequestedCase(blockId, httpServletRequest);
	}

	@RequestMapping("/searchRecords")
	public String viewSearchRecords() {
		return "common/search-records";
	}

	@ResponseBody
	@RequestMapping(value = "/getSearchRecordFilteredData", method = RequestMethod.POST)
	public SearchRecordsPaginationDto getFilteredSearchRecordData(SearchRecordsFilter searchRecordsFilter) {
		return this.userService.getFilteredSearchRecords(searchRecordsFilter);
	}

	@ResponseBody
	@RequestMapping(value = "/exportSearchRecordsToExcel", method = RequestMethod.POST)
	public ResponseEntity<Resource> exportSearchRecordsToExcel(SearchRecordsFilter searchRecordsFilter)
			throws IOException {
		List<SearchRecordsDashboardData> list = this.userService.getExportFilteredSearchRecords(searchRecordsFilter);
		ResponseEntity<Resource> resource = this.userService.exportSearchRecord(list);
		return resource;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteRequest", method = RequestMethod.POST)
	public String deletedRequest(@RequestParam("reqId") Integer reqId) {
		return this.userService.deleteRequest(reqId);
	}

	@RequestMapping(value = "/finalize-encounter-form/{reqId}", method = RequestMethod.GET)
	public RedirectView finalizeEncounterForm(@PathVariable("reqId") int reqId, HttpServletRequest request) {

		this.userService.finalizeEncounterForm(reqId);
		return new RedirectView(request.getContextPath() + "/provider/provider-dashboard");
	}

}
