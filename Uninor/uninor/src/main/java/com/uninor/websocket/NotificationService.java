package com.uninor.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

//@Service
//public class NotificationService {
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    public void sendNotification(Long clientId, String notificationMessage) {
//        messagingTemplate.convertAndSend("/topic/notifications/" + clientId, notificationMessage);
//    }
//}