package hallodoc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int admin_id;
	private int aspnet_userid;
	private String first_name;
	private String last_name;
	private String address_one;
	private String address_two;
	private String city;
	private int region_id;
	private String zip;
	private String alt_phone;
	private int created_by;
	private Date created_date;
	private int modified_by;
	private Date modified_date;
	private boolean is_deleted;
	private int role_id;	
}
