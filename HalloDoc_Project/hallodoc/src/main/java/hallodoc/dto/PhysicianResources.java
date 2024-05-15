package hallodoc.dto;

public class PhysicianResources {

	private int physicianId;
	private String physicianName;
	private String path;
	public int getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public PhysicianResources(int physicianId, String physicianName, String path) {
		super();
		this.physicianId = physicianId;
		this.physicianName = physicianName;
		this.path = path;
	}
	public PhysicianResources() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
