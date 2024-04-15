package hallodoc.repository;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.BlockRequests;

@Repository
public class BlockRequestsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int addBlockRequest(BlockRequests blockRequests) {
		int id = (Integer) this.hibernateTemplate.save(blockRequests);
		return id;
	}

}
