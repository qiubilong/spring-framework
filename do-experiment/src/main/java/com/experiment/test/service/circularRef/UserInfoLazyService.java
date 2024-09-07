package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/2
 */
/* 懒加载 */
@Component
public class UserInfoLazyService {

	@Autowired /*  */
	private ApplicationContext applicationContext;

	@Autowired
	@Lazy /* 注入代理类，用到时再到容器中取（buildLazyResolutionProxy） */
	private UserInfoLazyService userInfoLazyService;

	public void getService() {
		System.out.println(userInfoLazyService);
	}

}
