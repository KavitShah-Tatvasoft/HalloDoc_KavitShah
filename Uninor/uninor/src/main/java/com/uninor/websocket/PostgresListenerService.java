package com.uninor.websocket;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class PostgresListenerService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private NotificationController notificationController;

    private PGConnection pgConnection;

    @PostConstruct
    public void init() throws Exception {
        Connection connection = dataSource.getConnection();
        pgConnection = connection.unwrap(PGConnection.class);
        Statement stmt = connection.createStatement();
        stmt.execute("LISTEN table_change");
        stmt.close();
    }

    @Async("taskExecutor")
    @Scheduled(fixedDelay = 1000)
    public void checkForNotifications() throws Exception {
        PGNotification[] notifications = pgConnection.getNotifications();
        if (notifications != null) {
            for (PGNotification notification : notifications) {
                notificationController.notifyClients(notification.getParameter());
            }
        }
    }
}