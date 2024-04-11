package hallodoc.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CreatePatientRequestDto {

	private String symptoms;     // Admin notes in case of request created by ADMIN
	private String firstName;
	private String lastName;
	private static String dob;
	private String email;
	private String mobileNumber;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String room;
	private CommonsMultipartFile document;
	private String password;
	private String confirmPassword;
	private String isExsistingPatient;
	
	
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public static String getDob() {
		return dob;
	}
	public static void setDob(String dob) {
		CreatePatientRequestDto.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public CommonsMultipartFile getDocument() {
		return document;
	}
	public void setDocument(CommonsMultipartFile document) {
		this.document = document;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getIsExsistingPatient() {
		return isExsistingPatient;
	}
	public void setIsExsistingPatient(String isExsistingPatient) {
		this.isExsistingPatient = isExsistingPatient;
	}
	
	public static Date getFormatedDate() throws ParseException {

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormat.parse(dob);

		return date;
	}

	
	@Override
	public String toString() {
		return "CreatePatientRequestDto [symptoms=" + symptoms + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + ", room=" + room + ", document=" + document
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", isExsistingPatient="
				+ isExsistingPatient + "]";
	}
	public CreatePatientRequestDto(String symptoms, String firstName, String lastName, String email,
			String mobileNumber, String street, String city, String state, String zipcode, String room,
			CommonsMultipartFile document, String password, String confirmPassword, String isExsistingPatient) {
		super();
		this.symptoms = symptoms;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.room = room;
		this.document = document;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.isExsistingPatient = isExsistingPatient;
	}
	public CreatePatientRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
