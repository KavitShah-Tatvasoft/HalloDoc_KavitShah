package hallodoc.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.RequestNotes;
import hallodoc.model.RequestStatusLog;

@Repository
public class RequestNotesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public List<RequestNotes> getRequestSpecificNote(int reqId) {
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM RequestNotes where request.requestId=: reqId";

		Query q = s.createQuery(queryString);
		q.setParameter("reqId", reqId);
		List<RequestNotes> requestNotes = q.list();
		s.close();
		return requestNotes;
	}
	
	public void updateRequestNotes(RequestNotes requestNotes) {
		this.hibernateTemplate.update(requestNotes);
	}
	
	public int saveRequestNotes(RequestNotes requestNotes) {
		int id = (Integer)this.hibernateTemplate.save(requestNotes);
		return id;
	}

}
