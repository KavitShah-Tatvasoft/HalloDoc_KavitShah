package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.PhysicianAssignCaseDto;
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
}
