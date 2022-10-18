package com.bookcatalog.bookcatalogv2.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


/**
 * HandlerInterceptor ini mirip dengan Filter namun dia ada pada module Spirng MVC bukan pada Servlet
 * seperti Filter
 *
 * HandlerInterceptor ini lokasinya ada diantar dispacer servlete dan juga controller
 *
 *                             fILTERS                                                               Spring MVC
 *                         ______________                ____________________________________________________________________________________________
 *
 *                      --------   --------                                                       HandlerInterceptor
 *                      |      |   |      |                                                      __________________
 *                      |      |   |      |                                                      ********  ********
 *                      |      |   |      |              !!!!!!!!!!!!!!!!!!!!!!!!!               |      |  |      |             !!!!!!!!!!!!!!!!!
 *      Request         |      |   |      |  Request     |                       |  Request      |      |  |      |  Request    |               |
 * ---------------->    |      |   |      | ---------->  |                       | ---------->   |      |  |      | ----------> |               |
 *                      |      |   |      |              |                       |               |      |  |      |             |               |
 *                      |      |   |      |              |   DispatcherServlete  |               |      |  |      |             |  Controller   |
 * <----------------    |      |   |      | <----------  |                       | <----------   |      |  |      | <---------- |               |
 *      Response        |      |   |      |  Response    |                       |  Response     |      |  |      |   Response  |               |
 *                      |      |   |      |              |                       |               |      |  |      |             |               |
 *                      |      |   |      |              !!!!!!!!!!!!!!!!!!!!!!!!!               |      |  |      |             !!!!!!!!!!!!!!!!!
 *                      |      |   |      |                                                      ********  ********
 *                      --------   --------
 *
 * dalam gambar diatas Filter berada di luar komponen Spring MVC atau berada di luar spring MVC
 * Sedangkan HandlerInteceptor berada didalam komponen Spring MCV dengan menggunakan HandlerInterceptor
 * programer dapat meng Intercept request sebelum request tersebut mencapai Controller begitu juga
 * saat Response di kembalikan Programer dapat juga meng intercept response tersebut sebelum
 * response tersebut di render oleh komponen view
 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    /**
     * Method afterCompletion ini merupakan callback method yang akan dieksekusi
     * setelah pemrosessan request dan komponen view itu selesai dirender
     */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		log.info("afterCompletion log HanderInterceptor");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

    /**
     * Method prehandler ini method yang akan eksekusi setelah sebuah request selesai di proses
     * oleh controller dan kemudian akan dirender kedalam view
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		log.info("postHandle log HandlerInterceptor");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

    /**
     * Method prehandler ini method yang akan eksekusi sebelum sebuah request ini masuk kedalam conroller
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle log HandlerInterceptor");
        return HandlerInterceptor.super.preHandle(request, response, handler);
	}

    // note this source has relation at com.bookcatalog.bookcatalogv2.configurations.   WebMVCConfiguration

}
