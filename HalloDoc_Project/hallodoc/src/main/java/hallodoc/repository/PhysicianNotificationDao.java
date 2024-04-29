package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hallodoc.model.EncounterForm;
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
	
	@Transactional
	public String toggleNotification(Integer id) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE PhysicianNotification pn SET pn.isNotificationStopped = CASE pn.isNotificationStopped WHEN TRUE THEN FALSE ELSE TRUE END WHERE pn.physician.physicianId =: id";
		Query q = s.createQuery(queryString);
		q.setParameter("id", id);
		q.executeUpdate();
		tx.commit();
		s.close();
		return "Toggled Notification Boolean";
	}
}
