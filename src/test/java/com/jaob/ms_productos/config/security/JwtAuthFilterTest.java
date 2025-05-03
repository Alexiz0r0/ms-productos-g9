package com.jaob.ms_productos.config.security;

import com.jaob.ms_productos.aggregates.constants.Constantes;
import com.jaob.ms_productos.aggregates.response.AuthData;
import com.jaob.ms_productos.aggregates.response.AuthResponse;
import com.jaob.ms_productos.rest.AuthFeignClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JwtAuthFilterTest {

    @Mock
    private AuthFeignClient authClient;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    private AuthResponse authResponse;
    private AuthData authData;
    private String token = "Bearer eyJ0eXBlIjoiSldUIi.wiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFET";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authResponse = new AuthResponse();
        authData = new AuthData();
        authData.setId(1L);
        authData.setEmail("abc@abc.bb");
        authData.setRol("ADMIN");
        authResponse.setData(authData);
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn(token);
        when(authClient.validateToken(token)).thenReturn(new ResponseEntity<>(authResponse, HttpStatus.OK));
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals("abc@abc.bb", authentication.getPrincipal());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void lanzarUnauthorizedWhenMissingToken() throws IOException, ServletException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
        assertTrue(stringWriter.toString().contains(Constantes.MESSAGE_REQUIRED_TOKEN));
    }

    @Test
    void lanzarUnauthorizedWhenBadToken() throws IOException, ServletException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn("null");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
        assertTrue(stringWriter.toString().contains(Constantes.MESSAGE_REQUIRED_TOKEN));
    }

    @Test
    void lanzarUnauthorizedWhenInvalidToken() throws IOException, ServletException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn(token);
        when(authClient.validateToken(token)).thenThrow(new RuntimeException("Token inválido"));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
        assertTrue(stringWriter.toString().contains(Constantes.MESSAGE_INVALID_TOKEN));
    }

    @Test
    void lanzarUnauthorizedWhenNullAuthResponse() throws IOException, ServletException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn(token);
        when(authClient.validateToken(token)).thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void lanzarUnauthorizedWhenResponseNull() throws IOException, ServletException {
        // ARRANGE
        when(request.getHeader("Authorization")).thenReturn(token);
        when(authClient.validateToken(token)).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void lanzarUnauthorizedWhenAuthDataNull() throws IOException, ServletException {
        // ARRANGE
        AuthResponse response1 = new AuthResponse();
        when(request.getHeader("Authorization")).thenReturn(token);
        when(authClient.validateToken(token)).thenReturn(ResponseEntity.ok(response1));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        // ACT
        jwtAuthFilter.doFilterInternal(request, response, filterChain);
        // ASSERT
        verify(response).setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}