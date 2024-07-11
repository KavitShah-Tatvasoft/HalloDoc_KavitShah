package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice_table")
@Getter @Setter @NoArgsConstructor
public class InvoiceTable {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq", allocationSize = 1)
    private int invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private OrderTable orderTable;

    @CreationTimestamp
    @Column(name = "invoice_date")
    private LocalDateTime invoiceDate;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "taxable_amount")
    private double taxableAmount;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "tax_amount")
    private double taxAmount;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "payment_staus") // 1 - Paid, 2 - Unpaid
    private int paymentStatus;

    @Column(name = "wallet_amount_used")
    private double walletAmountUsed;

    @Column(name = "extra_data_used")
    private double extraDataUsed;

    @Column(name = "extra_data_unit_charges")
    private double extraDataUnitCharges;

    @Column(name = "extra_data_charges")
    private double extraDataCharges;

    @Column(name = "extra_sms_used")
    private int extraSmsUsed;

    @Column(name = "extra_sms_unit_charges")
    private double extraSmsUnitCharges;

    @Column(name = "extra_sms_charges")
    private double extraSmsCharges;

    @Column(name = "plan_bought_date")
    private LocalDateTime planBoughtDate;

}


