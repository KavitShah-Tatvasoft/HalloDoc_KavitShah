package hallodoc.dto;

import java.time.LocalDate;

public class UserProfileDto {
	
	private String userFirstName;
	private String userLastName;
	private String userDOB;
	private String userMobile;
	private String userStreet;
	private String userCity;
	private String userState;
	private String userZipCode;
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserStreet() {
		return userStreet;
	}
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserZipCode() {
		return userZipCode;
	}
	public void setUserZipCode(String userZipCode) {
		this.userZipCode = userZipCode;
	}
	@Override
	public String toString() {
		return "UserProfileDto [userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userDOB="
				+ userDOB + ", userMobile=" + userMobile + ", userStreet=" + userStreet + ", userCity=" + userCity
				+ ", userState=" + userState + ", userZipCode=" + userZipCode + "]";
	}
	public UserProfileDto(String userFirstName, String userLastName, String userDOB, String userMobile,
			String userStreet, String userCity, String userState, String userZipCode) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDOB = userDOB;
		this.userMobile = userMobile;
		this.userStreet = userStreet;
		this.userCity = userCity;
		this.userState = userState;
		this.userZipCode = userZipCode;
	}
	public UserProfileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
