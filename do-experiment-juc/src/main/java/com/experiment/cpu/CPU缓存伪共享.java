package com.experiment.cpu;

import sun.misc.Contended;

/**
 * @author chenxuegui
 * @since 2024/12/31
 */
public class CPU缓存伪共享 {

	public static void main(String[] args) throws Exception{

		Pointer pointer = new Pointer();

		long start = System.currentTimeMillis();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000000; i++) {
				pointer.x++;
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000000; i++) {
				pointer.y++;
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println(pointer.x+","+pointer.y);

		System.out.println(System.currentTimeMillis() - start);


	}


    /* 如果没有volatile修饰变量，编译器会自由地优化代码，避免了伪共享问题。禁止jit编译-XX:-UseCompiler后，也会发生伪共享问题 */
	private static class Pointer {
		// 避免伪共享： @Contended +  jvm参数：-XX:-RestrictContended  jdk8支持
		@Contended
		volatile long x;
		//避免伪共享： 缓存行填充
		//long p1, p2, p3, p4, p5, p6, p7; /* CPU缓存行大小为64B = 8个long */
		volatile long y;
	}
}
