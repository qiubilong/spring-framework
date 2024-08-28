package com.experiment.test.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
@Component
public class FeignBeanFactory implements FactoryBean<OrderFeignService> {


	@Override
	public OrderFeignService getObject() throws Exception {
		System.out.println("FeignBeanFactory getObject() 单例只会调用一次");
		return new OrderFeignService();
	}

	@Override
	public Class<?> getObjectType() {
		return OrderFeignService.class;
	}

	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
