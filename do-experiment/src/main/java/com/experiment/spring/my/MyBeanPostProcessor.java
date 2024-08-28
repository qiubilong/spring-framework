package com.experiment.spring.my;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
public interface MyBeanPostProcessor {
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}


	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
