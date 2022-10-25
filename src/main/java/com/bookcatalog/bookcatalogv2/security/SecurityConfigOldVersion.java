package com.bookcatalog.bookcatalogv2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookcatalog.bookcatalogv2.security.filters.UsernamePasswordAutenticationFilter;
import com.bookcatalog.bookcatalogv2.security.providers.UsernamePasswordAuthProvider;
import com.fasterxml.jackson.databind.ObjectMapper;


@EnableWebSecurity
@Configuration @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigOldVersion extends WebSecurityConfigurerAdapter {

    private static final java.lang.String V1_URL = "/v1/**";

    private static final java.lang.String V2_URL = "/v2/**";

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordProvider;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.usernamePasswordProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(V1_URL, V2_URL).authenticated()
        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(buildUsernamePasswordauthFileter("/v1/".concat("login")), UsernamePasswordAuthenticationFilter.class);
    }

    protected UsernamePasswordAutenticationFilter buildUsernamePasswordauthFileter(java.lang.String loginEntryPoint) {
        UsernamePasswordAutenticationFilter filter = new UsernamePasswordAutenticationFilter(loginEntryPoint, this.successHandler, this.failureHandler, this.objectMapper);
        filter.setAuthenticationManager(this.authManager);
        return filter;
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
