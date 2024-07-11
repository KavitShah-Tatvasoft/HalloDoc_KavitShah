package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RechargePlanFilter {

    private boolean isfilterApplied;
    private String refreshingPlanFilters;
    private String fixedDataPlanFilters;
    private String amountFilters;
    private String validityFilters;
    private String addOnDataFilters;
    private String roamingDataFilters;


}

