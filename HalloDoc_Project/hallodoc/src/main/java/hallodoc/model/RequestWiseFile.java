package hallodoc.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_wise_file")
public class RequestWiseFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_wise_file_id")
	private int requestWiseFileId;
	
	@Column(name = "request_id")
	private int requestId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "physician_id")
	private int physicianId;
	
	@Column(name = "admin_id")
	private int adminId;
	
	@Column(name = "doc_type")
	private int docType;
	
	@Column(name = "is_finalize")
	private boolean isFinalize;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "is_patient_records")
	private boolean isPatientRecords;

	public int getRequestWiseFileId() {
		return requestWiseFileId;
	}

	public void setRequestWiseFileId(int requestWiseFileId) {
		this.requestWiseFileId = requestWiseFileId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public int getDocType() {
		return docType;
	}

	public void setDocType(int docType) {
		this.docType = docType;
	}

	public boolean isFinalize() {
		return isFinalize;
	}

	public void setFinalize(boolean isFinalize) {
		this.isFinalize = isFinalize;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isPatientRecords() {
		return isPatientRecords;
	}

	public void setPatientRecords(boolean isPatientRecords) {
		this.isPatientRecords = isPatientRecords;
	}

	@Override
	public String toString() {
		return "RequestWiseFile [requestWiseFileId=" + requestWiseFileId + ", requestId=" + requestId + ", fileName="
				+ fileName + ", createdDate=" + createdDate + ", physicianId=" + physicianId + ", adminId=" + adminId
				+ ", docType=" + docType + ", isFinalize=" + isFinalize + ", isDeleted=" + isDeleted
				+ ", isPatientRecords=" + isPatientRecords + "]";
	}

	public RequestWiseFile(int requestWiseFileId, int requestId, String fileName, Date createdDate, int physicianId,
			int adminId, int docType, boolean isFinalize, boolean isDeleted, boolean isPatientRecords) {
		super();
		this.requestWiseFileId = requestWiseFileId;
		this.requestId = requestId;
		this.fileName = fileName;
		this.createdDate = createdDate;
		this.physicianId = physicianId;
		this.adminId = adminId;
		this.docType = docType;
		this.isFinalize = isFinalize;
		this.isDeleted = isDeleted;
		this.isPatientRecords = isPatientRecords;
	}

	public RequestWiseFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
