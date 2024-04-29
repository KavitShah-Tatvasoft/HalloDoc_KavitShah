package hallodoc.repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.EmailLogFiltersDto;
import hallodoc.dto.PatientHistoryDto;
import hallodoc.enumerations.AspNetRolesEnum;
import hallodoc.model.AspNetRoles;
import hallodoc.model.SmsLog;
import hallodoc.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addNewPatientRequest(User user) {
		int id = (Integer) this.hibernateTemplate.save(user);
		return id;
	}
	
	public List<User> getUserByEmail(String userEmail) {

		Session s = this.sessionFactory.openSession();
		String queryString = "FROM User where email=:userEmail";
		Query q = s.createQuery(queryString);
		q.setParameter("userEmail", userEmail);
		List<User> list = q.list();
		s.close();

		return list;
	}
	
	@Transactional
	public void updateUser(User users) {
		this.hibernateTemplate.update(users);
	}
	
	public List<User> getUserDetails(PatientHistoryDto patientHistoryDto){
		
			Session s = this.sessionFactory.openSession();
			
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cr = cb.createQuery(User.class);
			Root<User> root = cr.from(User.class);
			
			Join<User, AspNetRoles> roleJoin = root.join("aspNetRoles");

			Predicate[] predicates = new Predicate[5];

			
			predicates[0] = cb.equal(roleJoin.get("id"), AspNetRolesEnum.PATIENT.getAspNetRolesId());
			
			if (!patientHistoryDto.getFirstName().equals("")) {
				predicates[1] = cb.like(root.get("firstName"), patientHistoryDto.getFirstName() + "%");
			} else {
				predicates[1] = cb.like(root.get("firstName"), "%");
			}
			
			if (!patientHistoryDto.getLastName().equals("")) {
				predicates[2] = cb.like(root.get("lastName"), patientHistoryDto.getLastName() + "%");
			} else {
				predicates[2] = cb.like(root.get("lastName"), "%");
			}
			
			if (!patientHistoryDto.getEmail().equals("")) {
				predicates[3] = cb.like(root.get("email"), patientHistoryDto.getEmail() + "%");
			} else {
				predicates[3] = cb.like(root.get("email"), "%");
			}

			if (!patientHistoryDto.getPhoneNumber().equals("")) {
				predicates[4] = cb.like(root.get("mobile"), patientHistoryDto.getPhoneNumber() + "%");
			} else {
				predicates[4] = cb.like(root.get("mobile"), "%");
			}


			cr.select(root).where(predicates);

			Query<User> query = s.createQuery(cr);

			List<User> list = query.getResultList();
			s.close();
			return list;

		}



}
