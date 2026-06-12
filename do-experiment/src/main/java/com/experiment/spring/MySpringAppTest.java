package com.experiment.spring;

import com.experiment.spring.my.MyAnnotationConfigApplicationContext;
import com.experiment.spring.test.config.MySpringAppConfig;
import com.experiment.spring.test.service.MyOrderService;
import com.experiment.spring.test.service.UserWalletFeign;
import com.experiment.spring.test.service.UserWalletFeignImpl;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
public class MySpringAppTest {

	public static void main(String[] args) {

		MyAnnotationConfigApplicationContext applicationContext = new MyAnnotationConfigApplicationContext(MySpringAppConfig.class);

		System.out.println(applicationContext.getBean("myUserInfoService"));
		System.out.println(applicationContext.getBean("orderService"));
		System.out.println(applicationContext.getBean("orderService"));

		applicationContext.getBean("orderService", MyOrderService.class).createOrder();
		System.out.println(applicationContext.getBean("onceOrderService"));
		System.out.println(applicationContext.getBean("onceOrderService"));

		applicationContext.getBean("userWalletFeignImpl", UserWalletFeign.class).queryWallet(500L);
	}
}
