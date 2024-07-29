package com.uninor.repository;

import com.uninor.model.PlanActivation;
import com.uninor.model.ReuploadEmailLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ReuploadEmailLogRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public int saveReuploadEmailLog(ReuploadEmailLog reuploadEmailLog) {
        return (Integer)this.hibernateTemplate.save(reuploadEmailLog);
    }

    @Transactional
    public void expireAllPreviousRequests(String email){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String query = "UPDATE ReuploadEmailLog log SET log.isExpired=true WHERE log.email=:email";
        Query q = s.createQuery(query);
        q.setParameter("email", email);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    public ReuploadEmailLog getLogByToken(String token) {
        Session s = this.sessionFactory.openSession();
        String query = "FROM ReuploadEmailLog log WHERE log.logToken=:token";
        Query<ReuploadEmailLog> q = s.createQuery(query, ReuploadEmailLog.class);
        q.setParameter("token", token);
        List<ReuploadEmailLog> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public void updateReuploadEmailLog(ReuploadEmailLog reuploadEmailLog) {
        this.hibernateTemplate.update(reuploadEmailLog);
    }

}
