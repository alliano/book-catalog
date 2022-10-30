package com.bookcatalog.bookcatalogv2.configurations;

import java.util.Arrays;

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

import com.bookcatalog.bookcatalogv2.security.filters.JwtAuthenticationFilter;
import com.bookcatalog.bookcatalogv2.security.filters.UsernamePasswordAutenticationFilter;
import com.bookcatalog.bookcatalogv2.security.providers.JwtAuthenticationProvider;
import com.bookcatalog.bookcatalogv2.security.providers.UsernamePasswordAuthProvider;
import com.bookcatalog.bookcatalogv2.security.utils.SkipPathRequestMatcher;
import com.bookcatalog.bookcatalogv2.security.utils.TokenExtractor;
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
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordProvider;

    @Autowired
    private TokenExtractor tokenExtractor;


    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.usernamePasswordProvider);
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        java.util.List<java.lang.String> permitAllEndpoinList = Arrays.asList("/v1/login");
        java.util.List<java.lang.String> authenticationEnpoinList = Arrays.asList(V1_URL, V2_URL);

        http.authorizeRequests().antMatchers(V1_URL, V2_URL).authenticated()
        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(buildUsernamePasswordauthFileter("/v1/".concat("login")), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(buildJwtauthFilter(permitAllEndpoinList, authenticationEnpoinList), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     *
     * @param pathToSkip => url atau endpoin yang tidak memerlukan autentikasi Jwt
     * @param pattrenList => Url yang harus diautetikasi dengan Jwt
     * @return
     */
    protected JwtAuthenticationFilter buildJwtauthFilter(java.util.List<java.lang.String> pathToSkip, java.util.List<java.lang.String> pattrenList){
        SkipPathRequestMatcher matches = new SkipPathRequestMatcher(pathToSkip, pattrenList);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(failureHandler, tokenExtractor, matches);
        filter.setAuthenticationManager(this.authManager);
        return filter;
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
