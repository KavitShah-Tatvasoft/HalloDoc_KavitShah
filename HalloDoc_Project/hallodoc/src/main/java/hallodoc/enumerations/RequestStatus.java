package hallodoc.enumerations;

import java.util.HashMap;
import java.util.Map;

public enum RequestStatus {

	UNASSIGNED(1, "Unassigned"),
	ACCEPTED(2, "Accepted"),
	MD_EN_ROUTE(4, "MDEnRoute"),
	MD_ON_SITE(5, "MDONSite"),
	CONCLUDE(6, "Conclude"),
	CANCELLED(3, "Cancelled"),
	CANCELLED_BY_PATIENT(7, "CancelledByPatient"),
	CLOSED(8, "Closed"),
	UNPAID(9, "Unpaid"),
	CLEAR(10, "Clear"),
	BLOCK(11, "Block");

	private int requestId;
	private String requestStatus;

	private static Map<Integer, String> idToTextMapping;
	
	public static String getStatusTypeText(int i) {
		if (idToTextMapping == null) {
			initMapping();
		}
		return idToTextMapping.get(i);
	}

	private static void initMapping() {
		idToTextMapping = new HashMap<>();
		for (RequestStatus rse : values()) {
			idToTextMapping.put(rse.getRequestId(), rse.getRequestStatus());
		}
	}
	
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
