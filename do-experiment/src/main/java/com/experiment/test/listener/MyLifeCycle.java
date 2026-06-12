package com.experiment.test.listener;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/11
 */
@Component
public class MyLifeCycle implements SmartLifecycle {
	private boolean isRunning  = false;
	@Override
	public void start() {
		System.out.println("MyLifeCycleTest -容器生命周期- start");
		isRunning = true;
	}

	@Override
	public void stop() {
		System.out.println("MyLifeCycleTest -容器生命周期- stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
