package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sim_card")
public class SimCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sim_card_seq")
    @SequenceGenerator(name = "sim_card_seq", allocationSize = 1)
    @Column(name = "sim_card_id")
    private int simCardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @Column(name = "iccid_number")
    private String iccidNumber;

    @Column(name = "imsi_number")
    private String imsiNumber;

    @Column(name = "imei_number")
    private String imeiNumber;

    @Column(name = "sim_type") // 1- prepaid 2-postpaid
    private int simType;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "puk_number")
    private String pukNumber;

    @Column(name = "status")
    private boolean status;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "is_roaming_active")
    private boolean isRoamingActive;

    @Column(name = "is_plan_active")
    private boolean isPlanActive;

    @Column(name = "sim_accquired_type")
    private int simAcquiredType;

    public SimCard(int simCardId, Client client, String phoneNumber, LocalDateTime activationDate, String iccidNumber, String imsiNumber, String imeiNumber, int simType, boolean isAvailable, String pukNumber, boolean status, boolean isBlocked, boolean isRoamingActive, boolean isPlanActive) {
        this.simCardId = simCardId;
        this.client = client;
        this.phoneNumber = phoneNumber;
        this.activationDate = activationDate;
        this.iccidNumber = iccidNumber;
        this.imsiNumber = imsiNumber;
        this.imeiNumber = imeiNumber;
        this.simType = simType;
        this.isAvailable = isAvailable;
        this.pukNumber = pukNumber;
        this.status = status;
        this.isBlocked = isBlocked;
        this.isRoamingActive = isRoamingActive;
        this.isPlanActive = isPlanActive;
    }
}
