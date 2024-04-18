package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="sms_log")
public class SmsLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_log_id")
	private int smsLogId;

	@Column(name = "recipient_name")
	private String recipientName;

	@Column(name = "mobile_number")
	private String mobileNumber;

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

	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createdDate;

	@CreationTimestamp
	@Column(name = "sent_date")
	private LocalDateTime sentDate;

	@Column(name = "is_sms_sent")
	private boolean isSmsSent;

	@Column(name = "sent_tries")
	private int sentTries;

	@Column(name = "action")
	private int action;

	@Column(name = "is_expired")
	private boolean isExpired;

	@Column(name = "sms_type")
	private String smsType;

	public int getSmsLogId() {
		return smsLogId;
	}

	public void setSmsLogId(int smsLogId) {
		this.smsLogId = smsLogId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public boolean isSmsSent() {
		return isSmsSent;
	}

	public void setSmsSent(boolean isSmsSent) {
		this.isSmsSent = isSmsSent;
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

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	@Override
	public String toString() {
		return "SmsLog [smsLogId=" + smsLogId + ", recipientName=" + recipientName + ", mobileNumber=" + mobileNumber
				+ ", confirmationNumber=" + confirmationNumber + ", roleId=" + roleId + ", requestId=" + requestId
				+ ", adminId=" + adminId + ", physicianId=" + physicianId + ", createdDate=" + createdDate
				+ ", sentDate=" + sentDate + ", isSmsSent=" + isSmsSent + ", sentTries=" + sentTries + ", action="
				+ action + ", isExpired=" + isExpired + ", smsType=" + smsType + "]";
	}

	public SmsLog(int smsLogId, String recipientName, String mobileNumber, String confirmationNumber, int roleId,
			int requestId, int adminId, int physicianId, LocalDateTime createdDate, LocalDateTime sentDate,
			boolean isSmsSent, int sentTries, int action, boolean isExpired, String smsType) {
		super();
		this.smsLogId = smsLogId;
		this.recipientName = recipientName;
		this.mobileNumber = mobileNumber;
		this.confirmationNumber = confirmationNumber;
		this.roleId = roleId;
		this.requestId = requestId;
		this.adminId = adminId;
		this.physicianId = physicianId;
		this.createdDate = createdDate;
		this.sentDate = sentDate;
		this.isSmsSent = isSmsSent;
		this.sentTries = sentTries;
		this.action = action;
		this.isExpired = isExpired;
		this.smsType = smsType;
	}

	public SmsLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
