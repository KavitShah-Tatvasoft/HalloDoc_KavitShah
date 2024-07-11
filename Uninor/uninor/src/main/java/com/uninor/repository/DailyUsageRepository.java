package com.uninor.repository;

import com.uninor.model.ClientDailyDataUsage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DailyUsageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<ClientDailyDataUsage> getLastMonthData(int clientId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientDailyDataUsage WHERE client.clientId =: clientId ORDER BY usageDate DESC";
        Query<ClientDailyDataUsage> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setMaxResults(30);
        List<ClientDailyDataUsage> list = q.list();
        s.close();
        return list;
    }

}
