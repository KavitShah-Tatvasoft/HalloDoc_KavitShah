package com.uninor.dto;

import com.uninor.customValidator.AtLeast18YearsOld;
import com.uninor.customValidator.NullOrValidFileFormat;
import com.uninor.customValidator.ValidDateOfBirth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor
public class ResubmitDocumentsDto {

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$", message = "Invalid Token")
    private String token;

//    @ValidDateOfBirth
    @AtLeast18YearsOld
    private String dob;

    @Pattern(regexp = "^(?:[2-9]{1}[0-9]{3}[0-9]{4}[0-9]{4})?$", message = "Please provide valid aadhar card number")
    private String aadharCardNumber;

    @Pattern(regexp = "^(?:[A-Z]{5}[0-9]{4}[A-Z]{1})?$", message = "Please provide valid PAN Card number")
    private String panNumber;

    @NullOrValidFileFormat
    private CommonsMultipartFile aadharCardUploadedFile;

    @NullOrValidFileFormat
    private CommonsMultipartFile panCardUploadedFile;

}
