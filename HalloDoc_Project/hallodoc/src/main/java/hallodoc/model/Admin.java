package hallodoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="aspnet_userid")
	private int aspnetUserId;
	
	@Column(name="first_name")
	private String firsName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address_one")
	private String addressOne;
	
	@Column(name="address_two")
	private String addressTwo;
	
	@Column(name="city")
	private String city;
	
	@Column(name="region_id")
	private int regionId;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="alt_phone")
	private String altPhone;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_by")
	private int modifiedBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="role_id")
	private int roleId;	
}
