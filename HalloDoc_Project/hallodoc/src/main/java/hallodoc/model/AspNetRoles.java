package hallodoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asp_net_roles")
public class AspNetRoles {

	@Id
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return "AspNetRoles [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AspNetRoles(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public AspNetRoles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
