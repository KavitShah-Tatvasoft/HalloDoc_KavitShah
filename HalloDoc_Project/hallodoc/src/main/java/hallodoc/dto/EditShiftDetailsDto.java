package hallodoc.dto;

public class EditShiftDetailsDto {
			
	private int shiftDetailId;
	private String shiftDate;
	private String startTime;
	private String endTime;
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
	public EditShiftDetailsDto(int shiftDetailId, String shiftDate, String startTime, String endTime) {
		super();
		this.shiftDetailId = shiftDetailId;
		this.shiftDate = shiftDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public EditShiftDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
