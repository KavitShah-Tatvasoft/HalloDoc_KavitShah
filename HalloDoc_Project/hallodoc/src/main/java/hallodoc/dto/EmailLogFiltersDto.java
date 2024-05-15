package hallodoc.dto;

public class EmailLogFiltersDto {

	private Integer roleId;
	private String reciever;
	private String email;
	private String createdDate;
	private String sentDate;
	private int pageNo;
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	
	public EmailLogFiltersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailLogFiltersDto(Integer roleId, String reciever, String email, String createdDate, String sentDate,
			int pageNo) {
		super();
		this.roleId = roleId;
		this.reciever = reciever;
		this.email = email;
		this.createdDate = createdDate;
		this.sentDate = sentDate;
		this.pageNo = pageNo;
	}
	
	
	
	
	
	
}
