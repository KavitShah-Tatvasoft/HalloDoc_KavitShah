package com.uninor.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter @Setter @NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    @SequenceGenerator(name = "notification_log_seq", sequenceName = "notification_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private int notificationId;

    @Column(name = "notification_header")
    private String notificationHeader;

    private String message;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_read")
    private boolean isRead;

    @OneToOne
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}

