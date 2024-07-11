package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client_cupons")
public class ClientCupons {

    @Id
    @Column(name = "client_cupon")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_cupon_seq")
    @SequenceGenerator(name = "client_cupon_seq", allocationSize = 1)
    private int clientCuponId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cupon_id")
    private Cupon cupon;

    @Column(name = "cupon_code")
    private String cuponCode;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_used")
    private boolean isUsed;

}
