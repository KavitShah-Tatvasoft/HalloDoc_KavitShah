package com.uninor.repository;

import com.uninor.model.Client;
import com.uninor.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public void saveClient(Client client){
        this.hibernateTemplate.save(client);
    }

    public Client getClientById(int id){
        return this.hibernateTemplate.get(Client.class, id);
    }
}
