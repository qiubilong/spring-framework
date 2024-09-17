package com.experiment.test.mybatis.test.config;

import com.experiment.test.mybatis.spring.MyBatisImportBeanDefinitionRegistrar;
import com.experiment.test.mybatis.spring.MyBatisMapperScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(MyBatisImportBeanDefinitionRegistrar.class) /* 导入配置类ImportBeanDefinitionRegistrar --> BeanDefinition */
@MyBatisMapperScan("com.experiment.test.mybatis.test.mapper")
public class MyBatisConfig {
}
