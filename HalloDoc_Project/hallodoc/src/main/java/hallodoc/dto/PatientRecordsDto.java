package hallodoc.dto;

import java.util.Date;

public class PatientRecordsDto {

	private Integer requestId;
	private String requestClient;
	private String createdDate ;
	private String confNumber;
	private String providerName;
	private Date concludeDate;
	private String status;
	private Integer docCount;
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getRequestClient() {
		return requestClient;
	}
	public void setRequestClient(String requestClient) {
		this.requestClient = requestClient;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getConfNumber() {
		return confNumber;
	}
	public void setConfNumber(String confNumber) {
		this.confNumber = confNumber;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Date getConcludeDate() {
		return concludeDate;
	}
	public void setConcludeDate(Date concludeDate) {
		this.concludeDate = concludeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDocCount() {
		return docCount;
	}
	public void setDocCount(Integer docCount) {
		this.docCount = docCount;
	}
	@Override
	public String toString() {
		return "PatientRecordsDto [requestId=" + requestId + ", requestClient=" + requestClient + ", createdDate="
				+ createdDate + ", confNumber=" + confNumber + ", providerName=" + providerName + ", concludeDate="
				+ concludeDate + ", status=" + status + ", docCount=" + docCount + "]";
	}
	public PatientRecordsDto(Integer requestId, String requestClient, String createdDate, String confNumber,
			String providerName, Date concludeDate, String status, Integer docCount) {
		super();
		this.requestId = requestId;
		this.requestClient = requestClient;
		this.createdDate = createdDate;
		this.confNumber = confNumber;
		this.providerName = providerName;
		this.concludeDate = concludeDate;
		this.status = status;
		this.docCount = docCount;
	}
	public PatientRecordsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
