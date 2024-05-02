package com.unboxit.bnichecking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserNotAllowedException extends RuntimeException {
    public UserNotAllowedException(String message) {
        super("user is not allowed: " + message);
    }
}