package com.uninor.dto;

import com.uninor.customValidator.ValidProfilePic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
public class UpdateProfileDetailsDto {

    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{3,50}$")
    private String lastName;

    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 50)
    private String street;

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NotBlank
    @Size(min = 3, max = 50)
    private String state;

    @Pattern(regexp = "^[0-9]{6,8}$")
    private String zipcode;

    private String gstNumber;

}
