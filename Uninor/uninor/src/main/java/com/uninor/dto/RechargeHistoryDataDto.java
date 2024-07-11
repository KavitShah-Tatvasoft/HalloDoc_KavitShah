package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class RechargeHistoryDataDto {

    private int currentPage;
    private int totalPages;
    private boolean isPaidDataAvailable;
    private boolean isUnpaidDataAvailable;
    private List<RechargeHistoryDashboardDataDto> paidList;
    private RechargeHistoryDashboardDataDto unpaidPlan;

}
