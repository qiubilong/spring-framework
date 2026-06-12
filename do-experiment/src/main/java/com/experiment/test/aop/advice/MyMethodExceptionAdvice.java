package com.experiment.test.aop.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
/* 方法执行异常 - 通知 */
public class MyMethodExceptionAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
		System.out.println("MyMethodExceptionAdvice -- 方法执行异常 -- Exception="+ ex.getMessage());
	}
}
