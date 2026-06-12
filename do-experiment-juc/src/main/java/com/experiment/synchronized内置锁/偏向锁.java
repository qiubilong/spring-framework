package com.experiment.synchronized内置锁;

import com.experiment.common.SleepUtil;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author chenxuegui
 * @since 2025/1/20
 *
 * 001 -- 无锁
 * 101 -- 偏向锁 -- 偏向某个线程，释放锁后还是偏向锁。如果后面有其他线程来获取锁，就得撤销偏向锁
 * x00 -- 轻量级锁
 * x10 -- 重量级锁
 */
public class 偏向锁 {

	//禁止偏向锁   -XX:-UseBiasedLocking
	public static void main(String[] args) throws InterruptedException {

		System.out.println("------------未开启偏向锁时，无锁状态------------------");
		System.out.println(ClassLayout.parseInstance( new Object()).toPrintable());

		Thread.sleep(5000);//默认延迟4秒后开启偏向锁
		Object obj = new Object();

		System.out.println("------------没有线程加锁时，匿名偏向------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功，偏向锁------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}
			}
		},"thread1").start();

		Thread.sleep(5000);

		System.out.println("------------thread1 释放锁，还是偏向锁（markword没变）------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	@Test
	public void 偏向锁To无锁(){
		SleepUtil.sleepSec(5);//默认延迟4秒后开启偏向锁

		Object obj = new Object();
		System.out.println("------------锁对象刚创建，处于匿名偏向状态------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功，偏向锁（锁对象第一次被加锁）------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}

				System.out.println("------------thread1 释放锁，偏向锁（markword没变）------------------");
				System.out.println(ClassLayout.parseInstance(obj).toPrintable());
			}
		},"thread1").start();

		SleepUtil.sleepSec(5);//等待线程结束

		obj.hashCode();/* 因为偏向锁状态下没办法保存hashcode等信息，只能升级为无锁状态 */
		System.out.println("------------偏向锁调用hashCode(),偏向锁撤销为无锁状态  -----------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	/* 线程A执行完后，线程B来加锁，发现不是偏向自己，就进行偏向锁测，升级为轻量级锁 */
	@Test
	public void 偏向锁To轻量级锁(){
		SleepUtil.sleepSec(5);//默认延迟4秒后开启偏向锁

		Object obj = new Object();
		System.out.println("------------锁对象刚创建，处于匿名偏向状态------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread1 加锁成功，偏向锁------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}

				System.out.println("------------thread1 释放锁，偏向锁（markword没变）------------------");
				System.out.println(ClassLayout.parseInstance(obj).toPrintable());

				SleepUtil.sleepSec(30);//线程保活，防止虚拟机创建thread2时复用thread1，导致还是同一个线程获取偏向锁
			}
		},"thread1").start();

		SleepUtil.sleepSec(5);//等待释放锁
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------thread2 加锁成功，偏向锁 --> 轻量级锁------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());
				}
			}
		},"thread2").start();
		SleepUtil.sleepSec(5);//等待线程结束

		System.out.println("------------thread2 释放锁 --> 无锁状态---------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	@Test
	public void 偏向锁To重量级锁_hashCode(){
		SleepUtil.sleepSec(5);//默认延迟4秒后开启偏向锁


		Object obj = new Object();
		System.out.println("------------锁对象刚创建，处于匿名偏向状态------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------加锁成功，偏向锁------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());

					System.out.println("------------加锁中调用，hashCode() --> 重量级锁 ------------------");
					obj.hashCode();
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());

				}
			}
		},"thread1").start();

		SleepUtil.sleepSec(5);

		System.out.println("------------释放锁 --> 无锁状态---------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	@Test
	public void 偏向锁To重量级锁_wait(){
		SleepUtil.sleepSec(5);//默认延迟4秒后开启偏向锁


		Object obj = new Object();
		System.out.println("------------锁对象刚创建，处于匿名偏向状态------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj){
					System.out.println("------------加锁成功，偏向锁------------------");
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());

					System.out.println("------------加锁中调用，wait() --> 重量级锁 ------------------");
					try {
						obj.wait(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(ClassLayout.parseInstance(obj).toPrintable());

				}
			}
		},"thread1").start();

		SleepUtil.sleepSec(5);

		System.out.println("------------释放锁 --> 无锁状态---------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}
}
