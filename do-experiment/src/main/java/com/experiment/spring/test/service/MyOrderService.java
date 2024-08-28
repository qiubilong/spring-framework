package com.experiment.spring.test.service;

import com.experiment.spring.my.MyAutowired;
import com.experiment.spring.my.MyComponent;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent("orderService")
public class MyOrderService {

	@MyAutowired
	private MyUserInfoService userInfoService;

	public void createOrder(){
		System.out.println("MyOrderService.createOrder() --> userInfoService="+userInfoService);
	}
}
