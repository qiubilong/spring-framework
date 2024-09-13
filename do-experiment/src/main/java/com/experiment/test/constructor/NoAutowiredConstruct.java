package com.experiment.test.constructor;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/12
 */
@Component
public class NoAutowiredConstruct {

	/*
	*  无@AutoWired构造函数推断
	*  一个(有无参)构造函数 --> 选中
	*  多个构造函数 --> 选无参构造函数 --> 无则报错
	**/

	public NoAutowiredConstruct(){

	}

	public NoAutowiredConstruct(UserInfoService userInfoService){

	}

	public NoAutowiredConstruct(UserInfoService userInfoService, OrderService orderService){

	}
}
