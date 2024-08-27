package com.experiment;

import com.experiment.config.MyAppConfig;
import com.experiment.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
public class MyApplication {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);

		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.queryUsers();


		System.out.println(applicationContext.getBean("feignBeanFactory"));
		System.out.println(applicationContext.getBean("feignBeanFactory"));

	}
}
