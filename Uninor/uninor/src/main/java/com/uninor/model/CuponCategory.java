package com.uninor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "cupon_category")
public class CuponCategory {

    @Id
    @Column(name = "cupon_category_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cupon_category_seq")
    @SequenceGenerator(name = "cupon_category_seq", allocationSize = 1)
    private int cuponCategoryId;

    @Column(name = "cupon_category")
    private String cuponCategory;
}
