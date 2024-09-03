package com.uninor.customValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AtLeast18YearsOldValidator implements ConstraintValidator<AtLeast18YearsOld, LocalDate> {

    @Override
    public void initialize(AtLeast18YearsOld constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return Period.between(dateOfBirth, today).getYears() >= 18;
    }
}
