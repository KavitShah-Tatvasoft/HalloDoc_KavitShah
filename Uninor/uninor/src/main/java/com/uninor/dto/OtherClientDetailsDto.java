package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class OtherClientDetailsDto {

    private int clientId;
    private String clientName;
    private String clientBDate;
    private String clientAadharNumber;
    private String clientPanNumber;
    private String aadharFilePath;
    private String panFilePath;

}


