package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.RequestType;

@Repository
public class RequestTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

	public RequestType getRequestTypeObject(String type) {
		Session s = this.sessionFactory.openSession();
		String formattedType = type.substring(0, 1).toUpperCase() + type.substring(1);
		String queryString = "FROM RequestType where name=:r_type";
		Query q = s.createQuery(queryString);
		q.setParameter("r_type", formattedType);
		List<RequestType> list = q.list();
		s.close();
		return list.get(0);
	}
}
