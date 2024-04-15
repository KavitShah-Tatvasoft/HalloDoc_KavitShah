package hallodoc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asp_net_users")
public class AspNetUsers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String password_hash;
	private String email;
	private String phone_number;
	private Date modified_date;
	private String user_name;
	private Date created_date;
	
	@OneToOne(mappedBy = "aspNetUsers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	
	@OneToOne(mappedBy = "aspNetUsers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Admin admin;

	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
	public AspNetUsers(int id, String password_hash, String email, String phone_number, Date modified_date,
			String user_name, Date created_date, User user, Admin admin) {
		super();
		this.id = id;
		this.password_hash = password_hash;
		this.email = email;
		this.phone_number = phone_number;
		this.modified_date = modified_date;
		this.user_name = user_name;
		this.created_date = created_date;
		this.user = user;
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "AspNetUsers [id=" + id + ", password_hash=" + password_hash + ", email=" + email + ", phone_number="
				+ phone_number + ", modified_date=" + modified_date + ", user_name=" + user_name + ", created_date="
				+ created_date + ", user=" + user + ", admin=" + admin + "]";
	}
	public AspNetUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
