package com.experiment.test.aop;

import com.experiment.test.aop.advice.MyMethodAfterAdvice;
import com.experiment.test.aop.advice.MyMethodAroundAdvice;
import com.experiment.test.aop.advice.MyMethodBeforeAdvice;
import com.experiment.test.aop.advice.MyMethodExceptionAdvice;
import com.experiment.test.aop.impl.UserServiceImpl;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
public class TestSpringProxyFactoryAdvisor {

	public static void main(String[] args) {

		UserServiceImpl target = new UserServiceImpl();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);

		/* Advisor = Advice + PointCut   */
		proxyFactory.addAdvisor(new PointcutAdvisor() {

			//定义哪些方法需要aop
			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return "ping".equals(method.getName());
					}
				};
			}


			//添加方法执行通知
			@Override
			public Advice getAdvice() {
				return new MyMethodAroundAdvice();
			}

			@Override
			public boolean isPerInstance() {
				return false;
			}
		});

		UserServiceImpl proxy = (UserServiceImpl)proxyFactory.getProxy();


		proxy.ping();

		proxy.toString();

	}
}
