package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.CaseTag;
import hallodoc.model.Concierge;

@Repository
public class CaseTagDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public List<CaseTag> getCancellationReasons(){
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM CaseTag";
		Query q = s.createQuery(queryString);
		List<CaseTag> list = q.list();
		s.close();
		return list;
	}
}
