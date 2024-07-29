package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notification_type")
@Getter @Setter @NoArgsConstructor
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_type_seq")
    @SequenceGenerator(name = "notification_log_seq", sequenceName = "notification_type_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private int notificationId;

    @Column(name = "notification_type")
    private String notificationType;

}
