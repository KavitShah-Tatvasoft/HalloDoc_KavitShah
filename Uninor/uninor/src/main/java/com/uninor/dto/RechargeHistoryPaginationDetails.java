package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor
public class RechargeHistoryPaginationDetails {

    @Pattern(regexp = "\\d+", message = "Current page must be an integer greater than 0")
    private String currentPage;

    @Pattern(regexp = "\\d+", message = "Page Size must be an integer greater than 0")
    private String pageSize;
}
