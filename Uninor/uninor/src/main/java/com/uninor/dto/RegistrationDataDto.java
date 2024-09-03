package com.uninor.dto;

import com.uninor.customValidator.AtLeast18YearsOld;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDataDto {

    @NotNull(message = "Registration type cannot be null")
    private int selector;

    @NotNull
    private String clientId;

    @NotBlank(message = "First Name cannot be null")
    @Size(min = 3, max = 50, message = "First Name should be between 3 and 50 character")
    private String fname;

    @NotBlank(message = "Last Name cannot be null")
    @Size(min = 3, max = 50, message = "Last Name should be between 3 and 50 character")
    private String lname;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{9}$")
    private String mobileNumber;

    private int mobileId;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Enter a valid email")
    private String email;

    @AtLeast18YearsOld
    @NotBlank(message = "DOB cannot be null")
    private String dob;

    @NotNull(message = "Sim Type cannot be null")
    private int simType;

    private Integer companyId;

    @NotBlank(message = "Street cannot be null")
    @Size(min = 5, max = 50, message = "Street should be between 5 to 50 characters")
    private String street;

    @NotBlank(message = "City cannot be null")
    @Size(min = 3, max = 50, message = "City should be between 3 to 50 characters")
    private String city;

    @NotBlank(message = "State cannot be null")
    @Size(min = 3, max = 50, message = "State should be between 3 to 50 characters")
    private String state;

    @NotBlank(message = "Zipcode cannot be null")
    @Pattern(regexp = "\\d{6,8}", message = "Zipcode should be between 6 to 8 digits")
    private String zipcode;

    @NotBlank(message = "PAN Number cannot be null")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Please provide valid PAN Card number")
    private String panNumber;

    @NotBlank(message = "Aadhar Card Number cannot be null")
    @Pattern(regexp = "^[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4}$", message = "Please provide valid aadhar card number")
    private String aadharCardNumber;

//    @NotBlank(message = "GST Card Number cannot be null")`
//    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$")
    private String GSTNumber;

    @NotNull(message = "Upload PAN Card")
    private CommonsMultipartFile panCardUploadedFile;

    @NotNull(message = "Upload Aadhar Card")
    private CommonsMultipartFile aadharCardUploadedFile;

    @NotNull(message = "Upload Profile Pic")
    private CommonsMultipartFile profilePicUploadedFile;

    public RegistrationDataDto(int selector, String clientId, String fname, String lname, String mobileNumber, int mobileId, String email, String dob, int companyId, String street, String city, String state, String zipcode, String panNumber, String aadharCardNumber, String GSTNumber, CommonsMultipartFile panCardUploadedFile, CommonsMultipartFile aadharCardUploadedFile, CommonsMultipartFile profilePicUploadedFile) {
        this.selector = selector;
        this.clientId = clientId;
        this.fname = fname;
        this.lname = lname;
        this.mobileNumber = mobileNumber;
        this.mobileId = mobileId;
        this.email = email;
        this.dob = dob;
        this.companyId = companyId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.panNumber = panNumber;
        this.aadharCardNumber = aadharCardNumber;
        this.GSTNumber = GSTNumber;
        this.panCardUploadedFile = panCardUploadedFile;
        this.aadharCardUploadedFile = aadharCardUploadedFile;
        this.profilePicUploadedFile = profilePicUploadedFile;
    }
}
