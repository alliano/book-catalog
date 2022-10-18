package com.bookcatalog.bookcatalogv2.aspects.designater;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect @Slf4j @Configuration
public class Designator_args {

    /**
    * ini kita tidak perlu menuliskan return dan argumen nya (tanda * diawal dan (..) diakhir) seperti di designator execution()
    */
    @Pointcut("within(com.bookcatalog.bookcatalogv2.controllers.*)")
    private void withinPointCutExample(){}

    @Pointcut("args(com.bookcatalog.bookcatalogv2.dto.PublisherCreaterequestDto)")
    private void argsPoincutExample(){}

    @After("withinPointCutExample() && argsPoincutExample()")
    public void loggingAfterExecution(){
        log.info("******************************************");
        log.info("designnator args has trigered ====> args()");
        log.info("******************************************");
    }
}
