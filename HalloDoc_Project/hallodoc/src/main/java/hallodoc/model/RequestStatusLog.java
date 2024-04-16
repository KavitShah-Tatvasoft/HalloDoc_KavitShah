package hallodoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_status_log")
public class RequestStatusLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_status_log_id")
	private int request_status_log_id;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	private Request request;
	
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "physician_id")
	private Physician physician;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trans_to_physician_id")
	private Physician physician1;
	
	private String notes;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "trans_to_admin")
	private boolean transToAdmin;

	public int getRequest_status_log_id() {
		return request_status_log_id;
	}

	public void setRequest_status_log_id(int request_status_log_id) {
		this.request_status_log_id = request_status_log_id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Physician getPhysician1() {
		return physician1;
	}

	public void setPhysician1(Physician physician1) {
		this.physician1 = physician1;
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
		return "RequestStatusLog [request_status_log_id=" + request_status_log_id + ", request=" + request + ", status="
				+ status + ", physician=" + physician + ", admin=" + admin + ", physician1=" + physician1 + ", notes="
				+ notes + ", createdDate=" + createdDate + ", transToAdmin=" + transToAdmin + "]";
	}

	public RequestStatusLog(int request_status_log_id, Request request, int status, Physician physician, Admin admin,
			Physician physician1, String notes, Date createdDate, boolean transToAdmin) {
		super();
		this.request_status_log_id = request_status_log_id;
		this.request = request;
		this.status = status;
		this.physician = physician;
		this.admin = admin;
		this.physician1 = physician1;
		this.notes = notes;
		this.createdDate = createdDate;
		this.transToAdmin = transToAdmin;
	}

	public RequestStatusLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
