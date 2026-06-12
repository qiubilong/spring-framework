package com.experiment.spring.test.service;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
public interface UserWalletFeign {
	public Long queryWallet(Long uid);
}
