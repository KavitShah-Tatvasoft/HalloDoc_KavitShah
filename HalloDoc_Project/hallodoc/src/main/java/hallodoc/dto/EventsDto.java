package hallodoc.dto;

public class EventsDto {

	private int shiftDetailId;
	private String shiftDate;
	private String startTime;
	private String endTime;
	private String regionAbbr;
	private int regionId;
	private int physicianId;
	private int status;
	
	
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getShiftDetailId() {
		return shiftDetailId;
	}
	public void setShiftDetailId(int shiftDetailId) {
		this.shiftDetailId = shiftDetailId;
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
	public String getRegionAbbr() {
		return regionAbbr;
	}
	public void setRegionAbbr(String regionAbbr) {
		this.regionAbbr = regionAbbr;
	}
	public int getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public EventsDto(int shiftDetailId, String shiftDate, String startTime, String endTime, String regionAbbr,
			int regionId, int physicianId, int status) {
		super();
		this.shiftDetailId = shiftDetailId;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regionAbbr = regionAbbr;
		this.regionId = regionId;
		this.physicianId = physicianId;
		this.status = status;
	}
	public EventsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
