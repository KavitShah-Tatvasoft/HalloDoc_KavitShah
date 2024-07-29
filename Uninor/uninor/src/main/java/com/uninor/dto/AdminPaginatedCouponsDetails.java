package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class AdminPaginatedCouponsDetails {

    private int pages;
    private List<AllCouponsDetailsDto> allCouponsDetailsDtos;

}
