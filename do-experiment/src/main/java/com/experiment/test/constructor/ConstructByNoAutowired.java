package com.experiment.test.constructor;

import com.experiment.test.service.OrderService;
import com.experiment.test.service.UserInfoService;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/12
 */
@Component
public class ConstructByNoAutowired {

	/*
	*  无@AutoWired构造函数推断
	*  一个(有无参)构造函数 --> 选中
	*  多个构造函数 --> 无参构造函数 --> 无则报错
	**/

	public ConstructByNoAutowired(){

	}

	public ConstructByNoAutowired(UserInfoService userInfoService){

	}

	public ConstructByNoAutowired(UserInfoService userInfoService, OrderService orderService){

	}
}
