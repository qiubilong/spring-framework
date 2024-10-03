package com.experiment.test.config;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/29
 */
@Component
@EnableAspectJAutoProxy /* 注解中导入AspectJAutoProxyRegistrar --> AnnotationAwareAspectJAutoProxyCreator --> 解析@Aspect --> Advisor(Advice + Pointcut) --> ProxyFactory --> Bean Proxy */
@Import(TransactionalConfig.class)
public class AopConfig {
}
