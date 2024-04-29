package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.ViewNotesDto;
import hallodoc.model.Concierge;
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

	public List<RequestStatusLog> getAllRequestSpecificLogs(int reqId) {
		Session s = this.sessionFactory.openSession();

//		String queryString = "select new hallodoc.dto.ViewNotesDto(rsl.request_status_log_id,rsl.status,rsl.notes,rsl.createdDate,rn.adminNotes,rn.physicanNotes) from RequestStatusLog rsl right join RequestNotes rn on rsl.request.requestId = rn.request.requestId where rsl.request.requestId=: reqId ";
		String queryString = "FROM RequestStatusLog where request.requestId=: reqId";

		Query q = s.createQuery(queryString);
		q.setParameter("reqId", reqId);
		List<RequestStatusLog> requestStatusLogs = q.list();
		s.close();

		return requestStatusLogs;
	}

	public List<RequestStatusLog> getStatusSpecificLogs(int status, int requestId) {
		Session s = this.sessionFactory.openSession();
		
		String queryString = "FROM RequestStatusLog rsl WHERE rsl.request.requestId=: requestId AND rsl.status =: status";
		
		Query q = s.createQuery(queryString);
		q.setParameter("requestId", requestId);
		q.setParameter("status",status);
		List<RequestStatusLog> requestStatusLogs = q.list();
		s.close();

		return requestStatusLogs;
	}
}
