package com.experiment.test.mybatis.spring;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBatisFactoryBean implements FactoryBean {

	private final Class<?> mapperInterface;

	public MyBatisFactoryBean(Class<?> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	@Override
	public Object getObject() {

		//jdk动态代理
		return Proxy.newProxyInstance(MyBatisFactoryBean.class.getClassLoader(), new Class[]{mapperInterface}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("mybatis mapper method="+method);
				return null;
			}
		});

	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
