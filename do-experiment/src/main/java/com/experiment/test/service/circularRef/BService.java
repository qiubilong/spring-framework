package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/4
 */
/* 循环依赖 */
@Component
public class BService {

	private AService aService;

	public BService(AService aService) {
		this.aService = aService;
		System.out.println("BService(1)");

	}
}
