package com.experiment.test.mybatis.test.config;

import com.experiment.test.mybatis.spring.MyBatisImportBeanDefinitionRegistrar;
import com.experiment.test.mybatis.spring.MyBatisMapperScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@MyBatisMapperScan("com.experiment.test.mybatis.test.mapper")
public class MyBatisConfig {
}
