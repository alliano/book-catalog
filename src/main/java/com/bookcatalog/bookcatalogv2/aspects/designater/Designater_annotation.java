package com.bookcatalog.bookcatalogv2.aspects.designater;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect @Component @Slf4j
public class Designater_annotation {

    @Pointcut("@annotation(com.bookcatalog.bookcatalogv2.annotations.LogThisMethod)")
    public void annotationPoincutExample(){}

    @After("annotationPoincutExample()")
    public void afterExecutedLogging(){
        log.info("*******************************");
        log.info("this is designater @annotation");
        log.info("*******************************");
    }

}
