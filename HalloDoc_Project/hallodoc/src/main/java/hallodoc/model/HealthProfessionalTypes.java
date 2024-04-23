package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "health_professional_type")
public class HealthProfessionalTypes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "health_professional_id")
	private int healthProfessionalId;

	@Column(name = "profession_name")
	private String professionName;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	public int getHealthProfessionalId() {
		return healthProfessionalId;
	}

	public void setHealthProfessionalId(int healthProfessionalId) {
		this.healthProfessionalId = healthProfessionalId;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "HealthProfessionalType [healthProfessionalId=" + healthProfessionalId + ", professionName="
				+ professionName + ", createdDate=" + createdDate + ", isActive=" + isActive + ", isDeleted="
				+ isDeleted + "]";
	}

	public HealthProfessionalTypes(int healthProfessionalId, String professionName, LocalDateTime createdDate,
			boolean isActive, boolean isDeleted) {
		super();
		this.healthProfessionalId = healthProfessionalId;
		this.professionName = professionName;
		this.createdDate = createdDate;
		this.isActive = isActive;
		this.isDeleted = isDeleted;
	}

	public HealthProfessionalTypes() {
		super();
		// TODO Auto-generated constructor stub
	}

}