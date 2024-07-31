package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PlanDetailsDto {

    private int planId;
    private Double planAmount;
    private int planValidity;
    private Double dataAmount;
    private Double totalData;
    private int smsAllowance;
    private Double additionalData;
    private boolean isDailyRefreshing;
    private String voiceAllowance;

}
