package com.uninor.customValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {
    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            if (dob.isAfter(LocalDate.now())) {
                context.buildConstraintViolationWithTemplate("Date of birth cannot be a future date")
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            context.buildConstraintViolationWithTemplate("Invalid date format. Please use yyyy-MM-dd")
                    .addConstraintViolation();
            return false;
        }
    }



}