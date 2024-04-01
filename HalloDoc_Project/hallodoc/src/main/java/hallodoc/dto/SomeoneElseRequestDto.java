package hallodoc.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SomeoneElseRequestDto {

	private String symptoms;
	private String firstName;
	private String lastName;
	private static String dob;
	private String email;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String room;
	private String relation;
	private CommonsMultipartFile document;
	
	
	
	
	public SomeoneElseRequestDto(String symptoms, String firstName, String lastName, String email, String phoneNumber,
			String street, String city, String state, String zipcode, String room, String relation,
			CommonsMultipartFile document) {
		super();
		this.symptoms = symptoms;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.room = room;
		this.relation = relation;
		this.document = document;
	}

	@Override
	public String toString() {
		return "SomeoneElseRequestDto [symptoms=" + symptoms + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + ", room=" + room + ", relation=" + relation
				+ ", document=" + document + "]";
	}

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
		SomeoneElseRequestDto.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public CommonsMultipartFile getDocument() {
		return document;
	}

	public void setDocument(CommonsMultipartFile document) {
		this.document = document;
	}

	public SomeoneElseRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Date getFormatedDate() throws ParseException {

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormat.parse(dob);

		return date;
	}
}
