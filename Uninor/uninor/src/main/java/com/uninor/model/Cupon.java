package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cupon")
public class Cupon {

    @Id
    @Column(name = "cupon_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupon_seq")
    @SequenceGenerator(name = "cupon_seq", allocationSize = 1)
    private int cuponId;

    @Column(name = "cupon_name")
    private String cuponName;

    @Column(name = "description")
    private String description;

    @Column(name = "validity_period")
    private int validityPeriod;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @OneToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

//    @Column(name = "modified_by")
//    private int modifiedBy;

    @OneToOne
    @JoinColumn(name = "modified_by")
    private Admin modifiedBy;

    @OneToOne
    @JoinColumn(name = "cupon_category")
    private CuponCategory cuponCategory;

    @Column(name = "reward_amount")
    private String rewardAmount;

    @Column(name = "max_reward_amount")
    private int maxRewardAmount;

    @Column(name = "is_deductable")
    private boolean isDeductable;

    @Column(name = "is_cashback")
    private boolean isCashback;

    @Column(name = "is_available")
    private boolean isAvailable;
}
