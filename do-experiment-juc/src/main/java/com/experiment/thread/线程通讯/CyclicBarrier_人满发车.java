package com.experiment.thread.线程通讯;

import cn.tulingxueyuan.tools.SleepTools;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class CyclicBarrier_人满发车 {
	static Logger log = LoggerFactory.getLogger("");

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,

				new Runnable() { //到达屏障回调
					@Override
					public void run() {
						System.out.println("-----------------------");
						log.info("人齐了，准备发车");
						System.out.println("-----------------------");
						System.out.println();

						SleepTools.second(3);
					}
				});

        for (int i = 0; i < 10; i++) {
            final int id = i+1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        int sleepMills = ThreadLocalRandom.current().nextInt(5000);
                        Thread.sleep(sleepMills);
                        log.info(id + "号到了，上车");
                        cyclicBarrier.await();

                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
				}
            });
        }


		executorService.awaitTermination(1,TimeUnit.MINUTES);

    }


}