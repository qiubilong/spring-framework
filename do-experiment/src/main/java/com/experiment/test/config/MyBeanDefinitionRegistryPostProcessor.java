package com.experiment.test.config;

import com.experiment.spring.test.service.MyUserInfoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/29
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

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
    /* BeanFactoryPostProcessor拓展类，用于扫描注册BeanDefinition，如spring使用ConfigurationClassPostProcessor来扫描spring管理的bean */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

	}
}
