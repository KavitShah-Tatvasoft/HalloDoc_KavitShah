package hallodoc.dto;

public class OrdersDetailsDto {

	private Integer professionTypeId;
	private Integer businessTypeId;
	private String businessContactNumber;
	private String businessEmailContact;
	private String businessFaxNumber;
	private String businessPrescriptionDetails;
	private Integer refillsDetails;
	private Integer orderRequestId;
	
	
	
	public Integer getOrderRequestId() {
		return orderRequestId;
	}
	public void setOrderRequestId(Integer orderRequestId) {
		this.orderRequestId = orderRequestId;
	}
	public Integer getProfessionTypeId() {
		return professionTypeId;
	}
	public void setProfessionTypeId(Integer professionTypeId) {
		this.professionTypeId = professionTypeId;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public String getBusinessContactNumber() {
		return businessContactNumber;
	}
	public void setBusinessContactNumber(String businessContactNumber) {
		this.businessContactNumber = businessContactNumber;
	}
	public String getBusinessEmailContact() {
		return businessEmailContact;
	}
	public void setBusinessEmailContact(String businessEmailContact) {
		this.businessEmailContact = businessEmailContact;
	}
	public String getBusinessFaxNumber() {
		return businessFaxNumber;
	}
	public void setBusinessFaxNumber(String businessFaxNumber) {
		this.businessFaxNumber = businessFaxNumber;
	}
	public String getBusinessPrescriptionDetails() {
		return businessPrescriptionDetails;
	}
	public void setBusinessPrescriptionDetails(String businessPrescriptionDetails) {
		this.businessPrescriptionDetails = businessPrescriptionDetails;
	}
	public Integer getRefillsDetails() {
		return refillsDetails;
	}
	public void setRefillsDetails(Integer refillsDetails) {
		this.refillsDetails = refillsDetails;
	}
	
	public OrdersDetailsDto(Integer professionTypeId, Integer businessTypeId, String businessContactNumber,
			String businessEmailContact, String businessFaxNumber, String businessPrescriptionDetails,
			Integer refillsDetails, Integer orderRequestId) {
		super();
		this.professionTypeId = professionTypeId;
		this.businessTypeId = businessTypeId;
		this.businessContactNumber = businessContactNumber;
		this.businessEmailContact = businessEmailContact;
		this.businessFaxNumber = businessFaxNumber;
		this.businessPrescriptionDetails = businessPrescriptionDetails;
		this.refillsDetails = refillsDetails;
		this.orderRequestId = orderRequestId;
	}
	@Override
	public String toString() {
		return "OrdersDetailsDto [professionTypeId=" + professionTypeId + ", businessTypeId=" + businessTypeId
				+ ", businessContactNumber=" + businessContactNumber + ", businessEmailContact=" + businessEmailContact
				+ ", businessFaxNumber=" + businessFaxNumber + ", businessPrescriptionDetails="
				+ businessPrescriptionDetails + ", refillsDetails=" + refillsDetails + ", orderRequestId="
				+ orderRequestId + "]";
	}
	public OrdersDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
