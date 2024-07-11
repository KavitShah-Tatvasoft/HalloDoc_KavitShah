package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "roaming_activation")
@Getter @Setter @NoArgsConstructor
public class RoamingActivation {

    @Id
    @Column(name="roaming_activation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roaming_activation_seq")
    @SequenceGenerator(name = "roaming_activation_seq", allocationSize = 1)
    private int roamingActivationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sim_card_id")
    private SimCard simCard;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan roamingPlan;

    @Column(name = "data_usage")
    private Double roamingDataUsage;

    @Column(name = "sms_usage")
    private int smsUsage;

    @Column(name = "voice_usage")
    private int voiceUsage;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @CreationTimestamp
    @Column(name = "bought_date")
    private LocalDateTime boughtDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_service_change")
    private boolean isServiceChange;

    @Column(name = "is_reactive_available")
    private boolean isReactiveAvailable;
}
