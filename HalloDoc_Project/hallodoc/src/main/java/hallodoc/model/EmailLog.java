package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_log")
public class EmailLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email_log_id")
	private int emailLogId;

	@Column(name = "recipient_name")
	private String recipientName;

	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "confirmation_number")
	private String confirmationNumber;

	@Column(name = "role_id")
	private int roleId;

	@Column(name = "request_id")
	private int requestId;

	@Column(name = "admin_id")
	private int adminId;

	@Column(name = "physician_id")
	private int physicianId;

	@Column(name = "create_date")
	private LocalDateTime createdDate;

	@Column(name = "sent_date")
	private LocalDateTime sentDate;

	@Column(name = "is_email_sent")
	private boolean isEmailSent;

	@Column(name = "sent_tries")
	private int sentTries;

	@Column(name = "action")
	private int action;

	@Column(name = "is_expired")
	private boolean isExpired;

	@Column(name = "email_type")
	private String emailType;

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public int getEmailLogId() {
		return emailLogId;
	}

	public void setEmailLogId(int emailLogId) {
		this.emailLogId = emailLogId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}

	public boolean isEmailSent() {
		return isEmailSent;
	}

	public void setEmailSent(boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	public int getSentTries() {
		return sentTries;
	}

	public void setSentTries(int sentTries) {
		this.sentTries = sentTries;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "EmailLog [emailLogId=" + emailLogId + ", recipientName=" + recipientName + ", subjectName="
				+ subjectName + ", emailId=" + emailId + ", confirmationNumber=" + confirmationNumber + ", roleId="
				+ roleId + ", requestId=" + requestId + ", adminId=" + adminId + ", physicianId=" + physicianId
				+ ", createdDate=" + createdDate + ", sentDate=" + sentDate + ", isEmailSent=" + isEmailSent
				+ ", sentTries=" + sentTries + ", action=" + action + ", isExpired=" + isExpired + ", emailType="
				+ emailType + "]";
	}

	public EmailLog(int emailLogId, String recipientName, String subjectName, String emailId, String confirmationNumber,
			int roleId, int requestId, int adminId, int physicianId, LocalDateTime createdDate, LocalDateTime sentDate,
			boolean isEmailSent, int sentTries, int action, boolean isExpired, String emailType) {
		super();
		this.emailLogId = emailLogId;
		this.recipientName = recipientName;
		this.subjectName = subjectName;
		this.emailId = emailId;
		this.confirmationNumber = confirmationNumber;
		this.roleId = roleId;
		this.requestId = requestId;
		this.adminId = adminId;
		this.physicianId = physicianId;
		this.createdDate = createdDate;
		this.sentDate = sentDate;
		this.isEmailSent = isEmailSent;
		this.sentTries = sentTries;
		this.action = action;
		this.isExpired = isExpired;
		this.emailType = emailType;
	}

	public EmailLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getOnlyCreatedDate() {
		String[] tokens = this.createdDate.toString().split("T");
		return tokens[0];
	}
	
	public String getOnlySentDate() {
		String[] tokens = this.sentDate.toString().split("T");
		return tokens[0];
	}

}
