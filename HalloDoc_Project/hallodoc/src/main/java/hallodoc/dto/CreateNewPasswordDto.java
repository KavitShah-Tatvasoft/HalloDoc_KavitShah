package hallodoc.dto;

public class CreateNewPasswordDto {

	private String email;
	private String password;
	private String confPassword;
	private String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	
	
	@Override
	public String toString() {
		return "CreateNewPasswordDto [email=" + email + ", password=" + password + ", confPassword=" + confPassword
				+ ", token=" + token + "]";
	}
	
	
	public CreateNewPasswordDto(String email, String password, String confPassword, String token) {
		super();
		this.email = email;
		this.password = password;
		this.confPassword = confPassword;
		this.token = token;
	}
	
	
	public CreateNewPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
