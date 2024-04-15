package hallodoc.dto;

public class CancelCaseDetailsDto {

	private int caseTagId;
	private String additionalNotes;
	private int requestId;
	
	
	public CancelCaseDetailsDto(int caseTagId, String additionalNotes, int requestId) {
		super();
		this.caseTagId = caseTagId;
		this.additionalNotes = additionalNotes;
		this.requestId = requestId;
	}


	@Override
	public String toString() {
		return "CancelCaseDetailsDto [caseTagId=" + caseTagId + ", additionalNotes=" + additionalNotes + ", requestId="
				+ requestId + "]";
	}


	public int getCaseTagId() {
		return caseTagId;
	}


	public void setCaseTagId(int caseTagId) {
		this.caseTagId = caseTagId;
	}


	public String getAdditionalNotes() {
		return additionalNotes;
	}


	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}


	public int getRequestId() {
		return requestId;
	}


	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	public CancelCaseDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
