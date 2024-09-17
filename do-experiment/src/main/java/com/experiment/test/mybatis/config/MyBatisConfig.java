package com.experiment.test.mybatis.config;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(MyBatisImportBeanDefinitionRegistrar.class) /* 导入配置类ImportBeanDefinitionRegistrar --> BeanDefinition */
public class MyBatisConfig {
}
