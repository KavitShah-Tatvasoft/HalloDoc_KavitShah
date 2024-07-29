package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class ClientFilteredRequests {

    private int requestId;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private int currentRequestStatus;
}
