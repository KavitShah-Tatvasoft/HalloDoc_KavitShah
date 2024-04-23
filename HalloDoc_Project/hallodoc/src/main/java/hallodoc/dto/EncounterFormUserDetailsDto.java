package hallodoc.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EncounterFormUserDetailsDto {

	private String firstName;
	private String lastName;
	private LocalDate dob;
	private LocalDate dos;
	private String email;
	private String phoneNumber;
	private String location;

	
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getDos() {
		return dos;
	}

	public void setDos(LocalDate dos) {
		this.dos = dos;
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

	

	public EncounterFormUserDetailsDto(String firstName, String lastName, LocalDate dob, LocalDate dos, String email,
			String phoneNumber, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.dos = dos;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.location = location;
	}

	@Override
	public String toString() {
		return "EncounterFormUserDetailsDto [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", dos=" + dos + ", email=" + email + ", phoneNumber=" + phoneNumber + ", location=" + location + "]";
	}

	public EncounterFormUserDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
