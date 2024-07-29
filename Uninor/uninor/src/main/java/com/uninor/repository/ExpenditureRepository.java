package com.uninor.repository;

import com.uninor.model.ClientDailyDataUsage;
import com.uninor.model.Expenditure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenditureRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Expenditure> getLastMonthData(LocalDate startDate, LocalDate endDate){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Expenditure exp WHERE exp.date BETWEEN :startDate AND :endDate ORDER BY exp.date ASC";
        Query<Expenditure> q = s.createQuery(queryString);
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        List<Expenditure> list = q.list();
        s.close();
        return list;
    }

}
