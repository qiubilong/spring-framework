package com.experiment.test.mybatis.service;

import com.experiment.test.mybatis.mapper.OrderMapper;
import com.experiment.test.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private UserMapper userMapper;

	public void test(){
		System.out.println(orderMapper.selectById());
		System.out.println(userMapper.selectById());
	}

}
