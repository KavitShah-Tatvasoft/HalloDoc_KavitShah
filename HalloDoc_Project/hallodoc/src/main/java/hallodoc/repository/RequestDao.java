package hallodoc.repository;

import java.net.Authenticator.RequestorType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.dto.DashboardDataDto;
import hallodoc.dto.RequestDocumentsDto;
import hallodoc.dto.RequestFiltersDto;
import hallodoc.dto.SearchRecordsFilter;
import hallodoc.dto.StatusWiseCountDto;
import hallodoc.model.Physician;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
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

	public List<RequestDocumentsDto> getDocuments(int id) {
		Session s = this.sessionFactory.openSession();
		String query = "select new hallodoc.dto.RequestDocumentsDto(re.confirmationNumber, rwf.fileName, rwf.createdDate, rwf.uploaderName, rwf.fileExtension, rwf.storedFileName, rwf.requestWiseFileId) from Request re left join RequestWiseFile rwf on re.requestId = rwf.request.requestId where re.requestId=:reqId and rwf.isDeleted <> true";
		Query<RequestDocumentsDto> query1 = s.createQuery(query);
		query1.setParameter("reqId", id);
		List<RequestDocumentsDto> requests = query1.list();
		s.close();
		return requests;
	}

	public List<Request> getAllUserRequests(User user) {
		Session s = this.sessionFactory.openSession();

//		Query<DashboardDataDto> query = s.createQuery(
//				"select new hallodoc.dto.DashboardDataDto(re.requestId,physician.physicianId,physician.firstName,physician.lastName,re.createdDate,re.status,ad.adminId,ad.firstName,ad.lastName,count(ref.request.requestId)) from Request re left join re.listRequestWiseFiles ref left join re.user.aspNetUsers.admin ad left join re.physician physician where re.user=:loginUser group by re.requestId,ad.adminId,physician.physicianId");

		String query = "FROM Request re where re.user=:loginUser";
		Query hql = s.createQuery(query);
		hql.setParameter("loginUser", user);

		List<Request> results = hql.list();

		System.out.println("Query result obtained");
		s.close();
		return results;
	}

	public List<Request> getUserRequestByType(User user, RequestType reqType) {
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as req where req.user=:user and req.requestType=:reqType";
		Query query = s.createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("reqType", reqType);
		List<Request> reqList = query.list();
		s.close();
		return reqList;
	}

	public List<Request> getUserRequestByOtherType(User user, RequestType reqType) {
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

	public List<Request> getRequstStatusData(List<Integer> status) {
		Session s = this.sessionFactory.openSession();
		String hql = "from Request as req where req.status IN :status";
		Query query = s.createQuery(hql);
		query.setParameter("status", status);
		List<Request> requestsList = query.list();
		s.close();
		return requestsList;
	}

	public List<StatusWiseCountDto> getStatusWiseRequestCount() {
		Session s = this.sessionFactory.openSession();

		String hql = "select new hallodoc.dto.StatusWiseCountDto(re.status,COUNT(re.requestId)) from Request re group by re.status";
		Query query = s.createQuery(hql);
		List<StatusWiseCountDto> list = query.list();
		s.close();
		return list;
	}

	public List<Request> getFilteredRequests(RequestFiltersDto requestFiltersDto) {

		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Request> cr = cb.createQuery(Request.class);
		Root<Request> root = cr.from(Request.class);

		Join<Request, RequestClient> clientJoin = root.join("requestClient");

		Join<Request, RequestType> requestTypeJoin = root.join("requestType");

		Predicate[] predicates = new Predicate[4];

		if (!requestFiltersDto.getPatientName().equals("")) {
			predicates[0] = cb.like(clientJoin.get("firstName"), requestFiltersDto.getPatientName() + "%");
		} else {
			predicates[0] = cb.like(clientJoin.get("firstName"), "%");
		}

		if (!requestFiltersDto.getStateName().equals("All")) {
			predicates[1] = cb.equal(clientJoin.get("state"), requestFiltersDto.getStateName());
		} else {
			predicates[1] = cb.like(clientJoin.get("state"), "%");
		}

		switch (requestFiltersDto.getStatusType()) {
		case "1":
			predicates[2] = root.get("status").in(1);
			break;

		case "2":
			predicates[2] = root.get("status").in(2);
			break;

		case "3":
			predicates[2] = root.get("status").in(4, 5);
			break;

		case "4":
			predicates[2] = root.get("status").in(6);
			break;

		case "5":
			predicates[2] = root.get("status").in(3, 7, 8);
			break;

		case "6":
			predicates[2] = root.get("status").in(9);
			break;

		default:
			predicates[2] = root.get("status").in(1, 2, 3, 4, 5, 6, 7, 8, 9);
			break;
		}

		if (!requestFiltersDto.getRequestType().equals("All")) {
			predicates[3] = cb.equal(requestTypeJoin.get("name"), requestFiltersDto.getRequestType());
		} else {
			predicates[3] = cb.like(requestTypeJoin.get("name"), "%");
		}

		cr.select(root).where(predicates);

		Query<Request> query = s.createQuery(cr);

		List<Request> list = query.getResultList();
		s.close();
		return list;
	}

	public List<Request> getRequestByUser(Integer userId) {
		Session s = this.sessionFactory.openSession();
		String hql = "FROM Request rq WHERE rq.user.userID =: userId";
		Query query = s.createQuery(hql);
		query.setParameter("userId", userId);
		List<Request> list = query.list();
		s.close();
		return list;
	}

	public List<Request> getFilteredRequest(SearchRecordsFilter searchRecordsFilter) {
		Session s = this.sessionFactory.openSession();

		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Request> cr = cb.createQuery(Request.class);
		Root<Request> root = cr.from(Request.class);

		Join<Request, RequestClient> clientJoin = root.join("requestClient",JoinType.LEFT);

		Join<Request, RequestType> requestTypeJoin = root.join("requestType",JoinType.LEFT);
		
		Join<Request,Physician> physicianJoin = root.join("physician",JoinType.LEFT);

		Predicate[] predicates = new Predicate[9];

		switch (searchRecordsFilter.getRequestStatus()) {
		case 1:
			predicates[0] = root.get("status").in(1);
			break;

		case 2:
			predicates[0] = root.get("status").in(2);
			break;

		case 3:
			predicates[0] = root.get("status").in(4, 5);
			break;

		case 4:
			predicates[0] = root.get("status").in(6);
			break;

		case 5:
			predicates[0] = root.get("status").in(3, 7, 8);
			break;

		case 6:
			predicates[0] = root.get("status").in(9);
			break;

		default:
			predicates[0] = root.get("status").in(1, 2, 3, 4, 5, 6, 7, 8, 9);
			break;
		}

		

		if (!searchRecordsFilter.getFirstName().equals("")) {
			predicates[1] = cb.like(clientJoin.get("firstName"), searchRecordsFilter.getFirstName() + "%");
		} else {
			predicates[1] = cb.like(clientJoin.get("firstName"), "%");
		}

		if (!(searchRecordsFilter.getRequestType() == 0)) {
			predicates[2] = cb.equal(requestTypeJoin.get("requestTypeId"), searchRecordsFilter.getRequestType());
		} else {
			predicates[2] = requestTypeJoin.get("requestTypeId").in(1,2,3,4);
		}

		if (!searchRecordsFilter.getProviderName().equals("")) {
//			predicates[3] = cb.like(physicianJoin.get("firstName"), searchRecordsFilter.getProviderName() + "%");
			predicates[3] = cb.or(cb.like(physicianJoin.get("firstName"), searchRecordsFilter.getProviderName() + "%"), cb.isNull(physicianJoin));
		} else {
//			predicates[3] = cb.like(physicianJoin.get("firstName"), "%");
			predicates[3] = cb.or(cb.like(physicianJoin.get("firstName"), "%"), cb.isNull(physicianJoin));
		}
		
		if (!searchRecordsFilter.getEmail().equals("")) {
			predicates[4] = cb.like(clientJoin.get("email"), searchRecordsFilter.getEmail() + "%");
		} else {
			predicates[4] = cb.like(clientJoin.get("email"), "%");
		}
		
		if (!searchRecordsFilter.getPhoneNumber().equals("")) {
			predicates[5] = cb.like(clientJoin.get("phoneNumber"), searchRecordsFilter.getPhoneNumber() + "%");
		} else {
			predicates[5] = cb.like(clientJoin.get("phoneNumber"), "%");
		}
		
		if(!searchRecordsFilter.getFromDOS().equals("")) {
			LocalDate specifiedDate = LocalDate.parse(searchRecordsFilter.getFromDOS());
			Date specifiedDateAsDate = Date.from(specifiedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			predicates[6] = cb.greaterThan(root.get("acceptedDate"), specifiedDateAsDate);
		}else {
			predicates[6] = cb.like(clientJoin.get("phoneNumber"), "%");
		}
		
		if(!searchRecordsFilter.getToDOS().equals("")) {
			LocalDate specifiedDate = LocalDate.parse(searchRecordsFilter.getToDOS());
			Date specifiedDateAsDate = Date.from(specifiedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			predicates[7] = cb.lessThan(root.get("acceptedDate"), specifiedDateAsDate);
		}else {
			predicates[7] = cb.like(clientJoin.get("phoneNumber"), "%");
		}
		
		predicates[8] = cb.equal(root.get("isDeleted"), false);

		cr.select(root).where(predicates);

		Query<Request> query = s.createQuery(cr);

		List<Request> list = query.getResultList();
		s.close();
		return list;
	}
	
	public void deleteRequest(Integer reqId) {
		
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		String query = "UPDATE Request req SET req.isDeleted = true WHERE req.requestId =: reqId";
		Query hql = s.createQuery(query);
		hql.setParameter("reqId", reqId);
		hql.executeUpdate();
		tx.commit();
		s.close();
		
	}

}
