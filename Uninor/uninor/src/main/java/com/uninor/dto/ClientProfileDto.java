package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class ClientProfileDto {

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
    private String panNumber;
    private String aadharCardNumber;
    private String gstNumber;

}


