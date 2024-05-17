package hallodoc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "shift")
public class Shift {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shift_id")
	private int shiftId;

	@OneToOne
	@JoinColumn(name = "physician_id")
	private Physician physicianId  ;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "is_repeat")
	private boolean isRepeat;

	@Column(name = "week_days")
	private String weekDays;

	@Column(name = "repeat_upto")
	private int repeatUpto;

	@OneToOne
	@JoinColumn(name = "created_by")
	private AspNetUsers createdBy;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "shiftId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ShiftDetails> shiftDetails;

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public Physician getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Physician physicianId) {
		this.physicianId = physicianId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public int getRepeatUpto() {
		return repeatUpto;
	}

	public void setRepeatUpto(int repeatUpto) {
		this.repeatUpto = repeatUpto;
	}

	public AspNetUsers getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AspNetUsers createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<ShiftDetails> getShiftDetails() {
		return shiftDetails;
	}

	public void setShiftDetails(List<ShiftDetails> shiftDetails) {
		this.shiftDetails = shiftDetails;
	}

	public Shift(int shiftId, Physician physicianId, LocalDate startDate, boolean isRepeat, String weekDays,
			int repeatUpto, AspNetUsers createdBy, LocalDateTime createdDate, List<ShiftDetails> shiftDetails) {
		super();
		this.shiftId = shiftId;
		this.physicianId = physicianId;
		this.startDate = startDate;
		this.isRepeat = isRepeat;
		this.weekDays = weekDays;
		this.repeatUpto = repeatUpto;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.shiftDetails = shiftDetails;
	}

	public Shift() {
		super();
		// TODO Auto-generated constructor stub
	}

}
