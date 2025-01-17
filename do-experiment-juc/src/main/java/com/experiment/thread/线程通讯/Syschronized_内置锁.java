package com.experiment.thread.线程通讯;

/**
 * @author chenxuegui
 * @since 2025/1/16
 */
public class Syschronized_内置锁 {

	private static int count = 0;

	public static synchronized void  increment(){
		count++;
	}

	public static void main(String[] args) {
		System.out.println("--------");
	}
}
