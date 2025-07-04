package com.experiment.thread.线程通讯;

import com.experiment.common.Log;
import com.experiment.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/5
 */
public class Thread_Join {

	public static void main(String[] args) throws Exception{

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SleepUtil.sleepSec(10);
				Log.info("t1 end ");
			}
		},"t1");

		t1.start();


		Log.info("main run  ");

		t1.join();//wait t1 finish


		Log.info("main end  ");
	}
}
