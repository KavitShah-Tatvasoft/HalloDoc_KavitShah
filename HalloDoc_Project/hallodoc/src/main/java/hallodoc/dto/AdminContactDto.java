package hallodoc.dto;

public class AdminContactDto {
			
	private String checkedId;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	public String getCheckedId() {
		return checkedId;
	}
	public void setCheckedId(String checkedId) {
		this.checkedId = checkedId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	@Override
	public String toString() {
		return "AdminContactDto [checkedId=" + checkedId + ", phoneNumber=" + phoneNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	public AdminContactDto(String checkedId, String phoneNumber, String firstName, String lastName) {
		super();
		this.checkedId = checkedId;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public AdminContactDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
