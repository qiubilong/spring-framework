package com.experiment.test.aop.impl;

import com.experiment.test.aop.api.UserServiceAopApi;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAopImpl implements UserServiceAopApi {
	@Override
	public int ping() {
		System.out.println("UserServiceAop ping");
		return 1;
	}

	@Override
	public void test() {
		System.out.println("UserServiceAop test");
		throw new NullPointerException();
	}

	public final void testFinal(){

	}



}
