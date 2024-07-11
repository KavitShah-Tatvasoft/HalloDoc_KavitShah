package com.uninor.model;

import com.itextpdf.commons.bouncycastle.asn1.x509.ICRLReason;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "all_plan_seq")
    @SequenceGenerator(name = "all_plan_seq", allocationSize = 1)
    @Column(name = "plan_id")
    private int planId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private PlanCategories categoryId;

    @Column(name = "plan_code")
    private String planCode;

    private int validity;

    @Column(name = "recharge_amount")
    private Double rechargeAmount;

    @Column(name = "data_allowance")
    private Double dataAllowance;

    @Column(name = "sms_allowance")
    private int smsAllowance;

    @Column(name = "is_refreshing")
    private Boolean isRefreshing;

    @Column(name = "bought_count")
    private Integer boughtCount;

    @Column(name = "isExtraDataAvailable")
    private Boolean isExtraDataAvailable;

    @Column(name = "extra_data")
    private Double extraData;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "modified_by")
    private int modidfiedBy;

    @CreationTimestamp
    @Column(name="created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "voice_allowance")
    private String voiceAllowance;

}
