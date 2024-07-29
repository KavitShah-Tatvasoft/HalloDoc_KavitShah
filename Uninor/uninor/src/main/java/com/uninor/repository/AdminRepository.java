package com.uninor.repository;

import com.uninor.model.Admin;
import com.uninor.model.Client;
import com.uninor.model.PlanCategories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public Admin getAdminByEmail(String email) {
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Admin WHERE email=:email AND isDeleted=false";
        Query<Admin> q = s.createQuery(queryString);
        q.setParameter("email", email);
        List<Admin> list = q.list();
        s.close();
        return list.isEmpty() ? null : list.get(0);
    }

    public Admin getAdminById(int adminId) {
        return hibernateTemplate.get(Admin.class, adminId);
    }

    @Transactional
    public void updateAdmin(Admin admin) {
        this.hibernateTemplate.update(admin);
    }
}
