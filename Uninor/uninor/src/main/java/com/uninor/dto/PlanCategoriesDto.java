package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PlanCategoriesDto {

    private int planCategoryId;
    private String categoryName;

    public PlanCategoriesDto(int planCategoryId, String categoryName) {
        this.planCategoryId = planCategoryId;
        this.categoryName = categoryName;
    }
}
