package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UpdateCouponDetailsDto {

    private int couponId;
    private int couponCategory;
    private String couponName;
    private String couponDescription;
    private String couponReward;
    private int couponValidity;
    private int couponMaxReward;
    private boolean isCouponAvailable;

}
