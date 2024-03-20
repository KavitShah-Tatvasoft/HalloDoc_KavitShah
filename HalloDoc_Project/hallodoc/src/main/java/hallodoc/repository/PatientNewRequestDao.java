package hallodoc.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.AspNetRoles;
import hallodoc.model.AspNetUsers;
import hallodoc.model.Region;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestStatusLog;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;

@Repository
public class PatientNewRequestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

//	public RequestType getRequestTypeObject(String type) {
//		Session s = this.sessionFactory.openSession();
//		String formattedType = type.substring(0, 1).toUpperCase() + type.substring(1);
//		String queryString = "FROM RequestType where name=:r_type";
//		Query q = s.createQuery(queryString);
//		q.setParameter("r_type", formattedType);
//		List<RequestType> list = q.list();
//		s.close();
//		return list.get(0);
//	}

	
	
	
	
	
}
