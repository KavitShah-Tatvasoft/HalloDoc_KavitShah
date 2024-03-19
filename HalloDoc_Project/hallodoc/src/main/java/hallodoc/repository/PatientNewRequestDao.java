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

	@Transactional
	public int addNewPatientRequest(User user) {
		int id = (Integer) this.hibernateTemplate.save(user);
		return id;
	}
	
	@Transactional
	public int addNewRequestClient(RequestClient requestClient) {
		int id = (Integer) this.hibernateTemplate.save(requestClient);
		return id;
	}
	
	@Transactional
	public int addNewRequest(Request request) {
		int id = (Integer) this.hibernateTemplate.save(request);
		return id;
	}
	
	@Transactional
	public int addNewRequestStatusLog(RequestStatusLog requestStatusLog) {
		int id = (Integer) this.hibernateTemplate.save(requestStatusLog);
		return id;
	}
	
	@Transactional
	public int addNewRequestWiseFile(RequestWiseFile requestWiseFile) {
		int id = (Integer) this.hibernateTemplate.save(requestWiseFile);
		return id;
	}

	public List<Region> getRegionEntry(String region) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Region where name=:pRegion";
		Query q = s.createQuery(queryString);
		q.setParameter("pRegion", region);
		List<Region> list = q.list();
		s.close();
		return list;

	}
	
	public List<User> getUserByEmail(String userEmail) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM User where email=:userEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("userEmail", userEmail);
		List<User> list = q.list();
		return list;
	}


	public AspNetRoles getRoleObject(String role) {
		Session s = this.sessionFactory.openSession();

		String formattedRole = role.substring(0, 1).toUpperCase() + role.substring(1);

		String queryString = "FROM AspNetRoles where name=:role";
		Query q = s.createQuery(queryString);
		q.setParameter("role", formattedRole);
		List<AspNetRoles> list = q.list();
		s.close();
		return list.get(0);

	}

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
	
	@Transactional
	public void updateAspNetUser(AspNetUsers aspNetUsers) {
		this.hibernateTemplate.update(aspNetUsers);
	}
	
	@Transactional
	public void updateUser(User users) {
		this.hibernateTemplate.update(users);
	}

}
