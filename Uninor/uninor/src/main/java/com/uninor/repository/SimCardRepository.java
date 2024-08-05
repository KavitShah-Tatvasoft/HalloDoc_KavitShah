package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.SimCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    public List<SimCard> getSimCardDetailsByNumber(String number){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard WHERE phoneNumber =: number";
        Query<SimCard> q = s.createQuery(queryString);
        q.setParameter("number", number);
        List<SimCard> list = q.list();
        s.close();
        return list;
    }

    public List<SimCard> getClientSimCardDetailsByNumber(String number){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard WHERE phoneNumber =: number AND isAvailable = false";
        Query<SimCard> q = s.createQuery(queryString);
        q.setParameter("number", number);
        List<SimCard> list = q.list();
        s.close();
        return list;
    }

    public SimCard getClientSimCardDetailsByClientId(int clientId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard WHERE client.clientId =: clientId";
        Query<SimCard> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        List<SimCard> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public void deleteSimCardEntry(int simCardId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "DELETE FROM SimCard WHERE simCardId =:simCardId";
        Query query = s.createQuery(hql);
        query.setParameter("simCardId", simCardId);
        query.executeUpdate();
        tx.commit();
        s.close();
    }

    public SimCard getSimCardById(int id){
        return (SimCard) this.hibernateTemplate.get(SimCard.class, id);
    }

    @Transactional
    public void addOrUpdateSimCard(SimCard simCard){
        this.hibernateTemplate.saveOrUpdate(simCard);
    }

    public List<SimCard> getAllActiveSimDetails(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard sc WHERE sc.isAvailable=false";
        Query<SimCard> q = s.createQuery(queryString);
        List<SimCard> list = q.list();
        s.close();
        return list;
    }

    public void setActivationTime(LocalDateTime now, SimCard simCard){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE SimCard sc SET sc.activationDate=:now WHERE sc.simCardId=:simCardId";
        Query<SimCard> q = s.createQuery(queryString);
        q.setParameter("now", now);
        q.setParameter("simCardId",simCard.getSimCardId());
        q.executeUpdate();
        tx.commit();
        s.close();
    }

}
