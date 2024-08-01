package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UsersDetailsDto {

    private int clientId;
    private String clientName;
    private String clientEmail;
    private int currentRequestStatus;
}
