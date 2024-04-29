package hallodoc.dto;

public class BlockRequestsTableData {

	private Integer requestId;
	private String patientName;
	private String phoneNumber;
	private String email;
	private String createdDate;
	private String notes;
	private Boolean isActive;

	
	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BlockRequestsTableData [requestId=" + requestId + ", patientName=" + patientName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", createdDate=" + createdDate + ", notes=" + notes + ", isActive="
				+ isActive + "]";
	}

	public BlockRequestsTableData(Integer requestId, String patientName, String phoneNumber, String email,
			String createdDate, String notes, Boolean isActive) {
		super();
		this.requestId = requestId;
		this.patientName = patientName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.createdDate = createdDate;
		this.notes = notes;
		this.isActive = isActive;
	}

	public BlockRequestsTableData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
