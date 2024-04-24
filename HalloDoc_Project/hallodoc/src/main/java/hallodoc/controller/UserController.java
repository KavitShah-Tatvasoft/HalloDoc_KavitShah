package hallodoc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import hallodoc.dto.ClearCaseDto;
import hallodoc.dto.CloseCaseEditDataDto;
import hallodoc.dto.EncounterFormDto;
import hallodoc.dto.EncounterFormUserDetailsDto;
import hallodoc.dto.OrderVendorDetailsDto;
import hallodoc.dto.OrdersDetailsDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.VendorDetailsDto;
import hallodoc.email.EmailService;
import hallodoc.model.Admin;
import hallodoc.model.AspNetUsers;
import hallodoc.model.EncounterForm;
import hallodoc.model.HealthProfessionalTypes;
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
	private UserService uService;
	
	@Autowired
	private RequestDao requestDao;
	
	
	@Autowired
	private PatientService pService;
	

	@RequestMapping(value = "/sendReviewAgreement/{reqId}")
	public String showAgreementEmail(@PathVariable("reqId") int reqId, Model m) {
		
		boolean validateAgreement = uService.validateAgreementRequest(reqId);
		if(validateAgreement) {
			Request request = this.requestDao.getRequestOb(reqId);
			m.addAttribute("reqId",reqId);
			m.addAttribute("name", EmailService.capitalize(request.getRequestClient().getFirstName()) + " " + EmailService.capitalize(request.getRequestClient().getLastName()));
		return "common/review-agreement";
		
		}else {
			return "common/agreement-error";
		}
	}
	
	@RequestMapping(value="/acceptedReviewAgreement/{reqId}")
	public String acceptedReviewAgreement(@PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest) {
		
		uService.acceptAgreement(reqId, httpServletRequest, true, "");

		return "patient/patient-site";
	}
	
	@RequestMapping("/cancelAgreementDetails/{reqId}")
	public RedirectView cancelAgreementDetails(@RequestParam("cancel-details") String cancellationReasons, @PathVariable("reqId") int reqId, HttpServletRequest httpServletRequest ) {
		
		uService.acceptAgreement(reqId, httpServletRequest, false, cancellationReasons);
		
		return new RedirectView(httpServletRequest.getContextPath() + "/patientHome");
	}
	
	@RequestMapping("/viewRequestUploads/{reqId}")
	public String viewRequestUploads(@PathVariable("reqId") int reqId , HttpServletRequest httpServletRequest, Model m) {
		m.addAttribute("reqId", reqId);
		Request request = uService.getRequestById(reqId);
		m.addAttribute("ptName",request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber() );
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
		System.out.println("in upload");
		Date date = new Date();
		Request requestOb = uService.getRequestObject(requestId);
		try {
			HttpSession session = request.getSession(false);
			uService.uploadRequestDocument(document, name, requestOb, session, date);
		} catch (Exception e) {
			System.out.println("Http Session not found");
		}
		RedirectView redirectView = new RedirectView("/user/viewRequestUploads/" + requestId, true);
		return redirectView;
	}
	
	@ResponseBody
	@RequestMapping(value="/softDeleteUploadedFile", method = RequestMethod.POST)
	public String deleteUploadedFile(@RequestParam("fileId") int fileId) {
		
		uService.softDeleteRequestedFile(fileId);
		return "deleted";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/deleteMultipleFiles", method = RequestMethod.POST)
	public String deleteMultipleFiles(@RequestParam("data") String list) {
		
			uService.softDeleteMultipleRequestedFile(list);   // can you in query

		return "Success";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendFilesToPatient", method = RequestMethod.POST)
	public String sendFilesToPatient(@RequestParam("reqId") int reqId, @RequestParam("attachmentList") String list, HttpServletRequest httpServletRequest) {
		String status = uService.sendFilesToPatient(reqId,list,httpServletRequest);
		return status;
	}
	
	@RequestMapping(value="/sendOrderDetails/{reqId}")
	public String sendOrderDetails(@PathVariable("reqId") int reqId, Model model) {
		System.out.println(reqId);
		
		List<HealthProfessionalTypes> professionList = uService.getActiveProfessions();
		
		model.addAttribute("reqId",reqId);
		model.addAttribute("professionList",professionList);
		
		return "common/order-details";
	}
	
	@ResponseBody
	@RequestMapping(value="/getVendorDetails", method = RequestMethod.POST)
	public List<VendorDetailsDto> getVendorDetails(@RequestParam("professionTypeId")int professionTypeId) {
		System.out.println(professionTypeId +"hello");
		List<VendorDetailsDto> vendorList = uService.getActiveVendors(professionTypeId);
		return vendorList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getSelectedVendorDetails", method = RequestMethod.POST)
	public OrderVendorDetailsDto getSelectedVendorDetails(@RequestParam("vendorId") int vendorId) {
		OrderVendorDetailsDto orderVendorDetailsDto = uService.getOrderVendorDetails(vendorId);
		return orderVendorDetailsDto;
	}
	
	
	@RequestMapping(value="/sendOrderDetails", method = RequestMethod.POST)
	public RedirectView sendOrderDetails(@ModelAttribute("orderDetails") OrdersDetailsDto ordersDetailsDto, HttpServletRequest httpServletRequest) {
		String status = uService.sendOrderDetails(ordersDetailsDto,httpServletRequest);
		RedirectView redirectView = new RedirectView("../admin/adminDashboard");
		return redirectView;
	}
	
	@RequestMapping(value="/encounterForm/{reqId}")
	public String viewEncounterForm(@PathVariable("reqId") int reqId, Model m) {
		EncounterFormUserDetailsDto encounterFormUserDetailsDto = uService.getEncounterFormUserDetailsDto(reqId);
		EncounterFormDto encounterFormdto = uService.getEncounterFormDtoOb(reqId);
		m.addAttribute("reqId",reqId);
		m.addAttribute("userDetails",encounterFormUserDetailsDto);
		m.addAttribute("encounterForm",encounterFormdto);
		return "common/encounter-form";
	}
 	
	@RequestMapping(value="/editEncounterForm", method = RequestMethod.POST)
	public RedirectView editEncounterFormDetails(@ModelAttribute("editEncounterForm") EncounterFormDto encounterFormDto, HttpServletRequest httpServletRequest) {
//		System.out.println(encounterFormDto);
		String status = uService.updateEncounterFormDetails(encounterFormDto,httpServletRequest );
		return new RedirectView("../user/encounterForm/"+ encounterFormDto.getRequestId());
	}
	
	@RequestMapping(value="/closeCaseForm/{reqId}")
	public String closeCasePage(@PathVariable("reqId") int reqId, Model m) {
		m.addAttribute("reqId", reqId);
		Request request = uService.getRequestById(reqId);
		ClearCaseDto clearCaseDto = uService.getClearCasePtDetails(request);
		m.addAttribute("userDetails", clearCaseDto);
		m.addAttribute("ptName",request.getRequestClient().getFirstName() + " " + request.getRequestClient().getLastName());
		m.addAttribute("confNumber", request.getConfirmationNumber() );
		List<RequestDocumentsDto> requests = pService.getRequestDocuments(reqId);
		m.addAttribute("docList", requests);
		return "common/close-case";
	}
	
	
	@RequestMapping(value="/closeCaseRequest/{reqId}")
	public RedirectView closeCaseRequest(@PathVariable("reqId") int reqId, HttpServletRequest request) {
		
		String changeStatus = uService.closeRequestedCase(reqId, request);
		
		RedirectView redirectView = new RedirectView(request.getContextPath()+"/admin/adminDashboard");
		return redirectView;
	}
	
//	@ResponseBody
//	@RequestMapping(value="/updateCloseCaseDetails", method = RequestMethod.POST )
//	public String updateCloseCaseDetails(CloseCaseEditDataDto closeCaseEditDataDto){
//		System.out.println(closeCaseEditDataDto);
//		String status = uService.editCloseCaseDetails(closeCaseEditDataDto);
//		return "updated";
//	}
}

	
