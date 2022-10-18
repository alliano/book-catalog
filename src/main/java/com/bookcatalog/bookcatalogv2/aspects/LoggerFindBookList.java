package com.bookcatalog.bookcatalogv2.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect @Slf4j
@Configuration
public class LoggerFindBookList {

    @Pointcut(value = "execution(* com.bookcatalog.bookcatalogv2.controllers.BookResource.findBookList(..))")
    private void poinCutFIndBookList(){}

    @Around(value = "poinCutFIndBookList()")
    protected Object logAroud(ProceedingJoinPoint joinpoin) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("*************START LOGER FOR findBookList*****************");
            stopWatch.start();
            return joinpoin.proceed();
        } finally{
            stopWatch.stop();
            log.info("*************END LOGER FOR findBookList*******************");
        }

    }

}
