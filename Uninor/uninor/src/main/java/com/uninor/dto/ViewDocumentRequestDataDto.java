package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ViewDocumentRequestDataDto {

    private int requestId;
    private String userName;
    private String birthDate;
    private String aadharCardFilePath;
    private String panCardFilePath;
    private boolean isAadharCardVerified;
    private boolean isPanCardVerified;
    private String aadharCardNumber;
    private String panCardNumber;

}
