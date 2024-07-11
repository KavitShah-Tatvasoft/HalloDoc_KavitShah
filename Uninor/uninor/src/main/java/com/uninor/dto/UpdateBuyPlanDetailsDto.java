package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter @Setter @NoArgsConstructor
public class UpdateBuyPlanDetailsDto {

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
    private int cuponId;
    private String cuponName;
    private String rewardAmount;
    private int maxRewardAmount;
    private double discountApplied;
    private double usedWalletAmount;
    private double finalPayableAmount;
    private double planPriceWithoutWallet;
    private boolean isCuponError;
    private boolean isWalletError;
    private String cuponErrorMessage;
    private String walletErrorMessage;
    private boolean isCuponApplied;
}
