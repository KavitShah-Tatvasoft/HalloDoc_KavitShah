package hallodoc.dto;

public class ProviderMenuDto {

	private boolean stopNotification;
	private String providerName;
	private String role;
	private String onCallStatus;
	private String status;
	private int physicianStatus;
	
	public boolean isStopNotification() {
		return stopNotification;
	}
	public void setStopNotification(boolean stopNotification) {
		this.stopNotification = stopNotification;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getOnCallStatus() {
		return onCallStatus;
	}
	public void setOnCallStatus(String onCallStatus) {
		this.onCallStatus = onCallStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPhysicianStatus() {
		return physicianStatus;
	}
	public void setPhysicianStatus(int physicianStatus) {
		this.physicianStatus = physicianStatus;
	}
	@Override
	public String toString() {
		return "ProviderMenuDto [stopNotification=" + stopNotification + ", providerName=" + providerName + ", role="
				+ role + ", onCallStatus=" + onCallStatus + ", status=" + status + ", physicianStatus="
				+ physicianStatus + "]";
	}
	public ProviderMenuDto(boolean stopNotification, String providerName, String role, String onCallStatus,
			String status, int physicianStatus) {
		super();
		this.stopNotification = stopNotification;
		this.providerName = providerName;
		this.role = role;
		this.onCallStatus = onCallStatus;
		this.status = status;
		this.physicianStatus = physicianStatus;
	}
	public ProviderMenuDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
