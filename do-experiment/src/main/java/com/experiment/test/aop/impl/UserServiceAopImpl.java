package com.experiment.test.aop.impl;

import com.experiment.test.aop.api.UserServiceAopApi;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAopImpl implements UserServiceAopApi {
	@Override
	public void ping() {
		System.out.println("UserServiceAop ping");
	}

	@Override
	public void test() {
		System.out.println("UserServiceAop test");
		throw new NullPointerException();
	}
}
