package com.uninor.enumeration;

import lombok.Getter;

@Getter
public enum PlanCategoryEnum {

    GB_1_DAY(1,"1 GB/Day"),
    GB_2_DAY(3,"2 GB/Day"),
    GB_2_5_DAY(4,"2.5 GB/Day"),
    GB_3_DAY(5,"3 GB/Day"),
    FIXED_DATA_PLAN(6,"Fixed Data Plan"),
    ADD_ON_DATA(7,"Add On Data Plan"),
    POST_PAID_PLANS(8,"Post Paid Plans"),
    PREPAID_INTERNATIONAL_PLANS(9,"Prepaid International Roaming Plans"),
    GB_1_5_DAY(2,"1.5 GB/Day");

    private final int categoryId;
    private final String categoryName;

    PlanCategoryEnum(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
