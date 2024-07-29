package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter @Getter @NoArgsConstructor
public class CouponsDetailsFilterDto {

    @Min(5)
    @Max(20)
    private int pageSize;

    @Min(1)
    @Max(3)
    private int couponType;

    @Min(1)
    private int currentPage;

}
