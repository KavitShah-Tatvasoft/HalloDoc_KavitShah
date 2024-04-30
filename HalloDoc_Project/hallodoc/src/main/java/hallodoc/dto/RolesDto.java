package hallodoc.dto;

public class RolesDto {

	private Integer roleId;
	private String roleName;
	private Integer accountType;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "RolesDato [roleId=" + roleId + ", roleName=" + roleName + ", accountType=" + accountType + "]";
	}
	public RolesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RolesDto(Integer roleId, String roleName, Integer accountType) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.accountType = accountType;
	}
	
	
	
}
