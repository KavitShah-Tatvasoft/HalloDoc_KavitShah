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
import hallodoc.model.EmailLog;
import hallodoc.model.HealthProfessionalTypes;
import hallodoc.model.HealthProfessionals;
import hallodoc.model.SmsLog;

@Repository
public class LogsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addEmailLogEntry(EmailLog emailLog) {
		int emailLogId = (Integer) this.hibernateTemplate.save(emailLog);
		return emailLogId;
	}

	@Transactional
	public int addSmsLogEntry(SmsLog smsLog) {
		int smsLogId = (Integer) this.hibernateTemplate.save(smsLog);
		return smsLogId;
	}

	public List<EmailLog> getFilteredEmailLogData(EmailLogFiltersDto emailLogFiltersDto) {
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<EmailLog> cr = cb.createQuery(EmailLog.class);
		Root<EmailLog> root = cr.from(EmailLog.class);

		Predicate[] predicates = new Predicate[5];

		if (emailLogFiltersDto.getRoleId() == 0) {
			predicates[0] = cb.gt(root.get("roleId"), 0);
		} else {
			predicates[0] = cb.equal(root.get("roleId"), emailLogFiltersDto.getRoleId());
		}

		if (!emailLogFiltersDto.getReciever().equals("")) {
			predicates[1] = cb.like(root.get("recipientName"), emailLogFiltersDto.getReciever() + "%");
		} else {
			predicates[1] = cb.like(root.get("recipientName"), "%");
		}

		if (!emailLogFiltersDto.getEmail().equals("")) {
			predicates[2] = cb.like(root.get("emailId"), emailLogFiltersDto.getEmail() + "%");
		} else {
			predicates[2] = cb.like(root.get("emailId"), "%");
		}

		if (!emailLogFiltersDto.getCreatedDate().equals("")) {
			String createdDate = emailLogFiltersDto.getCreatedDate();
			LocalDate createDateTime = LocalDate.parse(createdDate);
			predicates[3] = cb.equal(cb.function("DATE", Date.class, root.get("createdDate")),
					Date.from(createDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[3] = cb.like(root.get("emailId"), "%");
		}

		if (!emailLogFiltersDto.getSentDate().equals("")) {
			String sentDate = emailLogFiltersDto.getSentDate();
			LocalDate sentDateTime = LocalDate.parse(sentDate);
			predicates[4] = cb.equal(cb.function("DATE", Date.class, root.get("sentDate")),
					Date.from(sentDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[4] = cb.like(root.get("emailId"), "%");
		}

		cr.select(root).where(predicates);

		Query<EmailLog> query = s.createQuery(cr);
		query.setMaxResults(10);
		if (emailLogFiltersDto.getPageNo() != 0) {
			query.setFirstResult((emailLogFiltersDto.getPageNo() - 1) * 10);
		} else {
			query.setFirstResult(0);
		}
		List<EmailLog> list = query.getResultList();
		s.close();
		return list;

	}

	public Long getFilteredEmailLogDataCount(EmailLogFiltersDto emailLogFiltersDto) {
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<EmailLog> root = cr.from(EmailLog.class);

		Predicate[] predicates = new Predicate[5];

		if (emailLogFiltersDto.getRoleId() == 0) {
			predicates[0] = cb.gt(root.get("roleId"), 0);
		} else {
			predicates[0] = cb.equal(root.get("roleId"), emailLogFiltersDto.getRoleId());
		}

		if (!emailLogFiltersDto.getReciever().equals("")) {
			predicates[1] = cb.like(root.get("recipientName"), emailLogFiltersDto.getReciever() + "%");
		} else {
			predicates[1] = cb.like(root.get("recipientName"), "%");
		}

		if (!emailLogFiltersDto.getEmail().equals("")) {
			predicates[2] = cb.like(root.get("emailId"), emailLogFiltersDto.getEmail() + "%");
		} else {
			predicates[2] = cb.like(root.get("emailId"), "%");
		}

		if (!emailLogFiltersDto.getCreatedDate().equals("")) {
			String createdDate = emailLogFiltersDto.getCreatedDate();
			LocalDate createDateTime = LocalDate.parse(createdDate);
			predicates[3] = cb.equal(cb.function("DATE", Date.class, root.get("createdDate")),
					Date.from(createDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[3] = cb.like(root.get("emailId"), "%");
		}

		if (!emailLogFiltersDto.getSentDate().equals("")) {
			String sentDate = emailLogFiltersDto.getSentDate();
			LocalDate sentDateTime = LocalDate.parse(sentDate);
			predicates[4] = cb.equal(cb.function("DATE", Date.class, root.get("sentDate")),
					Date.from(sentDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[4] = cb.like(root.get("emailId"), "%");
		}

		cr.select(cb.count(root)).where(predicates);
		Long total = s.createQuery(cr).getSingleResult();
		s.close();
		return total;

	}

	public List<SmsLog> getFilteredSMSLogData(EmailLogFiltersDto emailLogFiltersDto) {
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<SmsLog> cr = cb.createQuery(SmsLog.class);
		Root<SmsLog> root = cr.from(SmsLog.class);

		Predicate[] predicates = new Predicate[5];

		if (emailLogFiltersDto.getRoleId() == 0) {
			predicates[0] = cb.gt(root.get("roleId"), 0);
		} else {
			predicates[0] = cb.equal(root.get("roleId"), emailLogFiltersDto.getRoleId());
		}

		if (!emailLogFiltersDto.getReciever().equals("")) {
			predicates[1] = cb.like(root.get("recipientName"), emailLogFiltersDto.getReciever() + "%");
		} else {
			predicates[1] = cb.like(root.get("recipientName"), "%");
		}

		if (!emailLogFiltersDto.getEmail().equals("")) {
			predicates[2] = cb.like(root.get("mobileNumber"), emailLogFiltersDto.getEmail() + "%");
		} else {
			predicates[2] = cb.like(root.get("mobileNumber"), "%");
		}

		if (!emailLogFiltersDto.getCreatedDate().equals("")) {
			String createdDate = emailLogFiltersDto.getCreatedDate();
			LocalDate createDateTime = LocalDate.parse(createdDate);
			predicates[3] = cb.equal(cb.function("DATE", Date.class, root.get("createdDate")),
					Date.from(createDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[3] = cb.like(root.get("mobileNumber"), "%");
		}

		if (!emailLogFiltersDto.getSentDate().equals("")) {
			String sentDate = emailLogFiltersDto.getSentDate();
			LocalDate sentDateTime = LocalDate.parse(sentDate);
			predicates[4] = cb.equal(cb.function("DATE", Date.class, root.get("sentDate")),
					Date.from(sentDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[4] = cb.like(root.get("mobileNumber"), "%");
		}

		cr.select(root).where(predicates);

		Query<SmsLog> query = s.createQuery(cr);
		query.setMaxResults(10);
		if (emailLogFiltersDto.getPageNo() != 0) {
			query.setFirstResult((emailLogFiltersDto.getPageNo() - 1) * 10);
		} else {
			query.setFirstResult(0);
		}
		List<SmsLog> list = query.getResultList();
		s.close();
		return list;

	}
	
	public Long getFilteredSmsLogDataCount(EmailLogFiltersDto emailLogFiltersDto) {
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<SmsLog> root = cr.from(SmsLog.class);

		Predicate[] predicates = new Predicate[5];

		if (emailLogFiltersDto.getRoleId() == 0) {
			predicates[0] = cb.gt(root.get("roleId"), 0);
		} else {
			predicates[0] = cb.equal(root.get("roleId"), emailLogFiltersDto.getRoleId());
		}

		if (!emailLogFiltersDto.getReciever().equals("")) {
			predicates[1] = cb.like(root.get("recipientName"), emailLogFiltersDto.getReciever() + "%");
		} else {
			predicates[1] = cb.like(root.get("recipientName"), "%");
		}

		if (!emailLogFiltersDto.getEmail().equals("")) {
			predicates[2] = cb.like(root.get("mobileNumber"), emailLogFiltersDto.getEmail() + "%");
		} else {
			predicates[2] = cb.like(root.get("mobileNumber"), "%");
		}

		if (!emailLogFiltersDto.getCreatedDate().equals("")) {
			String createdDate = emailLogFiltersDto.getCreatedDate();
			LocalDate createDateTime = LocalDate.parse(createdDate);
			predicates[3] = cb.equal(cb.function("DATE", Date.class, root.get("createdDate")),
					Date.from(createDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[3] = cb.like(root.get("mobileNumber"), "%");
		}

		if (!emailLogFiltersDto.getSentDate().equals("")) {
			String sentDate = emailLogFiltersDto.getSentDate();
			LocalDate sentDateTime = LocalDate.parse(sentDate);
			predicates[4] = cb.equal(cb.function("DATE", Date.class, root.get("sentDate")),
					Date.from(sentDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			predicates[4] = cb.like(root.get("mobileNumber"), "%");
		}

		cr.select(cb.count(root)).where(predicates);
		Long total = s.createQuery(cr).getSingleResult();
		s.close();
		return total;

	}

}
