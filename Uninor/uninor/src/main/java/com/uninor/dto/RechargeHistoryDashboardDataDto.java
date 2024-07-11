package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RechargeHistoryDashboardDataDto {
    private int invoiceId;
    private String paymentMethod;
    private String paymentReference;
    private String planAmount;
    private String planBoughtDate;
    private String invoiceNumber;
}
