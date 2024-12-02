package com.experiment.thread;

import org.junit.Test;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class Interrupt {

	private final static Object lock1 = new Object();
	private final static Object lock2 = new Object();

	public static void interruptSyncronized(){
		synchronized (lock1){
			LogUtil.log(Thread.currentThread().getName() + " 获锁成功");
			SleepUtil.sleep(5000);
		}
		LogUtil.log(Thread.currentThread().getName() +"  释放锁");
	}

	@Test
	public  void testInterruptSyncronized(){
		Thread t1 = new Thread("t1"){
			@Override
			public void run() {
				LogUtil.log("t1 run - 等待获锁");
				interruptSyncronized();
			}
		};

		Thread t2 = new Thread("t2"){
			@Override
			public void run() {
				LogUtil.log("t2 run - 等待获锁");
				interruptSyncronized();
			}
		};
		t1.start();
		SleepUtil.sleep(100);

		t2.start();

		SleepUtil.sleep(200);
		t2.interrupt();              /* synchronized等待获锁时，不能响应中断  */
		LogUtil.log("t2 run - 发起中断");
	}


}
