package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowPlanDto {

    private String planCategory;
    private List<PlanDetailsDto> planList;
    private int planCount;
    private boolean isPrepaid;
    
}
