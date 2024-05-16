package hallodoc.dto;

public class EditShiftDto {

	private int shiftDetailId;
	private int regionId;
	private String regionName;
	private int physicianId;
	private String physicianName;
	private String shiftDate;
	private String startTime;
	private String endTime;
	public int getShiftDetailId() {
		return shiftDetailId;
	}
	public void setShiftDetailId(int shiftDetailId) {
		this.shiftDetailId = shiftDetailId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
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
	public String getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public EditShiftDto(int shiftDetailId, int regionId, String regionName, int physicianId, String physicianName,
			String shiftDate, String startTime, String endTime) {
		super();
		this.shiftDetailId = shiftDetailId;
		this.regionId = regionId;
		this.regionName = regionName;
		this.physicianId = physicianId;
		this.physicianName = physicianName;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public EditShiftDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
