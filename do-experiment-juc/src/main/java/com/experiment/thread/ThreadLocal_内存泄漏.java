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

	//static ThreadLocal<byte[]> bigThreadLocal = new ThreadLocal<>(); /* 静态类ThreadLocal对象因为有强引用，就算是弱引用，也不会被GC回收。如果没有remove也只是造成少量的内存泄漏 */

	public static void main(String[] args) {

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(500));

		for (int i = 0; i < 500; i++) {

			poolExecutor.submit(new Runnable() {
				@Override
				public void run() {
					/*
					 * Thread.ThreadLocalMap.Entry = ThreadLocal对象 + value
					 * 局部ThreadLocal对象 被包装成 弱引用WeakReference，方法栈帧销毁后，没有强引用指向，被gc回收。
					 *  但 线程池中的线程不会销毁，强引用着Thread.ThreadLocalMap.Entry.value，无法回收造成内存泄漏 */
					ThreadLocal<byte[]> bigThreadLocal = new ThreadLocal<>();

					//ThreadLocal一定要生成静态变量，否则Thread.ThreadLocalMap.Entry中的越来越多

					byte[] value = new byte[5 * 1024 * 1024];//5M
					bigThreadLocal.set(value);

					SleepUtil.sleep(200);
					Log.info("run end");

					bigThreadLocal.remove();//删除私有变量，回收内存
				}
			});
		}

		Log.info("poolExecutor submit");

	}
}
