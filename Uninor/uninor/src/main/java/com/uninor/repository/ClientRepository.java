package com.uninor.repository;

import com.uninor.dto.FilterUserRequest;
import com.uninor.enumeration.ClientRequestTypeEnum;
import com.uninor.enumeration.RequestStatusEnum;
import com.uninor.model.Client;
import com.uninor.model.ClientRequest;
import com.uninor.model.SimCard;
import com.uninor.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Client> getUserByEmail(String email){

        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Client WHERE email=:email";
        Query<Client> q = s.createQuery(queryString);
        q.setParameter("email", email);
        List<Client> list = q.list();
        s.close();
        return list;
    }



    @Transactional
    public void updateClient(Client client){
        this.hibernateTemplate.update(client);
    }

//    @Transactional
//    public void manuallyUpdateClient(Client client){
//        Client persistentClient = this.hibernateTemplate.get(Client.class, client.getClientId());
//        if (persistentClient != null) {
//            // Update fields of persistentClient with values from client
//            persistentClient.setValidationAttempts(client.getValidationAttempts());
//
//            this.hibernateTemplate.update(persistentClient);
//        } else {
//            throw new EntityNotFoundException("Client not found with ID: " + client.getClientId());
//        }
//    }

    @Transactional
    public void saveClient(Client client){
        this.hibernateTemplate.save(client);
    }

    public Client getClientById(int id){
        return this.hibernateTemplate.get(Client.class, id);
    }

    public Client getClientAndClientDocuments(int id){
        Session s = this.sessionFactory.openSession();
            String queryString = "SELECT DISTINCT cl FROM Client cl JOIN FETCH cl.clientDocuments cd WHERE cl.clientId =:id";
        Query<Client> q = s.createQuery(queryString);
        List<Client> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    @Transactional
    public Client getClientBYNumber(String phoneNumber){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM SimCard WHERE phoneNumber=:phoneNumber";
        Query<SimCard> q = s.createQuery(queryString);
        q.setParameter("phoneNumber", phoneNumber);
        List<SimCard> list = q.list();
        Client client = list.get(0).getClient();
        s.close();
        return client;
    }

    @Transactional
    public Client getClientByEmail(String email){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Client WHERE email=:email";
        Query<Client> q = s.createQuery(queryString);
        q.setParameter("email", email);
        List<Client> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public Client getClientByUserId(int userId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Client ct WHERE ct.user.userId=:userId";
        Query<Client> q = s.createQuery(queryString);
        q.setParameter("userId", userId);
        List<Client> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public Map<String,Integer> getRegisteredSignedUpUserCount(){
        Session session = this.sessionFactory.openSession();
        String hql = "SELECT "
                + "SUM(CASE WHEN u.isDocValidated = true AND u.isDeleted = false THEN 1 ELSE 0 END) AS countValidatedNotDeleted, "
                + "SUM(CASE WHEN u.isDocValidated = false AND u.isDeleted = false THEN 1 ELSE 0 END) AS countNotValidatedNotDeleted "
                + "FROM Client u";

        Object[] result = (Object[]) session.createQuery(hql).uniqueResult();
        Long countValidatedNotDeleted = (Long) result[0];
        Long countNotValidatedNotDeleted = (Long) result[1];
        Map<String,Integer> map = new HashMap<>();
        map.put("registeredUserCount", countValidatedNotDeleted.intValue());
        map.put("signedUpUserCount", countNotValidatedNotDeleted.intValue());
        return map;
    }

    public Page<Client> getPaginatedClientData(Pageable pageable, FilterUserRequest filterUserRequest){
        Session s = this.sessionFactory.openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);

        Join<Client, Users> userJoin = root.join("user");
        Predicate predicate;

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filterUserRequest.getRequestType() == 2) {
            predicate = cb.equal(userJoin.get("isRegistered"), false);
            predicates.add(predicate);
        }else {
            predicate = cb.equal(userJoin.get("isRegistered"), true);
            predicates.add(predicate);

            if(filterUserRequest.getStatusType() == 2){
                predicate = cb.equal(root.get("isDocValidated"), true);
                predicates.add(predicate);
            }

            if(filterUserRequest.getStatusType() == 3){
                predicate = cb.equal(root.get("isDocValidated"), false);
                predicates.add(predicate);
            }


        }


        if (filterUserRequest.getEmail() != null) {
            predicate = cb.like(cb.lower(root.get("email")), "%" + filterUserRequest.getEmail().toLowerCase() + "%");
            predicates.add(predicate);
        }

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        cr.where(finalPredicate);
        Query<Client> query = s.createQuery(cr);
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult((int) pageable.getOffset());
        List<Client> list = query.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Client> countRoot = countQuery.from(Client.class);
        Join<Client, Users> usersJoinCount = countRoot.join("user");

        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long count = s.createQuery(countQuery).getSingleResult();


        s.close();
        return new PageImpl<>(list, pageable, count);
    }

    public void setDeactivationFlag(int clientId, boolean flag){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE Client ct SET ct.isDeactivationRequestCreated=:flag WHERE ct.clientId=:clientId";
        Query<?> q = s.createQuery(queryString);
        q.setParameter("flag", flag);
        q.setParameter("clientId", clientId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

}
