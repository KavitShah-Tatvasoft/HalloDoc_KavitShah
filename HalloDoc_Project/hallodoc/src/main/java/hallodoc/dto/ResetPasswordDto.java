package hallodoc.dto;

public class ResetPasswordDto {
	
	private String Email;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "ResetPasswordDto [Email=" + Email + "]";
	}

	public ResetPasswordDto(String email) {
		super();
		Email = email;
	}

	public ResetPasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
