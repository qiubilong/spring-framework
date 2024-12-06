package com.experiment.thread;

import com.experiment.common.SleepUtil;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class ThreadEnd {     /* 业务线程最好的结束方式 */

	private static class BizThreadInterrupted extends Thread{
		@Override
		public void run() {
			try {
				while (!Thread.currentThread().isInterrupted()){/* 建议使用中断标志位interrupted来终止线程，线程阻塞后也能及时响应中断 */
					System.out.println("BizThreadInterrupted run");
					SleepUtil.sleep(3000);
				}

			} finally {
				System.out.println("BizThreadInterrupted finally");
			}
		}
	}

	private static class BizThreadVolatile extends Thread{
		private volatile boolean end = false; /* volatile修饰的变量相当于主存变量，非CPU缓存变量 */
		@Override
		public void run() {
			try {
				while (!end){ /* 自定义标志位来终止线程，线程阻塞后不能及时响应中断 */
					System.out.println("BizThreadVolatile run");
					Thread.sleep(3000); /* 自定义标志位来终止线程，线程阻塞后不能及时响应中断 */
				}

			}catch (Exception e){

			}
			finally {
				System.out.println("BizThreadVolatile finally");
			}
		}
	}


	public static void main(String[] args)  throws Exception{
		BizThreadInterrupted bizThreadInterrupted = new BizThreadInterrupted();
		bizThreadInterrupted.start();

		BizThreadVolatile bizThreadVolatile = new BizThreadVolatile();
		bizThreadVolatile.start();

		BizThreadNonVolatile bizThreadNonVolatile = new BizThreadNonVolatile();
		bizThreadNonVolatile.start();

		Thread.sleep(100);

		bizThreadInterrupted.interrupt();
		bizThreadVolatile.end = true;
	}

	private static class BizThreadNonVolatile extends Thread{
		private  boolean end = false; /* 非volatile修饰的变量相当于CPU缓存变量 */
		@Override
		public void run() {
			try {
				while (!end){
					System.out.println("BizThreadNonVolatile run");
					Thread.sleep(3000); /* 自定义非volatile标志位来终止线程，线程无法读到变量更新后的值，可能永远无法结束*/
				}

			}catch (Exception e){

			}
			finally {
				System.out.println("BizThreadNonVolatile finally");
			}
		}
	}
}
