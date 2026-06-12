package com.experiment.thread;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;

/**
 * @author chenxuegui
 * @since 2024/11/28
 */

/*
-- 守护线程-支持业务线程
[6] Monitor Ctrl-Break       //监控 Ctrl-Break 中断信号的
[5] Attach Listener          //内存 dump，线程 dump，类信息统计， 获取系统属性等
[4] Signal Dispatcher        //分发处理发送给 JVM 信号的线程
[3] Finalizer                //调用对象 finalize 方法的线程
[2] Reference Handler        //管理清除引用对象 WeakReference 、SoftReference 和 PhantomReference的线程

-- 非守护线程-业务线程
[1] main                     //main 线程， 用户程序入口
 */
public class JVMThreads {

	public static void main(String[] args) {
		// 获取所有线程的堆栈信息
		Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
		// 遍历每个线程及其堆栈信息
		for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
			Thread thread = entry.getKey();
			StackTraceElement[] stackTraceElements = entry.getValue();
			System.out.println("Thread: " + thread.getName() + " (ID: " + thread.getId() + ")");
			for (StackTraceElement element : stackTraceElements) {
				System.out.println("\tat " + element);
			}
			System.out.println("---------------------------------------------------");
		}
	}

	@Test
	public void threadMXBean(){
		//Java 虚拟机线程系统的管理接口
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		// 不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		// 遍历线程信息，仅打印线程ID和线程名称信息
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName() );
		}

		System.out.println(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
	}
}
