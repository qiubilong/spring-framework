package com.experiment.test.aop.impl;

import com.experiment.test.aop.api.UserServiceApi;

public class UserServiceImpl implements UserServiceApi {
	@Override
	public void ping() {
		System.out.println("userService ping");
	}

	@Override
	public void test() {
		System.out.println("userService test");
		throw new NullPointerException();
	}
}
