package hallodoc.dto;

public class OrderVendorDetailsDto {

	private String businessContact;
	private String businessEmail;
	private String businessFax;
	public String getBusinessContact() {
		return businessContact;
	}
	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessFax() {
		return businessFax;
	}
	public void setBusinessFax(String businessFax) {
		this.businessFax = businessFax;
	}
	@Override
	public String toString() {
		return "OrderVendorDetailsDto [businessContact=" + businessContact + ", businessEmail=" + businessEmail
				+ ", businessFax=" + businessFax + "]";
	}
	public OrderVendorDetailsDto(String businessContact, String businessEmail, String businessFax) {
		super();
		this.businessContact = businessContact;
		this.businessEmail = businessEmail;
		this.businessFax = businessFax;
	}
	public OrderVendorDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
