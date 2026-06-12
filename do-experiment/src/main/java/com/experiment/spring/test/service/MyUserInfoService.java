package com.experiment.spring.test.service;

import com.experiment.spring.my.MyBeanNameAware;
import com.experiment.spring.my.MyComponent;
import com.experiment.spring.my.MyInitializingBean;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent
public class MyUserInfoService implements MyInitializingBean, MyBeanNameAware {

	public void queryUserInfo(){
		System.out.println("MyUserInfoService.queryUserInfo xxx");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("MyUserInfoService.MyInitializingBean.afterPropertiesSet()");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("MyUserInfoService.MyInitializingBean.setBeanName()="+name);
	}
}
