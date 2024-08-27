package com.experiment.factory;

import java.util.UUID;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
public class OrderFeignService {

	public void createOrder(){
		System.out.println("createOrder-"+ UUID.randomUUID());
	}
}
