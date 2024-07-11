package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client_daily_usage")
@Getter @Setter @NoArgsConstructor
public class ClientDailyDataUsage {

    @Id
    @Column(name = "usage_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usage_seq")
    @SequenceGenerator(name = "usage_seq", allocationSize = 1)
    private int usageId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "usage_date")
    private LocalDate usageDate;

    @Column(name = "daily_usage")
    private double dailyUsage;

}
