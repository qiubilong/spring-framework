package com.experiment.test.constructor;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/12
 */
@Component
public class ExchangeService {

	/*
	*  无@AutoWired构造函数推断
	*  一个构造函数 --> 选中
	*  多个构造函数 --> 选参构造函数 --> 无则报错
	*
	**/

	/*public ExchangeService(){

	}*/

/*	public ExchangeService(UserInfoService userInfoService){

	}

	public ExchangeService(UserInfoService userInfoService, OrderService orderService){

	}*/
}
