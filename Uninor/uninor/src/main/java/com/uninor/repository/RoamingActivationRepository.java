package com.uninor.repository;

import com.uninor.model.PlanActivation;
import com.uninor.model.RoamingActivation;
import com.uninor.model.SimCard;
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
public class RoamingActivationRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public int saveRoamingActivationDetails(RoamingActivation roamingActivation) {
        return (Integer)this.hibernateTemplate.save(roamingActivation);
    }

    @Transactional
    public void updateRoamingActivationDetails(RoamingActivation roamingActivation) {
       this.hibernateTemplate.update(roamingActivation);
    }

    public void expireAllUnexpiredRoamingSimPlan(int simId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE RoamingActivation pa SET pa.isExpired=true, pa.isActive=false , pa.isServiceChange=true WHERE pa.simCard.simCardId =:simCardId AND pa.isExpired=false";
        Query<RoamingActivation> q = s.createQuery(queryString);
        q.setParameter("simCardId", simId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    public List<RoamingActivation> getCurrentSimRoamingPlan(int simId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM RoamingActivation WHERE simCard.simCardId=:simId AND isExpired=false AND isActive=true";
        Query<RoamingActivation> q = s.createQuery(queryString);
        q.setParameter("simId", simId);
        List<RoamingActivation> list = q.list();
        s.close();
        return list;
    }

    public List<RoamingActivation> getActivableRoamingPlanByMobile(String mobile){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM RoamingActivation  WHERE simCard.phoneNumber =:mobileNumber AND isReactiveAvailable=true AND isExpired=false";
        Query<RoamingActivation> q = s.createQuery(queryString);
        q.setParameter("mobileNumber", mobile);
        List<RoamingActivation> list = q.list();
        s.close();
        return list;
    }

    public List<RoamingActivation> getNextActivablePlan(String mobileNumber){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM RoamingActivation pa WHERE pa.simCard.phoneNumber =:mobileNumber AND pa.isExpired=false ORDER BY pa.boughtDate ASC";
        Query<RoamingActivation> q = s.createQuery(queryString);
        q.setParameter("mobileNumber", mobileNumber);
        q.setMaxResults(1);
        List<RoamingActivation> list = q.list();
        s.close();
        return list;
    }


}
