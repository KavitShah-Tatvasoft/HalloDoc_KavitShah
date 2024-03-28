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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userID;
	
//	@Column(name="aspnet_user_id")
//	private int aspnetUserId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aspnet_user_id")
	private AspNetUsers aspNetUsers;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String mobile;
	
	private String street;
	
	private String city;
	
	private String state;
	
//	@Column(name="region_id")
//	private int regionId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_id")
	private Region region;
	
	private String zipcode;
	
	@Column(name="str_month")
	private String strMonth;
	
	@Column(name="int_year")
	private int intYear;
	
	@Column(name="int_date")
	private int intDate;
	
//	@Column(name="created_by")
//	private int createdBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by")
	private AspNetUsers createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
//	@Column(name="modified_by")
//	private int modifiedBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "modified_by")
	private AspNetUsers modifiedBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	private int status;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="is_request_with_email")
	private boolean isRequestWithEmail;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private AspNetRoles aspNetRoles;
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStrMonth() {
		return strMonth;
	}

	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}

	public int getIntYear() {
		return intYear;
	}

	public void setIntYear(int intYear) {
		this.intYear = intYear;
	}

	public int getIntDate() {
		return intDate;
	}

	public void setIntDate(int intDate) {
		this.intDate = intDate;
	}

	public AspNetUsers getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AspNetUsers createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AspNetUsers getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(AspNetUsers modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isRequestWithEmail() {
		return isRequestWithEmail;
	}

	public void setRequestWithEmail(boolean isRequestWithEmail) {
		this.isRequestWithEmail = isRequestWithEmail;
	}

	public AspNetRoles getAspNetRoles() {
		return aspNetRoles;
	}

	public void setAspNetRoles(AspNetRoles aspNetRoles) {
		this.aspNetRoles = aspNetRoles;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", aspNetUsers=" + aspNetUsers + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", mobile=" + mobile + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", region=" + region + ", zipcode=" + zipcode + ", strMonth=" + strMonth
				+ ", intYear=" + intYear + ", intDate=" + intDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", isDeleted=" + isDeleted + ", isRequestWithEmail=" + isRequestWithEmail + ", aspNetRoles="
				+ aspNetRoles + "]";
	}

	public User(int userID, AspNetUsers aspNetUsers, String firstName, String lastName, String email, String mobile,
			String street, String city, String state, Region region, String zipcode, String strMonth, int intYear,
			int intDate, AspNetUsers createdBy, Date createdDate, AspNetUsers modifiedBy, Date modifiedDate, int status,
			boolean isDeleted, boolean isRequestWithEmail, AspNetRoles aspNetRoles) {
		super();
		this.userID = userID;
		this.aspNetUsers = aspNetUsers;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.street = street;
		this.city = city;
		this.state = state;
		this.region = region;
		this.zipcode = zipcode;
		this.strMonth = strMonth;
		this.intYear = intYear;
		this.intDate = intDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.isDeleted = isDeleted;
		this.isRequestWithEmail = isRequestWithEmail;
		this.aspNetRoles = aspNetRoles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
