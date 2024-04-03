package hallodoc.model;

import java.util.Date;
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

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="admin_id")
	private int adminId;
	
	
//	@Column(name="aspnet_userid")
//	private int aspnetUserId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aspnet_userid")
	private AspNetUsers aspNetUsers;
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address_one")
	private String addressOne;
	
	@Column(name="address_two")
	private String addressTwo;
	
	@Column(name="city")
	private String city;
	
//	@Column(name="region_id")
//	private int regionId;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "admin_region", 
        joinColumns = { @JoinColumn(name = "admin_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "region_id") }
    )
	List<Region> regions;
	
	
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="alt_phone")
	private String altPhone;
	
//	@Column(name="created_by")
//	private int createdBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by")
	private AspNetUsers aspNetUsers1;
	
	@Column(name="created_date")
	private Date createdDate;
	
//	@Column(name="modified_by")
//	private int modifiedBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modified_by")
	private AspNetUsers aspNetUsers2;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
//	@Column(name="role_id")
//	private int roleId;	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	
}
