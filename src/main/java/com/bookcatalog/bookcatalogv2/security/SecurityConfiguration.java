package com.bookcatalog.bookcatalogv2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bookcatalog.bookcatalogv2.security.filters.UsernamePasswordAutenticationFilter;
import com.bookcatalog.bookcatalogv2.security.providers.UsernamePasswordAuthProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


/**
 * disini kita annotasi dengan @EnableWebSecurity dengan tujuan agar kita bisa
 * meng overide konfigurasi default dari spring security
 */
@EnableWebMvc @EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration @RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String V1_URL = "/v1/**";

    private static final String V2_URL = "/v2/**";

    private final AuthenticationFailureHandler failureHandler;

    private final AuthenticationSuccessHandler successHandler;

    private final UsernamePasswordAuthProvider usernamePassAuthProvider;

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper;

    /**
     * kita wajib membuat bean ini untuk meng konfigurasi semua request dari clien seperti
     * POST GET PUT dan lain2 dan kita bisa juga meng konfigurasi path(url) dari suatu request
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(V1_URL, V2_URL).authenticated()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().csrf().disable().authenticationProvider(this.usernamePassAuthProvider)
        .addFilterBefore(buildAutenticationFilter(V1_URL.concat("/login")),
         UsernamePasswordAuthenticationFilter.class);
        /**
         * di .authenticationProvider(autentucationProvider()); sinilah proses autentikasi dilaukan
         */
        return http.build();
    }

    protected UsernamePasswordAutenticationFilter buildAutenticationFilter(String loginEntryPoin){
        UsernamePasswordAutenticationFilter filter = new UsernamePasswordAutenticationFilter(loginEntryPoin, this.successHandler, this.failureHandler, this.objectMapper);
        // filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    public AuthenticationManager authManagerBean( HttpSecurity http ) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.authenticationProvider(this.usernamePassAuthProvider);
        return authManagerBuilder.build();
    }


}
