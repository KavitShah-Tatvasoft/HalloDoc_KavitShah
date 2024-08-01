package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "otp_logs")
public class OtpLogs {

    @Id
    @Column(name="otp_log_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_log_seq")
    @SequenceGenerator(name = "otp_log_seq", allocationSize = 1)
    private int otpLogId;

    @Column(name = "otp_code")
    private String otpCode;

    @CreationTimestamp
    @Column(name="sent_date")
    private LocalDateTime sentDateTime;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

   @Column(name = "is_expired")
   private boolean isExpired;
}
