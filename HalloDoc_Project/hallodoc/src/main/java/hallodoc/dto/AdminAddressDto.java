package hallodoc.dto;

public class AdminAddressDto {
	
	private String address1;
	private String address2;
	private String city;
	private Integer regionId;
	private String zipcode;
	private String phone;
	
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
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "AdminAddressDto [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", regionId="
				+ regionId + ", zipcode=" + zipcode + ", phone=" + phone + "]";
	}
	public AdminAddressDto(String address1, String address2, String city, Integer regionId, String zipcode,
			String phone) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.regionId = regionId;
		this.zipcode = zipcode;
		this.phone = phone;
	}
	public AdminAddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
