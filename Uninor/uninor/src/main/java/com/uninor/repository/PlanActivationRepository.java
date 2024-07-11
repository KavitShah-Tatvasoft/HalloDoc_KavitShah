package com.uninor.repository;

import com.uninor.model.OtpLogs;
import com.uninor.model.PlanActivation;
import com.uninor.model.SimCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PlanActivationRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<PlanActivation> getActiveSimPlan(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanActivation pa WHERE pa.simCard.simCardId =:simCardId AND pa.isActive=true AND pa.isExpired=false";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("simCardId", simCard.getSimCardId());
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public List<PlanActivation> getActivePlanByMobile(String mobileNumber){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanActivation pa WHERE pa.simCard.phoneNumber =:mobileNumber AND pa.isActive=true AND pa.isExpired=false";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("mobileNumber", mobileNumber);
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public List<PlanActivation> getAllUnexpiredSimPlan(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanActivation pa WHERE pa.simCard.simCardId =:simCardId AND pa.isExpired=false";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("simCardId", simCard.getSimCardId());
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public List<PlanActivation> getNextAvailablePlan(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanActivation pa WHERE pa.simCard.simCardId =:simCardId AND pa.isExpired=false ORDER BY pa.boughtDate ASC";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("simCardId", simCard.getSimCardId());
        q.setMaxResults(1);
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }

    public void expireAllUnexpiredSimPlan(int simCardId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE PlanActivation pa SET pa.isExpired=true, pa.isActive=false , pa.isServiceChange=true WHERE pa.simCard.simCardId =:simCardId AND pa.isExpired=false";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("simCardId", simCardId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    @Transactional
    public int savePlanActivation(PlanActivation planActivation){
        return (Integer)this.hibernateTemplate.save(planActivation);
    }

    @Transactional
    public void updatePlanActivation(PlanActivation planActivation){
        this.hibernateTemplate.update(planActivation);
    }

    public List<PlanActivation> getAvailableActivationPlansByMobile(String mobileNumber){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanActivation pa WHERE pa.simCard.phoneNumber =:mobileNumber AND pa.isReactiveAvailable=true AND pa.isExpired=false";
        Query<PlanActivation> q = s.createQuery(queryString);
        q.setParameter("mobileNumber", mobileNumber);
        List<PlanActivation> list = q.list();
        s.close();
        return list;
    }



}
