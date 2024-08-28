package com.experiment.test.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/26
 * bean实例化后，执行初始化（init）方式时，拓展处理器
 */
@Component
public class MyInitBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean生命周期-Init-Before-----------beanName="+beanName+",bean="+bean);
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean生命周期-Init-After(AOP拓展点)---beanName="+beanName+",bean="+bean);
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
