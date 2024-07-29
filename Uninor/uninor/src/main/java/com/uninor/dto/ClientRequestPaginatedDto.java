package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ClientRequestPaginatedDto {

    private int currentPage;
    private int totalPages;
    private int requestType;
    private List<ClientFilteredRequests> clientFilteredRequests;
    private boolean isDataAvailable;

}
