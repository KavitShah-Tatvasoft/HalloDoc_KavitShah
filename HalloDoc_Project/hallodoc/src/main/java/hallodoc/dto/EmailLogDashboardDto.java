package hallodoc.dto;

public class EmailLogDashboardDto {
	
	private Integer emailLogId;
	private String action;
	private String  recipientName;
	private String roleName;
	private String emailId;
	private String createdDate;
	private String sendDate;
	private Boolean isEmailSent;
	private Integer sentTries;
	private String confNumber;
	public Integer getEmailLogId() {
		return emailLogId;
	}
	public void setEmailLogId(Integer emailLogId) {
		this.emailLogId = emailLogId;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public Boolean getIsEmailSent() {
		return isEmailSent;
	}
	public void setIsEmailSent(Boolean isEmailSent) {
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
		return "EmailLogDashboardDto [emailLogId=" + emailLogId + ", action=" + action + ", recipientName="
				+ recipientName + ", roleName=" + roleName + ", emailId=" + emailId + ", createdDate=" + createdDate
				+ ", sendDate=" + sendDate + ", isEmailSent=" + isEmailSent + ", sentTries=" + sentTries
				+ ", confNumber=" + confNumber + "]";
	}
	public EmailLogDashboardDto(Integer emailLogId, String action, String recipientName, String roleName,
			String emailId, String createdDate, String sendDate, Boolean isEmailSent, Integer sentTries,
			String confNumber) {
		super();
		this.emailLogId = emailLogId;
		this.action = action;
		this.recipientName = recipientName;
		this.roleName = roleName;
		this.emailId = emailId;
		this.createdDate = createdDate;
		this.sendDate = sendDate;
		this.isEmailSent = isEmailSent;
		this.sentTries = sentTries;
		this.confNumber = confNumber;
	}
	public EmailLogDashboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
