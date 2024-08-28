package com.experiment.test;

import com.experiment.test.config.MyAppConfig;
import com.experiment.test.factory.OrderFeignService;
import com.experiment.test.service.PrizeService;
import com.experiment.test.service.UserInfoService;
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

		UserInfoService userInfoService = (UserInfoService) applicationContext.getBean("userInfoService");
		userInfoService.queryUsers();

		System.out.println(applicationContext.getBean("feignBeanFactory"));
		OrderFeignService orderFeignService = (OrderFeignService) applicationContext.getBean("feignBeanFactory");
		orderFeignService.createOrder();


		applicationContext.getBean("prizeService", PrizeService.class).doPrize();

	}
}
