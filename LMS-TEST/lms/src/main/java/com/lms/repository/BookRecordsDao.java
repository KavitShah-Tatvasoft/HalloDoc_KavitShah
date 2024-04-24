package com.lms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lms.model.BookRecords;
import com.lms.model.Borrower;

@Repository
public class BookRecordsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int addBookRecordEntry(BookRecords bookRecords) {
		return (Integer)this.hibernateTemplate.save(bookRecords);
	}
	
	public List<BookRecords> getAllData(){
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM BookRecords bk ORDER BY bk.bookRecordId ";
		Query q = s.createQuery(queryString);
		List<BookRecords> list = q.list();
		s.close();
		return list;
	}
	
	public BookRecords getBookRecordData(int id) {
		BookRecords bookRecords = this.hibernateTemplate.get(BookRecords.class, id);
		return bookRecords;
	}
}
