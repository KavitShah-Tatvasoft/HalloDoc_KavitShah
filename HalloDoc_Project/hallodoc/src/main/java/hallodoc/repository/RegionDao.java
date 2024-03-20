package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.Region;

@Repository
public class RegionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;


	public List<Region> getRegionEntry(String region) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Region where name=:pRegion";
		Query q = s.createQuery(queryString);
		q.setParameter("pRegion", region);
		List<Region> list = q.list();
		s.close();
		return list;

	}

}
