package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
public class CreateNewCouponDto {

    @Min(1)
    @Max(4)
    private int couponType;

    @Size(min = 1, max = 50)
    private String couponName;

    @Min(5)
    @Max(100)
    private int rewardPercentage;

    @Min(250)
    private int rewardMB;

    @Min(1)
    @Max(365)
    private int validity;

    @Min(10)
    private int maxReward;

    @Size(min = 10, max = 150)
    private String couponDescription;

}
