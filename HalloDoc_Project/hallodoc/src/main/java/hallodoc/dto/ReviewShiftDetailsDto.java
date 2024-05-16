package hallodoc.dto;

public class ReviewShiftDetailsDto {

	private int shiftDetailId;
	private int regionId;
	private String physicainName;
	private String shiftDate;
	private String startTime;
	private String endTime;
	private String regionName;
	
	
	
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
	public String getPhysicainName() {
		return physicainName;
	}
	public void setPhysicainName(String physicainName) {
		this.physicainName = physicainName;
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
	public void setEndTime(String endTiime) {
		this.endTime = endTiime;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public ReviewShiftDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReviewShiftDetailsDto(int shiftDetailId, int regionId, String physicainName, String shiftDate,
			String startTime, String endTime, String regionName) {
		super();
		this.shiftDetailId = shiftDetailId;
		this.regionId = regionId;
		this.physicainName = physicainName;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.regionName = regionName;
	}
	
	
	
	
	
}
