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

import hallodoc.dto.BlockHistoryFilterData;
import hallodoc.dto.EmailLogFiltersDto;
import hallodoc.model.AspNetRoles;
import hallodoc.model.BlockRequests;
import hallodoc.model.Request;
import hallodoc.model.RequestClient;
import hallodoc.model.SmsLog;

@Repository
public class BlockRequestsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public BlockRequests getBlockRequestOb(Integer blockId) {
		return this.hibernateTemplate.get(BlockRequests.class, blockId);
	}
	
	@Transactional
	public int addBlockRequest(BlockRequests blockRequests) {
		int id = (Integer) this.hibernateTemplate.save(blockRequests);
		return id;
	}
	
	public List<BlockRequests> getBlockRequestData(BlockHistoryFilterData blockHistoryFilterData){
		Session s = this.sessionFactory.openSession();
		
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<BlockRequests> cr = cb.createQuery(BlockRequests.class);
		Root<BlockRequests> root = cr.from(BlockRequests.class);
		Join<BlockRequests, Request> blockJoin = root.join("request");
		Join<Request, RequestClient> requestJoin = blockJoin.join("requestClient");
		

		Predicate[] predicates = new Predicate[4];
		
		if (!blockHistoryFilterData.getName().equals("")) {
			predicates[0] = cb.like(requestJoin.get("firstName"), blockHistoryFilterData.getName() + "%");
		} else {
			predicates[0] = cb.like(requestJoin.get("firstName"), "%");
		}
		
		if (!blockHistoryFilterData.getPhone().equals("")) {
			predicates[1] = cb.like(root.get("phoneNumber"), blockHistoryFilterData.getPhone() + "%");
		} else {
			predicates[1] = cb.like(root.get("phoneNumber"), "%");
		}
		
		if (!blockHistoryFilterData.getEmail().equals("")) {
			predicates[2] = cb.like(root.get("email"), blockHistoryFilterData.getEmail() + "%");
		} else {
			predicates[2] = cb.like(root.get("email"), "%");
		}
		
		if(!blockHistoryFilterData.getDate().equals("")) {
			String createdDate = blockHistoryFilterData.getDate(); 
			LocalDate createDateTime = LocalDate.parse(createdDate); 
			predicates[3] = cb.equal(cb.function("DATE", Date.class, root.get("createdDate")), Date.from(createDateTime.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}else {
			predicates[3] = cb.like(root.get("phoneNumber"), "%");
		}

		

		cr.select(root).where(predicates);

		Query<BlockRequests> query = s.createQuery(cr);

		List<BlockRequests> list = query.getResultList();
		s.close();
		return list;

	}
	
	@Transactional
	public void updateBlockRequest(BlockRequests blockRequests) {
		this.hibernateTemplate.update(blockRequests);
	}


}
