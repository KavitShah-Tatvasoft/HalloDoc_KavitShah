package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor
public class FilterUserRequest {

    @Min(1)
    @Max(4)
    private int requestType;

    private String email;

    private String mobile;

    @Min(0)
    @Max(3)
    private int statusType;

    @Min(5)
    @Max(20)
    private int pageSize;

    @Min(1)
    private int currentPage;

}
