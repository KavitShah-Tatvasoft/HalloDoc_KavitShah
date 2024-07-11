package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum SimType {

    PREPAID(1, "Prepaid"),
    POSTPAID(2, "Postpaid");


    private final int simCardTypeId;
    private final String simCardType;

    SimType(int simCardTypeId, String simCardType) {
        this.simCardTypeId = simCardTypeId;
        this.simCardType = simCardType;
    }
}
