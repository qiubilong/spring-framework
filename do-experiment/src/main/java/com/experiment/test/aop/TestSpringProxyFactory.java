package com.experiment.test.aop;

import com.experiment.test.aop.impl.UserServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TestSpringProxyFactory {
	public static void main(String[] args) {

		UserServiceImpl target = new UserServiceImpl();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);
		proxyFactory.addAdvice(new MethodInterceptor() {
			@Nullable
			@Override
			public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object proceed = invocation.proceed();
				System.out.println("after");
				return proceed;
			}
		});

		UserServiceImpl proxy = (UserServiceImpl)proxyFactory.getProxy();

		proxy.ping();

	}
}
