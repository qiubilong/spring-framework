package com.experiment.并发容器;

import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/*  CopyOnWriteArrayList 线程读不加锁-volatile保证可见性， 写加锁-ReentrantLock保证互斥
 *  类似设计思想应用 - Service定时加载数据库配置，用户线程直接读
 */
@Slf4j
public class CopyOnWriteArrayList_缓存 {
	public static void main(String[] args) {
		CopyOnWriteArrayList<String> ipBlacks = new CopyOnWriteArrayList<String>();
		ipBlacks.add("ip0");
		ipBlacks.add("ip1");
		ipBlacks.add("ip2");

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					int id = new Random().nextInt(4);
					String ip = "ip" + id;
					if(ipBlacks.contains(ip)){
						log.info("命中ip黑名单 "+ip);
					}else {
						log.info("未命中ip黑名单 "+ip);
					}
					SleepUtil.sleepSec(2);
				}
			}
		}).start();


	   SleepUtil.sleepSec(20);
	   ipBlacks.add("ip3");
	}
}
