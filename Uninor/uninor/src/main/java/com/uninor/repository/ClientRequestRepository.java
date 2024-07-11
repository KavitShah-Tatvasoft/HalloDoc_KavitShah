package com.uninor.repository;

import com.uninor.enumeration.ClientRequestTypeEnum;
import com.uninor.enumeration.RequestStatusEnum;
import com.uninor.model.ClientRequest;
import com.uninor.model.Companies;
import com.uninor.model.SimCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ClientRequestRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<ClientRequest> getClientBlockRequests(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientRequest cl WHERE cl.simCard.simCardId =: simId AND cl.requestStatus = 1 AND cl.requestType = 3";
        Query<ClientRequest> q = s.createQuery(queryString);
        q.setParameter("simId",simCard.getSimCardId());
        List<ClientRequest> list = q.list();
        s.close();
        return list;
    }

    public List<ClientRequest> getClientUnblockRequests(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        int type = ClientRequestTypeEnum.UNBLOCK.getRequestTypeId();
        int status = RequestStatusEnum.PENDING.getRequestStatusId();
        String queryString = "FROM ClientRequest cl WHERE cl.simCard.simCardId =: simId AND cl.requestStatus =:status AND cl.requestType =:type";
        Query<ClientRequest> q = s.createQuery(queryString);
        q.setParameter("simId",simCard.getSimCardId());
        q.setParameter("status",status);
        q.setParameter("type",type);
        List<ClientRequest> list = q.list();
        s.close();
        return list;
    }

    public ClientRequest getSimDeactivationRequest(SimCard simCard){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientRequest cl WHERE cl.simCard.simCardId =: simId AND cl.requestStatus = status AND cl.requestType = type";
        Query<ClientRequest> q = s.createQuery(queryString);
        q.setParameter("simId",simCard.getSimCardId());
        q.setParameter("status",RequestStatusEnum.PENDING.getRequestStatusId());
        q.setParameter("type",ClientRequestTypeEnum.DEACTIVATION.getRequestTypeId());
        List<ClientRequest> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public int saveClientRequest(ClientRequest clientRequest) {
        return (Integer) this.hibernateTemplate.save(clientRequest);
    }

}
