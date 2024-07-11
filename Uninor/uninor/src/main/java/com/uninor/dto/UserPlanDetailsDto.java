package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserPlanDetailsDto {

    private int planId;
    private Double planAmount;
    private int planValidity;
    private Double dataAmount;
    private Double totalData;
    private int smsAllowance;
    private Double additionalData;
    private boolean isDailyRefreshing;
    private String voiceAllowance;
    private Double walletAmount;
    private Double taxAmount;
    private Double planPriceWithoutTax;
}
