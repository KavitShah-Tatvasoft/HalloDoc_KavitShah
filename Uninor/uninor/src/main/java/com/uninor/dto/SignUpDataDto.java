package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter @NoArgsConstructor
public class SignUpDataDto {

    @NotBlank(message = "First name should not be empty")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 character")
    private String fname;

    @NotBlank(message = "Last name should not be empty")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 character")
    private String lname;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email should not be null")
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    private String otp;

    @NotBlank
    @Size(min = 1)
    private String clientId;

    public SignUpDataDto(String fname, String lname, String email, String otp, String clientId) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.otp = otp;
        this.clientId = clientId;
    }
}
