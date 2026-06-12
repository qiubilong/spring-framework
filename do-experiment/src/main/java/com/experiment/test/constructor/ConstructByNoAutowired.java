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
	*  1、一个(有无参)构造函数 --> 选中
	*  2、多个构造函数 --> 不存在无参构造函数则报错
	**/
    //第2点报错，是因为无法确定使用哪个构造函数，使用参数最多的构造函数？也不行，因为参数最多的构造函数有多个也不知道怎么办



	public ConstructByNoAutowired(){

	}

	public ConstructByNoAutowired(UserInfoService userInfoService){

	}

	public ConstructByNoAutowired(UserInfoService userInfoService, OrderService orderService){

	}
}
