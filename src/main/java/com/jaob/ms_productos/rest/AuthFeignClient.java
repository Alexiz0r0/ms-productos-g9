package com.jaob.ms_productos.rest;

import com.jaob.ms_productos.aggregates.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-client", url = "http://localhost:40001")
public interface AuthFeignClient {

    @GetMapping("/auth/validate")
    ResponseEntity<AuthResponse> validateToken(@RequestHeader("Authorization") String token);

}
