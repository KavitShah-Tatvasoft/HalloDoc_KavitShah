package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_type")
public class RequestType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_type_id")
	private String requestTypeId;
	
	@Column(name = "name")
	private String name;

	public String getRequestTypeId() {
		return requestTypeId;
	}

	public void setRequestTypeId(String requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RequestType [requestTypeId=" + requestTypeId + ", name=" + name + "]";
	}

	public RequestType(String requestTypeId, String name) {
		super();
		this.requestTypeId = requestTypeId;
		this.name = name;
	}

	public RequestType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
