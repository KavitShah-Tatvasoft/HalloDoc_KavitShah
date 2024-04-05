package hallodoc.repository;

import java.net.Authenticator.RequestorType;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.DashboardDataDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.model.Request;
import hallodoc.model.RequestType;
import hallodoc.model.RequestWiseFile;
import hallodoc.model.User;

@Repository
public class RequestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int addNewRequest(Request request) {
		int id = (Integer) this.hibernateTemplate.save(request);
		return id;
	}
	
	public Request getRequestOb(int id) {
		Request request = (Request) this.hibernateTemplate.get(Request.class, id);
		return request;
	}

	public int getNewRequestsNo(Date sdate, Date edate) {
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as Request where Request.createdDate between :date and :ceilDate";
		Query query = s.createQuery(hql);
		// a date having timestamp part, 00:00:00.0, or missing completely
		query.setParameter("date", sdate);
		// a date having timestamp part, 23:59:59.999
		query.setParameter("ceilDate", edate);

		List<Request> result = query.list();
		System.out.println(result.size());
		s.close();
		return result.size();
	}
	
	public List<RequestDocumentsDto> getDocuments(int id){
		Session s = this.sessionFactory.openSession();
		String query = "select new hallodoc.dto.RequestDocumentsDto(re.confirmationNumber, rwf.fileName, rwf.createdDate, rwf.uploaderName, rwf.fileExtension, rwf.storedFileName) from Request re left join RequestWiseFile rwf on re.requestId = rwf.request.requestId where re.requestId=:reqId";
		Query<RequestDocumentsDto> query1 = s.createQuery(query);
		query1.setParameter("reqId", id);
		List<RequestDocumentsDto> requests = query1.list();
		s.close();
		return requests;
	}

	public List<DashboardDataDto> getAllUserRequests(User user) {
		Session s = this.sessionFactory.openSession();

		Query<DashboardDataDto> query = s.createQuery(
		  "select new hallodoc.dto.DashboardDataDto(re.requestId,physician.physicianId,physician.firstName,physician.lastName,re.createdDate,re.status,ad.adminId,ad.firstName,ad.lastName,count(ref.request.requestId)) from Request re left join re.listRequestWiseFiles ref left join re.requestStatusLogs.admin ad left join re.physician physician where re.user=:loginUser group by re.requestId,ad.adminId,physician.physicianId");
		query.setParameter("loginUser", user);

		List<DashboardDataDto> results = query.list();

		System.out.println("Query result obtained");
		s.close();
		return results;
	}
	
	public List<Request> getUserRequestByType(User user,RequestType reqType){
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as req where req.user=:user and req.requestType=:reqType";
		Query query = s.createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("reqType", reqType);
		List<Request> reqList = query.list();
		s.close();
		return reqList;
	}
	
	public List<Request> getUserRequestByOtherType(User user,RequestType reqType){
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as req where req.user=:user and req.requestType!=:reqType";
		Query query = s.createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("reqType", reqType);
		List<Request> reqList = query.list();
		s.close();
		return reqList;
	}
	
	@Transactional
	public void updateRequest(Request request) {
		this.hibernateTemplate.update(request);
	}

	public List<Request> getRequstStatusData(int status){
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as req where req.status=:status";
		Query query = s.createQuery(hql);
		query.setParameter("status", status);
		List<Request> requestsList = query.list();
		s.close();
		return requestsList;
	}

}
