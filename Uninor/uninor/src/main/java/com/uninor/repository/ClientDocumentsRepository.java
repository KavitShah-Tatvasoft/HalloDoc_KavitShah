package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.ClientDocuments;
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
public class ClientDocumentsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void updateClientDocuments(ClientDocuments clientDocuments) {
        this.hibernateTemplate.update(clientDocuments);
    }

    public ClientDocuments getClientDocumentDetails(int clientId){

        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientDocuments cd WHERE cd.client.clientId=:clientId";
        Query<ClientDocuments> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        List<ClientDocuments> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public ClientDocuments getClientDocumentByEmail(String email){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM ClientDocuments cd WHERE cd.client.email=:email";
        Query<ClientDocuments> q = s.createQuery(queryString);
        q.setParameter("email", email);
        List<ClientDocuments> list = q.list();
        s.close();
        return list.isEmpty()?null:list.get(0);
    }

    public void deleteClientDocuments(int clientId) {
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "DELETE FROM ClientDocuments cd WHERE cd.client.clientId=:clientId";
        Query<?> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

}
