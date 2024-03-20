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
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public List<AspNetUsers> getUserByUsername(String u_username) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM AspNetUsers where user_name=:user_username";
		Query q = s.createQuery(queryString);
		q.setParameter("user_username", u_username);
		List<AspNetUsers> list = q.list();
		return list;
	}

	public List<AspNetUsers> getUserByEmail(String u_email) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM AspNetUsers where email=:user_email";
		Query q = s.createQuery(queryString);
		q.setParameter("user_email", u_email);
		List<AspNetUsers> list = q.list();
		return list;
	}

	public String isUserPresent(String email) {
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM AspNetUsers where email=:user_email";
		Query q = s.createQuery(queryString);
		q.setParameter("user_email", email);
		List<AspNetUsers> list = q.list();
		if (list.size() > 0) {
			return "success";
		} else {
			return "failure";
		}
	}

	@Transactional
	public int createPatient(AspNetUsers user) {
		int id = (Integer) this.hibernateTemplate.save(user);
		return id;
	}
	

	@Transactional
	public void updateAspNetUser(AspNetUsers aspNetUsers) {
		this.hibernateTemplate.update(aspNetUsers);
	}
}
