package hallodoc.model;

import javax.annotation.Generated;
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

@Entity
@Table(name = "physician_notification")
public class PhysicianNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer physicianNotication;
	
//	@Column(name = "physician_id")
//	private Integer physicianId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="physician_id")
	private Physician physician;
	
	@Column(name = "is_notification_stopped")
	private Boolean isNotificationStopped;

	

	public PhysicianNotification(Integer physicianNotication, Physician physician, Boolean isNotificationStopped) {
		super();
		this.physicianNotication = physicianNotication;
		this.physician = physician;
		this.isNotificationStopped = isNotificationStopped;
	}



	@Override
	public String toString() {
		return "PhysicianNotification [physicianNotication=" + physicianNotication + ", physician=" + physician
				+ ", isNotificationStopped=" + isNotificationStopped + "]";
	}



	public Integer getPhysicianNotication() {
		return physicianNotication;
	}



	public void setPhysicianNotication(Integer physicianNotication) {
		this.physicianNotication = physicianNotication;
	}



	public Physician getPhysician() {
		return physician;
	}



	public void setPhysician(Physician physician) {
		this.physician = physician;
	}



	public Boolean getIsNotificationStopped() {
		return isNotificationStopped;
	}



	public void setIsNotificationStopped(Boolean isNotificationStopped) {
		this.isNotificationStopped = isNotificationStopped;
	}



	public PhysicianNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
