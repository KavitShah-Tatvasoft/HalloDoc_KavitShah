package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SimSuggestionsDto {

private int simCardId;
private String phoneNumber;

    public SimSuggestionsDto(int simCardId, String phoneNumber) {
        this.simCardId = simCardId;
        this.phoneNumber = phoneNumber;
    }
}
