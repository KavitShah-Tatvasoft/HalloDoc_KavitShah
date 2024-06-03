package com.uninor.repository;

import com.uninor.dto.SignUpDataDto;
import com.uninor.model.Client;
import com.uninor.model.OtpLogs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OTPLogsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public int addOTPLog(OtpLogs otpLogs){
        return (Integer) this.hibernateTemplate.save(otpLogs);
    }

    public List<OtpLogs> getOtpForClient(String email, String otp){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM OtpLogs ol WHERE ol.email=:email AND ol.otpCode=:otp";
        Query<OtpLogs> q = s.createQuery(queryString);
        q.setParameter("email", email);
        q.setParameter("otp", otp);
        List<OtpLogs> list = q.list();
        s.close();
        return list;
    }

}
