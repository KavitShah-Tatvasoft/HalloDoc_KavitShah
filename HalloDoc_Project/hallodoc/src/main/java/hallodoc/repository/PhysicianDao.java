package hallodoc.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hallodoc.dto.PhysicianAssignCaseDto;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;
import hallodoc.model.Physician;
import hallodoc.model.Request;

@Repository
public class PhysicianDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public Physician getPhysicianById(int id) {
		Physician physician = this.hibernateTemplate.get(Physician.class, id);
		return physician;
	}
	
	public List<PhysicianAssignCaseDto> getPhysicianByRegion(int regionId){
		
		Session s = this.sessionFactory.openSession();
		String query = "SELECT phy.physician_id, phy.first_name, phy.last_name FROM physician phy INNER JOIN physician_region phr ON phy.physician_id = phr.physician_id WHERE phr.region_id =:regionId";
		Query sql = s.createNativeQuery(query);
		sql.setParameter("regionId", regionId);
		List<PhysicianAssignCaseDto> phyList = sql.list();
		s.close();
		return phyList;
		
	}
	
	public List<Integer> getPhysicianObByRegion(int regionId){
		
		Session s = this.sessionFactory.openSession();
//		String query = "SELECT * FROM physician phy LEFT JOIN physician_region phr ON phy.physician_id = phr.physician_id WHERE phr.region_id =:regionId AND phy.is_deleted = false";
		String query = "SELECT phy.physician_id FROM physician phy LEFT JOIN physician_region phr ON phy.physician_id = phr.physician_id WHERE phr.region_id =:regionId AND phy.is_deleted = false";
		Query sql = s.createNativeQuery(query);
		sql.setParameter("regionId", regionId);
		List<Integer> phyList = sql.list();
		s.close();
		return phyList;
		
	}
	
	public List<Physician> getAllActivePhysician(){
		
		Session s = this.sessionFactory.openSession();
		String query = "FROM Physician phy WHERE phy.isDeleted = false";
		Query sql = s.createQuery(query);
		List<Physician> phyList = sql.list();
		s.close();
		return phyList;
		
	}
	
	public List<Physician> getPhysicianByEmail(String email){
		
		Session s = this.sessionFactory.openSession();
		String query = "FROM Physician phy WHERE phy.email =:email";
		Query sql = s.createQuery(query);
		sql.setParameter("email", email);
		List<Physician> phyList = sql.list();
		s.close();
		return phyList;
		
	}
	
	@Transactional
	public void updatePhysician(Physician physician) {
		this.hibernateTemplate.update(physician);
	}
	
	@Transactional
	public void deleteProviderAccount(Integer phyId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String query = "UPDATE Physician phy SET phy.isDeleted = true WHERE phy.physicianId =:phyId";
		Query sql = s.createQuery(query);
		sql.setParameter("phyId", phyId);
		sql.executeUpdate();
		tx.commit();
		s.close();
	}
	
	public List<Physician> getPhysicianByRegionList(List<Integer> list){
		
		Session s = this.sessionFactory.openSession();
		Query query = s.createQuery("FROM Physician phy WHERE phy.physicianId IN (:list)");
		query.setParameter("list", list);
		List<Physician> listPhysician = query.list();
		s.close();
		return listPhysician;
	}
}
