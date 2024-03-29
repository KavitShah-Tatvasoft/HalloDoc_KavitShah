package hallodoc.dto;

import java.util.Date;

import hallodoc.helper.Constants;
import hallodoc.service.PatientService;

public class RequestDocumentsDto {

	private String confirmationNumber;
	private String filename;
	private Date createdDate;
	private String uploaderName;
	private String fileExtension;
	private String storedFileName;
	
	
	
	
	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

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
	
	
	

	public RequestDocumentsDto(String confirmationNumber, String filename, Date createdDate, String uploaderName,
			String fileExtension, String storedFileName) {
		super();
		this.confirmationNumber = confirmationNumber;
		this.filename = filename;
		this.createdDate = createdDate;
		this.uploaderName = uploaderName;
		this.fileExtension = fileExtension;
		this.storedFileName = storedFileName;
	}

	@Override
	public String toString() {
		return "RequestDocumentsDto [confirmationNumber=" + confirmationNumber + ", filename=" + filename
				+ ", createdDate=" + createdDate + ", uploaderName=" + uploaderName + ", fileExtension=" + fileExtension
				+ ", storedFileName=" + storedFileName + "]";
	}

	public RequestDocumentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUrl() {
		String prefix = Constants.CONTEXT_PATH;
		return String.format("%s/%s/%s/%s/%s", prefix, "resources", "fileuploads", "patient", storedFileName);
	}
	
}
