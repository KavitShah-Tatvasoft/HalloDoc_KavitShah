package hallodoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "request_status_log")
public class RequestStatusLog {
	
	@Column(name = "request_status_log_id")
	private int request_status_log_id;
	
	@Column(name = "request_id")
	private int requestId;
	
	private int status;
	
	@Column(name = "physician_id")
	private int physicianId;
	
	@Column(name = "admin_id")
	private int adminId;
	
	@Column(name = "trans_to_physician_id")
	private int transToPhysicianId;
	
	private String notes;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_date")
	private boolean transToAdmin;

	public int getRequest_status_log_id() {
		return request_status_log_id;
	}

	public void setRequest_status_log_id(int request_status_log_id) {
		this.request_status_log_id = request_status_log_id;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getTransToPhysicianId() {
		return transToPhysicianId;
	}

	public void setTransToPhysicianId(int transToPhysicianId) {
		this.transToPhysicianId = transToPhysicianId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isTransToAdmin() {
		return transToAdmin;
	}

	public void setTransToAdmin(boolean transToAdmin) {
		this.transToAdmin = transToAdmin;
	}

	@Override
	public String toString() {
		return "RequestStatusLog [request_status_log_id=" + request_status_log_id + ", requestId=" + requestId
				+ ", status=" + status + ", physicianId=" + physicianId + ", adminId=" + adminId
				+ ", transToPhysicianId=" + transToPhysicianId + ", notes=" + notes + ", createdDate=" + createdDate
				+ ", transToAdmin=" + transToAdmin + "]";
	}

	public RequestStatusLog(int request_status_log_id, int requestId, int status, int physicianId, int adminId,
			int transToPhysicianId, String notes, Date createdDate, boolean transToAdmin) {
		super();
		this.request_status_log_id = request_status_log_id;
		this.requestId = requestId;
		this.status = status;
		this.physicianId = physicianId;
		this.adminId = adminId;
		this.transToPhysicianId = transToPhysicianId;
		this.notes = notes;
		this.createdDate = createdDate;
		this.transToAdmin = transToAdmin;
	}

	public RequestStatusLog() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	

}
