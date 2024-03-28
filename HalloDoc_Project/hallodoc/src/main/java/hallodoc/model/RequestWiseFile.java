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
import javax.persistence.Table;

@Entity
@Table(name = "request_wise_file")
public class RequestWiseFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_wise_file_id")
	private int requestWiseFileId;
	
//	@Column(name = "request_id")
//	private int requestId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	private Request request;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "created_date")
	private Date createdDate;
	
//	@Column(name = "physician_id")
//	private int physicianId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "physician_id")
	private Physician physician;
	
//	@Column(name = "admin_id")
//	private int adminId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
	@Column(name = "doc_type")
	private int docType;
	
	@Column(name = "is_finalize")
	private boolean isFinalize;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "is_patient_records")
	private boolean isPatientRecords;
	
	@Column(name="uploader_name")
	private String uploaderName;
	
	@Column(name="file_extension")
	private String fileExtension;
	
	

	public String getUploaderName() {
		return uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public int getRequestWiseFileId() {
		return requestWiseFileId;
	}

	public void setRequestWiseFileId(int requestWiseFileId) {
		this.requestWiseFileId = requestWiseFileId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
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

	
	public RequestWiseFile(int requestWiseFileId, Request request, String fileName, Date createdDate,
			Physician physician, Admin admin, int docType, boolean isFinalize, boolean isDeleted,
			boolean isPatientRecords, String uploaderName, String fileExtension) {
		super();
		this.requestWiseFileId = requestWiseFileId;
		this.request = request;
		this.fileName = fileName;
		this.createdDate = createdDate;
		this.physician = physician;
		this.admin = admin;
		this.docType = docType;
		this.isFinalize = isFinalize;
		this.isDeleted = isDeleted;
		this.isPatientRecords = isPatientRecords;
		this.uploaderName = uploaderName;
		this.fileExtension = fileExtension;
	}

	@Override
	public String toString() {
		return "RequestWiseFile [requestWiseFileId=" + requestWiseFileId + ", request=" + request + ", fileName="
				+ fileName + ", createdDate=" + createdDate + ", physician=" + physician + ", admin=" + admin
				+ ", docType=" + docType + ", isFinalize=" + isFinalize + ", isDeleted=" + isDeleted
				+ ", isPatientRecords=" + isPatientRecords + ", uploaderName=" + uploaderName + ", fileExtension="
				+ fileExtension + "]";
	}

	public RequestWiseFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
