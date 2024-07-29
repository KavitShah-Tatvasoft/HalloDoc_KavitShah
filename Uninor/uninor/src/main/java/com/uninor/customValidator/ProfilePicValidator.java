package com.uninor.customValidator;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ProfilePicValidator implements ConstraintValidator<ValidProfilePic, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!fileExtension.equalsIgnoreCase("jpg") &&!fileExtension.equalsIgnoreCase("jpeg") &&!fileExtension.equalsIgnoreCase("png")) {
            context.buildConstraintViolationWithTemplate("Only JPG, JPEG, and PNG files are allowed")
                    .addConstraintViolation();
            return false;
        }
        if (file.getSize() > 5000000) {
            context.buildConstraintViolationWithTemplate("File size must not exceed 5MB")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

