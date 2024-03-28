package hallodoc.dto;

import java.util.Date;

public class RequestDocumentsDto {

	private String confirmationNumber;
	private String filename;
	private Date createdDate;
	private String uploaderName;
	private String fileExtension;

	
	
	
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
	
	
	
	@Override
	public String toString() {
		return "RequestDocumentsDto [confirmationNumber=" + confirmationNumber + ", filename=" + filename
				+ ", createdDate=" + createdDate + ", uploaderName=" + uploaderName + ", fileExtension=" + fileExtension
				+ "]";
	}
	
	public RequestDocumentsDto(String confirmationNumber, String filename, Date createdDate, String uploaderName,
			String fileExtension) {
		super();
		this.confirmationNumber = confirmationNumber;
		this.filename = filename;
		this.createdDate = createdDate;
		this.uploaderName = uploaderName;
		this.fileExtension = fileExtension;
	}

	public RequestDocumentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
