package com.experiment.thread.线程通讯;

import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author chenxuegui
 * @since 2024/12/11
 */
@Slf4j
public class CountDownLatch_等待任务结束 {

	public static void main(String[] args) throws InterruptedException {
		int taskNum = 5;
		CountDownLatch countDownLatch = new CountDownLatch(taskNum);

		for (int i = 0; i < taskNum; i++) {
			int finalI = i;
			new Thread(new Runnable() {
				@Override
				public void run() {

					SleepUtil.sleepSec(1 + new Random().nextInt(5));
					System.out.println("任务"+ finalI +"执行完毕");
					countDownLatch.countDown();
				}
			}).start();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					countDownLatch.await();
					log.info("等待工作完成2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"等待工作完成线程2").start();

		countDownLatch.await();
		log.info("主线程:在所有任务运行完成后，进行结果汇总");
	}
}
