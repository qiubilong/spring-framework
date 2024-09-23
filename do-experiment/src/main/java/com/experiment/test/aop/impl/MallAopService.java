package com.experiment.test.aop.impl;

import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/23
 */
@Component
public class MallAopService {

	public void doBuyPrize(){
		System.out.println("MallAopService.doBuyPrize()");
	}
}
