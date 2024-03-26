package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="business")
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "business_id")
	private int businessId;
	
	private String name;
	
	private String email;
	
	@Column(name = "address_one")
	private String addressOne;
	
	@Column(name = "address_two")
	private String addressTwo;
	
	@Column(name = "city")
	private String city;
	
//	@Column(name = "region_id")
//	private int regionId;
	
	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "region_id")
	private Region region;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "fax_number")
	private String faxNumber;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@Column(name="modified_by")
	private int modifiedBy;
	
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name = "is_deleted ")
	private boolean isDelete;

	@Column(name="property_name")
	private String propertyName;
	
	
	
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", name=" + name + ", email=" + email + ", addressOne="
				+ addressOne + ", addressTwo=" + addressTwo + ", city=" + city + ", region=" + region + ", zipCode="
				+ zipCode + ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", isDelete=" + isDelete + ", propertyName=" + propertyName + "]";
	}

	public Business(int businessId, String name, String email, String addressOne, String addressTwo, String city,
			Region region, String zipCode, String phoneNumber, String faxNumber, int createdBy,
			LocalDateTime createdDate, int modifiedBy, LocalDateTime modifiedDate, boolean isDelete,
			String propertyName) {
		super();
		this.businessId = businessId;
		this.name = name;
		this.email = email;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.region = region;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isDelete = isDelete;
		this.propertyName = propertyName;
	}

	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
