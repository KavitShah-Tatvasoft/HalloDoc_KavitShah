package com.uninor.repository;

import com.uninor.model.ClientCupons;
import com.uninor.model.Notification;
import com.uninor.model.NotificationType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public Object[] getCurrentAvailableClientCoupons(int clientId){
        Session s = this.sessionFactory.openSession();
        String queryString = "FROM Notification nt WHERE nt.client.clientId =: clientId AND nt.isDeleted =false ORDER BY nt.createdDate DESC";
        String query = "FROM Notification nt WHERE nt.client.clientId =: clientId AND nt.isRead =false";
        Query<Notification> q = s.createQuery(queryString);
        Query<Notification> q1 = s.createQuery(query);
        q.setParameter("clientId", clientId);
        q1.setParameter("clientId", clientId);
        List<Notification> list = q.list();
        List<Notification> list1 = q1.list();

        Object[] result = new Object[2];
        result[0] = list;
        result[1] = list1.size();

        s.close();
        return result;
    }

    public void deleteAllClientNotification(int clientId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE Notification nt SET nt.isDeleted=true, nt.isRead=true WHERE nt.client.clientId =: clientId";
        Query<Notification> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    public void deleteNotification(int notificationId, int clientId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE Notification nt SET nt.isDeleted=true, nt.isRead=true WHERE nt.client.clientId =: clientId AND nt.notificationId=:notificationId";
        Query<Notification> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.setParameter("notificationId", notificationId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    public void updateClientNotificatinReadReciepts(int clientId){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String queryString = "UPDATE Notification nt SET nt.isRead=true WHERE nt.client.clientId =: clientId";
        Query<Notification> q = s.createQuery(queryString);
        q.setParameter("clientId", clientId);
        q.executeUpdate();
        tx.commit();
        s.close();
    }

    public NotificationType getNotificationTypeObj(int notificationId){
        return (NotificationType) this.hibernateTemplate.get(NotificationType.class, notificationId);
    }

    public void saveAllNotification(List<Notification> notificationList){
        Session s = this.sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        for (Notification notification : notificationList) {
            s.save(notification);
        }
        tx.commit();
        s.close();
    }



}
