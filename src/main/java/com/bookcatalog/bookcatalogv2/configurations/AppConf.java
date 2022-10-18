package com.bookcatalog.bookcatalogv2.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;


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

}
