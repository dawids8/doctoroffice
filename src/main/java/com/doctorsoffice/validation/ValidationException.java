package com.doctorsoffice.validation;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Validation exception: " + super.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
