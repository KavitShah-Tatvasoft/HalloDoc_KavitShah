package hallodoc.dto;

public class SearchRecordsDashboardData {

	private String patientName;
	private String requestorName;
	private String dateOfService;
	private String closeCaseDate;
	private String email;
	private String phoneNumber;
	private String address;
	private String zip;
	private String requestStatus;
	private String physicianName;
	private String physicianNote;
	private String cancelledByPhysicianNote;
	private String adminNotes;
	private String patientNotes;
	private Integer requestId;
	private int pageNo;
	
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
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
	public String getRequestorName() {
		return requestorName;
	}
	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}
	public String getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}
	public String getCloseCaseDate() {
		return closeCaseDate;
	}
	public void setCloseCaseDate(String closeCaseDate) {
		this.closeCaseDate = closeCaseDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getPhysicianNote() {
		return physicianNote;
	}
	public void setPhysicianNote(String physicianNote) {
		this.physicianNote = physicianNote;
	}
	public String getCancelledByPhysicianNote() {
		return cancelledByPhysicianNote;
	}
	public void setCancelledByPhysicianNote(String cancelledByPhysicianNote) {
		this.cancelledByPhysicianNote = cancelledByPhysicianNote;
	}
	public String getAdminNotes() {
		return adminNotes;
	}
	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}
	public String getPatientNotes() {
		return patientNotes;
	}
	public void setPatientNotes(String patientNotes) {
		this.patientNotes = patientNotes;
	}
	
	
	public SearchRecordsDashboardData(String patientName, String requestorName, String dateOfService,
			String closeCaseDate, String email, String phoneNumber, String address, String zip, String requestStatus,
			String physicianName, String physicianNote, String cancelledByPhysicianNote, String adminNotes,
			String patientNotes, Integer requestId, int pageNo) {
		super();
		this.patientName = patientName;
		this.requestorName = requestorName;
		this.dateOfService = dateOfService;
		this.closeCaseDate = closeCaseDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.zip = zip;
		this.requestStatus = requestStatus;
		this.physicianName = physicianName;
		this.physicianNote = physicianNote;
		this.cancelledByPhysicianNote = cancelledByPhysicianNote;
		this.adminNotes = adminNotes;
		this.patientNotes = patientNotes;
		this.requestId = requestId;
		this.pageNo = pageNo;
	}
	public SearchRecordsDashboardData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
