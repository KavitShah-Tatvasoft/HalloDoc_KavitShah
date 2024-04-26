package hallodoc.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physician_notification")
public class PhysicianNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer physicianNotication;
	
	@Column(name = "physician_id")
	private Integer physicianId;
	
	@Column(name = "is_notification_stopped")
	private Boolean isNotificationStopped;

	public Integer getPhysicianNotication() {
		return physicianNotication;
	}

	public void setPhysicianNotication(Integer physicianNotication) {
		this.physicianNotication = physicianNotication;
	}

	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}

	public Boolean getIsNotificationStopped() {
		return isNotificationStopped;
	}

	public void setIsNotificationStopped(Boolean isNotificationStopped) {
		this.isNotificationStopped = isNotificationStopped;
	}

	@Override
	public String toString() {
		return "PhysicianNotification [physicianNotication=" + physicianNotication + ", physicianId=" + physicianId
				+ ", isNotificationStopped=" + isNotificationStopped + "]";
	}

	public PhysicianNotification(Integer physicianNotication, Integer physicianId, Boolean isNotificationStopped) {
		super();
		this.physicianNotication = physicianNotication;
		this.physicianId = physicianId;
		this.isNotificationStopped = isNotificationStopped;
	}

	public PhysicianNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
