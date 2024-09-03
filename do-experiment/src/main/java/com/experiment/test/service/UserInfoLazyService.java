package com.experiment.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/2
 */
@Component
public class UserInfoLazyService {

	@Autowired
	@Lazy
	private UserInfoLazyService userInfoLazyService;

	public void getService() {
		System.out.println(userInfoLazyService);
	}
}
