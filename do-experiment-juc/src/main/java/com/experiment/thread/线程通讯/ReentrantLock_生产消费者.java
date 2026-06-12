package com.experiment.thread.线程通讯;

import com.experiment.common.SleepUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxuegui
 * @since 2024/12/10
 */
@Slf4j
public class ReentrantLock_生产消费者 {

	public static void main(String[] args) throws IOException {

		Queue queue = new Queue(3);

		new Producer(queue).start();
		new Consumer(queue).start();


		System.in.read();

	}


	private static class Queue{
		private Object[] datas;
		private int size;
		private int offerIndex;
		private int takeIndex;

        private final ReentrantLock lock = new ReentrantLock();
		private final Condition notEmpty;//需要等待的条件
		private final Condition notFull;

		public Queue(int size) {
			datas = new Object[size];
			notEmpty = lock.newCondition();
			notFull = lock.newCondition();
		}

		@SneakyThrows
		public boolean offerData(Object data){
			lock.lock();
			try {
				while (datas.length == size){
					log.info("offerData 队列满等待,data="+data);
					notFull.await();
				}
				if(offerIndex == datas.length){
					offerIndex = 0;
				}
				datas[offerIndex ++] = data;
				size ++;

				log.info("offerData offerIndex="+(offerIndex-1)+",data="+data);
				notEmpty.signalAll(); /* 将条件等待队列的所有线程节点，迁移到 获锁同步队列 */
			}finally {
				lock.unlock(); /* 唤醒获锁同步队列首节点线程，竞争获锁 */
			}
			return true;
		}

		@SneakyThrows
		public Object takeData(){
			lock.lock();
			try {
				while (size == 0){
					notEmpty.await();
				}
				if(takeIndex == datas.length){
					takeIndex = 0;
				}
				Object data = datas[takeIndex++];
				size --;

				log.info("takeData takeIndex="+(takeIndex-1)+",data="+data);
				notFull.signalAll();
				return data;
			}finally {
				lock.unlock();
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
