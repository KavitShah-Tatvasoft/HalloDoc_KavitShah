package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum NotificationTypeEnum {

    INTERNET_USAGE(1, "Internet Usage"),
    PLAN_EXPIRATION(2, "Plan Expiration"),
    RECHARGE_SUCCESSFUL(3, "Recharge Succesful"),
    COUPON(4,"Coupon");


    private final int notificationTypeId;
    private final String notificationType;

    NotificationTypeEnum(int notificationTypeId, String notificationType) {
        this.notificationTypeId = notificationTypeId;
        this.notificationType = notificationType;
    }
}
