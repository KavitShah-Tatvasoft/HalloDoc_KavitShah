package hallodoc.dto;

public class GetRolesDto {

	
	private Integer roleId;
	private String roleName;
	
	
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
	public GetRolesDto(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public GetRolesDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
