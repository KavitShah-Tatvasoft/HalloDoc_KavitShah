package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum RequestStatusEnum {

    PENDING(1, "Pending"),
    COMPLETED(2, "Completed");

    private final int requestStatusId;
    private final String requestStatusType;

    RequestStatusEnum(int requestStatusId, String requestStatusType) {
        this.requestStatusId = requestStatusId;
        this.requestStatusType = requestStatusType;
    }
}
