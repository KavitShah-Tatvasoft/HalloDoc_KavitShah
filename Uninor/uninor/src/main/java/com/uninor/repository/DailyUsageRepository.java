package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.ClientDailyDataUsage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    public void insertOrUpdateDailyUsage(int dataUsage, Client client){
        LocalDate today = LocalDate.now();
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientDailyDataUsage WHERE client.clientId =: clientId AND usageDate=:today";
        Query<ClientDailyDataUsage> q = s.createQuery(queryString);
        q.setParameter("clientId", client.getClientId());
        q.setParameter("today", today);
        ClientDailyDataUsage currentUsage = q.uniqueResult();

        if(currentUsage != null){
            currentUsage.setDailyUsage(currentUsage.getDailyUsage() + dataUsage);
            this.hibernateTemplate.update(currentUsage);
        }else {
            currentUsage = new ClientDailyDataUsage();
            currentUsage.setDailyUsage(dataUsage);
            currentUsage.setUsageDate(today);
            currentUsage.setClient(client);
            this.hibernateTemplate.save(currentUsage);
        }
        s.close();

    }

}
