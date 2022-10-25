package com.bookcatalog.bookcatalogv2.security.handlers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bookcatalog.bookcatalogv2.security.jwt.JWTTokenFactory;
import com.bookcatalog.bookcatalogv2.security.model.AccessJwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * kelas ini digunakan untuk mengatasi jikalau proses autentuication berhasil
 */
@Configuration @AllArgsConstructor
public class UsernamePasswordAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    private final JWTTokenFactory jwtTokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // AccessJwtToken token = jwtTokenFactory.createAccessJwtToken(userDetails.getUsername(), userDetails.getAuthorities());
        // resultMap.put("token", token.getToken());

        System.out.println("\n\n"+authentication.getPrincipal()+"\n\n");
        System.out.println("\n\n"+authentication.getDetails()+"\n\n");


        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        this.objectMapper.writeValue(response.getWriter(), resultMap);
        clearAuthenticationAttributes(request);
    }

    //bersishkan session => delete session
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
