package hallodoc.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.CreateShiftDto;
import hallodoc.dto.ProviderOnCallStatusDto;
import hallodoc.model.Physician;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.RequestType;
import hallodoc.model.Shift;
import hallodoc.model.ShiftDetails;
import hallodoc.model.SmsLog;

@Repository
public class ShiftDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addNewShift(Shift shift) {
		int shiftId = (Integer) this.hibernateTemplate.save(shift);
		return shiftId;
	}

	public List<ShiftDetails> getPhysicianAvailbility(List<LocalDate> shiftDates, Physician physician) {

		Session s = this.sessionFactory.openSession();
		String query = "FROM ShiftDetails sd WHERE sd.shiftId.physicianId.physicianId =: phyId AND sd.isDeleted=false AND sd.shiftDate IN (:shiftDates)";
		Query hql = s.createQuery(query);
		hql.setParameter("phyId", physician.getPhysicianId());
		hql.setParameter("shiftDates", shiftDates);
		List<ShiftDetails> list = hql.list();
		s.close();
		return list;

	}

	public List<ShiftDetails> getAllActivePhysicianShifts(int regionId, int physicianId) {
		Session s = this.sessionFactory.openSession();
		Query hql;
		if (regionId == 0) {
			String query = "FROM ShiftDetails sd WHERE sd.isDeleted=false AND sd.shiftId.physicianId.physicianId =:phyId";
			hql = s.createQuery(query);
		} else {
			String query = "FROM ShiftDetails sd WHERE sd.isDeleted=false AND sd.regionId =: regionId AND sd.shiftId.physicianId.physicianId =:phyId";
			hql = s.createQuery(query);
			hql.setParameter("regionId", regionId);
		}
		hql.setParameter("phyId", physicianId);
		List<ShiftDetails> list = hql.list();
		s.close();
		return list;
	}

	public List<ShiftDetails> getAllActiveShifts(int regionId) {
		Session s = this.sessionFactory.openSession();
		Query hql;
		if (regionId == 0) {
			String query = "FROM ShiftDetails sd WHERE sd.isDeleted=false";
			hql = s.createQuery(query);
		} else {
			String query = "FROM ShiftDetails sd WHERE sd.isDeleted=false AND sd.regionId =: regionId";
			hql = s.createQuery(query);
			hql.setParameter("regionId", regionId);
		}
		List<ShiftDetails> list = hql.list();
		s.close();
		return list;
	}

	public ShiftDetails getEventById(int eventId) {
		return this.hibernateTemplate.get(ShiftDetails.class, eventId);
	}

	@Transactional
	public void updateShiftDetails(ShiftDetails shiftDetails) {
		this.hibernateTemplate.update(shiftDetails);
	}

	@Transactional
	public String toggleShiftStatus(int shiftDetailId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE ShiftDetails sd SET sd.status = CASE sd.status WHEN 0 THEN 1 ELSE 0 END WHERE sd.shiftDetailId =: id";
		Query q = s.createQuery(queryString);
		q.setParameter("id", shiftDetailId);
		q.executeUpdate();
		tx.commit();
		s.close();
		return "Toggled Status";
	}

	@Transactional
	public String approveShifts(List<Integer> shiftDetailIds) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE ShiftDetails sd SET sd.status = 1 WHERE sd.shiftDetailId IN (:shiftDetailIds)";
		Query q = s.createQuery(queryString);
		q.setParameter("shiftDetailIds", shiftDetailIds);
		q.executeUpdate();
		tx.commit();
		s.close();
		return "Toggled Status";
	}

	@Transactional
	public String deleteShift(int shiftDetailId) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE ShiftDetails sd SET sd.isDeleted=true WHERE sd.shiftDetailId =: id";
		Query q = s.createQuery(queryString);
		q.setParameter("id", shiftDetailId);
		q.executeUpdate();
		tx.commit();
		s.close();
		return "Shift Deleted";
	}

	@Transactional
	public String deleteShifts(List<Integer> shiftDetailIds) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String queryString = "UPDATE ShiftDetails sd SET sd.isDeleted=true WHERE sd.shiftDetailId IN (:shiftDetailIds)";
		Query q = s.createQuery(queryString);
		q.setParameter("shiftDetailIds", shiftDetailIds);
		q.executeUpdate();
		tx.commit();
		s.close();
		return "Selected Shifts Deleted";
	}

//	public List<ProviderOnCallStatusDto> getProviderOnCallStatus(){
//		Session s = this.sessionFactory.openSession();
//		String queryString = "";
//		"select new hallodoc.dto.ProviderOnCallStatusDto(re.requestId,physician.physicianId,physician.firstName,physician.lastName,re.createdDate,re.status,ad.adminId,ad.firstName,ad.lastName,count(ref.request.requestId)) from Physician phy LEFT JOIN ShiftDetails sd ON phy.physicianId = sd.shiftId.physicianId.physicianId WHERE 2 BETWEEN 1 AND 3" 
//		Query q = s.createQuery(queryString);
//		List<ProviderOnCallStatusDto> providerOnCallStatusDtos = q.list();
//		s.close();
//		return providerOnCallStatusDtos;
//	}

	public List<ShiftDetails> getFilteredShiftReviewDetails(int regionId, int pageNo) {

		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<ShiftDetails> cr = cb.createQuery(ShiftDetails.class);
		Root<ShiftDetails> root = cr.from(ShiftDetails.class);

		Predicate[] predicates = new Predicate[2];

		if (regionId == 0) {
			predicates[0] = cb.equal(root.get("isDeleted"), false);
		} else {
			predicates[0] = cb.equal(root.get("regionId"), regionId);
		}

		predicates[1] = cb.equal(root.get("status"), 0);

		cr.select(root).where(predicates);

		Query<ShiftDetails> query = s.createQuery(cr);
		if (pageNo != 0) {
			query.setFirstResult((pageNo - 1) * 5);
		} else {
			query.setFirstResult(0);
		}
		query.setMaxResults(10);
		List<ShiftDetails> list = query.getResultList();
		s.close();
		return list;

	}

	public Long getFilteredShiftReviewDetailsCount(int regionId, int pageNo) {

		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<ShiftDetails> root = cr.from(ShiftDetails.class);

		Predicate[] predicates = new Predicate[2];

		if (regionId == 0) {
			predicates[0] = cb.equal(root.get("isDeleted"), false);
		} else {
			predicates[0] = cb.equal(root.get("regionId"), regionId);
		}

		predicates[1] = cb.equal(root.get("status"), 0);

		cr.select(cb.count(root)).where(predicates);
		Long total = s.createQuery(cr).getSingleResult();
		s.close();
		return total;

	}

	public List<ShiftDetails> getOnGoingShifts(LocalTime time, int regionId) {

		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<ShiftDetails> cr = cb.createQuery(ShiftDetails.class);
		Root<ShiftDetails> root = cr.from(ShiftDetails.class);

		Predicate[] predicates = new Predicate[3];

		predicates[0] = cb.equal(root.get("isDeleted"), false);

		if (regionId == 0) {
			predicates[1] = cb.equal(root.get("isDeleted"), false);
		} else {
			predicates[1] = cb.equal(root.get("regionId"), regionId);
		}

		Predicate startTimePredicate = cb.greaterThanOrEqualTo(root.<LocalTime>get("startTime"), time);
		Predicate endTimePredicate = cb.lessThanOrEqualTo(root.<LocalTime>get("endTime"), time);

		Predicate isWithinTimeRange = cb.and(startTimePredicate, endTimePredicate);

		predicates[2] = cb.and(startTimePredicate, endTimePredicate);
		cr.select(root).where(predicates);
		
		Query<ShiftDetails> query = s.createQuery(cr);
		
		List<ShiftDetails> shiftDetails = query.getResultList();
		s.close();
		return shiftDetails;

	}
}
