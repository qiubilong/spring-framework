package com.experiment.web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2025/9/17
 */
@Component
public class ComponentBean {

	@Bean
	public DataConf dataConf(){
		return new DataConf();
	}
}
