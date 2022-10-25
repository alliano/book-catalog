package com.bookcatalog.bookcatalogv2.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.bookcatalog.bookcatalogv2.dto.LoginRequestDto;
import com.bookcatalog.bookcatalogv2.exceptions.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * untuk menjadikan kelas ini sebagai autentication filter. kelas ini harus meng extends
 * ke interface AbstractAuthenticationProcessingFilter
 * kelas ini akan meng intercept semua request yang masuk dan mendelegasikan ke AutenticationManager
 */

public class UsernamePasswordAutenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler authSuccessHandler;

    private final AuthenticationFailureHandler authFailureHandler;

    private ObjectMapper objectMapper;

    public UsernamePasswordAutenticationFilter(String defaultFilterProcessesUrl,  AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        this.authSuccessHandler = successHandler;
        this.authFailureHandler = failureHandler;
        this.objectMapper = objectMapper;
    }

    /**
     * method ini akan mengintercept semua request yang masuk dan request tersebut akan di
     * delegasikan ke autentication manager
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
       LoginRequestDto requestDto = this.objectMapper.readValue(request.getReader(), LoginRequestDto.class);
       if(StringUtils.isBlank(requestDto.getPassword()) || StringUtils.isBlank(requestDto.getUsername())) {
           throw new BadRequestException("username and password is required");
       }
       UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword());
       return this.getAuthenticationManager().authenticate(token);
    }

    //method ini digunakan untuk meng custom bagaimna jika autentivation berhasill
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.authSuccessHandler.onAuthenticationSuccess(request, response, authResult);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    //method ini digunakan untuk meng custom jikalau autentication gagal
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        this.authFailureHandler.onAuthenticationFailure(request, response, failed);
        super.unsuccessfulAuthentication(request, response, failed);
    }

}
