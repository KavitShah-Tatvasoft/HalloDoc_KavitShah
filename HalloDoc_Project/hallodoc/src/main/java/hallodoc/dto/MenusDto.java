package hallodoc.dto;

import javax.persistence.Column;

public class MenusDto {

	private int menuId;
	private String name;
	private int accountType;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "MenusDto [menuId=" + menuId + ", name=" + name + ", accountType=" + accountType + "]";
	}
	public MenusDto(int menuId, String name, int accountType) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.accountType = accountType;
	}
	public MenusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
