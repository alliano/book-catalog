package com.bookcatalog.bookcatalogv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bookcatalog.bookcatalogv2.security.SecurityConfiguration;

public class SecurityConfigurationTest {


    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup(){
        this.applicationContext = new AnnotationConfigApplicationContext(SecurityConfiguration.class);
    }

    @Test
    public void testConfig(){
    }
}
