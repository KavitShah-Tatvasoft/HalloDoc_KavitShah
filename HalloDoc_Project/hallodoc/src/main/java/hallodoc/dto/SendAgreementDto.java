package hallodoc.dto;

public class SendAgreementDto {
	
	private int reqId;
	private String phoneNumber;
	private String email;
	
	
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
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
	
	public SendAgreementDto(int reqId, String phoneNumber, String email) {
		super();
		this.reqId = reqId;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	@Override
	public String toString() {
		return "SendAgreementDto [reqId=" + reqId + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	public SendAgreementDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
