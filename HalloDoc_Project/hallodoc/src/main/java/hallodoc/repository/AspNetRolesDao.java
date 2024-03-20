package hallodoc.repository;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.AspNetRoles;

@Repository
public class AspNetRolesDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	

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

}
