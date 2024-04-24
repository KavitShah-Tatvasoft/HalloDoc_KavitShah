package com.lms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;


@Repository
public class BookDao {


	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<Book> getBookByName(String name) {
		String bookName = name.trim();
		Session s = this.sessionFactory.openSession();
		String queryString = "FROM Book bk WHERE bk.bookName =:bookName";
		Query q = s.createQuery(queryString);
		q.setParameter("bookName", bookName);
		List<Book> list = q.list();
		s.close();
		return list;
		
	}
}
