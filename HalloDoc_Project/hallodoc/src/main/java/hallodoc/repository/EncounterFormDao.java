package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.EncounterForm;
import hallodoc.model.HealthProfessionalTypes;

@Repository
public class EncounterFormDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<EncounterForm> getEncounterFormByReqID(int reqId) {
		
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM EncounterForm ef WHERE ef.request.requestId =:reqId";
		Query q = s.createQuery(queryString);
		q.setParameter("reqId", reqId);
		List<EncounterForm> list = q.list();
		s.close();
		return list;
	}
	
	@Transactional
	public void updateEncounterForm(EncounterForm encounterForm) {
		this.hibernateTemplate.update(encounterForm);
	}
	
	@Transactional
	public int saveEncounterForm(EncounterForm encounterForm) {
		int id = (Integer)this.hibernateTemplate.save(encounterForm);
		return id;
	}
}
