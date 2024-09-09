package com.experiment.test.config;

import com.experiment.test.common.PrizeConf;
import com.experiment.test.common.User;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Random;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
@ComponentScan("com.experiment.test")
@Configuration(enforceUniqueMethods=false)/* 加不加@Configuration可以实例化@Bean，加@Configuration后通过代理保证只生成一个Bean  */
@PropertySource("classpath:spring.properties") /* 通过environment加载资源 */
@EnableAsync
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
