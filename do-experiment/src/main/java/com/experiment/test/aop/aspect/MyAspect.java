package com.experiment.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Pointcut("execution(public void com.experiment.test..*.testAop())")
	public void a(){

	}

	@Before("a()")
	public void MyAdviceBefore(JoinPoint joinPoint) {
		System.out.println("MyAdviceBefore");
	}


}