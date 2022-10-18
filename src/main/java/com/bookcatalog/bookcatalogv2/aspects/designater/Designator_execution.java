package com.bookcatalog.bookcatalogv2.aspects.designater;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect @Configuration @Slf4j
public class Designator_execution {

    //method ini adalah sebagai aspect yang kita buat
    @Before("restApi()") // => before ini akan kita berikan parameter sesuai dengan nama poincut nya @Before ini adalah advice nya
    public void beforeExecutedLogging() {
        log.info("***************************************");
        log.info("this logger from Designator execution()");
        log.info("***************************************");
    }
    //method ini adalah sebagai poincut
    @Pointcut("execution(* com.bookcatalog.bookcatalogv2.controllers.AuthorResource.findAuthorBySecureId(..))") // annotasi ini menandakan method ini sebagai poincut dan untuk membaut poincut expression
    public void restApi(){}

}
