package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.AspNetUsers;



@Repository
public class AspNetUserDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate; 
	
	
	public List<AspNetUsers> getUserByUsername(String u_username) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session s = factory.openSession();
		String queryString = "FROM AspNetUsers where user_name=:user_username";
		Query q = s.createQuery(queryString);
		q.setParameter("user_username", u_username);
		List<AspNetUsers> list = q.list();
		return list;
	}
	
	@Transactional
	public int createPatient(AspNetUsers user) {
		int id = (Integer)this.hibernateTemplate.save(user);
		return id;
	}
}
