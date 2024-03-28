package hallodoc.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import hallodoc.model.Admin;
import hallodoc.model.Physician;
import hallodoc.model.RequestWiseFile;

public class DashboardDataDto {

	private Integer requestId;
	private Integer physicianId;
	private String physicianFirstName;
	private String physicianLastName;
	private Date createdDate;
	private Integer status;
	private Integer adminId;
	private String adminFirstName;
	private String adminLastName;
	private Long count;
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getPhysicianId() {
		return physicianId;
	}
	public void setPhysicianId(Integer physicianId) {
		this.physicianId = physicianId;
	}
	public String getPhysicianFirstName() {
		return physicianFirstName;
	}
	public void setPhysicianFirstName(String physicianFirstName) {
		this.physicianFirstName = physicianFirstName;
	}
	public String getPhysicianLastName() {
		return physicianLastName;
	}
	public void setPhysicianLastName(String physicianLastName) {
		this.physicianLastName = physicianLastName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminFirstName() {
		return adminFirstName;
	}
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}
	public String getAdminLastName() {
		return adminLastName;
	}
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DashboardDataDto [requestId=" + requestId + ", physicianId=" + physicianId + ", physicianFirstName="
				+ physicianFirstName + ", physicianLastName=" + physicianLastName + ", createdDate=" + createdDate
				+ ", status=" + status + ", adminId=" + adminId + ", adminFirstName=" + adminFirstName
				+ ", adminLastName=" + adminLastName + ", count=" + count + "]";
	}
	public DashboardDataDto(Integer requestId, Integer physicianId, String physicianFirstName, String physicianLastName,
			Date createdDate, Integer status, Integer adminId, String adminFirstName, String adminLastName,
			Long count) {
		super();
		this.requestId = requestId;
		this.physicianId = physicianId;
		this.physicianFirstName = physicianFirstName;
		this.physicianLastName = physicianLastName;
		this.createdDate = createdDate;
		this.status = status;
		this.adminId = adminId;
		this.adminFirstName = adminFirstName;
		this.adminLastName = adminLastName;
		this.count = count;
	}
	public DashboardDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
