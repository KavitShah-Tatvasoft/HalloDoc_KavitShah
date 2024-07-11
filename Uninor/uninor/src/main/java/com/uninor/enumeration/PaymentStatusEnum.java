package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {

    PAID(1, "Paid"),
    UNPAID(2, "Unpaid");

    private final int paymentStatusId;
    private final String paymentStatus;

    PaymentStatusEnum(int paymentStatusId, String paymentStatus) {
        this.paymentStatusId = paymentStatusId;
        this.paymentStatus = paymentStatus;
    }
}
