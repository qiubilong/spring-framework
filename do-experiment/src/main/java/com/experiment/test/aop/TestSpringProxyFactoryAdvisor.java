package com.experiment.test.aop;

import com.experiment.test.aop.advice.MyMethodAroundAdvice;
import com.experiment.test.aop.api.UserServiceAopApi;
import com.experiment.test.aop.impl.UserServiceAopImpl;
import org.aopalliance.aop.Advice;
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

		UserServiceAopImpl target = new UserServiceAopImpl();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);

		proxyFactory.setInterfaces(UserServiceAopApi.class);/* JdkDynamicProxy */

		/* Advisor = Advice + PointCut   */
		proxyFactory.addAdvisor(new PointcutAdvisor() {

			//匹配规则 --> 定义哪些方法需要aop
			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return "ping".equals(method.getName());
					}
				};
			}


			//代理逻辑 --> 方法执行通知
			@Override
			public Advice getAdvice() {
				return new MyMethodAroundAdvice();
			}

			@Override
			public boolean isPerInstance() {
				return false;
			}
		});

		UserServiceAopApi proxy = (UserServiceAopApi)proxyFactory.getProxy();


		proxy.ping();

		proxy.toString();

	}
}
