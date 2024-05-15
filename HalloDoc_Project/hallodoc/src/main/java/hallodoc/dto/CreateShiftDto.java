package hallodoc.dto;

public class CreateShiftDto {
	
	private int region;
	private int physicianId;
	private String shiftDate;
	private String startTime;
	private String endTime;
	private String isRepeated;
	private String selectedDays;
	private int repeatTimes;
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	public int getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
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
	public String getIsRepeated() {
		return isRepeated;
	}
	public void setIsRepeated(String isRepeated) {
		this.isRepeated = isRepeated;
	}
	public String getSelectedDays() {
		return selectedDays;
	}
	public void setSelectedDays(String selectedDays) {
		this.selectedDays = selectedDays;
	}
	public int getRepeatTimes() {
		return repeatTimes;
	}
	public void setRepeatTimes(int repeatTimes) {
		this.repeatTimes = repeatTimes;
	}
	public CreateShiftDto(int region, int physicianId, String shiftDate, String startTime, String endTime,
			String isRepeated, String selectedDays, int repeatTimes) {
		super();
		this.region = region;
		this.physicianId = physicianId;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isRepeated = isRepeated;
		this.selectedDays = selectedDays;
		this.repeatTimes = repeatTimes;
	}
	public CreateShiftDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
