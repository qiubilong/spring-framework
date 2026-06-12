package com.experiment.test.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:my-spring${spring.profiles.active:}.properties")
/* 配置类 --> 通过environment加载资源 --> BeanDefinition  */
public class ConfigByPropertySource {
}
