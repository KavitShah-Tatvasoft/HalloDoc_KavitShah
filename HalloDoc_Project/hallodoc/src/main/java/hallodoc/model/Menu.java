package hallodoc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_id")
	private int menuId;
	
	private String name;
	
	@Column(name="account_type")
	private int accountType;
	
	@Column(name="sort_order")
	private int sortOrder;
	
	@ManyToMany(mappedBy = "menus")
	private List<Role> roles;

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

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Menu(int menuId, String name, int accountType, int sortOrder, List<Role> roles) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.accountType = accountType;
		this.sortOrder = sortOrder;
		this.roles = roles;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
