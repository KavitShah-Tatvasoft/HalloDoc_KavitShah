package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.OrderVendorDetailsDto;
import hallodoc.dto.VendorDetailsDto;
import hallodoc.model.Concierge;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;

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
}
