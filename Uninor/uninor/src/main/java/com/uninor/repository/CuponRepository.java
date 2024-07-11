package com.uninor.repository;

import com.uninor.model.ClientCupons;
import com.uninor.model.Cupon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CuponRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<ClientCupons> getClientCupons(int clientId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientCupons WHERE client.clientId = :clientId AND isExpired=false AND isUsed=false";
        Query<ClientCupons> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        List<ClientCupons> list = q.list();
        s.close();
        return list;
    }

    public List<ClientCupons> getClientCuponByCuponCode(int clientId, String cuponCode){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientCupons WHERE client.clientId =:clientId AND cuponCode =:cuponCode";
        Query<ClientCupons> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setParameter("cuponCode", cuponCode);
        List<ClientCupons> list = q.list();
        s.close();
        return list;
    }

    @Transactional
    public void updateClientCupons(ClientCupons cupon){
        this.hibernateTemplate.update(cupon);
    }

    public List<Cupon> getAllAvailableCoupons(){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Cupon c WHERE  c.isAvailable =true";
        Query<Cupon> q = s.createQuery(queryString);
        List<Cupon> list = q.list();
        s.close();
        return list;
    }

    @Transactional
    public int saveClientCupons(ClientCupons cupon){
        return (Integer) this.hibernateTemplate.save(cupon);
    }

}
