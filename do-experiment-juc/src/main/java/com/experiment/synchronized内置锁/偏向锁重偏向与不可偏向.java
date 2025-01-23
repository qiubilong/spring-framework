package com.experiment.synchronized内置锁;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chenxuegui
 * @since 2025/1/23
 *  BiasedLockingBulkRebiasThreshold = 20    //默认偏向锁批量重偏向阈值
 *  · 批量重偏向和批量撤销是针对类的优化，和对象无关。
 *  · 偏向锁重偏向一次之后不可再次重偏向。
 *  · 当某个类已经触发批量撤销机制后，JVM会默认当前类产生了严重的问题，剥夺了该类的新实例对象使用偏向锁的权利
 */
@Slf4j
public class 偏向锁重偏向与不可偏向 {  /* 当偏向锁撤销次数到20次时，虚拟机认为以前偏向的线程是不合适的，可以重新偏向新的线程，以减少没必要的偏向撤销 */

	public static void main(String[] args) throws Exception{
		Thread.sleep(5000);//默认延迟4秒后开启偏向锁

		List<Object> list = new ArrayList<>();
		// 线程1
		new Thread(() -> {
			for (int i = 0; i < 50; i++) {
				//批量加锁，全部变成偏向锁状态
				Object lock = new Object();
				synchronized (lock) {
					list.add(lock);
				}
			}
			try {
				//为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thead1").start();

		//睡眠3s钟保证线程thead1创建对象并且加锁完成
		Thread.sleep(3000);
		log.debug("打印thead1，list中第20个对象的锁状态：偏向锁");
		log.debug((ClassLayout.parseInstance(list.get(19)).toPrintable()));

		// 线程2
		new Thread(() -> {
			for (int i = 0; i < 50; i++) {
				Object obj = list.get(i);
				synchronized (obj) {
					if(i>=15&&i<=17){
						log.debug("thread2-第" + (i + 1) + "次加锁执行中 --（偏向锁撤销 -->无锁 --> 轻量级锁） \t"+
								ClassLayout.parseInstance(obj).toPrintable());
					}

					if(i==19){
						log.debug("thread2-第" + (i + 1) + "次加锁执行中 -- （偏向锁撤销次数>20，偏向锁重偏向 -->偏向锁 ）\t"+
								ClassLayout.parseInstance(obj).toPrintable());
					}
				}

				if(i==17){
					log.debug("thread2-第" + (i + 1) + "次释放锁 --（轻量级锁 -->无锁 ）\t"+
							ClassLayout.parseInstance(obj).toPrintable());
				}
				if(i==19){
					log.debug("thread2-第" + (i + 1) + "次释放锁 --（偏向锁重偏向 -->偏向锁 ）\t"+
							ClassLayout.parseInstance(obj).toPrintable());
				}
			}
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thead2").start();
		Thread.sleep(3000);//等待线程加锁完成


		//时间-XX:BiasedLockingDecayTime=25000ms范围内没有达到40次，撤销次数清为0，重新计时
		//当其达到批量撤销的阈值后（默认40），JVM就认为该class的使用场景存在多线程竞争，会标记该class为不可偏向，之后，对于该class的锁，直接走轻量级锁的逻辑。
		new Thread(() -> {
			for (int i = 0; i < 50; i++) {
				Object lock =list.get(i);
				synchronized (lock){
				}
			}
		},"thread3").start();

		Thread.sleep(3000);//等待线程加锁完成

		Object obj = new Object();
		System.out.println("------------达到批量撤销的阈值后（默认40），新建的锁对象不再是可偏向状态，而是无锁状态------------------");
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());

		LockSupport.park();
	}
}
