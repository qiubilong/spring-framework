package com.experiment.test.config;

import com.experiment.spring.test.service.MyUserInfoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/29
 */
@Component
public class MyBeanDefinitionRegistry implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistry.beanFactory="+beanFactory);

		//注册别名
		beanFactory.registerAlias("userInfoService","com.experiment.test.service.UserInfoService");

		//注册beanDefinition
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanFactory;

		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MyUserInfoService.class);
		beanDefinitionBuilder.setLazyInit(false);
		factory.registerBeanDefinition("com.experiment.spring.test.service.MyUserInfoService",beanDefinitionBuilder.getBeanDefinition());

	}
}
