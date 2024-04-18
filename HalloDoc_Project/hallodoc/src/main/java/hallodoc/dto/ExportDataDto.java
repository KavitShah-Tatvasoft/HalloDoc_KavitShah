package hallodoc.dto;

public class ExportDataDto {

	private String patientName;
	private String stateName;
	private String requestType;
	private String statusType;
	private String currentStatus;
	
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Override
	public String toString() {
		return "ExportDataDto [patientName=" + patientName + ", stateName=" + stateName + ", requestType=" + requestType
				+ ", statusType=" + statusType + ", currentStatus=" + currentStatus + "]";
	}
	public ExportDataDto(String patientName, String stateName, String requestType, String statusType,
			String currentStatus) {
		super();
		this.patientName = patientName;
		this.stateName = stateName;
		this.requestType = requestType;
		this.statusType = statusType;
		this.currentStatus = currentStatus;
	}
	public ExportDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
