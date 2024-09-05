package com.experiment.test.service;

import com.experiment.test.service.pay.IPayService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/30
 */
@Component
public class OrderService {


    @Autowired /* byClass ( @Primary > @Priority )  > byName  */
	@Qualifier("jdPayService")/* 直接byName  */
	private IPayService aliPayService;

	@Override
	public String toString() {
		return "OrderService{" +
				"payService=" + aliPayService +
				'}';
	}

}
