package hallodoc.enumerations;

public enum AspNetRolesEnum {
	
	ADMIN(1, "Admin"),
	PROVIDER(2, "Provider"),
	PATIENT(3, "Patient");
	
	private int aspNetRolesId;
	private String aspNetRolesName;
	
	AspNetRolesEnum(int aspNetRolesId,String aspNetRolesName) {
		this.aspNetRolesId=aspNetRolesId;
		this.aspNetRolesName=aspNetRolesName;
	}

	public int getAspNetRolesId() {
		return aspNetRolesId;
	}

	public String getAspNetRolesName() {
		return aspNetRolesName;
	}

	
}