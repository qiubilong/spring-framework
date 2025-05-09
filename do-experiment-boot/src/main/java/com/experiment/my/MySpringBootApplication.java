package com.experiment.my;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented

@Configuration
@ComponentScan
@Import(WebServerAutoConfiguer.class)
public @interface MySpringBootApplication {
}
