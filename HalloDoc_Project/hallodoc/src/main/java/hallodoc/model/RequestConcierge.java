package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="request_concierge")
public class RequestConcierge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_concierge_id")
	private int requestConciergeId;
	
	@OneToOne
	@JoinColumn(name="request_id")
	private Request request;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "concierge_id")
	private Concierge concierge;

	public int getRequestConciergeId() {
		return requestConciergeId;
	}

	public void setRequestConciergeId(int requestConciergeId) {
		this.requestConciergeId = requestConciergeId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Concierge getConcierge() {
		return concierge;
	}

	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}

	@Override
	public String toString() {
		return "RequestConcierge [requestConciergeId=" + requestConciergeId + ", request=" + request + ", concierge="
				+ concierge + "]";
	}

	public RequestConcierge(int requestConciergeId, Request request, Concierge concierge) {
		super();
		this.requestConciergeId = requestConciergeId;
		this.request = request;
		this.concierge = concierge;
	}

	public RequestConcierge() {
		super();
		// TODO Auto-generated constructor stub
	}

}