package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="case_tag")
public class CaseTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="case_tag_id")
	private int caseTagId;
	
	private String name;

	public int getCaseTagId() {
		return caseTagId;
	}

	public void setCaseTagId(int caseTagId) {
		this.caseTagId = caseTagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CaseTag [caseTagId=" + caseTagId + ", name=" + name + "]";
	}

	public CaseTag(int caseTagId, String name) {
		super();
		this.caseTagId = caseTagId;
		this.name = name;
	}

	public CaseTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
