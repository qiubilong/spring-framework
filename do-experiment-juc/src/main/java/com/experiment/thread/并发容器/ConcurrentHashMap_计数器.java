package com.experiment.thread.并发容器;

import com.experiment.common.SleepUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenxuegui
 * @since 2024/12/13
 */
public class ConcurrentHashMap_计数器 {
	static Logger log = LoggerFactory.getLogger("");

	static ConcurrentHashMap<Character, AtomicLong> statMap = new ConcurrentHashMap();

	public static void main(String[] args) throws Exception{
		AtomicLong total = new AtomicLong();
		int threads = 10;
		CountDownLatch countDownLatch = new CountDownLatch(threads);

		for (int i = 0; i < threads; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Random random = new Random();
					for (int j = 0; j < 10; j++) {
						// 生成一个随机大写字母
						char randomUpper = (char) ('A' + random.nextInt(26));

						AtomicLong atomicLong = statMap.computeIfAbsent(randomUpper, (k) -> { /* 同一个key只初始一个节点值 */
							log.info("字符 "+ randomUpper +" 初始化统计");
							return new AtomicLong(0);
						});
						atomicLong.incrementAndGet();
						total.incrementAndGet();

						//log.info("统计字符 " + randomUpper);
						SleepUtil.sleep(200);
					}

					countDownLatch.countDown();
				}
			}).start();
		}

		countDownLatch.await();
		log.info("统计汇总================total:"+total);
		System.out.println(statMap);

	}
}
