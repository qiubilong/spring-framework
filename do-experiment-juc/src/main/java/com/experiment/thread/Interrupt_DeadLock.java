package com.experiment.thread;


import com.experiment.thread.common.Log;
import com.experiment.thread.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class Interrupt_DeadLock {  /* synchronized死锁时，不能响应中断  */
	private final static Object lock1 = new Object();
	private final static Object lock2 = new Object();

	public static void lockMulti(Object lock1, Object lock2){ /* 发生死锁，可用 jstack -pid 查看 */
		synchronized (lock1){
			Log.info("获锁lock1="+lock1);
			SleepUtil.sleep(200);
			synchronized (lock2){
				Log.info("获锁lock2="+lock2);
				SleepUtil.sleep(200);
			}
		}
	}

	public static void main(String[] args) {
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
		t2.interrupt();              /* synchronized死锁时，不能响应中断  */
	}

}
