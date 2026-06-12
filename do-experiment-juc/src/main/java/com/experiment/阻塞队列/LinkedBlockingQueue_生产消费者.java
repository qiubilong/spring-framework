package com.experiment.阻塞队列;

import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenxuegui
 * @since 2024/12/18
 */
@Slf4j
public class LinkedBlockingQueue_生产消费者 {

	public static void main(String[] args) {

		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					int data = new Random().nextInt();
					try {
						SleepUtil.sleepSec(10);
						queue.put(data);
						log.info("put data="+data+",queue="+queue);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		},"生产者").start();



		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						int data = queue.take();
						log.info("take data="+data);
						SleepUtil.sleepSec(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		},"消费者").start();
	}
}
