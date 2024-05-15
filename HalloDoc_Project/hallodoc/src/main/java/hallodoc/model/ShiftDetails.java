package hallodoc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "shift_details")
public class ShiftDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shift_detail_id")
	private int shiftDetailId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shift_id")
	private Shift shiftId;

	@Column(name = "shift_date")
	private LocalDate shiftDate;

	@Column(name = "region_id")
	private int regionId;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	private int status;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToOne
	@JoinColumn(name = "modified_by")
	private AspNetUsers modifiedBy;

	@UpdateTimestamp
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;

	@Column(name = "last_running_date")
	private LocalDateTime lastRunningTime;

	@Column(name = "event_id")
	private String eventId;

	public int getShiftDetailId() {
		return shiftDetailId;
	}

	public void setShiftDetailId(int shiftDetailId) {
		this.shiftDetailId = shiftDetailId;
	}

	public Shift getShiftId() {
		return shiftId;
	}

	public void setShiftId(Shift shiftId) {
		this.shiftId = shiftId;
	}

	public LocalDate getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(LocalDate shiftDate) {
		this.shiftDate = shiftDate;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AspNetUsers getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(AspNetUsers modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getLastRunningTime() {
		return lastRunningTime;
	}

	public void setLastRunningTime(LocalDateTime lastRunningTime) {
		this.lastRunningTime = lastRunningTime;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public ShiftDetails(int shiftDetailId, Shift shiftId, LocalDate shiftDate, int regionId, LocalTime startTime,
			LocalTime endTime, int status, boolean isDeleted, AspNetUsers modifiedBy, LocalDateTime modifiedDate,
			LocalDateTime lastRunningTime, String eventId) {
		super();
		this.shiftDetailId = shiftDetailId;
		this.shiftId = shiftId;
		this.shiftDate = shiftDate;
		this.regionId = regionId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.isDeleted = isDeleted;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.lastRunningTime = lastRunningTime;
		this.eventId = eventId;
	}

	public ShiftDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
