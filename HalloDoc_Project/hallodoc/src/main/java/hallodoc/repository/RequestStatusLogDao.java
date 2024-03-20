package hallodoc.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.RequestStatusLog;

@Repository
public class RequestStatusLogDao {


	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addNewRequestStatusLog(RequestStatusLog requestStatusLog) {
		int id = (Integer) this.hibernateTemplate.save(requestStatusLog);
		return id;
	}
}
