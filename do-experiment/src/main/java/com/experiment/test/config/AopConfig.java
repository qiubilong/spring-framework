package com.experiment.test.config;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/29
 */
@Component
@EnableAspectJAutoProxy /* 注解中导入AspectJAutoProxyRegistrar --> */
public class AopConfig {
}
