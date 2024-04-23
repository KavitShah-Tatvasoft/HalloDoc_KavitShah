package hallodoc.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	@Column(name="fax_number")
	private String faxNumber;
	
	@Column(name="vendor_id")
	private Integer vendorId;
	
	@Column(name="number_of_refills")
	private Integer numberOfRefills;
	
	@Column(name="prescriptions")
	private String prescriptions;
	
	@Column(name="request_id")
	private Integer requestId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@CreationTimestamp
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="business_email")
	private String businessEmail;
	
	@Column(name="business_contact")
	private String businessContact;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getNumberOfRefills() {
		return numberOfRefills;
	}

	public void setNumberOfRefills(Integer numberOfRefills) {
		this.numberOfRefills = numberOfRefills;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", faxNumber=" + faxNumber + ", vendorId=" + vendorId
				+ ", numberOfRefills=" + numberOfRefills + ", prescriptions=" + prescriptions + ", requestId="
				+ requestId + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", businessEmail="
				+ businessEmail + ", businessContact=" + businessContact + "]";
	}

	public OrderDetails(Integer orderId, String faxNumber, Integer vendorId, Integer numberOfRefills,
			String prescriptions, Integer requestId, Integer createdBy, LocalDateTime createdDate, String businessEmail,
			String businessContact) {
		super();
		this.orderId = orderId;
		this.faxNumber = faxNumber;
		this.vendorId = vendorId;
		this.numberOfRefills = numberOfRefills;
		this.prescriptions = prescriptions;
		this.requestId = requestId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.businessEmail = businessEmail;
		this.businessContact = businessContact;
	}

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
