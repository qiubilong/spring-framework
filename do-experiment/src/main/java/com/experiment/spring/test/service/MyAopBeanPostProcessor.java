package com.experiment.spring.test.service;

import com.experiment.spring.my.MyBeanPostProcessor;
import com.experiment.spring.my.MyComponent;
import org.springframework.beans.BeansException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent
public class MyAopBeanPostProcessor implements MyBeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("userWalletFeignImpl".equals(beanName)){
			System.out.println("MyAopBeanPostProcessor.postProcessAfterInitialization.beanName="+beanName);
			return Proxy.newProxyInstance(MyAopBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("userWalletFeignImpl AOP method="+method);
					return method.invoke(bean,args);
				}
			});
		}

		return MyBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
