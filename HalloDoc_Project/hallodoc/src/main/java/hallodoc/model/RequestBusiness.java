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
@Table(name="request_business")
public class RequestBusiness {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_business_id")
	private int requestBusinessId;
	
	@OneToOne
	@JoinColumn(name="request_id")
	private Request request;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_id")
	private Business business;

	public int getRequestBusinessId() {
		return requestBusinessId;
	}

	public void setRequestBusinessId(int requestBusinessId) {
		this.requestBusinessId = requestBusinessId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public RequestBusiness(int requestBusinessId, Request request, Business business) {
		super();
		this.requestBusinessId = requestBusinessId;
		this.request = request;
		this.business = business;
	}

	@Override
	public String toString() {
		return "RequestBusiness [requestBusinessId=" + requestBusinessId + ", request=" + request + ", business="
				+ business + "]";
	}

	public RequestBusiness() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
