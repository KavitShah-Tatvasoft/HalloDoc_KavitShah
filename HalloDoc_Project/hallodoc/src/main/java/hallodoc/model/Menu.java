package hallodoc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_id")
	private int menuId;
	
	private String name;
	
	@Column(name="account_type")
	private int accountType;
	
	@Column(name="sort_order")
	private int sortOrder;
	
	 @ManyToMany(mappedBy = "menus")
	 private List<Role> roles;
}
