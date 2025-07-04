package com.experiment.thread;


import com.experiment.common.Log;
import com.experiment.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/2
 *
 * 死锁条件：
 * 1、一个资源同时只允许一个线程使用
 * 2、一个线程获取到的资源不可以强行剥夺
 * 3、一个线程已经占有锁资源，再申请其他锁
 * 4、多个线程获取锁顺序形成循环依赖
 *
 * 避免死锁 -- 如果锁已经不可避免
 * 1、注意加锁顺序，保证按顺序加锁
 * 2、设置加锁超时时间
 * 3、提前检查，预防死锁
 *
 */
public class Interrupt_DeadLock {  /* synchronized死锁时，不能响应中断  */
	private final static Object lock1 = new Object();
	private final static Object lock2 = new Object();

	public static void lockMulti(Object lock1, Object lock2){ /* 发生死锁，可用 jstack pid 查看 */
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
