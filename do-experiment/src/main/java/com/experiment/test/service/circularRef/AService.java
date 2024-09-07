package com.experiment.test.service.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/4
 */
/* 循环依赖 */
@Component
public class AService {

	private BService bService;

	@Lazy/* 生成代理对象解决循环依赖，按需从BeanFactory中获取原始对象 */
	public AService(BService bService) {
		this.bService = bService;
	}

}
