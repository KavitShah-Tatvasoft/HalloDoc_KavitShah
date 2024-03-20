package hallodoc.repository;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.EmailToken;
import hallodoc.model.Region;

@Repository
public class EmailTokenDao {


	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int createNewEmail(EmailToken emailToken) {
		int mailId = (Integer) this.hibernateTemplate.save(emailToken);
		return mailId;
	}
	
	public List<EmailToken> getDuplicateEmailEntry(String email) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM EmailToken where email=:pEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("pEmail",email);
		List<EmailToken> list = q.list();
		s.close();
		return list;

	}
	
	@Transactional
	public String updateOldEmailResetStatus(List<EmailToken> list) {
		for (EmailToken emailToken : list) {
			this.hibernateTemplate.update(emailToken);
		}
		
		return "All Email Status Updated";
	}
	
}
