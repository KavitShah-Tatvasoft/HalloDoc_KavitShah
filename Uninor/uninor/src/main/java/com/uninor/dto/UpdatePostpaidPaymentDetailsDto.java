package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class UpdatePostpaidPaymentDetailsDto {

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
    private double taxAmount; /**/
    private double planPriceWithoutTax;
    private String smsUsed;
    private String extraSmsCharges;
    private String extraDataCharges;
    private double smsCharge;
    private String dataUsed;
    private double dataCharges;
    private double totalAmount;
    private double payableAmount;
    private boolean isCuponError;
    private boolean isWalletError;
    private String cuponErrorMessage;
    private String walletErrorMessage;
    private boolean isCuponApplied;
    private double usedWalletAmount;
    private double discountApplied;
    private double finalPayableAmount;
    private double planPriceWithoutWallet;




}
