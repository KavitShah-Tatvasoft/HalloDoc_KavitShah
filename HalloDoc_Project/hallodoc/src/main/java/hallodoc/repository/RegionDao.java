package hallodoc.repository;

import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<Region> getAllRegions(){
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Region";
		Query q = s.createQuery(queryString);
		List<Region> list = q.list();
		s.close();
		return list;
	}
	
	public List<Region> getRegionById(int regionId){
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Region where regionId=:regionId";
		Query q = s.createQuery(queryString);
		q.setParameter("regionId", regionId);
		List<Region> list = q.list();
		s.close();
		return list;
	}
	
	@Transactional
	public void deleteAdminRegionEntry(int adminId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx =s.beginTransaction();
		String nativeQuery = "DELETE FROM admin_region adr WHERE adr.admin_id=:adminId";
		Query q = s.createNativeQuery(nativeQuery);
		q.setParameter("adminId", adminId);
		q.executeUpdate();
		tx.commit();
		s.close();
	}
	
	@Transactional
	public void deleteProviderRegionEntry(int providerId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx =s.beginTransaction();
		String nativeQuery = "DELETE FROM physician_region phr WHERE phr.physician_id=:providerId";
		Query q = s.createNativeQuery(nativeQuery);
		q.setParameter("providerId", providerId);
		q.executeUpdate();
		tx.commit();
		s.close();
	}
	
	
	
	public List<Integer> getPhysicianRegion(Integer phyId){
		Session s = this.sessionFactory.openSession();
		String queryString = "SELECT phr.region_id FROM physician_region phr WHERE phr.physician_id=:phyId";
		Query q = s.createNativeQuery(queryString);
		q.setParameter("phyId", phyId);
		List<Integer> list = q.list();
		s.close();
		return list;
	}

}
