package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AllCouponsDetailsDto {

    private int couponId;
    private String couponDescription;
    private String couponHeading;
    private String maxRewards;
    private int couponType;
    private boolean couponAvailable;

}
