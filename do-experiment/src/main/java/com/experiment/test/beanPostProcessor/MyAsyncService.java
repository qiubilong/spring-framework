package com.experiment.test.beanPostProcessor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/11/7
 */
@Component
public class MyAsyncService {

	@Async
	public void asyncTest1(){
		System.out.println("asyncTest1");
	}

	@Async("myTaskExecutor")
	public void asyncTest2(){
		System.out.println("asyncTest2");
	}
}
