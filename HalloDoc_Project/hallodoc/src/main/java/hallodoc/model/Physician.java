package hallodoc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "physician")
public class Physician {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "physician_id")
	private int physicianId;

//	@Column(name = "aspnet_user_id")
//	private int aspnetUserId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aspnet_user_id")
	private AspNetUsers aspNetUsers;

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
	private Boolean isAgreementDoc;

	@Column(name = "is_background_doc")
	private Boolean isBackgroundDoc;

	@Column(name = "is_non_disclosure_doc")
	private Boolean isNonDisclosureDoc;

	@Column(name = "address_one")
	private String addressOne;

	@Column(name = "address_two")
	private String addressTwo;

	private String city;

	@Column(name = "region_id")
	private int regionId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "physician_region", joinColumns = { @JoinColumn(name = "physician_id") }, inverseJoinColumns = {
			@JoinColumn(name = "region_id") })
	private List<Region> regions;

	private String zip;

	@Column(name = "alt_phone")
	private String altPhone;

//	@Column(name = "created_by")
//	private int createdBy;

	@OneToOne
	@JoinColumn(name = "created_by")
	private AspNetUsers createdBy;

	@Column(name = "created_date") // make sure to add/remove json ignore
	private Date createdDate;

//	@Column(name = "modified_by")
//	private int modifiedBy;

	@OneToOne
	@JoinColumn(name = "modified_by")
	private AspNetUsers modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	private Integer status;

	@Column(name = "business_name")
	private String businessName;

	@Column(name = "business_website")
	private String businessWebsite;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

//	@Column(name = "role_id")
//	private int roleId;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(name = "npi_number")
	private String npiNumber;

	@Column(name = "is_license_doc")
	private Boolean isLicenseDoc;
	
	@Column(name = "is_hipaa_doc")
	private Boolean isHipaaDoc;

	private String signature;

	@Column(name = "sync_email_address")
	private String syncEmailAddress;
	
	@OneToOne(mappedBy = "physician", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private PhysicianNotification physicianNotification;
	
	
	@OneToMany(mappedBy = "physician" ,fetch = FetchType.LAZY)
	private List<Request> request;
	
	
	
	
	

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}

	public PhysicianNotification getPhysicianNotification() {
		return physicianNotification;
	}

	public void setPhysicianNotification(PhysicianNotification physicianNotification) {
		this.physicianNotification = physicianNotification;
	}

	
	public Boolean getIsHipaaDoc() {
		return isHipaaDoc;
	}

	public void setIsHipaaDoc(Boolean isHipaaDoc) {
		this.isHipaaDoc = isHipaaDoc;
	}

	public Boolean getIsAgreementDoc() {
		return isAgreementDoc;
	}

	public void setIsAgreementDoc(Boolean isAgreementDoc) {
		this.isAgreementDoc = isAgreementDoc;
	}

	public Boolean getIsBackgroundDoc() {
		return isBackgroundDoc;
	}

	public void setIsBackgroundDoc(Boolean isBackgroundDoc) {
		this.isBackgroundDoc = isBackgroundDoc;
	}

	public Boolean getIsNonDisclosureDoc() {
		return isNonDisclosureDoc;
	}

	public void setIsNonDisclosureDoc(Boolean isNonDisclosureDoc) {
		this.isNonDisclosureDoc = isNonDisclosureDoc;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsLicenseDoc() {
		return isLicenseDoc;
	}

	public void setIsLicenseDoc(Boolean isLicenseDoc) {
		this.isLicenseDoc = isLicenseDoc;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public AspNetUsers getAspNetUsers() {
		return aspNetUsers;
	}

	public void setAspNetUsers(AspNetUsers aspNetUsers) {
		this.aspNetUsers = aspNetUsers;
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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
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

	public AspNetUsers getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AspNetUsers createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AspNetUsers getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(AspNetUsers modifiedBy) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	



	public Physician(int physicianId, AspNetUsers aspNetUsers, String firstName, String lastName, String email,
			String mobile, String medicalLicense, String photo, String adminNotes, Boolean isAgreementDoc,
			Boolean isBackgroundDoc, Boolean isNonDisclosureDoc, String addressOne, String addressTwo, String city,
			int regionId, List<Region> regions, String zip, String altPhone, AspNetUsers createdBy, Date createdDate,
			AspNetUsers modifiedBy, Date modifiedDate, Integer status, String businessName, String businessWebsite,
			Boolean isDeleted, Role role, String npiNumber, Boolean isLicenseDoc, Boolean isHipaaDoc, String signature,
			String syncEmailAddress, PhysicianNotification physicianNotification, List<Request> request) {
		super();
		this.physicianId = physicianId;
		this.aspNetUsers = aspNetUsers;
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
		this.regions = regions;
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
		this.role = role;
		this.npiNumber = npiNumber;
		this.isLicenseDoc = isLicenseDoc;
		this.isHipaaDoc = isHipaaDoc;
		this.signature = signature;
		this.syncEmailAddress = syncEmailAddress;
		this.physicianNotification = physicianNotification;
		this.request = request;
	}

	public Physician() {
		super();
		// TODO Auto-generated constructor stub
	}

}
