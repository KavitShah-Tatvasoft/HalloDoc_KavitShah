package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class AdminDashboardDataDto {

    private String totalRegisteredUsers;
    private String totalSignedUpUsers;
    private String totalGeneratedRevenue;
    private List<String> topCategoryName;
    private List<Integer> topCategoryBoughtCount;
    private List<String> currentMonthDateList;
    private List<Double> currentMonthExpenditureList;

}
