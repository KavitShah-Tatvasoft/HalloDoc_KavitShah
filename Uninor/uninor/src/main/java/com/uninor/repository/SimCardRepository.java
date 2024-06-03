package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.SimCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimCardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<SimCard> getAvailableSimCardSuggestions(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard WHERE isAvailable = true ORDER BY RAND()";
        Query<SimCard> q = s.createQuery(queryString);
        q.setMaxResults(3);
        List<SimCard> list = q.list();
        s.close();
        return list;
    }

}
