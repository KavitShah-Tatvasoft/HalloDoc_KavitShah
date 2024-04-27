package hallodoc.dto;

public class ProviderUpdatedInfoDto {
			
	private String regions;
	private Integer id;
	private String fName;
	private String lName;
	private String phone;
	private String license;
	private String npi;
	private String syncMail;
	
	public String getRegions() {
		return regions;
	}
	public void setRegions(String regions) {
		this.regions = regions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public String getSyncMail() {
		return syncMail;
	}
	public void setSyncMail(String syncMail) {
		this.syncMail = syncMail;
	}
	@Override
	public String toString() {
		return "ProviderUpdatedInfo [regions=" + regions + ", id=" + id + ", fName=" + fName + ", lName=" + lName
				+ ", phone=" + phone + ", license=" + license + ", npi=" + npi + ", syncMail=" + syncMail + "]";
	}
	public ProviderUpdatedInfoDto(String regions, Integer id, String fName, String lName, String phone, String license,
			String npi, String syncMail) {
		super();
		this.regions = regions;
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.license = license;
		this.npi = npi;
		this.syncMail = syncMail;
	}
	public ProviderUpdatedInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
