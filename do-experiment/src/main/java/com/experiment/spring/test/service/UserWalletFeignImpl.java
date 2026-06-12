package com.experiment.spring.test.service;

import com.experiment.spring.my.MyComponent;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent
public class UserWalletFeignImpl implements UserWalletFeign{
	@Override
	public Long queryWallet(Long uid) {
		System.out.println("UserWalletFeignImpl.queryWallet.uid="+uid);
		return 12122L;
	}
}
