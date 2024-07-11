package com.uninor.exceptions;

import com.uninor.helper.ErrorsMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Map<String, String>> handleFileErrors(InvalidFileException ex) {
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("errors", ex.getMessage());
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SimNotAvailableException.class)
    public ResponseEntity<Map<String, String>> handleSimCardUnavailableErrors(SimNotAvailableException ex) {
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("errors", ex.getMessage());
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataUnavailabilityError(DataNotFoundException ex) {
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("errors", ex.getMessage());
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataFoundError(InvalidDataFoundException ex) {
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("errors", ex.getMessage());
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        System.out.println("Validation Errors");
        Set<String> errorsSet = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getField).collect(Collectors. toCollection(TreeSet::new));
        Iterator<String> namesIterator = errorsSet.iterator();
        StringBuilder message = new StringBuilder("Please provide valid ");

        while(namesIterator.hasNext()) {
            message.append(ErrorsMapper.globalMap.get(namesIterator.next()));
            if(namesIterator.hasNext()) {
                message.append(",");
            }
        }
        message.append(".");

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getField).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(message.toString()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getErrorsMap(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errors", message);
        return errorResponse;
    }
}