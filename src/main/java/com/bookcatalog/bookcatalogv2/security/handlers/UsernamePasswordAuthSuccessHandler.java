package com.bookcatalog.bookcatalogv2.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * kelas ini digunakan untuk mengatasi jikalau proses autentuication berhasil
 */
@Configuration @AllArgsConstructor
public class UsernamePasswordAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
        resultMap.put("result", "SUCCESS");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        this.objectMapper.writeValue(response.getWriter(), resultMap);
    }



}
