package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_table")
@Getter @Setter @NoArgsConstructor
public class OrderTable {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", allocationSize = 1)
    private int orderId;

    @Column(name = "order_refrence")
    private String orderRef;

    @ManyToOne
    @JoinColumn(name = "sim_id")
    private SimCard simCard;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

//    @Column(name = "client_id")
//    private int clientId;

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_status") // 1 - Pending, 2 - Completed, 3 - Cancelled
    private int orderStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "total_discount")
    private Double totalDiscount;

    @Column(name = "payment_refrence")
    private String paymentRefrence;



}
