package com.example.courseworkthree.exception;

public class InvalidSockRequestException extends RuntimeException {
    public InvalidSockRequestException(String message) {
        super(message);
    }
}
