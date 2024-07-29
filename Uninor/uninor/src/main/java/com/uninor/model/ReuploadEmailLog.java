package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reupload_email_log")
@Getter @Setter @NoArgsConstructor
public class ReuploadEmailLog {

    @Id
    @Column(name="email_log_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reupload_email_log_seq")
    @SequenceGenerator(name = "reupload_email_log_seq", allocationSize = 1)
    private int emailLogId;

    @Column(name = "log_token")
    private String logToken;

    @Column(name = "email")
    private String email;

    @Column(name = "isExpired")
    private boolean isExpired;

    @CreationTimestamp
    @Column(name = "sent_date")
    private LocalDateTime sentDate;

}
