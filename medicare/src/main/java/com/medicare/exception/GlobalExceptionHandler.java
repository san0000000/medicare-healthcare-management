package com.medicare.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.medicare.exception.PatientNotFoundException;
import com.medicare.dto.ApiResponse;
import com.medicare.exception.DuplicatePatientEmailException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(
        MethodArgumentNotValidException exception) {

    String message = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .orElse("Validation failed");

    Map<String, Object> error = Map.of(
            "status", 400,
            "message", message
    );

    return ResponseEntity.badRequest().body(error);
}
@ExceptionHandler(PatientNotFoundException.class)
public ResponseEntity<ApiResponse> handlePatientNotFound(
        PatientNotFoundException ex) {

    ApiResponse response = new ApiResponse(
            ex.getMessage(),
             HttpStatus.NOT_FOUND.value()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
}
@ExceptionHandler(DuplicatePatientEmailException.class)
public ResponseEntity<ApiResponse> handleDuplicatePatientEmail(
        DuplicatePatientEmailException ex) {

    ApiResponse response = new ApiResponse(
            ex.getMessage(),
            HttpStatus.CONFLICT.value()
    );

    return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(response);
        }
        @ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ApiResponse> handleIllegalArgument(
        IllegalArgumentException ex) {

    ApiResponse response = new ApiResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
    );

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
        }
}