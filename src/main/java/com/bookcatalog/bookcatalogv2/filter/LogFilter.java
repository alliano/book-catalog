package com.bookcatalog.bookcatalogv2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Filter ini adalah salahsatu kompoonen didalam servlete APi buakan didalam spring MVC
 * dengan menggunkan fileter programer dapat mengubah atau bahkan memblock request yang masuk
 * sebelum mencapai servlete begitu juga sebaliknya filter juga dalat digunakan untuk mengubah atau
 * bahkan memblock response sebelum dikirimkan kembali ke pada client.
 *
 * salah satu contoh filter didalam Spring framework adalah :
 * Example :
 *          - Spring Security
 *              yang mana Spring security ini kita gunakan untk meng authentikasi dan juga otorisasi
 */

 @Configuration @Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("*********************************");
        log.info("hello from filter {} ",request.getLocalAddr());
        log.info("*********************************");
        chain.doFilter(request, response);
    }

}
