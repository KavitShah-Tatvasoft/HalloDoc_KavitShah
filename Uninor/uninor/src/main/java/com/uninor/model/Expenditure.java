package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenditure")
@Getter @Setter @NoArgsConstructor
public class Expenditure {

    @Id
    @Column(name = "expenditure_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenditure_seq")
    @SequenceGenerator(name = "expenditure_seq", allocationSize = 1)
    private int expenditureId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private double amount;

}
