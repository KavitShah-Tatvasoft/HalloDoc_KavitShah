package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "health_professionals")
public class HealthProfessionals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_id")
	private Integer vendorId;

	@Column(name = "vendor_name")
	private String vendorName;
	
	@OneToOne
	@JoinColumn(name = "profession")
	private HealthProfessionalTypes healthProfessionalTypes;
	
	@Column(name = "fax_number")
	private String faxNumber;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	@OneToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	private String email;
	
	@Column(name = "business_contact")
	private String businessContact;

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public HealthProfessionalTypes getHealthProfessionalTypes() {
		return healthProfessionalTypes;
	}

	public void setHealthProfessionalTypes(HealthProfessionalTypes healthProfessionalTypes) {
		this.healthProfessionalTypes = healthProfessionalTypes;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBusinessContact() {
		return businessContact;
	}

	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}

	@Override
	public String toString() {
		return "HealthProfessionals [vendorId=" + vendorId + ", vendorName=" + vendorName + ", healthProfessionalType="
				+ healthProfessionalTypes + ", faxNumber=" + faxNumber + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", region=" + region + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", phoneNumber=" + phoneNumber + ", isDeleted=" + isDeleted
				+ ", email=" + email + ", businessContact=" + businessContact + "]";
	}

	public HealthProfessionals(Integer vendorId, String vendorName, HealthProfessionalTypes healthProfessionalType,
			String faxNumber, String address, String city, String state, String zip, Region region,
			LocalDateTime createdDate, LocalDateTime modifiedDate, String phoneNumber, Boolean isDeleted, String email,
			String businessContact) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.healthProfessionalTypes = healthProfessionalType;
		this.faxNumber = faxNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.region = region;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.phoneNumber = phoneNumber;
		this.isDeleted = isDeleted;
		this.email = email;
		this.businessContact = businessContact;
	}

	public HealthProfessionals() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
