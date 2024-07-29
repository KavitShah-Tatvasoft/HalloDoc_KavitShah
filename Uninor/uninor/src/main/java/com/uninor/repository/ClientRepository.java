package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.SimCard;
import com.uninor.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

//    public Client getClientByUserId(int userId){
//        Session s = this.sessionFactory.openSession();
//        String queryString = "SELECT * FROM client ct WHERE ct.user_id=:userId";
//        Query q = s.createNativeQuery(queryString);
//        q.setParameter("userId", userId);
//        Object[] result = (Object[]) q.getSingleResult();
//        s.close();
//
//        Client client
//    }

}
