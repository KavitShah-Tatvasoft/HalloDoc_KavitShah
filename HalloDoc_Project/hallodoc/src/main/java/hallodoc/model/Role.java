package hallodoc.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private int roleId;
	
	private String name;
	
	@Column(name="account_type")
	private int accountType;
	
//	@Column(name="created_by")
//	private int createdBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="created_by")
	private AspNetUsers aspNetUsers;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
//	@Column(name="modified_by")
//	private int modifiedBy;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="modified_by")
	private AspNetUsers aspNetUsers1;
	
	@Column(name="modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "role_menu", 
        joinColumns = { @JoinColumn(name = "role_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "menu_id") }
    )
    List<Menu> menus;
	
	
}
