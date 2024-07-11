package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClientCuponDetailsDto {

    private int couponId;
    private String couponCode;
    private String couponDescription;
    private String couponHeading;
    private String expirationDate;
    private boolean isExpired;
    private boolean isUsed;
    private boolean isCashbackAvailable;
    private boolean isDiscountAvailable;
    private double maxRewards;

    public ClientCuponDetailsDto(int couponId, String couponCode, String couponDescription, String couponHeading, String expirationDate, boolean isExpired, boolean isUsed, boolean isCashbackAvailable, boolean isDiscountAvailable, double maxRewards) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.couponDescription = couponDescription;
        this.couponHeading = couponHeading;
        this.expirationDate = expirationDate;
        this.isExpired = isExpired;
        this.isUsed = isUsed;
        this.isCashbackAvailable = isCashbackAvailable;
        this.isDiscountAvailable = isDiscountAvailable;
        this.maxRewards = maxRewards;
    }
}
