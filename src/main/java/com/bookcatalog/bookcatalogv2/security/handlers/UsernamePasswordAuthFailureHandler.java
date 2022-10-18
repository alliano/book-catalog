package com.bookcatalog.bookcatalogv2.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * keelas ini digunakan jikalau proses autetentication gagal
 */
@Configuration @AllArgsConstructor
public class UsernamePasswordAuthFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {
        java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
        resultMap.put("result", "AUTHENTICATION FAILED");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        this.objectMapper.writeValue(response.getWriter(), resultMap);

    }



}
