package com.uninor.repository;

import com.uninor.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Users> getUserByEmail(String email){

        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Users WHERE emailAddress=:email";
        Query<Users> q = s.createQuery(queryString);
        q.setParameter("email", email);
        List<Users> list = q.list();
        s.close();
        return list;
    }



}
