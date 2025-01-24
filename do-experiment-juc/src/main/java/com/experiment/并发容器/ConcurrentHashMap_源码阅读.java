package com.experiment.并发容器;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenxuegui
 * @since 2025/1/24
 */
public class ConcurrentHashMap_源码阅读 {

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	private static final int DEFAULT_CAPACITY = 16;

	private static int RESIZE_STAMP_BITS = 16;

	private static final int MIN_TRANSFER_STRIDE = 16;


	private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1; //65535

	static final int NCPU = Runtime.getRuntime().availableProcessors();//操作系统可见处理器数

	/**
	 * The bit shift for recording size stamp in sizeCtl.
	 */
	private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;

	public static void main(String[] args) {
		int tableLength = DEFAULT_CAPACITY;
		int n = DEFAULT_CAPACITY;

		ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
		concurrentHashMap.put("key1","val1");

		concurrentHashMap.remove("key1");

		int stride = (NCPU > 1) ? (n >>> 3) / NCPU : n;
		System.out.println(n >>> 3);
		System.out.println("stride="+stride);


		System.out.println((n << 1) - (n >>> 1));
		System.out.println((n << 1)*0.75);

		int rs = resizeStamp(tableLength) << RESIZE_STAMP_SHIFT;
		System.out.println(rs);

		System.out.println(MAX_RESIZERS);

		System.out.println("-----------------------");
		tryPresize(n << 1);
	}

	static final int resizeStamp(int n) {
		return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
	}

	private final static void tryPresize(int size) {
		int num = size + (size >>> 1) + 1;
		System.out.println(num);
		System.out.println(tableSizeFor(num));
	}

	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
}
