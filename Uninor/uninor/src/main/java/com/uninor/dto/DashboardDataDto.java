package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class DashboardDataDto {

    private String clientName;
    private int clientId;
    private boolean isRoamingActive;
    private boolean isSimPrepaid;
    private String mobileType;
    private String mobileNumber;
    private String walletAmount;
    private boolean isPlanExpired;
    private String remainingDataAmount;
    private String totalDataAmount;
    private String availableDataPercentage;
    private String renewTime;
    private String planAmount;
    private int planId;
    private String planExpiryDate;
    private boolean isPostpaidExtraDataUsed;
    private List<String> topCategoryName;
    private List<Integer> topCategoryBoughtCount;
    private List<String> usageDateList;
    private List<Double> usageAmountList;
    private String voiceUsage;
    private boolean isSimBlocked;
}
