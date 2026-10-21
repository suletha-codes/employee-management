package com.example.employeemanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),"Not Found",exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
