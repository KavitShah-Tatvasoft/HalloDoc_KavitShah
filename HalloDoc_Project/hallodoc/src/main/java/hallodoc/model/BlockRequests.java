package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import hallodoc.helper.DateHelper;

@Entity
@Table(name="block_requests")
public class BlockRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="block_request_id")
	private int blockRequestId;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="reason")
	private String reason;
	
//	@Column(name="request_id")
//	private int requestId;
	
	@OneToOne
	@JoinColumn(name = "request_id")
	private Request request;
	
	@CreationTimestamp
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	
	public String getFormattedCreatedDate() {
		String[] date = this.createdDate.toString().split("T")[0].split("-");
		String year = date[0];
		String day = date[2];
		String month = DateHelper.getMonthName(Integer.parseInt(date[1]));
		
		return month + " " + day + ", " + year;
	}
	

	public int getBlockRequestId() {
		return blockRequestId;
	}

	public void setBlockRequestId(int blockRequestId) {
		this.blockRequestId = blockRequestId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "BlockRequests [blockRequestId=" + blockRequestId + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", isActive=" + isActive + ", reason=" + reason + ", request=" + request + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

	public BlockRequests(int blockRequestId, String phoneNumber, String email, boolean isActive, String reason,
			Request request, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		super();
		this.blockRequestId = blockRequestId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isActive = isActive;
		this.reason = reason;
		this.request = request;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public BlockRequests() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
