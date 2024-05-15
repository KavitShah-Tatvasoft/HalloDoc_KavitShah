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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.CreateShiftDto;
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
	
	public List<ShiftDetails> getAllActiveShifts(){
		Session s = this.sessionFactory.openSession();
		String query = "FROM ShiftDetails sd WHERE sd.isDeleted=false";
		Query hql = s.createQuery(query);
		List<ShiftDetails> list = hql.list();
		s.close();
		return list;
	}
}
