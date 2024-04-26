package hallodoc.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hallodoc.model.PhysicianNotification;

@Repository
public class PhysicianNotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int savePhysicianNotificationOb(PhysicianNotification physicianNotification) {
		return (Integer)this.hibernateTemplate.save(physicianNotification);
	}
}
