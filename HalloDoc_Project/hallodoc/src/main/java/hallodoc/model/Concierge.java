package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "concierge")
public class Concierge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "concierge_id")
	private int conciergeId;
	
	@Column(name = "concierge_name")
	private String conciergeName;
	
	private String email;
	
	private String address;
	
	private String street;
	
	private String city;
	
	private String state;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_id")
	private Region region;
	
	@Column(name = "role_id")
	private int roleId;

	public int getConciergeId() {
		return conciergeId;
	}

	public void setConciergeId(int conciergeId) {
		this.conciergeId = conciergeId;
	}

	public String getConciergeName() {
		return conciergeName;
	}

	public void setConciergeName(String conciergeName) {
		this.conciergeName = conciergeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Concierge(int conciergeId, String conciergeName, String email, String address, String street, String city,
			String state, String zipCode, LocalDateTime createdDate, Region region, int requestId, int roleId) {
		super();
		this.conciergeId = conciergeId;
		this.conciergeName = conciergeName;
		this.email = email;
		this.address = address;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.createdDate = createdDate;
		this.region = region;
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Concierge [conciergeId=" + conciergeId + ", conciergeName=" + conciergeName + ", email=" + email
				+ ", address=" + address + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", createdDate=" + createdDate + ", region=" + region 
				+ ", roleId=" + roleId + "]";
	}

	public Concierge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}