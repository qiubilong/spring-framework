package com.experiment.test.config;

import com.experiment.test.common.PrizeConf;
import com.experiment.test.common.User;
import com.experiment.test.service.UserInfoService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
@ComponentScan("com.experiment.test")
@Configuration(enforceUniqueMethods=false)/* 加不加@Configuration可以实例化@Bean，加@Configuration会生成代理配置类，保证内部多次方法调用只会生成一个bean  */
@Conditional(MyConditional.class)

@EnableAsync      /* 开启异步功能 -->导入配置类 AsyncConfigurationSelector -->注入 AsyncAnnotationBeanPostProcessor --> AsyncAnnotationAdvisor --> 解析 @Async --> AnnotationAsyncExecutionInterceptor */
@EnableScheduling /* 开启定时功能 -->导入配置类 SchedulingConfiguration    -->注入 ScheduledAnnotationBeanPostProcessor -->解析 @Schedule   */

public class MyAppConfig {

	@Bean /* <Bean>类似，factoryBeanName=myAppConfig， factoryMethodName=prizeConf */
	PrizeConf prizeConf(){
		PrizeConf conf = new PrizeConf(222L, "一等奖");
		conf.setPrizeUser(prizeUser());
		return conf;
	}

	@Bean /* <Bean>类似，factoryBeanName=myAppConfig， factoryMethodName=prizeConf */
	PrizeConf prizeConf(User prizeUser){
		PrizeConf conf = new PrizeConf(222L, "一等奖");
		conf.setPrizeUser(prizeUser);
		return conf;
	}
	@Bean
	User prizeUser(){
		return new User(new Random().nextLong(),"用户");
	}

	@Bean
	MessageSource messageSource(){
		/* 国际多语言 */
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
}
