package com.experiment.test.config;

import com.experiment.spring.test.service.MyUserInfoService;
import com.experiment.test.constructor.ConstructByBeanDefinition;
import com.experiment.test.service.UserInfoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
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

		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClassName(ConstructByBeanDefinition.class.getName());
		/* 相当于手动指定构造函数 */
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserInfoService.class);

		registry.registerBeanDefinition("constructByBeanDefinition",beanDefinition);

	}
}
