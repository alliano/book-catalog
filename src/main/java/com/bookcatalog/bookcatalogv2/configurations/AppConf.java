package com.bookcatalog.bookcatalogv2.configurations;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookcatalog.bookcatalogv2.security.utils.JwtTokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Configuration
public class AppConf {

    @Bean(value = "passEncoder")
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(value = "mapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean(value = "key")
    public Key key(){
        byte[] keyBytes = Decoders.BASE64.decode("387487y327yrp2crehbfywecskdgfbihpgp8gp8q67847tfgy3qdvfd");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean(value = "jwttokenFactory")
    public JwtTokenFactory jwtTokenFactory(Key key) {
        return new JwtTokenFactory(key);
    }

}
