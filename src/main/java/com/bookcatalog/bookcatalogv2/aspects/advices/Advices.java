package com.bookcatalog.bookcatalogv2.aspects.advices;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration @Aspect @Slf4j
public class Advices {

    @Pointcut("execution(* com.bookcatalog.bookcatalogv2.controllers.BookResource.*(..))")
    private void annotationPoincuteExample(){}

    @Before("annotationPoincuteExample()")
    public void adviceBeforeExecExample(){
        log.info("******************************");
        log.info("this log from advice @Before");
        log.info("******************************");
    }
    @After("annotationPoincuteExample()")
    public void adviceAfterExecExample(){
        log.info("******************************");
        log.info("this log from advice @After");
        log.info("******************************");
    }

    @AfterReturning("annotationPoincuteExample()")
    public void adviceAfterReturning(){
        log.info("********************************");
        log.info("this log from advice @AfterReturning");
        log.info("********************************");
    }
    @AfterThrowing("annotationPoincuteExample()")
    public void adviceAfterThrowing(){
        log.info("************************************");
        log.info("this log from advice @AfterThrowing");
        log.info("************************************");
    }
}
