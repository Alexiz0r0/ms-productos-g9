package com.jaob.ms_productos.controller;

import com.jaob.ms_productos.aggregates.constants.Constantes;
import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBase<String>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ResponseBase<String> response = new ResponseBase<>(
                Constantes.CODE_NOT_FOUND,
                true,
                exception.getMessage(),
                null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
