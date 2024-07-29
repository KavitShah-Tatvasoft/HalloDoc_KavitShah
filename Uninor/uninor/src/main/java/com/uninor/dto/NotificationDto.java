package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NotificationDto {

    private int notificationId;
    private String header;
    private String message;
    private int notificationType;
    private String sendDate;
    private int newCount;

}
