package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/4
 */
@Component
public class AService {

	private BService bService;

	@Lazy
	public AService(BService bService) {
		this.bService = bService;
	}

	@Async
	public void doAsyn(){

	}
}
