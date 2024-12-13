package com.experiment.thread.线程通讯;

import com.experiment.common.Log;
import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenxuegui
 * @since 2024/12/12
 */
@Slf4j
public class wait_notify_线程池 {

	public static void main(String[] args) {
		Pool pool = new Pool(3);

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					Connection connect = null;
					try {
						Log.info("等待获取连接...");
						connect = pool.getConnect(5000);
						Log.info("获取连接成功 connect="+connect);
						SleepUtil.sleepSec(new Random().nextInt(20));
					} catch (Exception e) {
						Log.info("获取连接失败",e);
					}finally {
						if(connect != null){
							pool.releaseConnect(connect);
							Log.info("释放连接 connect="+ connect);
						}
					}

				}
			});
		}

		executorService.shutdown();

	}

	private static class Pool{
		private final Object lock = new Object();
		private int size;
		private LinkedList<Connection>  connections = new LinkedList<>();

		public Pool(int size) {
			this.size = size;
			this.initPool();
		}

		private void initPool(){
			for (int i = 0; i < this.size; i++) {
				connections.addLast(new Connection());
			}
		}

		public Connection getConnect(long waitMills) throws Exception {
			long deadLine = waitMills + System.currentTimeMillis();
			long remainTime = waitMills;
			synchronized (lock){
				while (connections.isEmpty()){
					if(waitMills<=0){
						lock.wait();
					}else {
						lock.wait(remainTime);
						remainTime = deadLine - System.currentTimeMillis();
						if(remainTime<=0){
							throw new TimeoutException(waitMills+" Mills");
						}
					}
				}

				return connections.removeFirst();
			}
		}

		public void releaseConnect(Connection connection){
			if(connection == null){
				return;
			}
			synchronized (lock){
				connections.addLast(connection);
				lock.notifyAll();
			}

		}
	}

	private static class Connection{
		private static AtomicInteger idNext = new AtomicInteger(1);
		private int id = idNext.getAndAdd(1);

		@Override
		public String toString() {
			return "Connection{" +
					"id=" + id +
					'}';
		}
	}
}
