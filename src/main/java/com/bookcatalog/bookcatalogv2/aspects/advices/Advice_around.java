package com.bookcatalog.bookcatalogv2.aspects.advices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Configuration @Aspect @Slf4j
public class Advice_around {

    @Pointcut("execution(* com.bookcatalog.bookcatalogv2.controllers.CategoryResource.findcategoryList(..))")
    private void testAdviceAround(){}

    /**
     * untuk menggunakan advice @Around kita harus tamabahkan Parameter pada method Advice nya dalam kasus ini method
     * advice nya adalah proccessingTimeLogging()
     * kita harus tambahkan parameter ProceedingJoinPoint dari package org.aspectj.lang.ProceedingJoinPoint
     * dan method advice tersebut harus meretrun kan ProceedingJoinPoint
     *
     * @param joinPoint
     * @return
     * @throws Exception
     * @throws Throwable
     */
    @Around("testAdviceAround()")
    private Object proccessingTimeLogging(ProceedingJoinPoint joinPoint) throws Exception, Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            log.info("*********************************");
            log.info("this is log form advice @Around");
            log.info(joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            log.info("total time start : "+ stopWatch.getTotalTimeMillis());
            log.info("*********************************");
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("*********************************");
            log.info("this is log form advice @Around");
            log.info(joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            log.info("total time end : "+ stopWatch.getTotalTimeMillis());
            log.info("*********************************");
        }
    }
}
