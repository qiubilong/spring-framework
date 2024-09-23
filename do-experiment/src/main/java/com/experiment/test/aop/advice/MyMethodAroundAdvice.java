package com.experiment.test.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
/* 方法前后 - 通知 */
public class MyMethodAroundAdvice implements MethodInterceptor {
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {

		System.out.println("MyMethodAroundAdvice --方法前后织入 -- 方法执行前");
		Object result = invocation.proceed();/* 责任链模式，执行下一个过滤器 */
		System.out.println("MyMethodAroundAdvice --方法前后织入 -- 方法执行前");

		return result;
	}
}
