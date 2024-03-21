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
	@Column(name="request_business_id")
	private int requestBusinessId;
	
	@OneToOne
	@JoinColumn(name="request_id")
	private Request request;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "concierge_id")
	private Concierge concierge;

 
}
