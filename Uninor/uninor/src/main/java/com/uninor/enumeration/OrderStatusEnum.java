package com.uninor.enumeration;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum OrderStatusEnum {

    PENDING(1, "Pending"),
    COMPLETED(2, "Completed"),
    CANCELLED(3, "Cancelled");


    private final int orderStatusId;
    private final String orderStatus;

    OrderStatusEnum(int orderStatusId, String orderStatus) {
        this.orderStatusId = orderStatusId;
        this.orderStatus = orderStatus;
    }
}
