package com.tnpserver.security;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnpserver.exception.HttpApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        String errorResponse = new ObjectMapper().writeValueAsString(
                HttpApiResponse.builder()
                        .message("Access Denied !! "+authException.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.name())
                        .reason("Authentication Required")
                        .build()
        );
        response.getWriter().write(errorResponse);
    }

}