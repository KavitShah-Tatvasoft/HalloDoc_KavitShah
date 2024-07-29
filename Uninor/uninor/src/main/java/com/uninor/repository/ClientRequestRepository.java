package com.uninor.repository;

import com.uninor.dto.FilterUserRequest;
import com.uninor.enumeration.ClientRequestTypeEnum;
import com.uninor.enumeration.RequestStatusEnum;
import com.uninor.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        String queryString = "FROM ClientRequest cl WHERE cl.simCard.simCardId =: simId AND cl.requestStatus =:status AND cl.requestType =:type";
        Query<ClientRequest> q = s.createQuery(queryString);
        q.setParameter("simId",simCard.getSimCardId());
        q.setParameter("status",RequestStatusEnum.PENDING.getRequestStatusId());
        q.setParameter("type",ClientRequestTypeEnum.DEACTIVATION.getRequestTypeId());
        List<ClientRequest> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public ClientRequest getRequestById(int requestId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientRequest cl WHERE cl.requestId=:requestId";
        Query<ClientRequest> q = s.createQuery(queryString);
        q.setParameter("requestId",requestId);
        List<ClientRequest> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public int saveClientRequest(ClientRequest clientRequest) {
        return (Integer) this.hibernateTemplate.save(clientRequest);
    }

    @Transactional
    public void updateClientRequest(ClientRequest clientRequest) {
         this.hibernateTemplate.update(clientRequest);
    }

    public Page<ClientRequest> getPaginatedClientRequestData(Pageable pageable, FilterUserRequest filterUserRequest) {
        Session s = this.sessionFactory.openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ClientRequest> cr = cb.createQuery(ClientRequest.class);
        Root<ClientRequest> root = cr.from(ClientRequest.class);

        Join<ClientRequest, SimCard> simJoin = root.join("simCard");
        Join<SimCard, Client> clientJoin = simJoin.join("client");
        Predicate predicate;

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filterUserRequest.getRequestType() == ClientRequestTypeEnum.ACTIVATION.getRequestTypeId()) {
            if (filterUserRequest.getStatusType() == 2) {
                predicate = cb.equal(clientJoin.get("validationAttempts"), 0);
                predicates.add(predicate);
            } else if (filterUserRequest.getStatusType() == 3) {
                predicate = cb.gt(clientJoin.get("validationAttempts"), 0);
                predicates.add(predicate);
            } else {
                predicate = cb.greaterThanOrEqualTo(clientJoin.get("validationAttempts"), 0);
                predicates.add(predicate);
            }
        }

        predicate = cb.equal(root.get("requestType"), filterUserRequest.getRequestType());
        predicates.add(predicate);

        predicate = cb.equal(root.get("requestStatus"), RequestStatusEnum.PENDING.getRequestStatusId());
        predicates.add(predicate);

        if (!filterUserRequest.getEmail().isEmpty()) {
            predicate = cb.like(cb.lower(clientJoin.get("email")), "%" + filterUserRequest.getEmail().toLowerCase() + "%");
            predicates.add(predicate);
        }

        if (!filterUserRequest.getMobile().isEmpty()) {
            predicate = cb.like(simJoin.get("phoneNumber"), "%" + filterUserRequest.getMobile() + "%");
            predicates.add(predicate);
        }


        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        cr.where(finalPredicate);
        Query<ClientRequest> query = s.createQuery(cr);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult((int) pageable.getOffset());
        List<ClientRequest> list = query.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ClientRequest> countRoot = countQuery.from(ClientRequest.class);
        Join<ClientRequest, SimCard> simJoinCount = countRoot.join("simCard");
        Join<SimCard, Client> clientJoinCount = simJoinCount.join("client");

        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long count = s.createQuery(countQuery).getSingleResult();


        s.close();
        return new PageImpl<>(list, pageable, count);
    }


}
