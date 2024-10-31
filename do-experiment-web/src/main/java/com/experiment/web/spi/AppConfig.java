package com.experiment.web.spi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author chenxuegui
 * @since 2024/10/30
 */
@Configuration
@ComponentScan("com.experiment.web")
public class AppConfig extends BeanConfig { /* 递归解析配置类 */
}
