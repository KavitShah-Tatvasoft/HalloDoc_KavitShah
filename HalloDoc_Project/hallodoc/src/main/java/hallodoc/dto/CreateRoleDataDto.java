package hallodoc.dto;

public class CreateRoleDataDto {

	private String roleName;
	private Integer roleType;
	private String selectedMenu;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	@Override
	public String toString() {
		return "CreateRoleDataDto [roleName=" + roleName + ", roleType=" + roleType + ", selectedMenu=" + selectedMenu
				+ "]";
	}

	public CreateRoleDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateRoleDataDto(String roleName, Integer roleType, String selectedMenu) {
		super();
		this.roleName = roleName;
		this.roleType = roleType;
		this.selectedMenu = selectedMenu;
	}

}
