package com.experiment.synchronized内置锁;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author chenxuegui
 * @since 2025/1/16
 */
public class 对象布局 {
	public static void main(String[] args) {
		Object obj = new Test();

		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}

	private static class Test {
		private long p;
	}
}
