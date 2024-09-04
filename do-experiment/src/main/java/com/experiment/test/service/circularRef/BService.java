package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/4
 */
@Component
public class BService {

	@Autowired
	private AService aService;
}
