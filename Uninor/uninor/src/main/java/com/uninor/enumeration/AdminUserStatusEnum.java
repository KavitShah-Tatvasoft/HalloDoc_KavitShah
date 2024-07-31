package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum AdminUserStatusEnum {

    REGISTERED(1, "Registered"),
    PENDING(2, "Pending"),
    SIGNED_UP(3, "SignedUp");

    private final int userStatusId;
    private final String userStatus;

    AdminUserStatusEnum(int userStatusId, String userStatus) {
        this.userStatusId = userStatusId;
        this.userStatus = userStatus;
    }
}
