package hallodoc.dto;

import java.util.List;

public class EditRoleDto {

	private String roleName;
	private Integer roleType;
	private List<MenusDto> menuList;
	private List<MenusDto> allRoleTypeMenuList;
	
	
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
	public List<MenusDto> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenusDto> menuList) {
		this.menuList = menuList;
	}
	public List<MenusDto> getAllRoleTypeMenuList() {
		return allRoleTypeMenuList;
	}
	public void setAllRoleTypeMenuList(List<MenusDto> allRoleTypeMenuList) {
		this.allRoleTypeMenuList = allRoleTypeMenuList;
	}
	public EditRoleDto(String roleName, Integer roleType, List<MenusDto> menuList, List<MenusDto> allRoleTypeMenuList) {
		super();
		this.roleName = roleName;
		this.roleType = roleType;
		this.menuList = menuList;
		this.allRoleTypeMenuList = allRoleTypeMenuList;
	}
	public EditRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
