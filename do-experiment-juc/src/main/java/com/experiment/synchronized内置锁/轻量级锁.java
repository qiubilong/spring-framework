package com.experiment.synchronized内置锁;

import com.experiment.common.SleepUtil;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author chenxuegui
 * @since 2025/1/23
 */
public class 轻量级锁 {

	@Test
	public void 无锁To轻量级锁(){

		Object obj = new Object();
		System.out.println("------------无锁状态（默认延迟4秒后才开启偏向锁）------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功，轻量级锁 ------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}

				System.out.println("------------thread1 释放锁，无锁状态 ------------------");
				System.out.println(ClassLayout.parseInstance(obj).toPrintable());
			}
		},"thread1").start();

		SleepUtil.sleepSec(5);//等待线程结束
	}
    /* 轻微竞争场景：无锁状态 -->轻量级锁 --> 无锁状态 --> 轻量级锁 */
	@Test
	public void 轻量级锁To轻量级锁(){
		Object obj = new Object();
		System.out.println("------------无锁状态（默认延迟4秒后才开启偏向锁）------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功：轻量级锁 ------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}

				System.out.println("------------thread1 释放锁：无锁状态 ------------------");
				System.out.println(ClassLayout.parseInstance(obj).toPrintable());
			}
		},"thread1").start();
		SleepUtil.sleep(1);//模拟轻微竞争

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread2 加锁成功：轻量级锁 ------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}

				System.out.println("------------thread2 释放锁：无锁状态 ------------------");
				System.out.println(ClassLayout.parseInstance(obj).toPrintable());
			}
		},"thread2").start();

		SleepUtil.sleepSec(5);//等待线程结束
	}

	/* 并发竞争场景：无锁状态、轻量级锁  --> 重量级锁 */
	@Test
	public void 轻量级锁To重量级锁(){
		Object obj = new Object();
		System.out.println("------------无锁状态（默认延迟4秒后才开启偏向锁）------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功 ------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}
			}
		},"thread1");

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread2 加锁成功 ------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}
			}
		},"thread2");

		thread1.start();
		thread2.start();

		SleepUtil.sleepSec(5);//等待线程结束

		System.out.println("------------释放锁：无锁状态 ------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

}
