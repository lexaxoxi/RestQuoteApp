package com.example.demo.exception;


import com.example.demo.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class AppGlobalExceptionHandler {

       @ExceptionHandler(value = {com.example.demo.exception.AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO userNotAuthorized(com.example.demo.exception.AuthException e) {
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO entityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return new ErrorResponseDTO(e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = {com.example.demo.exception.EntityAlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO entityExistsException(com.example.demo.exception.EntityAlreadyExistException e) {
        return new ErrorResponseDTO(e.getMessage());
    }


}
