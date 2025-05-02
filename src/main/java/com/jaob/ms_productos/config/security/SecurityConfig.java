package com.jaob.ms_productos.config.security;

import com.jaob.ms_productos.aggregates.constants.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter authFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(Constantes.ENDPOINT_FOR_ALL_ROLES).hasAnyAuthority("USUARIO", "SUPERADMIN", "ADMIN")
                        .requestMatchers(Constantes.CREATE_ENDPOINT_FOR_ADMIN).hasAnyAuthority("SUPERADMIN", "ADMIN")
                        .requestMatchers(Constantes.DELETE_ENDPOINT_FOR_ADMIN).hasAnyAuthority("SUPERADMIN", "ADMIN")
                        .requestMatchers(Constantes.UPDATE_ENDPOINT_FOR_ADMIN).hasAnyAuthority("SUPERADMIN", "ADMIN")
                        .anyRequest().authenticated()
        ).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
