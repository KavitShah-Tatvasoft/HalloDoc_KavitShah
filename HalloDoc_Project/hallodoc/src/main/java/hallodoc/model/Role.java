package hallodoc.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;
	
	private String name;
	
	@Column(name="account_type")
	private int accountType;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="created_by")
	private AspNetUsers createdBy;
	
	@CreationTimestamp
	@Column(name="created_date")
	private LocalDateTime createdDate;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="modified_by")
	private AspNetUsers modifiedBy;
	
	@UpdateTimestamp
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@ManyToMany()
    @JoinTable(
        name = "role_menu", 
        joinColumns = { @JoinColumn(name = "role_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "menu_id") }
    )
    List<Menu> menus;

	

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", accountType=" + accountType + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", isDeleted=" + isDeleted + ", menus=" + menus + "]";
	}



	public int getRoleId() {
		return roleId;
	}



	public void setRoleId(int roleId) {
		this.roleId = roleId;
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



	public AspNetUsers getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(AspNetUsers createdBy) {
		this.createdBy = createdBy;
	}



	public LocalDateTime getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}



	public AspNetUsers getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(AspNetUsers modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public List<Menu> getMenus() {
		return menus;
	}



	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}



	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
