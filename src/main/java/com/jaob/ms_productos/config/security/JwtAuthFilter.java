package com.jaob.ms_productos.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaob.ms_productos.aggregates.constants.Constantes;
import com.jaob.ms_productos.aggregates.response.AuthData;
import com.jaob.ms_productos.aggregates.response.AuthResponse;
import com.jaob.ms_productos.aggregates.response.ResponseBase;
import com.jaob.ms_productos.rest.AuthFeignClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthFeignClient authClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(tokenHeader) || !StringUtils.startsWithIgnoreCase(tokenHeader, "Bearer ")) {
            handleJwtException(response, Constantes.MESSAGE_REQUIRED_TOKEN);
            return;
        }
        try {
            AuthData user = getUserInformation(tokenHeader);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), null, List.of(new SimpleGrantedAuthority(user.getRol())));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            handleJwtException(response, Constantes.MESSAGE_INVALID_TOKEN);
        }
        filterChain.doFilter(request, response);
    }

    private void handleJwtException(HttpServletResponse response, String message) throws IOException {
        ResponseBase<String> customResponse = new ResponseBase<>(
                Constantes.CODE_UNAUTHORIZED,
                true,
                message,
                null);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), customResponse);
    }

    private AuthData getUserInformation(String token) throws IOException {
        ResponseEntity<AuthResponse> responseEntity = authClient.validateToken(token);
        if (responseEntity == null || responseEntity.getBody() == null || responseEntity.getBody().getData() == null) {
            throw new RuntimeException("Unauthorized");
        }
        return responseEntity.getBody().getData();
    }
}
