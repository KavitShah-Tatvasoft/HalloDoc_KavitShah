package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor
public class LoginDto {

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Mobile number must consist of 10 digits only.")
    private String number;

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$", message = "OTP must consist of 6 digits only.")
    private String otp;
}
