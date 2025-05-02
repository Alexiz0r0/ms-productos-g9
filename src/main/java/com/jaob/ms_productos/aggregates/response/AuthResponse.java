package com.jaob.ms_productos.aggregates.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private int statusCode;
    private boolean hasError;
    private String message;
    private AuthData data;
}
