package com.app.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(LoanAlreadyExistsException exception){
        ErrorResponseDto error = new ErrorResponseDto();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setErrorMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        return error;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(ResourceNotFoundException exception){
        ErrorResponseDto error = new ErrorResponseDto();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setErrorMessage(exception.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST);
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception exception){
        ErrorResponseDto error = new ErrorResponseDto();
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setErrorMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return error;
    }
}
