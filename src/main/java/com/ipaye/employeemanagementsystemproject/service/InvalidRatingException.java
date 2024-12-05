package com.ipaye.employeemanagementsystemproject.service;

public class InvalidRatingException extends RuntimeException {

    public InvalidRatingException(String message) {
        super(message);
    }
}
