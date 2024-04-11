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

@Entity
@Table(name="request_notes")
public class RequestNotes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="request_notes_id")
	private int requestNotesId;
	
//	@Column(name="request_id")
//	private int requestId;
	
	@OneToOne
	@JoinColumn(name="request_id")
	private Request request;
	
	@Column(name="str_month")
	private String strMonth;
	
	@Column(name="int_year")
	private int intYear;
	
	@Column(name="int_date")
	private int intDate;
	
	@Column(name="physican_notes")
	private String physicanNotes;
	
	@Column(name="admin_notes")
	private String adminNotes;
	
//	@Column(name="created_by")
//	private int createdBy;
	
	@OneToOne
	@JoinColumn(name="created_by")
	private AspNetUsers createdBy;
	
	
	@CreationTimestamp
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
//	@Column(name="modified_by")
//	private int modifiedBy;
	
	@OneToOne
	@JoinColumn(name="modified_by")
	private AspNetUsers modifiedBy;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name="administrative_notes")
	private String administrativeNotes;
	
	@Column(name="note")
	private String note;

	
	public RequestNotes(int requestNotesId, Request request, String strMonth, int intYear, int intDate,
			String physicanNotes, String adminNotes, AspNetUsers createdBy, LocalDateTime createdDate,
			AspNetUsers modifiedBy, LocalDateTime modifiedDate, String administrativeNotes, String note) {
		super();
		this.requestNotesId = requestNotesId;
		this.request = request;
		this.strMonth = strMonth;
		this.intYear = intYear;
		this.intDate = intDate;
		this.physicanNotes = physicanNotes;
		this.adminNotes = adminNotes;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.administrativeNotes = administrativeNotes;
		this.note = note;
	}


	@Override
	public String toString() {
		return "RequestNotes [requestNotesId=" + requestNotesId + ", request=" + request + ", strMonth=" + strMonth
				+ ", intYear=" + intYear + ", intDate=" + intDate + ", physicanNotes=" + physicanNotes + ", adminNotes="
				+ adminNotes + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", administrativeNotes=" + administrativeNotes
				+ ", note=" + note + "]";
	}


	public int getRequestNotesId() {
		return requestNotesId;
	}


	public void setRequestNotesId(int requestNotesId) {
		this.requestNotesId = requestNotesId;
	}


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}


	public String getStrMonth() {
		return strMonth;
	}


	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}


	public int getIntYear() {
		return intYear;
	}


	public void setIntYear(int intYear) {
		this.intYear = intYear;
	}


	public int getIntDate() {
		return intDate;
	}


	public void setIntDate(int intDate) {
		this.intDate = intDate;
	}


	public String getPhysicanNotes() {
		return physicanNotes;
	}


	public void setPhysicanNotes(String physicanNotes) {
		this.physicanNotes = physicanNotes;
	}


	public String getAdminNotes() {
		return adminNotes;
	}


	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}


	public AspNetUsers getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(AspNetUsers createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public AspNetUsers getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(AspNetUsers modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getAdministrativeNotes() {
		return administrativeNotes;
	}


	public void setAdministrativeNotes(String administrativeNotes) {
		this.administrativeNotes = administrativeNotes;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public RequestNotes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
