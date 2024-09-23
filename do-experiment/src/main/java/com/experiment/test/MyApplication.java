package com.experiment.test;

import com.experiment.spring.test.service.MyUserInfoService;
import com.experiment.test.aop.api.UserServiceAopApi;
import com.experiment.test.aop.impl.UserServiceAopImpl;
import com.experiment.test.autowired.AutowiredByTypeService;
import com.experiment.test.config.MyAppConfig;
import com.experiment.test.factory.OrderFeignService;
import com.experiment.test.mybatis.test.service.MyBatisExchangeService;
import com.experiment.test.service.PrizeService;
import com.experiment.test.circularRef.UserInfoLazyService;
import com.experiment.test.service.UserInfoService;
import com.experiment.test.autowired.lookup.LookupService;
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


		//手动注入beanDefinition,执行Bean别名
		applicationContext.getBean("com.experiment.test.service.UserInfoService");
		applicationContext.getBean("com.experiment.spring.test.service.MyUserInfoService", MyUserInfoService.class).queryUserInfo();

		//@Autowired属性注入优先级  ByClass(@Primary) -- ByName
		System.out.println(applicationContext.getBean("orderService"));

		//环境变量，配置文件
		MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
		System.out.println(propertySources);

		//@Lazy加载、循环依赖
		applicationContext.getBean("userInfoLazyService", UserInfoLazyService.class).getService();
		applicationContext.getBean("lookupService", LookupService.class).test();

		//事件发布
		System.out.println(applicationContext.getBean(MessageSource.class).getMessage("test2", null, new Locale("en")));
		applicationContext.publishEvent("发布事件123");

		//bean属性byType自动注入
		applicationContext.getBean(AutowiredByTypeService.class).test();

		//mybatis集成
		applicationContext.getBean(MyBatisExchangeService.class).test();
		System.out.println();

		//aop
	/*	UserServiceAopApi userServiceAopByFactoryBean = (UserServiceAopApi) applicationContext.getBean("userServiceAopByFactoryBean");
		userServiceAopByFactoryBean.ping();*/
		UserServiceAopImpl userServiceAopImpl = (UserServiceAopImpl)applicationContext.getBean("userServiceAopImpl");
		userServiceAopImpl.ping();
	}
}
