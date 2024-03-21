package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.AspNetUsers;
import hallodoc.model.Concierge;
import hallodoc.model.RequestConcierge;

@Repository
public class ConciergeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<Concierge> getExistingConciergeByEmail(String conciergeEmail){
		
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Concierge where email=:conciergeEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("conciergeEmail", conciergeEmail);
		List<Concierge> list = q.list();
		return list;
	}
	
	@Transactional
	public int addConcierge(Concierge concierge) {
		int id = (Integer) this.hibernateTemplate.save(concierge);
		return id;
	}
	
	@Transactional
	public String updateConcierge(Concierge concierge) {
		this.hibernateTemplate.update(concierge);
		return "updated concierge";
	}
	
	@Transactional
	public int addRequestConcierge(RequestConcierge requestConcierge) {
		int id = (Integer) this.hibernateTemplate.save(requestConcierge);
		return id;
	}
}
