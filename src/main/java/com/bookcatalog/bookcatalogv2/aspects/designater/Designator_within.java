package com.bookcatalog.bookcatalogv2.aspects.designater;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect @Configuration @Slf4j
public class Designator_within {

    @Before("withinExample()")
    public void beforLOgggingExcecution() {
        log.info("****************************************");
        log.info("this is applers from designator within");
        log.info("****************************************");
    }

    @Pointcut("within(com.bookcatalog.bookcatalogv2.controllers.AuthorResource.*)")
    private void withinExample(){}

}
