package com.experiment.test.aop.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
/* 方法执行前 - 通知 */
public class MyMethodBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("MyMethodBeforeAdvice--方法执行前");
		/* MethodBeforeAdvice会封装成MethodInterceptor,
		   先调用before方法，后调用MethodInvocation.proceed()
		 */
	}
}
