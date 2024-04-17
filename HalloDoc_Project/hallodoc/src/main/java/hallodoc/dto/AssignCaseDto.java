package hallodoc.dto;

public class AssignCaseDto {

	private Integer reqId;
	private Integer physicianId;
	private String description;

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AssignCaseDto [reqId=" + reqId + ", physicianId=" + physicianId + ", description=" + description + "]";
	}

	public AssignCaseDto(Integer reqId, Integer physicianId, String description) {
		super();
		this.reqId = reqId;
		this.physicianId = physicianId;
		this.description = description;
	}

	public AssignCaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
