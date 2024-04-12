package hallodoc.dto;

public class UpdateCaseDto {
	
	private int reqId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String dateOfBirth;
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "UpdateCaseDto [reqId=" + reqId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + "]";
	}
	public UpdateCaseDto(int reqId, String firstName, String lastName, String phoneNumber, String dateOfBirth) {
		super();
		this.reqId = reqId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
	}
	public UpdateCaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
