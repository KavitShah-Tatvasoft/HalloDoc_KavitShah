package hallodoc.enumerations;

public enum RequestStatus {

	UNASSIGNED(1, "Unassigned"),
	ACCEPTED(2, "Accepted"),
	MD_EN_ROUTE(3, "MDEnRoute"),
	MD_ON_SITE(4, "MDONSite"),
	CONCLUDE(5, "Conclude"),
	CANCELLED(6, "Cancelled"),
	CANCELLED_BY_PATIENT(7, "CancelledByPatient"),
	CLOSED(8, "Closed"),
	UNPAID(9, "Unpaid"),
	CLEAR(10, "Clear"),
	BLOCK(11, "Block");

	private int requestId;
	private String requestStatus;

	RequestStatus(int requestId, String requestStatus) {
		this.requestId = requestId;
		this.requestStatus = requestStatus;
	}

	public int getRequestId() {
		return requestId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

}
