package com.rafael.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rafael.crudspring.exception.RecordNotFoundException;

@RestControllerAdvice
public class AppControllerAdvice {
    
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExcpetion(RecordNotFoundException e){
        return e.getMessage();
    }
}
