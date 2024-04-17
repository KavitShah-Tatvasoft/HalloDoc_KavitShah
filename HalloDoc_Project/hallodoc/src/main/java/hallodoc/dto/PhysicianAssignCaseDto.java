package hallodoc.dto;

public class PhysicianAssignCaseDto {

	private String firstName;
	private String lastName;
	private Integer physicianId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}
	public PhysicianAssignCaseDto(String firstName, String lastName, Integer physicianId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.physicianId = physicianId;
	}
	public PhysicianAssignCaseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PhysicianAssignCaseDto [firstName=" + firstName + ", lastName=" + lastName + ", physicianId="
				+ physicianId + "]";
	}
	
	
}
