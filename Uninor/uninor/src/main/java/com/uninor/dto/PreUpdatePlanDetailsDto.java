package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter @NoArgsConstructor
public class PreUpdatePlanDetailsDto {

    private Integer planId;

    @NotNull
    @Min(1)
    private int planCategoryId;

    @NotNull
    @Min(19)
    @Max(9999)
    private double planAmount;

    @NotNull
    @Max(1000)
    private int smsAllowance;

    @Pattern(regexp = "^(Unlimited|\\d+ minutes)$")
    private String voiceAllowance;

    @NotNull
    private double dataAllowance;

    @NotNull
    private double extraDataAllowance;

    @NotNull
//    @Min(1)  checked for min in service based on category
    @Max(365)
    private int validityPeriod;

    @NotNull
    @Min(0)
    @Max(1)
    private int isAvailable;

    @NotNull
    @Min(0)
    @Max(1)
    private int extraDataAvailable;

    @NotNull
    @Min(0)
    @Max(1)
    private int dailyRefresh;

    @NotNull
    @Min(0)
    @Max(1)
    private int isNewPlan;

}
