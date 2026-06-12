package com.experiment.test.circularRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/14
 */
@Component
public class ProxyAService {

	@Autowired
	private ProxyBService proxyBService;

	/* 循环依赖 --> 出现多次动态代理时 --> 最后的代理对象与提前曝光的代理对象不同 --> 报错  */
	//@Async
	public void asyn(){

	}



}
