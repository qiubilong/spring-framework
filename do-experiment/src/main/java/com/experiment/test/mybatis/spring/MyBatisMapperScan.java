package com.experiment.test.mybatis.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyBatisImportBeanDefinitionRegistrar.class) /* 配置类递归搜索@Import--> 导入配置类ImportBeanDefinitionRegistrar --> BeanDefinition */
public @interface MyBatisMapperScan {
	String value();
}
