package hallodoc.dto;

public class UserAccessDto {

	private String accountType;
	private String name;
	private String phoneNumber;
	private String status;
	private Integer openRequests;
	private Integer userId;
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public Integer getOpenRequests() {
		return openRequests;
	}
	public void setOpenRequests(Integer openRequests) {
		this.openRequests = openRequests;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserAccessDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAccessDto(String accountType, String name, String phoneNumber, String status, Integer openRequests,
			Integer userId) {
		super();
		this.accountType = accountType;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.openRequests = openRequests;
		this.userId = userId;
	}
	
	
	
}
