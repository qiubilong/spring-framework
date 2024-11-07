package com.experiment.test.beanPostProcessor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxuegui
 * @since 2024/11/7
 */
@Component
public class MyAsyncService implements IMyAsyncService{

	//@Async
	public void asyncTest1(){
		System.out.println("asyncTest1");
	}

	@Async("myTaskExecutor")
	@Transactional
	@Override
	public void asyncTest2(){
		System.out.println("asyncTest2");
		throw new UnsupportedOperationException();
	}
}
