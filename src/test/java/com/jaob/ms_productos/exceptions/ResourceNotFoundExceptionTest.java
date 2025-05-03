package com.jaob.ms_productos.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        // ARRANGE
        String message = "Recurso no encontrado";
        // ACT
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        // ASSERT
        assertEquals(message, exception.getMessage());
    }
}