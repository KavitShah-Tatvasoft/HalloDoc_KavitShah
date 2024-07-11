package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plan_activation")
@Getter @Setter @NoArgsConstructor
public class PlanActivation {

    @Id
    @Column(name="plan_activation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_activation_seq")
    @SequenceGenerator(name = "plan_activation_seq", allocationSize = 1)
    private int planActivationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sim_card_id")
    private SimCard simCard;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_usage_id")
    private PlanUsage planUsage;

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
