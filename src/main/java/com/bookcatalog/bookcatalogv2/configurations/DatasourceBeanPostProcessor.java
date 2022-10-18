package com.bookcatalog.bookcatalogv2.configurations;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Component
public class DatasourceBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
		if (bean instanceof DataSource){
			ProxyFactory factory = new ProxyFactory(bean);
			factory.setProxyTargetClass(true);
			factory.addAdvice(new ProxyDataSourceInterceptor((DataSource) bean));
			return factory.getProxy();
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public static class ProxyDataSourceInterceptor implements org.aopalliance.intercept.MethodInterceptor {

		private final DataSource dataSource;

		public ProxyDataSourceInterceptor(DataSource dataSource) {
			super();
			this.dataSource = ProxyDataSourceBuilder.create(dataSource).countQuery().logQueryBySlf4j(SLF4JLogLevel.INFO).build();
		}
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			Method proxyMethod = ReflectionUtils.findMethod(dataSource.getClass(), invocation.getMethod().getName());
			if(proxyMethod != null) {
				return proxyMethod.invoke(dataSource, invocation.getArguments());
			}
			return invocation.proceed();
		}

	}

}
