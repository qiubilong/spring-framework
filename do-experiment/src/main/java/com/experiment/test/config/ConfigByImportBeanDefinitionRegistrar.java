package com.experiment.test.config;

import com.experiment.test.config.imports.ByImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(ByImportBeanDefinitionRegistrar.class) /* 导入ImportBeanDefinitionRegistrar子类  --> 注册BeanDefinition */
public class ConfigByImportBeanDefinitionRegistrar {
}
