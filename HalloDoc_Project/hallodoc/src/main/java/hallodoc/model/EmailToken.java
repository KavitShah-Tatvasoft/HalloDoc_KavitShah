package hallodoc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_token")
public class EmailToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private int tokenId;
	
	private String token;
	
	private String email;
	
	@Column(name = "is_reset_completed")
	private boolean isResetCompleted;
	
	@Column(name="sent_date")
	private LocalDateTime sentDate;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

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

	public boolean isResetCompleted() {
		return isResetCompleted;
	}

	public void setResetCompleted(boolean isResetCompleted) {
		this.isResetCompleted = isResetCompleted;
	}

	public LocalDateTime getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDateTime sentDate) {
		this.sentDate = sentDate;
	}

	@Override
	public String toString() {
		return "EmailToken [tokenId=" + tokenId + ", token=" + token + ", email=" + email + ", isResetCompleted="
				+ isResetCompleted + ", sentDate=" + sentDate + "]";
	}

	public EmailToken(int tokenId, String token, String email, boolean isResetCompleted, LocalDateTime sentDate) {
		super();
		this.tokenId = tokenId;
		this.token = token;
		this.email = email;
		this.isResetCompleted = isResetCompleted;
		this.sentDate = sentDate;
	}

	public EmailToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
