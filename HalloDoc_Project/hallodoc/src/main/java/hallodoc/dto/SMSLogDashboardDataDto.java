package hallodoc.dto;

public class SMSLogDashboardDataDto {

	private Integer smsLogId;
	private String action;
	private String  recipientName;
	private String roleName;
	private String mobileNumber;
	private String createdDate;
	private String sendDate;
	private String isEmailSent;
	private Integer sentTries;
	private String confNumber;
	public Integer getSmsLogId() {
		return smsLogId;
	}
	public void setSmsLogId(Integer smsLogId) {
		this.smsLogId = smsLogId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getIsEmailSent() {
		return isEmailSent;
	}
	public void setIsEmailSent(String isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
	public Integer getSentTries() {
		return sentTries;
	}
	public void setSentTries(Integer sentTries) {
		this.sentTries = sentTries;
	}
	public String getConfNumber() {
		return confNumber;
	}
	public void setConfNumber(String confNumber) {
		this.confNumber = confNumber;
	}
	@Override
	public String toString() {
		return "SMSLogDashboardData [smsLogId=" + smsLogId + ", action=" + action + ", recipientName=" + recipientName
				+ ", roleName=" + roleName + ", mobileNumber=" + mobileNumber + ", createdDate=" + createdDate
				+ ", sendDate=" + sendDate + ", isEmailSent=" + isEmailSent + ", sentTries=" + sentTries
				+ ", confNumber=" + confNumber + "]";
	}
	public SMSLogDashboardDataDto(Integer smsLogId, String action, String recipientName, String roleName,
			String mobileNumber, String createdDate, String sendDate, String isEmailSent, Integer sentTries,
			String confNumber) {
		super();
		this.smsLogId = smsLogId;
		this.action = action;
		this.recipientName = recipientName;
		this.roleName = roleName;
		this.mobileNumber = mobileNumber;
		this.createdDate = createdDate;
		this.sendDate = sendDate;
		this.isEmailSent = isEmailSent;
		this.sentTries = sentTries;
		this.confNumber = confNumber;
	}
	public SMSLogDashboardDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
