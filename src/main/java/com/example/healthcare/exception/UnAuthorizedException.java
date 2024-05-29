package com.example.healthcare.exception;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String not_authorized) {
        getMessage();
    }
}
