package com.uninor.customValidator;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class NullOrFileFormatValidator implements ConstraintValidator<NullOrValidFileFormat, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if(file == null || file.isEmpty()){
            return true;
        }

        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!fileExtension.equalsIgnoreCase("jpg") &&!fileExtension.equalsIgnoreCase("jpeg") &&!fileExtension.equalsIgnoreCase("png") &&!fileExtension.equalsIgnoreCase("pdf")) {
            context.buildConstraintViolationWithTemplate("Only JPG, JPEG, PNG and PDF files are allowed")
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

    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "pdf");


//    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
//        // Allow null or empty files
////        if (file == null || file.isEmpty()) {
////            System.out.println("File is null or empty");
////            return true;
////        }
////
////        // Get the file extension
////        String fileName = file.getOriginalFilename();
////        if (fileName == null || !fileName.contains(".")) {
////            addConstraintViolation(context, "File must have a valid extension.");
////            return false;
////        }
////
////        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
////
////        // Check if the extension is in the allowed list
////        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
////            addConstraintViolation(context, "Only JPG, JPEG, PNG, and PDF files are allowed.");
////            return false;
////        }
////
////        // Check file size (5MB in bytes)
////        if (file.getSize() > 5000000) {
////            addConstraintViolation(context, "File size must not exceed 5MB.");
////            return false;
////        }
//
//        return true;
//    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
