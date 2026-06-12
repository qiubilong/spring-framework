package com.experiment.test.aop.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
/* 方法执行后 - 通知 */
public class MyMethodAfterAdvice implements AfterReturningAdvice {
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("MyMethodAfterAdvice -- 方法执行后method="+method);
		/* AfterReturningAdvice会适配成MethodInterceptor,
		   先执行代理责任链MethodInvocation.proceed()
		   后执行after代理逻辑
		 */

	}
}
