package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter @NoArgsConstructor
public class CuponDto {

    private int cuponId;
    private String cuponName;
    private String rewardAmount;
    private int maxRewardAmount;

}

