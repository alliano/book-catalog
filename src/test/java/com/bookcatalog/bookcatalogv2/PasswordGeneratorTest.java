package com.bookcatalog.bookcatalogv2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = {"development"})
@Slf4j
public class PasswordGeneratorTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncider;

    @Test
    public void generatePassword(){
        log.info("********************************************************");
        log.info("password generated");
        log.info("{}",passwordEncider.encode("test123"));
        log.info("********************************************************");
    }
}
