package com.uninor.repository;

import com.uninor.model.Companies;
import com.uninor.model.OtpLogs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompaniesRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Companies> getCompanyDetails(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Companies";
        Query<Companies> q = s.createQuery(queryString);
        List<Companies> list = q.list();
        s.close();
        return list;
    }

}
