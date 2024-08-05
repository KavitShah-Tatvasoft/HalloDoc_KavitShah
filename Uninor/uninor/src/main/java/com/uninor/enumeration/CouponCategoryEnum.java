package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum CouponCategoryEnum{

    CASHBACK(1, "Cashback"),
    FLAT_DISCOUNT(2, "Flat Discount"),
    DATA_PACKS(3, "Data Packs"),
    NO_REWARD(4, "No Rewards");

    private final int couponCategoryId;
    private final String couponCategory;

    CouponCategoryEnum(int couponCategoryId, String couponCategory) {
        this.couponCategoryId = couponCategoryId;
        this.couponCategory = couponCategory;
    }
}
