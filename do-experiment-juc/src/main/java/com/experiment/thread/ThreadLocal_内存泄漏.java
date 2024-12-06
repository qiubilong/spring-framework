package com.experiment.thread;

import com.experiment.common.Log;
import com.experiment.common.SleepUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxuegui
 * @since 2024/12/6
 */
public class ThreadLocal_内存泄漏 {

	static ThreadLocal<byte[]> bigThreadLocal = new ThreadLocal<>();

	public static void main(String[] args) {

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(500));

		for (int i = 0; i < 500; i++) {

			poolExecutor.submit(new Runnable() {
				@Override
				public void run() {

					bigThreadLocal.set(new byte[5 * 1024 * 1024 * 8]);//5M

					SleepUtil.sleepSec(3);
					Log.info("run end");
					bigThreadLocal.remove();
				}
			});
		}

		Log.info("poolExecutor submit");

	}
}
