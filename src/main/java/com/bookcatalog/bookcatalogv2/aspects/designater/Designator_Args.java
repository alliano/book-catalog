package com.bookcatalog.bookcatalogv2.aspects.designater;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect @Configuration @Slf4j
public class Designator_Args {

    /**
     * cara membuat poincut ini cukup sederhana
     * 1. kita membaut @interface (kita buat annotasi)
     *    contoh : ada pada package com.bookcatalog.bookcatalogv2.annotations.LogthisArgs
     *
     * 2. lalu kita membaut poincut expresion (@args()) dan utuk parameter nya kita sesuaikan
     *    contoh : @Pointcut("@args(com.bookcatalog.bookcatalogv2.annotations.LogthisArgs)")
     *
     * 3. dan kita set pada advice nya
     *    contoh : @Before("withinPointCutExample() && argsPointcutExample()")
     *
     *
    */
    @Pointcut("@args(com.bookcatalog.bookcatalogv2.annotations.LogthisArgs)")
    private void argsPointcutExample(){}

    @Pointcut("within(com.bookcatalog.bookcatalogv2.controllers.BookResource*)")
    private void withinPointCutExample(){}

    @Before("withinPointCutExample() && argsPointcutExample()")
    public void loggingArgsBeforeExample(){
        log.info("***************************************");
        log.info("this log appers form designator @args");
        log.info("***************************************");
    }

}
