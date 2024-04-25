package hallodoc.dto;

public class HealthProfessionalDataDto {
	
	private String profession;
	private String businessName;
	private String email;
	private String faxNumber;
	private String phoneNumber;
	private String businessContact;
	
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBusinessContact() {
		return businessContact;
	}
	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}
	@Override
	public String toString() {
		return "HealthProfessionalDataDto [profession=" + profession + ", businessName=" + businessName + ", email="
				+ email + ", faxNumber=" + faxNumber + ", phoneNumber=" + phoneNumber + ", businessContact="
				+ businessContact + "]";
	}
	public HealthProfessionalDataDto(String profession, String businessName, String email, String faxNumber,
			String phoneNumber, String businessContact) {
		super();
		this.profession = profession;
		this.businessName = businessName;
		this.email = email;
		this.faxNumber = faxNumber;
		this.phoneNumber = phoneNumber;
		this.businessContact = businessContact;
	}
	public HealthProfessionalDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
