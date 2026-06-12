package com.experiment.web.spi;

import org.springframework.context.annotation.Bean;

/**
 * @author chenxuegui
 * @since 2024/10/31
 */
public class BeanConfig {

	@Bean
	public AtBeanVo atBeanVo(){
		System.out.println();
		return new AtBeanVo();
	}


	@Bean
	public static AtBeanVo atBeanVoStatic(){
		System.out.println();
		return new AtBeanVo();
	}
}
