package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum ClientRequestTypeEnum {

    ACTIVATION(1, "Activation"),
    DEACTIVATION(2, "Deactivation"),
    BLOCK(3, "Block"),
    UNBLOCK(4, "Unblock");

    private final int requestTypeId;
    private final String requestType;

    ClientRequestTypeEnum(int requestTypeId, String requestType) {
        this.requestTypeId = requestTypeId;
        this.requestType = requestType;
    }
}
