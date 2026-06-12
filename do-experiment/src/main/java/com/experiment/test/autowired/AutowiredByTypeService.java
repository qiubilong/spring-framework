package com.experiment.test.autowired;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;

/**
 * @author chenxuegui
 * @since 2024/9/14
 */
public class AutowiredByTypeService {

	private UserInfoService userInfoService;

	private OrderService orderService;


	/* 指定AutowiredMode.byClass --> setXXX -->  参数类型 --> 自动注入依赖 */
	public void setService(UserInfoService userService) {
		this.userInfoService = userService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void test(){
		System.out.println("AutowiredByTypeServiceTest,userInfoService="+userInfoService);
		System.out.println("AutowiredByTypeServiceTest,orderService="+orderService);
	}
}
