package hallodoc.dto;

public class NewRequestDataDto {
	
	private String name;
	private int day;
	private String month;
	private int year;
	private String requestor;
	private String requestedDate;
	private String ptPhoneNumber;
	private String ptPhoneNumberType;
	private String reqPhoneNumber;
	private String reqPhoneNumberType;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String notes;
	private String physicianName;
	private String dateOfService;
	private String region;
	private boolean isDeleted;
	private String ptEmail;
	
	
	public String getPtEmail() {
		return ptEmail;
	}
	public void setPtEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getDateOfService() {
		return dateOfService;
	}
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPtPhoneNumberType() {
		return ptPhoneNumberType;
	}
	public void setPtPhoneNumberType(String ptPhoneNumberType) {
		this.ptPhoneNumberType = ptPhoneNumberType;
	}
	public String getReqPhoneNumberType() {
		return reqPhoneNumberType;
	}
	public void setReqPhoneNumberType(String reqPhoneNumberType) {
		this.reqPhoneNumberType = reqPhoneNumberType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}
	public String getPtPhoneNumber() {
		return ptPhoneNumber;
	}
	public void setPtPhoneNumber(String ptPhoneNumber) {
		this.ptPhoneNumber = ptPhoneNumber;
	}
	public String getReqPhoneNumber() {
		return reqPhoneNumber;
	}
	public void setReqPhoneNumber(String reqPhoneNumber) {
		this.reqPhoneNumber = reqPhoneNumber;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	@Override
	public String toString() {
		return "NewRequestDataDto [name=" + name + ", day=" + day + ", month=" + month + ", year=" + year
				+ ", requestor=" + requestor + ", requestedDate=" + requestedDate + ", ptPhoneNumber=" + ptPhoneNumber
				+ ", ptPhoneNumberType=" + ptPhoneNumberType + ", reqPhoneNumber=" + reqPhoneNumber
				+ ", reqPhoneNumberType=" + reqPhoneNumberType + ", street=" + street + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode + ", notes=" + notes + ", physicianName=" + physicianName
				+ ", dateOfService=" + dateOfService + ", region=" + region + ", isDeleted=" + isDeleted + ", ptEmail="
				+ ptEmail + "]";
	}
	public NewRequestDataDto(String name, int day, String month, int year, String requestor, String requestedDate,
			String ptPhoneNumber, String ptPhoneNumberType, String reqPhoneNumber, String reqPhoneNumberType,
			String street, String city, String state, String zipcode, String notes, String physicianName,
			String dateOfService, String region, boolean isDeleted, String ptEmail) {
		super();
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
		this.requestor = requestor;
		this.requestedDate = requestedDate;
		this.ptPhoneNumber = ptPhoneNumber;
		this.ptPhoneNumberType = ptPhoneNumberType;
		this.reqPhoneNumber = reqPhoneNumber;
		this.reqPhoneNumberType = reqPhoneNumberType;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.notes = notes;
		this.physicianName = physicianName;
		this.dateOfService = dateOfService;
		this.region = region;
		this.isDeleted = isDeleted;
		this.ptEmail = ptEmail;
	}
	public NewRequestDataDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
