package com.experiment.thread;


import org.junit.Test;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class InterruptSyncronized {

	private final static Object lock1 = new Object();
	private final static Object lock2 = new Object();

	public static void interruptSyncronized(){
		synchronized (lock1){  /* synchronized等待获锁时，不能响应中断  */
			log.info(Thread.currentThread().getName() + " 获锁成功");
			SleepUtil.sleep(5000);
		}
		log.info(Thread.currentThread().getName() +"  释放锁");
	}

	public static void main(String[] args) {
		Thread t1 = new Thread("t1") {
			@Override
			public void run() {
				log.info("t1 run - 等待获锁");
				interruptSyncronized();
			}
		};

		Thread t2 = new Thread("t2") {
			@Override
			public void run() {
				log.info("t2 run - 等待获锁");
				interruptSyncronized();
			}
		};
		t1.start();
		SleepUtil.sleep(100);

		t2.start();

		SleepUtil.sleep(200);
		t2.interrupt();              /* synchronized等待获锁时，不能响应中断  */
		log.info("t2 run - 发起中断");

	}

	public static void lockMulti(Object lock1, Object lock2){
		synchronized (lock1){
			log.info("获锁lock1");
			SleepUtil.sleep(200);
			synchronized (lock2){
				log.info("获锁lock2");
				SleepUtil.sleep(200);
			}
		}
	}

	@Test
	public void TestInterrupt_DeadLock(){

		Thread t1 = new Thread("t1") {
			@Override
			public void run() {
				lockMulti(lock2,lock1);
			}
		};

		Thread t2 = new Thread("t2") {
			@Override
			public void run() {
				lockMulti(lock1,lock2);
			}
		};

		t1.start();
		t2.start();
		SleepUtil.sleep(200);
		t2.interrupt();              /* synchronized等待获锁时，不能响应中断  */
	}
}
