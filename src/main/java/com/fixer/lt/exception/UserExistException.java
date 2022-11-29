package com.fixer.lt.exception;


public class UserExistException extends RuntimeException {
    public UserExistException(final String message) {
        super(message);
    }
}