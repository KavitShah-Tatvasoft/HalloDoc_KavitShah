package hallodoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "physician_id")
	private int physicianId;
	
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
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
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
	
	
}
