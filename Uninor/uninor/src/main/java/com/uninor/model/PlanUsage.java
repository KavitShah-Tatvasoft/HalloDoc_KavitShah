package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plan_usage")
@Getter @Setter @NoArgsConstructor
public class PlanUsage {

    @Id
    @Column(name="plan_usage_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_usage_seq")
    @SequenceGenerator(name = "plan_usage_seq", allocationSize = 1)
    private int planUsageId;

    @Column(name = "data_usage")
    private double dataUsage;

    @Column(name = "sms_usage")
    private int smsUsage;

    @Column(name = "additional_data")
    private double additionalData;

    @Column(name = "is_postpaid_data_over")
    private boolean isPostpaidDataOver;
}
