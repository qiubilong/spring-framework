package com.experiment.test.beanFactoryPostProcessor;

import com.experiment.spring.test.service.MyUserInfoService;
import com.experiment.test.autowired.AutowiredByTypeService;
import com.experiment.test.constructor.ConstructByBeanDefinition;
import com.experiment.test.service.UserInfoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
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

		/* 指定构造函数  */
		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClassName(ConstructByBeanDefinition.class.getName());
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference(UserInfoService.class));
		registry.registerBeanDefinition("constructByBeanDefinition",beanDefinition);


		/* 指定AutowireMode自动setXXX注入依赖, spring已经废弃，推荐使用@Autowired注入依赖关系更加清晰 */
		AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition1.setBeanClass(AutowiredByTypeService.class);
		beanDefinition1.setAutowireMode(Autowire.BY_TYPE.value());
		//beanDefinition1.setAutowireMode(Autowire.BY_NAME.value());
		registry.registerBeanDefinition("autowiredByTypeService",beanDefinition1);
	}
}
