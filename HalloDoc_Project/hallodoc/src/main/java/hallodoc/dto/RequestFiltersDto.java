package hallodoc.dto;

public class RequestFiltersDto {

	private String patientName;
	private String stateName;
	private String requestType;
	private String statusType;
	private int pageNo;
	
	
	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

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

	public RequestFiltersDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestFiltersDto(String patientName, String stateName, String requestType, String statusType, int pageNo) {
		super();
		this.patientName = patientName;
		this.stateName = stateName;
		this.requestType = requestType;
		this.statusType = statusType;
		this.pageNo = pageNo;
	}

	
	

	

}
