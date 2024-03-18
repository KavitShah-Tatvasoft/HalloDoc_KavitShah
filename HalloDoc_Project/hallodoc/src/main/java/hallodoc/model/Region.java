package hallodoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class Region {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_id")
	private int regionId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "abbreviation")
	private String abbreviation;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Region(int regionId, String name, String abbreviation) {
		super();
		this.regionId = regionId;
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
