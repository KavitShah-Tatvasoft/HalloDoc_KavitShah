package hallodoc.dto;

public class BlockHistoryFilterData {
	
	private String name;
	private String date;
	private String email;
	private String phone;
	private int pageNo;
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "BlockHistoryFilterData [name=" + name + ", date=" + date + ", email=" + email + ", phone=" + phone
				+ "]";
	}
	public BlockHistoryFilterData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlockHistoryFilterData(String name, String date, String email, String phone, int pageNo) {
		super();
		this.name = name;
		this.date = date;
		this.email = email;
		this.phone = phone;
		this.pageNo = pageNo;
	}
	
	
	
}
