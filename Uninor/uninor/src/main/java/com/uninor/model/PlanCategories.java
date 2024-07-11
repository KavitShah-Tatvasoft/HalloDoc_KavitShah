package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "plan_categories")
public class PlanCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq")
    @SequenceGenerator(name = "plan_seq", allocationSize = 1)
    @Column(name = "plan_id")
    int planId;

    @Column(name = "plan_category")
    String planCategory;

    @Column(name = "is_deleted")
    boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_date_time")
    LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updated_date_time")
    LocalDateTime updateDateTime;

    @Column(name = "created_by")
    int createdBy;

    @Column(name = "updated_by")
    int updatedBy;

    @Column(name = "sac_code")
    private String sacCode;

    @Column(name = "plan_type")  //1-prepaid 2-postpaid
    private int planType;

    @Column(name = "is_roaming_available")
    private boolean isRoamingAvailable;

    @Column(name = "is_add_on")
    private boolean isAddOn;

    @Column(name = "is_fixed_data")
    private boolean isFixedData;

}
