package com.experiment.spring.test.service;

import com.experiment.spring.my.MyBeanPostProcessor;
import com.experiment.spring.my.MyComponent;
import org.springframework.beans.BeansException;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent
public class MyTestBeanPostProcessor implements MyBeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return MyBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("MyTestBeanPostProcessor.postProcessAfterInitialization.beanName="+beanName+",bean="+bean);
		System.out.println();
		return MyBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
