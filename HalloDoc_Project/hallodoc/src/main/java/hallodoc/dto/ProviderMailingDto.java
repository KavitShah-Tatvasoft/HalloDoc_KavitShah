package hallodoc.dto;

public class ProviderMailingDto {
	
	private Integer id;
	private String address1;
	private String address2;
	private String city;
	private String zip;
	private String phone;
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ProviderMailingDto [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", state=" + state + "]";
	}
	public ProviderMailingDto(Integer id, String address1, String address2, String city, String zip, String phone,
			String state) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.state = state;
	}
	public ProviderMailingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
