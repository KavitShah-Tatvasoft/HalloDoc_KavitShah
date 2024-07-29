package com.uninor.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public String sendNotification(String message) throws Exception {
        return message;
    }

    public void notifyClients(String message) {
        this.template.convertAndSend("/topic/notifications", message);
    }
}
