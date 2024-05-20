package hallodoc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_id")
	private Integer regionId;

	@Column(name = "name")
	private String name;

	@Column(name = "abbreviation")
	private String abbreviation;

//	@ManyToMany(mappedBy = "regions", fetch = FetchType.EAGER)
//    private List<Admin> admins;

//	@ManyToMany(mappedBy = "regions", fetch = FetchType.EAGER)
//	private List<Physician> physician;

//	public Region(int regionId, String name, String abbreviation, List<Physician> physician) {
//		super();
//		this.regionId = regionId;
//		this.name = name;
//		this.abbreviation = abbreviation;
//		this.physician = physician;
//	}
//
//	@Override
//	public String toString() {
//		return "Region [regionId=" + regionId + ", name=" + name + ", abbreviation=" + abbreviation + ", physician="
//				+ physician + "]";
//	}
	
	

	public int getRegionId() {
		return regionId;
	}

	

	public Region(int regionId, String name, String abbreviation) {
	super();
	this.regionId = regionId;
	this.name = name;
	this.abbreviation = abbreviation;
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
	
	

//	public List<Physician> getPhysician() {
//		return physician;
//	}
//
//	public void setPhysician(List<Physician> physician) {
//		this.physician = physician;
//	}

//	public List<Admin> getAdmins() {
//		return admins;
//	}
//
//	public void setAdmins(List<Admin> admins) {
//		this.admins = admins;
//	}

	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

}
