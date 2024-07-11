package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SimDetailsDto {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String iccidNumber;
    private String imsiNumber;
    private String imeiNumber;
    private String pukNumber;
    private String simType;
    private String blockStatus;
    private String roamingStatus;

}
