package hallodoc.dto;

public class NewBusinessDto {
	private String businessName;
	private String businessProfession;
	private String businessFaxNumber;
	private String businessPhone;
	private String businessEmail;
	private String businessContact;
	private String businessStreet;
	private String businessCity;
	private String businessState;
	private String businessZip;
	private String businessId;
	
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessProfession() {
		return businessProfession;
	}
	public void setBusinessProfession(String businessProfession) {
		this.businessProfession = businessProfession;
	}
	public String getBusinessFaxNumber() {
		return businessFaxNumber;
	}
	public void setBusinessFaxNumber(String businessFaxNumber) {
		this.businessFaxNumber = businessFaxNumber;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessContact() {
		return businessContact;
	}
	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}
	public String getBusinessStreet() {
		return businessStreet;
	}
	public void setBusinessStreet(String businessStreet) {
		this.businessStreet = businessStreet;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public String getBusinessState() {
		return businessState;
	}
	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}
	public String getBusinessZip() {
		return businessZip;
	}
	public void setBusinessZip(String businessZip) {
		this.businessZip = businessZip;
	}
	
	public NewBusinessDto(String businessName, String businessProfession, String businessFaxNumber,
			String businessPhone, String businessEmail, String businessContact, String businessStreet,
			String businessCity, String businessState, String businessZip, String businessId) {
		super();
		this.businessName = businessName;
		this.businessProfession = businessProfession;
		this.businessFaxNumber = businessFaxNumber;
		this.businessPhone = businessPhone;
		this.businessEmail = businessEmail;
		this.businessContact = businessContact;
		this.businessStreet = businessStreet;
		this.businessCity = businessCity;
		this.businessState = businessState;
		this.businessZip = businessZip;
		this.businessId = businessId;
	}
	@Override
	public String toString() {
		return "NewBusinessDto [businessName=" + businessName + ", businessProfession=" + businessProfession
				+ ", businessFaxNumber=" + businessFaxNumber + ", businessPhone=" + businessPhone + ", businessEmail="
				+ businessEmail + ", businessContact=" + businessContact + ", businessStreet=" + businessStreet
				+ ", businessCity=" + businessCity + ", businessState=" + businessState + ", businessZip=" + businessZip
				+ ", businessId=" + businessId + "]";
	}
	public NewBusinessDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
