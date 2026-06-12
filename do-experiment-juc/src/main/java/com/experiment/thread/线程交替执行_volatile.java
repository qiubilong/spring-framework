package com.experiment.thread;

import java.util.concurrent.Semaphore;

/**
 * @author chenxuegui
 * @since 2025/6/20
 */
public class 线程交替执行_volatile {

	static volatile int s1 = 0;
	static volatile int s2 = 0;

	public static void main(String[] args) {

		s1 = 1;
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

				while (true){
					if(s1 ==1){
						System.out.println("A");
						s1 = 0;
						s2 = 1;
					}
				}

			}
		},"t1");



		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){

					if(s2 == 1){
						System.out.println("B");
						s2 = 0;
						s1 =1;
					}
				}
			}
		},"t2");

		t1.start();
		t2.start();


	}
}
