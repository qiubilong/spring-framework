package com.experiment.test.mybatis.spring;

import com.experiment.test.mybatis.test.mapper.OrderMapper;
import com.experiment.test.mybatis.test.mapper.UserMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

public class MyBatisImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry, importBeanNameGenerator);
	}

	@Override /* importingClassMetadata == 注解所在类，如MyBatisConfig  */
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyBatisMapperScan.class.getName());
		String path = (String)annotationAttributes.get("value");

		MybatisClassPathBeanDefinitionScanner scanner = new MybatisClassPathBeanDefinitionScanner(registry);

		/* 扫描到所有接口都是候选者，包括Interface */
		scanner.addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});

		scanner.scan(path);
	}
}
