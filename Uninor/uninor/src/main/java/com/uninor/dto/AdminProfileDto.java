package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AdminProfileDto {

    private String profilePicPath;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String birthDate;
    private String street;
    private String city;
    private String state;
    private String zipcode;

}
