package com.experiment.test.constructor;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/12
 */
public class ConstructByBeanDefinition {

	/*
	*  通过BeanDefinition手动注定构造函数
	*/

	public ConstructByBeanDefinition(UserInfoService userInfoService){
		System.out.println("构造函数 - ConstructByBeanDefinition(UserInfoService userInfoService)");

	}

	public ConstructByBeanDefinition(UserInfoService userInfoService, OrderService orderService){

	}
}
