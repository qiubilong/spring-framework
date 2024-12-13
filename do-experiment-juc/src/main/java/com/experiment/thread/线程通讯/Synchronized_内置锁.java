package com.experiment.thread.线程通讯;

import com.experiment.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/4
 */
public class Synchronized_内置锁 {

	private final Object lock = new Object();

	private Integer count = 0;

	public void incrCount(){
		synchronized (lock){ /* 阻塞获锁排队不可中断 */
			count ++;
		}

	}

	public static void main(String[] args) {

		Synchronized_内置锁 synchronized内置锁 = new Synchronized_内置锁();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					synchronized内置锁.incrCount();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					synchronized内置锁.incrCount();
				}
			}
		}).start();

		SleepUtil.sleep(2000);
		System.out.println("count = " + synchronized内置锁.count);

	}
}
