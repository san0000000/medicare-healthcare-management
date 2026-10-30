package com.medicare.exception;

public class DuplicatePatientEmailException extends RuntimeException {

    public DuplicatePatientEmailException(String message) {
        super(message);
    }
}