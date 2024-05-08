package hallodoc.dto;

public class PhysicianRequestDataDto {

	private String patientFirstName;
	private String patientLastName;
	private String ptPhoneNumber;
	private String reqPhoneNumber = null;
	private String ptStreet;
	private String ptCity;
	private String ptState;
	private String ptZipcode;
	private int reqId;
	private String ptPhoneType = "Patient";
	private String reqPhoneType;
	private Integer callType;
	private boolean isFinalized;
	
	
	
	
	public boolean isFinalized() {
		return isFinalized;
	}
	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
		this.callType = callType;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPtStreet() {
		return ptStreet;
	}
	public void setPtStreet(String ptStreet) {
		this.ptStreet = ptStreet;
	}
	public String getPtCity() {
		return ptCity;
	}
	public void setPtCity(String ptCity) {
		this.ptCity = ptCity;
	}
	public String getPtState() {
		return ptState;
	}
	public void setPtState(String ptState) {
		this.ptState = ptState;
	}
	public String getPtZipcode() {
		return ptZipcode;
	}
	public void setPtZipcode(String ptZipcode) {
		this.ptZipcode = ptZipcode;
	}
	
	public String getPtPhoneNumber() {
		return ptPhoneNumber;
	}
	public void setPtPhoneNumber(String ptPhoneNumber) {
		this.ptPhoneNumber = ptPhoneNumber;
	}
	public String getReqPhoneNumber() {
		return reqPhoneNumber;
	}
	public void setReqPhoneNumber(String reqPhoneNumber) {
		this.reqPhoneNumber = reqPhoneNumber;
	}
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	
	
	
	
	public String getPtPhoneType() {
		return ptPhoneType;
	}
	public void setPtPhoneType(String ptPhoneType) {
		this.ptPhoneType = ptPhoneType;
	}
	public String getReqPhoneType() {
		return reqPhoneType;
	}
	public void setReqPhoneType(String reqPhoneType) {
		this.reqPhoneType = reqPhoneType;
	} 	 
	
	
	
	public PhysicianRequestDataDto(String patientFirstName, String patientLastName, String ptPhoneNumber,
			String reqPhoneNumber, String ptStreet, String ptCity, String ptState, String ptZipcode, int reqId,
			String ptPhoneType, String reqPhoneType, Integer callType, boolean isFinalized) {
		super();
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.ptPhoneNumber = ptPhoneNumber;
		this.reqPhoneNumber = reqPhoneNumber;
		this.ptStreet = ptStreet;
		this.ptCity = ptCity;
		this.ptState = ptState;
		this.ptZipcode = ptZipcode;
		this.reqId = reqId;
		this.ptPhoneType = ptPhoneType;
		this.reqPhoneType = reqPhoneType;
		this.callType = callType;
		this.isFinalized = isFinalized;
	}
	public PhysicianRequestDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
