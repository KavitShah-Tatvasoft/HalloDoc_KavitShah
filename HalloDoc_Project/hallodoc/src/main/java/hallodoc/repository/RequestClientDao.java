package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.RequestClient;

@Repository
public class RequestClientDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int addNewRequestClient(RequestClient requestClient) {
		int id = (Integer) this.hibernateTemplate.save(requestClient);
		return id;
	}
	

}
