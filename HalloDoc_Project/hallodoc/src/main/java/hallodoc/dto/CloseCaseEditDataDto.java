package hallodoc.dto;

public class CloseCaseEditDataDto {

	private String fName;
	private String lName;
	private String pNumber;
	private String dob;
	private Integer reqId;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	@Override
	public String toString() {
		return "CloseCaseEditDataDto [fName=" + fName + ", lName=" + lName + ", pNumber=" + pNumber + ", dob=" + dob
				+ ", reqId=" + reqId + "]";
	}

	public CloseCaseEditDataDto(String fName, String lName, String pNumber, String dob, Integer reqId) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.pNumber = pNumber;
		this.dob = dob;
		this.reqId = reqId;
	}

	public CloseCaseEditDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
