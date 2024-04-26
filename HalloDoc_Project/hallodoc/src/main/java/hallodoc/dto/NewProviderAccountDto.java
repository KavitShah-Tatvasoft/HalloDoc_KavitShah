package hallodoc.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class NewProviderAccountDto {

	private Integer pId;
	private String pUsername;
	private Integer pStatus;
	private Integer pRole;
	private String pPassword;
	private String pFirstName;
	private String pLastName;
	private String pEmail;
	private String pPhone;
	private String pLicense;
	private String pNPI;
	private String pSyncEmail;
	private String pRegions;
	private String pAddressOne;
	private String pAddressTwo;
	private String pCity;
	private Integer pState;
	private String pZip;
	private String pMPhone;
	private String pBusinessName;
	private String pBusinessWebsite;
	private CommonsMultipartFile pPhoto;
	private CommonsMultipartFile pSignature;
	private String pNotes;
	private CommonsMultipartFile pAgreement;
	private CommonsMultipartFile pHipaa;
	private CommonsMultipartFile pBackgroundCheck;
	private CommonsMultipartFile pNda;
	private CommonsMultipartFile pLicenseDoc;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpUsername() {
		return pUsername;
	}

	public void setpUsername(String pUsername) {
		this.pUsername = pUsername;
	}

	public Integer getpStatus() {
		return pStatus;
	}

	public void setpStatus(Integer pStatus) {
		this.pStatus = pStatus;
	}

	public Integer getpRole() {
		return pRole;
	}

	public void setpRole(Integer pRole) {
		this.pRole = pRole;
	}

	public String getpPassword() {
		return pPassword;
	}

	public void setpPassword(String pPassword) {
		this.pPassword = pPassword;
	}

	public String getpFirstName() {
		return pFirstName;
	}

	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}

	public String getpLastName() {
		return pLastName;
	}

	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}

	public String getpEmail() {
		return pEmail;
	}

	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	public String getpPhone() {
		return pPhone;
	}

	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}

	public String getpLicense() {
		return pLicense;
	}

	public void setpLicense(String pLicense) {
		this.pLicense = pLicense;
	}

	public String getpNPI() {
		return pNPI;
	}

	public void setpNPI(String pNPI) {
		this.pNPI = pNPI;
	}

	public String getpSyncEmail() {
		return pSyncEmail;
	}

	public void setpSyncEmail(String pSyncEmail) {
		this.pSyncEmail = pSyncEmail;
	}

	public String getpRegions() {
		return pRegions;
	}

	public void setpRegions(String pRegions) {
		this.pRegions = pRegions;
	}

	public String getpAddressOne() {
		return pAddressOne;
	}

	public void setpAddressOne(String pAddressOne) {
		this.pAddressOne = pAddressOne;
	}

	public String getpAddressTwo() {
		return pAddressTwo;
	}

	public void setpAddressTwo(String pAddressTwo) {
		this.pAddressTwo = pAddressTwo;
	}

	public String getpCity() {
		return pCity;
	}

	public void setpCity(String pCity) {
		this.pCity = pCity;
	}

	public Integer getpState() {
		return pState;
	}

	public void setpState(Integer pState) {
		this.pState = pState;
	}

	public String getpZip() {
		return pZip;
	}

	public void setpZip(String pZip) {
		this.pZip = pZip;
	}

	public String getpMPhone() {
		return pMPhone;
	}

	public void setpMPhone(String pMPhone) {
		this.pMPhone = pMPhone;
	}

	public String getpBusinessName() {
		return pBusinessName;
	}

	public void setpBusinessName(String pBusinessName) {
		this.pBusinessName = pBusinessName;
	}

	public String getpBusinessWebsite() {
		return pBusinessWebsite;
	}

	public void setpBusinessWebsite(String pBusinessWebsite) {
		this.pBusinessWebsite = pBusinessWebsite;
	}

	public CommonsMultipartFile getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(CommonsMultipartFile pPhoto) {
		this.pPhoto = pPhoto;
	}

	public CommonsMultipartFile getpSignature() {
		return pSignature;
	}

	public void setpSignature(CommonsMultipartFile pSignature) {
		this.pSignature = pSignature;
	}

	public String getpNotes() {
		return pNotes;
	}

	public void setpNotes(String pNotes) {
		this.pNotes = pNotes;
	}

	public CommonsMultipartFile getpAgreement() {
		return pAgreement;
	}

	public void setpAgreement(CommonsMultipartFile pAgreement) {
		this.pAgreement = pAgreement;
	}

	public CommonsMultipartFile getpHipaa() {
		return pHipaa;
	}

	public void setpHipaa(CommonsMultipartFile pHipaa) {
		this.pHipaa = pHipaa;
	}

	public CommonsMultipartFile getpBackgroundCheck() {
		return pBackgroundCheck;
	}

	public void setpBackgroundCheck(CommonsMultipartFile pBackgroundCheck) {
		this.pBackgroundCheck = pBackgroundCheck;
	}

	public CommonsMultipartFile getpNda() {
		return pNda;
	}

	public void setpNda(CommonsMultipartFile pNda) {
		this.pNda = pNda;
	}

	public CommonsMultipartFile getpLicenseDoc() {
		return pLicenseDoc;
	}

	public void setpLicenseDoc(CommonsMultipartFile pLicenseDoc) {
		this.pLicenseDoc = pLicenseDoc;
	}

	@Override
	public String toString() {
		return "NewProviderAccountDto [pId=" + pId + ", pUsername=" + pUsername + ", pStatus=" + pStatus + ", pRole="
				+ pRole + ", pPassword=" + pPassword + ", pFirstName=" + pFirstName + ", pLastName=" + pLastName
				+ ", pEmail=" + pEmail + ", pPhone=" + pPhone + ", pLicense=" + pLicense + ", pNPI=" + pNPI
				+ ", pSyncEmail=" + pSyncEmail + ", pRegions=" + pRegions + ", pAddressOne=" + pAddressOne
				+ ", pAddressTwo=" + pAddressTwo + ", pCity=" + pCity + ", pState=" + pState + ", pZip=" + pZip
				+ ", pMPhone=" + pMPhone + ", pBusinessName=" + pBusinessName + ", pBusinessWebsite=" + pBusinessWebsite
				+ ", pPhoto=" + pPhoto + ", pSignature=" + pSignature + ", pNotes=" + pNotes + ", pAgreement="
				+ pAgreement + ", pHipaa=" + pHipaa + ", pBackgroundCheck=" + pBackgroundCheck + ", pNda=" + pNda
				+ ", pLicenseDoc=" + pLicenseDoc + "]";
	}

	public NewProviderAccountDto(Integer pId, String pUsername, Integer pStatus, Integer pRole, String pPassword,
			String pFirstName, String pLastName, String pEmail, String pPhone, String pLicense, String pNPI,
			String pSyncEmail, String pRegions, String pAddressOne, String pAddressTwo, String pCity, Integer pState,
			String pZip, String pMPhone, String pBusinessName, String pBusinessWebsite, CommonsMultipartFile pPhoto,
			CommonsMultipartFile pSignature, String pNotes, CommonsMultipartFile pAgreement,
			CommonsMultipartFile pHipaa, CommonsMultipartFile pBackgroundCheck, CommonsMultipartFile pNda,
			CommonsMultipartFile pLicenseDoc) {
		super();
		this.pId = pId;
		this.pUsername = pUsername;
		this.pStatus = pStatus;
		this.pRole = pRole;
		this.pPassword = pPassword;
		this.pFirstName = pFirstName;
		this.pLastName = pLastName;
		this.pEmail = pEmail;
		this.pPhone = pPhone;
		this.pLicense = pLicense;
		this.pNPI = pNPI;
		this.pSyncEmail = pSyncEmail;
		this.pRegions = pRegions;
		this.pAddressOne = pAddressOne;
		this.pAddressTwo = pAddressTwo;
		this.pCity = pCity;
		this.pState = pState;
		this.pZip = pZip;
		this.pMPhone = pMPhone;
		this.pBusinessName = pBusinessName;
		this.pBusinessWebsite = pBusinessWebsite;
		this.pPhoto = pPhoto;
		this.pSignature = pSignature;
		this.pNotes = pNotes;
		this.pAgreement = pAgreement;
		this.pHipaa = pHipaa;
		this.pBackgroundCheck = pBackgroundCheck;
		this.pNda = pNda;
		this.pLicenseDoc = pLicenseDoc;
	}

	public NewProviderAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
