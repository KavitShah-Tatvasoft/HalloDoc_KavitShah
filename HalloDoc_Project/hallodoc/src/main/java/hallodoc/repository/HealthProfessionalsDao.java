package hallodoc.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hallodoc.dto.OrderVendorDetailsDto;
import hallodoc.dto.VendorDetailsDto;
import hallodoc.model.Concierge;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestType;

@Repository
public class HealthProfessionalsDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<HealthProfessionalTypes> getProfessions(){
		
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM HealthProfessionalTypes hpt WHERE hpt.isActive=true AND hpt.isDeleted=false";
		Query q = s.createQuery(queryString);
		List<HealthProfessionalTypes> list = q.list();
		s.close();
		return list;
	}
	
	public List<VendorDetailsDto> getVendors(int professionTypeId) {
		Session s = this.sessionFactory.openSession();
		String hql = "select new hallodoc.dto.VendorDetailsDto(hp.vendorId, hp.vendorName) from HealthProfessionals hp WHERE hp.isDeleted=false AND hp.healthProfessionalTypes.healthProfessionalId =:professionTypeId ";
		Query q = s.createQuery(hql);
		q.setParameter("professionTypeId", professionTypeId);
		List<VendorDetailsDto> list = q.list();
		s.close();
		return list;
	}
	
	public OrderVendorDetailsDto getOrderVendorDetails(int vendorId) {
		Session s = this.sessionFactory.openSession();
		String hql = "select new hallodoc.dto.OrderVendorDetailsDto(hp.businessContact, hp.email, hp.faxNumber) from HealthProfessionals hp WHERE hp.vendorId =:vendorId";
		Query q = s.createQuery(hql);
		q.setParameter("vendorId", vendorId);
		List<OrderVendorDetailsDto> list = q.list();
		s.close();
		return list.get(0);

	}
	
	public HealthProfessionals getProfessionalOb(int id) {
		return this.hibernateTemplate.get(HealthProfessionals.class, id);
	}
	
	public HealthProfessionalTypes getProfessionTypeById(int id) {
		return this.hibernateTemplate.get(HealthProfessionalTypes.class, id);
	}
	
	public List<HealthProfessionals> getHealthProfessionalDetails(String name, Integer typeId){
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<HealthProfessionals> cr = cb.createQuery(HealthProfessionals.class);
		Root<HealthProfessionals> root = cr.from(HealthProfessionals.class);

		Join<HealthProfessionals, HealthProfessionalTypes> typeJoin = root.join("healthProfessionalTypes");
		

		Predicate[] predicates = new Predicate[3];

		if (!name.equals("")) {
			predicates[0] = cb.like(root.get("vendorName"), name + "%");
		} else {
			predicates[0] = cb.like(root.get("vendorName"), "%");
		}


		if(typeId == 0) {
			predicates[1] = cb.gt(typeJoin.get("healthProfessionalId"), 0);
		}else {
			predicates[1] = cb.equal(typeJoin.get("healthProfessionalId"), typeId);
		}
		
		predicates[2] = cb.equal(root.get("isDeleted"), false);

		cr.select(root).where(predicates);

		Query<HealthProfessionals> query = s.createQuery(cr);

		List<HealthProfessionals> list = query.getResultList();
		s.close();
		return list;
	}
	
	
	@Transactional
	public int saveHealthProfessional(HealthProfessionals healthProfessionals) {
		return (Integer)this.hibernateTemplate.save(healthProfessionals);
	}
	
	public HealthProfessionals getHealthProfessionalById(Integer businessId) {
		return this.hibernateTemplate.get(HealthProfessionals.class, businessId);
	}
	
	@Transactional
	public void updateHealthProfessionals(HealthProfessionals healthProfessionals) {
		this.hibernateTemplate.update(healthProfessionals);
	}

}
