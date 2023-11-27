package com.tnpserver.security;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnpserver.exception.HttpApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    private String errorMessage;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                handleJwtException("Illegal Argument while fetching the username !!", e, response, HttpServletResponse.SC_UNAUTHORIZED);
            } catch (ExpiredJwtException e) {
                handleJwtException("Given jwt token is expired !!", e, response, HttpServletResponse.SC_UNAUTHORIZED);
            } catch (MalformedJwtException e) {
                handleJwtException("Corrupted Token found. Please provide valid Token.", e, response, HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception e) {
                handleJwtException("Unexpected Error occurred", e, response, HttpServletResponse.SC_UNAUTHORIZED);
            }

        } else {
            logger.info("Invalid Header Value !! ");
        }

        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean isTokenValid = this.jwtHelper.isTokenValid(token, userDetails);
            Boolean isTokenExpired = jwtHelper.isTokenExpired(token);
            if (isTokenValid && !isTokenExpired) {
                // set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                logger.info("Validation fails !!");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid or expired");
            }

        }

        filterChain.doFilter(request, response);

    }

    private void handleJwtException(String message, Exception e,HttpServletResponse response, int statusCode) throws IOException {
        logger.error(message, e);
        response.sendError(statusCode, message);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        if (message != null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            String errorResponse = new ObjectMapper().writeValueAsString(
                    HttpApiResponse.builder()
                            .message(message)
                            .status(HttpStatus.BAD_REQUEST.name())
                            .reason("Failure")
                            .build()
            );
            response.getWriter().write(errorResponse);
        }
    }
}
