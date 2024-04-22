package hallodoc.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.EmailLog;
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

}
