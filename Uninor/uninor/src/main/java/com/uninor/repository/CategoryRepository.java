package com.uninor.repository;

import com.uninor.model.Plan;
import com.uninor.model.PlanCategories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public class CategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<PlanCategories> getAllAvailableCategory() {
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM PlanCategories WHERE isDeleted=false ORDER BY planId";
        Query<PlanCategories> q = s.createQuery(queryString);
        List<PlanCategories> list = q.list();
        s.close();
        return list;
    }

}
