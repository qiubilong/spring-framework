package com.experiment.thread;

import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenxuegui
 * @since 2024/12/30
 *  CPU缓存一致性
 * https://blog.csdn.net/m0_72088858/article/details/127275149
 */
@Slf4j
public class BizThreadEnd_NonVolatile {

	public static void main(String[] args) {
		BizThread bizThread = new BizThread();
		bizThread.start();


		SleepUtil.sleep(2000);
		bizThread.end = true;
	}


    /*  变量end为非volatile变量时，jit编译器会优化代码，导致即使CPU有缓存一致性run循环代码不能正常退出。禁止jit编译后可正常退出-XX:-UseCompiler  */
	private static class BizThread extends Thread{

		private  boolean end = false; /* 非volatile修饰的变量相当于CPU缓存变量 */
		@Override
		public void run() {
			try {
				while (!end){/* 自定义非volatile标志位来终止线程，线程无法读到变量更新后的值，可能永远无法结束*/
					//log.info("BizThreadNonVolatile run");//log底层加锁，会导致内存可见性
					//Thread.sleep(3000); //cpu上下文切换，会导致内存可见性
				}

			}catch (Exception e){

			}
			finally {
				System.out.println("BizThreadNonVolatile finally");
			}
		}
	}

}
