package com.experiment.thread;

import com.experiment.thread.common.Log;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
/* 守护线程就是非业务线程，处理支持工作，JVM没有业务线程时会直接退出 */
public class MyDaemonThread extends Thread{

	@Override
	public void run() {

		try {
			while (true){
				Log.info("MyDaemonThread run");
			}
		}finally {
			Log.info("MyDaemonThread finally"); /* 线程是守护线程时，线程可以随时退出，不一定执行finally方法 */
		}

	}

	public static void main(String[] args) throws Exception{
		MyDaemonThread thread = new MyDaemonThread();
		thread.setDaemon(true);/* 业务线程不建议设置 */

		thread.start();

		Thread.sleep(0);
		Log.info("MainThread end");
	}
}
