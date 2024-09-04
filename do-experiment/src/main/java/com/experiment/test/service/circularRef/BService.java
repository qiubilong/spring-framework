package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/4
 */
@Component
public class BService {

	private AService aService;

	public BService() {
		System.out.println("BService(0)");
	}


	@Autowired
	public BService(AService aService) {
		this.aService = aService;
		System.out.println("BService(1)");

	}
}
