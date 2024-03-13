package hallodoc.dto;

public class CreatePatientDto {
	private String email;
	private String password_hash;
	private String confirmPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public CreatePatientDto(String email, String password_hash, String confirmPassword) {
		super();
		this.email = email;
		this.password_hash = password_hash;
		this.confirmPassword = confirmPassword;
	}
	public CreatePatientDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CreatePatientDto [email=" + email + ", password_hash=" + password_hash + ", confirmPassword="
				+ confirmPassword + "]";
	}
	
	
	
}
