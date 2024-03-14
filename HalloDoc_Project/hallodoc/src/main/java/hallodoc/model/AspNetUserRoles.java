package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AspNetUserRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "role_id")
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public AspNetUserRoles(int userId, int roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public AspNetUserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AspNetUserRoles [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
}
