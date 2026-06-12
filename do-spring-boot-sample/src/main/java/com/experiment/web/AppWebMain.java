package com.experiment.web;


import com.experiment.web.spi.AppConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chenxuegui
 * @since 2025/2/6
 */
@SpringBootApplication
public class AppWebMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppWebMain.class, args);

		System.out.println(context.getBean(AppConfig.class));
	}
}
