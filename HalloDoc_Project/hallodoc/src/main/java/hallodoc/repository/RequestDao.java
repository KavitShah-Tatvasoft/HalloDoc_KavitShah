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

import hallodoc.model.Request;

@Repository
public class RequestDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	

	@Transactional
	public int addNewRequest(Request request) {
		int id = (Integer) this.hibernateTemplate.save(request);
		return id;
	}
	
	public int getNewRequestsNo(Date sdate, Date edate) {
		Session s = this.sessionFactory.openSession();
		
		
		String hql = "from Request as Request where Request.createdDate between :date and :ceilDate"; 
		Query query = s.createQuery(hql); 
		// a date having timestamp part, 00:00:00.0, or missing completely
		query.setParameter("date", sdate); 
		// a date having timestamp part, 23:59:59.999
		query.setParameter("ceilDate", edate);
		
		
		List<Request> result = query.list();
		System.out.println(result.size());
		s.close();
		return result.size();
	}

}
