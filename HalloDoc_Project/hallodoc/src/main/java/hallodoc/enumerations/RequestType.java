package hallodoc.enumerations;

public enum RequestType {

	BUSINESS(1, "Business"),
	PATIENT(2, "Patient"),
	FAMILY(3, "Family"),
	CONCIERGE(4, "Concierge");
	
	private String requestType;
	private int id;
	
	RequestType(int id, String requestType) {
		this.requestType = requestType;
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
