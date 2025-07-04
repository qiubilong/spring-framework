package com.experiment.thread;

import java.util.concurrent.Semaphore;

/**
 * @author chenxuegui
 * @since 2025/6/20
 */
public class 线程交替执行 {

	public static void main(String[] args) {

		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);

		try {
			s2.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

				while (true){

					try {
						s1.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("A");

					s2.release();
				}

			}
		},"t1");



		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){

					try {
						s2.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("B");

					s1.release();
				}
			}
		},"t2");

		t1.start();
		t2.start();


	}
}
