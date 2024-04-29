package hallodoc.dto;

public class SearchRecordsFilter {

	private int requestStatus;
	private String firstName;
	private int requestType;
	private String fromDOS;
	private String toDOS;
	private String providerName;
	private String email;
	private String phoneNumber;
	
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getFromDOS() {
		return fromDOS;
	}
	public void setFromDOS(String fromDOS) {
		this.fromDOS = fromDOS;
	}
	public String getToDOS() {
		return toDOS;
	}
	public void setToDOS(String toDOS) {
		this.toDOS = toDOS;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "SearchRecordsFilter [requestStatus=" + requestStatus + ", firstName=" + firstName + ", requestType="
				+ requestType + ", fromDOS=" + fromDOS + ", toDOS=" + toDOS + ", providerName=" + providerName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	public SearchRecordsFilter(int requestStatus, String firstName, int requestType, String fromDOS,
			String toDOS, String providerName, String email, String phoneNumber) {
		super();
		this.requestStatus = requestStatus;
		this.firstName = firstName;
		this.requestType = requestType;
		this.fromDOS = fromDOS;
		this.toDOS = toDOS;
		this.providerName = providerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	public SearchRecordsFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
