package com.jaob.ms_productos.controller;

import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleResourceNotFoundException() {
        // ARRANGE
        String errorMessage = "Recurso no encontrado";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);
        // ACT
        ResponseEntity<ResponseBase<String>> response = handler.handleResourceNotFoundException(exception);
        // ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isHasError());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
}