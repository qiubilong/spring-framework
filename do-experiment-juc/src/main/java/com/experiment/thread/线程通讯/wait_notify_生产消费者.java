package com.experiment.thread.线程通讯;

import com.experiment.common.SleepUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chenxuegui
 * @since 2024/12/11
 */
@Slf4j
public class wait_notify_生产消费者 {
	volatile static boolean stop = false;

	public static void main(String[] args) throws IOException {
		wait_notify_生产消费者.Queue queue = new wait_notify_生产消费者.Queue(3);
		Producer producer = new Producer(queue);
		producer.start();
		Consumer consumer = new Consumer(queue);
		consumer.start();

		SleepUtil.sleepSec(20);

		producer.interrupt();
		consumer.interrupt();

		SleepUtil.sleepSec(6);
		System.out.println(queue.itemProduct);
		System.out.println(queue.itemConsume);
	}

	private static class Queue {
		private List<Object> itemProduct = new ArrayList<>(100);
		private List<Object> itemConsume = new ArrayList<>(100);

		private Object[] datas;
		private int size;

		private int offerIndex;
		private int takeIndex;

		public Queue(int size) {
			datas = new Object[size];
		}

		@SneakyThrows
		public  void  offerData(Object data){
			synchronized (this){
				while (datas.length == size){
					wait();
				}
				if(offerIndex == datas.length ){
					offerIndex = 0;
				}
				datas[offerIndex++] = data;
				size++;

				log.info("offerData offerIndex="+(offerIndex-1)+",data="+data);
				notifyAll();

				itemProduct.add(data);
			}

		}

		@SneakyThrows
		public Object takeData(){
			synchronized (this){
				while ( 0 == size){
					wait();
				}
				if(takeIndex == datas.length){
					takeIndex  = 0;
				}
				Object data = datas[takeIndex++];
				size--;
				notifyAll();
				log.info("takeData takeIndex="+(takeIndex-1)+",data="+data);

				itemConsume.add(data);
				return data;
			}
		}

	}

	private static class Producer extends Thread{
		private Queue queue;

		public Producer(Queue queue) {
			super("生产者");
			this.queue = queue;
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()){
				Random random = new Random();
				Integer data = random.nextInt();
				queue.offerData(data);
				SleepUtil.sleepSec(2);
			}
		}
	}

	private static class Consumer extends Thread{
		private Queue queue;

		public Consumer(Queue queue) {
			super("消费者");
			this.queue = queue;
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()){
				Object data = queue.takeData();
				SleepUtil.sleepSec(5);
			}

		}
	}
}
