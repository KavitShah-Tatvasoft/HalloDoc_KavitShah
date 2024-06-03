package com.uninor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequestDto {

    @NotBlank(message = "First name should not be empty")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 character")
    private String fname;

    @NotBlank(message = "Last name should not be empty")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 character")
    private String lname;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email should not be null")
    private String email;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignupRequestDto(String fname, String lname, String email) {
        super();
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public SignupRequestDto() {
        super();
    }
}
