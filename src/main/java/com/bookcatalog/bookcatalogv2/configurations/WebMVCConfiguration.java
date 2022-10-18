package com.bookcatalog.bookcatalogv2.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bookcatalog.bookcatalogv2.interceptors.LoggerInterceptor;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    /**
     * method ini digunakan untuk meregistrasikan HandlerInterceptor yang saya buat
     * di com.bookcatalog.bookcatalogv2.interceptors.Loggernterceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
