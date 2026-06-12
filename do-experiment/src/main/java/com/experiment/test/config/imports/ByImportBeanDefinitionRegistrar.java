package com.experiment.test.config.imports;

import com.experiment.test.config.ConfigByImportBeanDefinitionRegistrar;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/* ImportBeanDefinitionRegistrar --> 注册BeanDefinition */
public class ByImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry, importBeanNameGenerator);

	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);

		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClassName(ByImportBeanDefinitionService.class.getName());
		registry.registerBeanDefinition("byImportBeanDefinitionService",beanDefinition);
	}


}

