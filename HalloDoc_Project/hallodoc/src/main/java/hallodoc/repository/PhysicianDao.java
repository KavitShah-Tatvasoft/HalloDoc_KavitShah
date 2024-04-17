package hallodoc.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.Physician;

@Repository
public class PhysicianDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public Physician getPhysicianById(int id) {
		Physician physician = this.hibernateTemplate.get(Physician.class, id);
		return physician;
	}
}
