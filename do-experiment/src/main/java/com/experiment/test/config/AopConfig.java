package com.experiment.test.config;

import com.experiment.test.aop.advice.MyMethodAroundAdvice;
import com.experiment.test.aop.impl.UserServiceAopImpl;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
@Component
public class AopConfig {


	@Bean
	BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		//bean 名字通配符
		beanNameAutoProxyCreator.setBeanNames("*Aop*");
		//Advice or Advisor 名字通配符
		beanNameAutoProxyCreator.setInterceptorNames("myMethodAroundAdvice");
		beanNameAutoProxyCreator.setProxyTargetClass(true);

		return beanNameAutoProxyCreator;
	}

	@Bean
	MyMethodAroundAdvice myMethodAroundAdvice(){
		return new MyMethodAroundAdvice();
	}


	@Bean /* 针对单个Bean配置Aop */
	ProxyFactoryBean userServiceAopByFactoryBean(){

		UserServiceAopImpl target = new UserServiceAopImpl();
		ProxyFactoryBean factoryBean = new ProxyFactoryBean();
		factoryBean.setTarget(target);
		factoryBean.addAdvisor(new PointcutAdvisor() {
			@Override
			public Pointcut getPointcut() {
				return new StaticMethodMatcherPointcut() {
					@Override
					public boolean matches(Method method, Class<?> targetClass) {
						return "ping".equals(method.getName());
					}
				};
			}

			@Override
			public Advice getAdvice() {
				return new MethodInterceptor() {
					@Nullable
					@Override
					public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
						System.out.println("userServiceAopByFactoryBean -- 方法执行前");
						Object proceed = invocation.proceed();
						System.out.println("userServiceAopByFactoryBean -- 方法执行后");
						return proceed;
					}
				};
			}
			@Override
			public boolean isPerInstance() {
				return false;
			}
		});

		return factoryBean;
	}

}
