package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PostPaidPlanDetailsPaymentDto {

    private int planId;
    private double planAmount;
    private int planValidity;
    private double dataAmount;
    private double totalData;
    private int smsAllowance;
    private double additionalData;
    private boolean isDailyRefreshing;
    private String voiceAllowance;
    private double walletAmount;
    private double taxAmount;
    private double planPriceWithoutTax;
    private String smsUsed;
    private String extraSmsCharges;
    private String extraDataCharges;
    private double smsCharge;
    private String dataUsed;
    private double dataCharges;
    private double totalAmount;
    private double payableAmount;

}
