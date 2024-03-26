package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.Business;
import hallodoc.model.Concierge;
import hallodoc.model.RequestBusiness;
import hallodoc.model.RequestConcierge;

@Repository
public class BusinessDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<Business> getExistingBusinessByEmail(String businessEmail){
		
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Business where email=:businessEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("businessEmail", businessEmail);
		List<Business> list = q.list();
		return list;
	}
	

	@Transactional
	public int addBusiness(Business business) {
		int id = (Integer) this.hibernateTemplate.save(business);
		return id;
	}
	
	@Transactional
	public String updateBusiness(Business business) {
		this.hibernateTemplate.update(business);
		return "updated business in dao";
	}
	
	@Transactional
	public int addRequestBusiness(RequestBusiness requestBusiness) {
		int id = (Integer) this.hibernateTemplate.save(requestBusiness);
		return id;
	}
}
