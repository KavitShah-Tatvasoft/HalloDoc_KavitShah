package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addNewPatientRequest(User user) {
		int id = (Integer) this.hibernateTemplate.save(user);
		return id;
	}
	
	public List<User> getUserByEmail(String userEmail) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM User where email=:userEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("userEmail", userEmail);
		List<User> list = q.list();
		return list;
	}
	
	@Transactional
	public void updateUser(User users) {
		this.hibernateTemplate.update(users);
	}


}
