package com.experiment.test.config;

import com.experiment.test.common.PrizeConf;
import com.experiment.test.common.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
@ComponentScan("com.experiment.test")
@Configuration
/* 加不加@Configuration可以实例化@Bean，加@Configuration后通过代理保证只生成一个Bean  */
public class MyAppConfig {

	@Bean
	PrizeConf prizeConf(){
		PrizeConf conf = new PrizeConf(222L, "一等奖");
		conf.setPrizeUser(prizeUser());
		return conf;
	}

	@Bean
	User prizeUser(){
		return new User(new Random().nextLong(),"用户");
	}
}
