package com.experiment.test;

import com.experiment.spring.test.service.MyUserInfoService;
import com.experiment.test.config.MyAppConfig;
import com.experiment.test.factory.OrderFeignService;
import com.experiment.test.service.PrizeService;
import com.experiment.test.service.circularRef.UserInfoLazyService;
import com.experiment.test.service.UserInfoService;
import com.experiment.test.service.lookup.LookupService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;

import java.util.Locale;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
public class MyApplication {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			System.out.println();
		}

		//等价 AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class)
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(MyAppConfig.class);/* 生成MyAppConfig配置类的BeanDefinition */

		applicationContext.refresh();/* 加载配置，生成非懒加载bean */


		//通过别名查询
		UserInfoService userInfoService = (UserInfoService) applicationContext.getBean("com.experiment.test.service.UserInfoService");
		userInfoService.queryUsers();

		//通过FactoryBean手动实例化Bean
		System.out.println(applicationContext.getBean("feignFactoryBean"));
		OrderFeignService orderFeignService = (OrderFeignService) applicationContext.getBean("feignFactoryBean");
		orderFeignService.createOrder();


		applicationContext.getBean("prizeService", PrizeService.class).doPrize();


		applicationContext.getBean("com.experiment.test.service.UserInfoService");
		applicationContext.getBean("com.experiment.spring.test.service.MyUserInfoService", MyUserInfoService.class).queryUserInfo();


		System.out.println(applicationContext.getBean("orderService"));

		MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
		System.out.println(propertySources);


		applicationContext.getBean("userInfoLazyService", UserInfoLazyService.class).getService();
		applicationContext.getBean("lookupService", LookupService.class).test();


		System.out.println(applicationContext.getBean(MessageSource.class).getMessage("test2", null, new Locale("en")));

	}
}
