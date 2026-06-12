package com.experiment.test.constructor;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/14
 */
@Component
public class ConstructByAutowired {

	/* 不能指定多个@Autowired构造函数 */
	@Autowired
	public ConstructByAutowired(UserInfoService userInfoService){

	}

	//@Autowired
	public ConstructByAutowired(UserInfoService userInfoService, OrderService orderService){

	}
}
