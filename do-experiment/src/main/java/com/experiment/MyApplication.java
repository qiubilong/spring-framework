package com.experiment;

import com.experiment.common.PrizeConf;
import com.experiment.config.MyAppConfig;
import com.experiment.factory.OrderFeignService;
import com.experiment.service.PrizeService;
import com.experiment.service.UserInfoService;
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
