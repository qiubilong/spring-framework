package com.experiment.thread.线程通讯;

import com.experiment.thread.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/4
 */
public class Syncronized {

	private final Object lock = new Object();

	private Integer count = 0;

	public void incrCount(){
		synchronized (lock){ /* 阻塞获锁排队不可中断 */
			count ++;
		}

	}

	public static void main(String[] args) {

		Syncronized syncronized = new Syncronized();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					syncronized.incrCount();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					syncronized.incrCount();
				}
			}
		}).start();

		SleepUtil.sleep(2000);
		System.out.println("count = " + syncronized.count);

	}
}
