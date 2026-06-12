package com.experiment;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxuegui
 * @since 2024/12/19
 */
public class 线程池 {

	public static void main(String[] args) throws Exception{
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,30, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(1000));


		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("...threadPoolExecutor.execute");
			}
		});

		Future<?> f = threadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("...threadPoolExecutor.submit");
			}
		});
		f.get();

		threadPoolExecutor.shutdown();

		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("...threadPoolExecutor.shutdown");
			}
		});


		List<Runnable> runnables = threadPoolExecutor.shutdownNow();

		threadPoolExecutor.awaitTermination(3,TimeUnit.MINUTES);
	}
}
