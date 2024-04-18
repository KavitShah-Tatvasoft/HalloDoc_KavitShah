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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public AspNetUsers getAspNetUsers() {
		return aspNetUsers;
	}

	public void setAspNetUsers(AspNetUsers aspNetUsers) {
		this.aspNetUsers = aspNetUsers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public AspNetUsers getAspNetUsers1() {
		return aspNetUsers1;
	}

	public void setAspNetUsers1(AspNetUsers aspNetUsers1) {
		this.aspNetUsers1 = aspNetUsers1;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AspNetUsers getAspNetUsers2() {
		return aspNetUsers2;
	}

	public void setAspNetUsers2(AspNetUsers aspNetUsers2) {
		this.aspNetUsers2 = aspNetUsers2;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", aspNetUsers=" + aspNetUsers + ", firstName=" + firstName + ", lastName="
				+ lastName + ", addressOne=" + addressOne + ", addressTwo=" + addressTwo + ", city=" + city
				+ ", regions=" + regions + ", zip=" + zip + ", altPhone=" + altPhone + ", aspNetUsers1=" + aspNetUsers1
				+ ", createdDate=" + createdDate + ", aspNetUsers2=" + aspNetUsers2 + ", modifiedDate=" + modifiedDate
				+ ", isDeleted=" + isDeleted + ", role=" + role + "]";
	}

	public Admin(int adminId, AspNetUsers aspNetUsers, String firstName, String lastName, String addressOne,
			String addressTwo, String city, List<Region> regions, String zip, String altPhone, AspNetUsers aspNetUsers1,
			Date createdDate, AspNetUsers aspNetUsers2, Date modifiedDate, boolean isDeleted, Role role) {
		super();
		this.adminId = adminId;
		this.aspNetUsers = aspNetUsers;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.regions = regions;
		this.zip = zip;
		this.altPhone = altPhone;
		this.aspNetUsers1 = aspNetUsers1;
		this.createdDate = createdDate;
		this.aspNetUsers2 = aspNetUsers2;
		this.modifiedDate = modifiedDate;
		this.isDeleted = isDeleted;
		this.role = role;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
