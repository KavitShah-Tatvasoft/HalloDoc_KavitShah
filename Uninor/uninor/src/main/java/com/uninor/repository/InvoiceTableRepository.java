package com.uninor.repository;

import com.uninor.dto.RechargeHistoryDashboardDataDto;
import com.uninor.model.InvoiceTable;
import com.uninor.model.OtpLogs;
import com.uninor.model.RoamingActivation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class InvoiceTableRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public int saveInvoiceTableDetails(InvoiceTable invoiceTable) {
        return (Integer)this.hibernateTemplate.save(invoiceTable);
    }

    public List<InvoiceTable> getInvoiceDetails(int clientId, int invoiceId) {
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM InvoiceTable ivt JOIN FETCH ivt.client WHERE ivt.client.clientId =: clientId AND ivt.invoiceId=:invoiceId";
        Query<InvoiceTable> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setParameter("invoiceId", invoiceId);
        List<InvoiceTable> list = q.list();
        s.close();
        return list;
    }

    public Page<InvoiceTable> getPaginatedInvoiceData(Pageable pageable, int clientId, String mobileNumber){
        Session s = this.sessionFactory.openSession();
        String queryString = "SELECT DISTINCT ivt FROM InvoiceTable ivt JOIN FETCH ivt.client WHERE ivt.client.clientId =: clientId AND ivt.mobileNumber=:mobileNumber ORDER BY ivt.invoiceDate DESC";
        Query<InvoiceTable> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setParameter("mobileNumber", mobileNumber);
        q.setMaxResults(pageable.getPageSize());
        q.setFirstResult((int) pageable.getOffset());
        List<InvoiceTable> list = q.list();

        Query<Long> countQuery = s.createQuery("SELECT COUNT(DISTINCT ivt.invoiceId) FROM InvoiceTable ivt JOIN ivt.client cl WHERE cl.clientId =: clientId AND ivt.mobileNumber=:mobileNumber", Long.class);
        countQuery.setParameter("clientId", clientId);
        countQuery.setParameter("mobileNumber", mobileNumber);
        Long count = countQuery.uniqueResult();

        s.close();
        return new PageImpl<>(list, pageable, count);

    }

}
