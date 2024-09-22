package com.experiment.test.aop;

import com.experiment.test.aop.api.UserServiceApi;
import com.experiment.test.aop.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJdkProxy {
	public static void main(String[] args) {

		UserServiceImpl target = new UserServiceImpl();

		UserServiceApi userService = (UserServiceApi) Proxy.newProxyInstance(TestJdkProxy.class.getClassLoader(), new Class[]{UserServiceApi.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before");
				Object invoke = method.invoke(target, args);
				System.out.println("after");
				return invoke;
			}
		});

		userService.ping();

	}
}
