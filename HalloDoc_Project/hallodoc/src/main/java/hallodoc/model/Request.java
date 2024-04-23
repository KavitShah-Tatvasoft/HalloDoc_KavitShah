package hallodoc.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_id")
	private Integer requestId;

//	@Column(name = "request_type_id")
//	private int requestTypeId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "request_type_id")
	private RequestType requestType;

//	@Column(name = "user_id")
//	private String userId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String email;

	private int status;

//	@Column(name = "physician_id")
//	private int physicianId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "physician_id")
	private Physician physician;

	@Column(name = "confirmation_number")
	private String confirmationNumber;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "modified_date")
	private Date modifieDate;

//	@Column(name = "declined_by")
//	private int declinedBy;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "declined_by")
	private AspNetUsers aspUsers;

	@Column(name = "last_wellness_date")
	private Date lastWellnessDate;

	@Column(name = "call_type")
	private int callType;

	@Column(name = "completed_by_physician")
	private boolean completedByPhysician;

	@Column(name = "last_reservation_date")
	private Date lastReservationDate;

	@Column(name = "accepted_date")
	private Date acceptedDate;

	@Column(name = "relation_name")
	private String relationName;

	@Column(name = "case_number")
	private String caseNumber;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "request")
	private RequestClient requestClient;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "request")
	private List<RequestWiseFile> listRequestWiseFiles;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "request")
	private RequestNotes requestNotes;

	@Column(name = "case_tag")
	private Integer caseTag;

	@Column(name = "case_tag_physician")
	private Integer caseTagPhysician;

	public Request(Integer requestId, RequestType requestType, User user, String firstName, String lastName,
			String phoneNumber, String email, int status, Physician physician, String confirmationNumber,
			Date createdDate, boolean isDeleted, Date modifieDate, AspNetUsers aspUsers, Date lastWellnessDate,
			int callType, boolean completedByPhysician, Date lastReservationDate, Date acceptedDate,
			String relationName, String caseNumber, RequestClient requestClient,
			List<RequestWiseFile> listRequestWiseFiles, RequestNotes requestNotes, Integer caseTag,
			Integer caseTagPhysician) {
		super();
		this.requestId = requestId;
		this.requestType = requestType;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.status = status;
		this.physician = physician;
		this.confirmationNumber = confirmationNumber;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
		this.modifieDate = modifieDate;
		this.aspUsers = aspUsers;
		this.lastWellnessDate = lastWellnessDate;
		this.callType = callType;
		this.completedByPhysician = completedByPhysician;
		this.lastReservationDate = lastReservationDate;
		this.acceptedDate = acceptedDate;
		this.relationName = relationName;
		this.caseNumber = caseNumber;
		this.requestClient = requestClient;
		this.listRequestWiseFiles = listRequestWiseFiles;
		this.requestNotes = requestNotes;
		this.caseTag = caseTag;
		this.caseTagPhysician = caseTagPhysician;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", requestType=" + requestType + ", user=" + user + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", status=" + status + ", physician=" + physician + ", confirmationNumber=" + confirmationNumber
				+ ", createdDate=" + createdDate + ", isDeleted=" + isDeleted + ", modifieDate=" + modifieDate
				+ ", aspUsers=" + aspUsers + ", lastWellnessDate=" + lastWellnessDate + ", callType=" + callType
				+ ", completedByPhysician=" + completedByPhysician + ", lastReservationDate=" + lastReservationDate
				+ ", acceptedDate=" + acceptedDate + ", relationName=" + relationName + ", caseNumber=" + caseNumber
				+ ", requestClient=" + requestClient + ", listRequestWiseFiles=" + listRequestWiseFiles
				+ ", requestNotes=" + requestNotes + ", caseTag=" + caseTag + ", caseTagPhysician=" + caseTagPhysician
				+ "]";
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getModifieDate() {
		return modifieDate;
	}

	public void setModifieDate(Date modifieDate) {
		this.modifieDate = modifieDate;
	}

	public AspNetUsers getAspUsers() {
		return aspUsers;
	}

	public void setAspUsers(AspNetUsers aspUsers) {
		this.aspUsers = aspUsers;
	}

	public Date getLastWellnessDate() {
		return lastWellnessDate;
	}

	public void setLastWellnessDate(Date lastWellnessDate) {
		this.lastWellnessDate = lastWellnessDate;
	}

	public int getCallType() {
		return callType;
	}

	public void setCallType(int callType) {
		this.callType = callType;
	}

	public boolean isCompletedByPhysician() {
		return completedByPhysician;
	}

	public void setCompletedByPhysician(boolean completedByPhysician) {
		this.completedByPhysician = completedByPhysician;
	}

	public Date getLastReservationDate() {
		return lastReservationDate;
	}

	public void setLastReservationDate(Date lastReservationDate) {
		this.lastReservationDate = lastReservationDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public RequestClient getRequestClient() {
		return requestClient;
	}

	public void setRequestClient(RequestClient requestClient) {
		this.requestClient = requestClient;
	}

	public List<RequestWiseFile> getListRequestWiseFiles() {
		return listRequestWiseFiles;
	}

	public void setListRequestWiseFiles(List<RequestWiseFile> listRequestWiseFiles) {
		this.listRequestWiseFiles = listRequestWiseFiles;
	}

	public RequestNotes getRequestNotes() {
		return requestNotes;
	}

	public void setRequestNotes(RequestNotes requestNotes) {
		this.requestNotes = requestNotes;
	}

	public Integer getCaseTag() {
		return caseTag;
	}

	public void setCaseTag(Integer caseTag) {
		this.caseTag = caseTag;
	}

	public Integer getCaseTagPhysician() {
		return caseTagPhysician;
	}

	public void setCaseTagPhysician(Integer caseTagPhysician) {
		this.caseTagPhysician = caseTagPhysician;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LocalDate getDateOfServiceObject() {
	return	acceptedDate.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}

}
