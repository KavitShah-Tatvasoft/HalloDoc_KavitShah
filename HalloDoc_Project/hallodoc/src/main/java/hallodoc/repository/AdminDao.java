package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import hallodoc.model.Admin;
import hallodoc.model.Menu;

@Repository
public class AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	
	public List<Admin> getAdminData(){
		Session s = this.sessionFactory.openSession();

		String queryString = "FROM Admin admin where admin.isDeleted = false";
		Query q = s.createQuery(queryString);
		List<Admin> list = q.list();
		s.close();
		return list;
	}
		
}
