package hallodoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "physician")
public class Physician {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "physician_id")
	private int physicianId;
	
	@JsonIgnore
	@Column(name = "aspnet_user_id")
	private int aspnetUserId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	private String mobile;
	
	@Column(name = "medical_license")
	private String medicalLicense;
	
	private String photo;
	
	@Column(name = "admin_notes")
	private String adminNotes;
	
	@Column(name = "is_agreement_doc")
	private boolean isAgreementDoc;
	
	@Column(name = "is_background_doc")
	private boolean isBackgroundDoc;
	
	@Column(name = "is_non_disclosure_doc")
	private boolean isNonDisclosureDoc;
	
	@Column(name = "address_one")
	private String addressOne;
	
	@Column(name = "address_two")
	private String addressTwo;
	
	private String city;
	
	@Column(name = "region_id")
	private int regionId;

	private String zip;
	
	@Column(name = "alt_phone")
	private String altPhone;
	
	@JsonIgnore
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")  // make sure to add/remove json ignore
	private Date createdDate;
	
	@JsonIgnore
	@Column(name = "modified_by")
	private int modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	private int status;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "business_website")
	private String businessWebsite;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "npi_number")
	private String npiNumber;
	
	@Column(name = "is_license_doc")
	private boolean isLicenseDoc;
	
	private String signature;
	
	@Column(name = "sync_email_address")
	private String syncEmailAddress;

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public int getAspnetUserId() {
		return aspnetUserId;
	}

	public void setAspnetUserId(int aspnetUserId) {
		this.aspnetUserId = aspnetUserId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMedicalLicense() {
		return medicalLicense;
	}

	public void setMedicalLicense(String medicalLicense) {
		this.medicalLicense = medicalLicense;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAdminNotes() {
		return adminNotes;
	}

	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}

	public boolean isAgreementDoc() {
		return isAgreementDoc;
	}

	public void setAgreementDoc(boolean isAgreementDoc) {
		this.isAgreementDoc = isAgreementDoc;
	}

	public boolean isBackgroundDoc() {
		return isBackgroundDoc;
	}

	public void setBackgroundDoc(boolean isBackgroundDoc) {
		this.isBackgroundDoc = isBackgroundDoc;
	}

	public boolean isNonDisclosureDoc() {
		return isNonDisclosureDoc;
	}

	public void setNonDisclosureDoc(boolean isNonDisclosureDoc) {
		this.isNonDisclosureDoc = isNonDisclosureDoc;
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

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessWebsite() {
		return businessWebsite;
	}

	public void setBusinessWebsite(String businessWebsite) {
		this.businessWebsite = businessWebsite;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getNpiNumber() {
		return npiNumber;
	}

	public void setNpiNumber(String npiNumber) {
		this.npiNumber = npiNumber;
	}

	public boolean isLicenseDoc() {
		return isLicenseDoc;
	}

	public void setLicenseDoc(boolean isLicenseDoc) {
		this.isLicenseDoc = isLicenseDoc;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSyncEmailAddress() {
		return syncEmailAddress;
	}

	public void setSyncEmailAddress(String syncEmailAddress) {
		this.syncEmailAddress = syncEmailAddress;
	}

	@Override
	public String toString() {
		return "Physician [physicianId=" + physicianId + ", aspnetUserId=" + aspnetUserId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", medicalLicense="
				+ medicalLicense + ", photo=" + photo + ", adminNotes=" + adminNotes + ", isAgreementDoc="
				+ isAgreementDoc + ", isBackgroundDoc=" + isBackgroundDoc + ", isNonDisclosureDoc=" + isNonDisclosureDoc
				+ ", addressOne=" + addressOne + ", addressTwo=" + addressTwo + ", city=" + city + ", regionId="
				+ regionId + ", zip=" + zip + ", altPhone=" + altPhone + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", businessName=" + businessName + ", businessWebsite=" + businessWebsite + ", isDeleted=" + isDeleted
				+ ", roleId=" + roleId + ", npiNumber=" + npiNumber + ", isLicenseDoc=" + isLicenseDoc + ", signature="
				+ signature + ", syncEmailAddress=" + syncEmailAddress + "]";
	}

	public Physician(int physicianId, int aspnetUserId, String firstName, String lastName, String email, String mobile,
			String medicalLicense, String photo, String adminNotes, boolean isAgreementDoc, boolean isBackgroundDoc,
			boolean isNonDisclosureDoc, String addressOne, String addressTwo, String city, int regionId, String zip,
			String altPhone, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate, int status,
			String businessName, String businessWebsite, boolean isDeleted, int roleId, String npiNumber,
			boolean isLicenseDoc, String signature, String syncEmailAddress) {
		super();
		this.physicianId = physicianId;
		this.aspnetUserId = aspnetUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.medicalLicense = medicalLicense;
		this.photo = photo;
		this.adminNotes = adminNotes;
		this.isAgreementDoc = isAgreementDoc;
		this.isBackgroundDoc = isBackgroundDoc;
		this.isNonDisclosureDoc = isNonDisclosureDoc;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.regionId = regionId;
		this.zip = zip;
		this.altPhone = altPhone;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.businessName = businessName;
		this.businessWebsite = businessWebsite;
		this.isDeleted = isDeleted;
		this.roleId = roleId;
		this.npiNumber = npiNumber;
		this.isLicenseDoc = isLicenseDoc;
		this.signature = signature;
		this.syncEmailAddress = syncEmailAddress;
	}

	public Physician() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
