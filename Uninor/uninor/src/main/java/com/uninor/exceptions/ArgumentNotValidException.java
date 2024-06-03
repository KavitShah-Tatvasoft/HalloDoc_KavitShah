package com.uninor.exceptions;

public class ArgumentNotValidException extends RuntimeException {

    public ArgumentNotValidException(String message) {
        super(message);
    }
}
