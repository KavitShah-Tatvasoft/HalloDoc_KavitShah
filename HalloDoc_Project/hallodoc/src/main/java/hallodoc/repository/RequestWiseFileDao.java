package hallodoc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import hallodoc.model.RequestWiseFile;

@Repository
public class RequestWiseFileDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	
	@Transactional
	public int addNewRequestWiseFile(RequestWiseFile requestWiseFile) {
		int id = (Integer) this.hibernateTemplate.save(requestWiseFile);
		return id;
	}
	
	@Transactional
	public void deleteRequestedFile(RequestWiseFile requestWiseFile) {
		this.hibernateTemplate.update(requestWiseFile);
	}
	
	public RequestWiseFile getRequestWiseFileOb(int fileId) {
		return this.hibernateTemplate.get(RequestWiseFile.class, fileId);
	}

}
