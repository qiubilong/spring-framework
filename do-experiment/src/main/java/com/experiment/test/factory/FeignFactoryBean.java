package com.experiment.test.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/* 手动实例化Bean对象
*  跳过spring bean对象实例化生命周期
*  */
@Component
public class FeignFactoryBean implements FactoryBean<OrderFeignService> {


	@Override
	public OrderFeignService getObject() throws Exception {
		System.out.println("FeignBeanFactory getObject() 单例只会调用一次");
		return new OrderFeignService();
	}

	/* 声明class，用于注入查找匹配，因为spring是按需实例化Bean，例如Bean是懒加载、原型Bean */
	@Override
	public Class<?> getObjectType() {
		return OrderFeignService.class;
	}

}
