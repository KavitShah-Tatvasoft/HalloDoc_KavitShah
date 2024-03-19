package hallodoc.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CommonRequestDto {

	private String reqFirstName;
	private String reqLastName;
	private String reqMobileNumber;
	private String reqEmail;
	private String reqRelation;
	private String reqProperty;
	private String reqStreet;
	private String reqCity;
	private String reqState;
	private String reqZipcode;
	private String reqCaseNumber;

	private String symptoms;
	private String ptFirstName;
	private String ptLastName;
	private static String ptDob;
	private String ptEmail;
	private String ptMobileNumber;
	private String ptStreet;
	private String ptCity;
	private String ptState;
	private String ptZipcode;
	private String ptRoom;
	private CommonsMultipartFile document;

	public String getReqFirstName() {
		return reqFirstName;
	}

	public void setReqFirstName(String reqFirstName) {
		this.reqFirstName = reqFirstName;
	}

	public String getReqLastName() {
		return reqLastName;
	}

	public void setReqLastName(String reqLastName) {
		this.reqLastName = reqLastName;
	}

	public String getReqMobileNumber() {
		return reqMobileNumber;
	}

	public void setReqMobileNumber(String reqMobileNumber) {
		this.reqMobileNumber = reqMobileNumber;
	}

	public String getReqEmail() {
		return reqEmail;
	}

	public void setReqEmail(String reqEmail) {
		this.reqEmail = reqEmail;
	}

	public String getReqRelation() {
		return reqRelation;
	}

	public void setReqRelation(String reqRelation) {
		this.reqRelation = reqRelation;
	}

	public String getReqProperty() {
		return reqProperty;
	}

	public void setReqProperty(String reqProperty) {
		this.reqProperty = reqProperty;
	}

	public String getReqStreet() {
		return reqStreet;
	}

	public void setReqStreet(String reqStreet) {
		this.reqStreet = reqStreet;
	}

	public String getReqCity() {
		return reqCity;
	}

	public void setReqCity(String reqCity) {
		this.reqCity = reqCity;
	}

	public String getReqState() {
		return reqState;
	}

	public void setReqState(String reqState) {
		this.reqState = reqState;
	}

	public String getReqZipcode() {
		return reqZipcode;
	}

	public void setReqZipcode(String reqZipcode) {
		this.reqZipcode = reqZipcode;
	}

	public String getReqCaseNumber() {
		return reqCaseNumber;
	}

	public void setReqCaseNumber(String reqCaseNumber) {
		this.reqCaseNumber = reqCaseNumber;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getPtFirstName() {
		return ptFirstName;
	}

	public void setPtFirstName(String ptFirstName) {
		this.ptFirstName = ptFirstName;
	}

	public String getPtLastName() {
		return ptLastName;
	}

	public void setPtLastName(String ptLastName) {
		this.ptLastName = ptLastName;
	}

	public static String getPtDob() {
		return ptDob;
	}

	public static void setPtDob(String ptDob) {
		CommonRequestDto.ptDob = ptDob;
	}

	public String getPtEmail() {
		return ptEmail;
	}

	public void setPtEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}

	public String getPtMobileNumber() {
		return ptMobileNumber;
	}

	public void setPtMobileNumber(String ptMobileNumber) {
		this.ptMobileNumber = ptMobileNumber;
	}

	public String getPtStreet() {
		return ptStreet;
	}

	public void setPtStreet(String ptStreet) {
		this.ptStreet = ptStreet;
	}

	public String getPtCity() {
		return ptCity;
	}

	public void setPtCity(String ptCity) {
		this.ptCity = ptCity;
	}

	public String getPtState() {
		return ptState;
	}

	public void setPtState(String ptState) {
		this.ptState = ptState;
	}

	public String getPtZipcode() {
		return ptZipcode;
	}

	public void setPtZipcode(String ptZipcode) {
		this.ptZipcode = ptZipcode;
	}

	public String getPtRoom() {
		return ptRoom;
	}

	public void setPtRoom(String ptRoom) {
		this.ptRoom = ptRoom;
	}

	public CommonsMultipartFile getDocument() {
		return document;
	}

	public void setDocument(CommonsMultipartFile document) {
		this.document = document;
	}

	public static Date getFormatedDate() throws ParseException {

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormat.parse(ptDob);

		return date;
	}

	@Override
	public String toString() {
		return "CommonRequestDto [reqFirstName=" + reqFirstName + ", reqLastName=" + reqLastName + ", reqMobileNumber="
				+ reqMobileNumber + ", reqEmail=" + reqEmail + ", reqRelation=" + reqRelation + ", reqProperty="
				+ reqProperty + ", reqStreet=" + reqStreet + ", reqCity=" + reqCity + ", reqState=" + reqState
				+ ", reqZipcode=" + reqZipcode + ", reqCaseNumber=" + reqCaseNumber + ", symptoms=" + symptoms
				+ ", ptFirstName=" + ptFirstName + ", ptLastName=" + ptLastName + ", ptEmail=" + ptEmail
				+ ", ptMobileNumber=" + ptMobileNumber + ", ptStreet=" + ptStreet + ", ptCity=" + ptCity + ", ptState="
				+ ptState + ", ptZipcode=" + ptZipcode + ", ptRoom=" + ptRoom + ", document=" + document + "]";
	}

	public CommonRequestDto(String reqFirstName, String reqLastName, String reqMobileNumber, String reqEmail,
			String reqRelation, String reqProperty, String reqStreet, String reqCity, String reqState,
			String reqZipcode, String reqCaseNumber, String symptoms, String ptFirstName, String ptLastName,
			String ptEmail, String ptMobileNumber, String ptStreet, String ptCity, String ptState, String ptZipcode,
			String ptRoom, CommonsMultipartFile document) {
		super();
		this.reqFirstName = reqFirstName;
		this.reqLastName = reqLastName;
		this.reqMobileNumber = reqMobileNumber;
		this.reqEmail = reqEmail;
		this.reqRelation = reqRelation;
		this.reqProperty = reqProperty;
		this.reqStreet = reqStreet;
		this.reqCity = reqCity;
		this.reqState = reqState;
		this.reqZipcode = reqZipcode;
		this.reqCaseNumber = reqCaseNumber;
		this.symptoms = symptoms;
		this.ptFirstName = ptFirstName;
		this.ptLastName = ptLastName;
		this.ptEmail = ptEmail;
		this.ptMobileNumber = ptMobileNumber;
		this.ptStreet = ptStreet;
		this.ptCity = ptCity;
		this.ptState = ptState;
		this.ptZipcode = ptZipcode;
		this.ptRoom = ptRoom;
		this.document = document;
	}

	public CommonRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
