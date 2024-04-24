package com.lms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;
import com.lms.model.Borrower;

@Repository
public class BorrowerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<Borrower> getOldBorrowerId(String name) {
		String bname = name.trim();
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Borrower bk WHERE bk.borrowerName =:bname";
		Query q = s.createQuery(queryString);
		q.setParameter("bname", bname);
		List<Borrower> list = q.list();
		s.close();
		return list;
		
	}
	
	@Transactional
	public int addBorrower(Borrower borrower) {
		return (Integer)this.hibernateTemplate.save(borrower);
	}

}
