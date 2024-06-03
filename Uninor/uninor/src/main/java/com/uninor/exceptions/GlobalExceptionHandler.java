package com.uninor.exceptions;

import com.uninor.helper.ErrorsMapper;
import org.apache.commons.collections4.IterableUtils;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

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