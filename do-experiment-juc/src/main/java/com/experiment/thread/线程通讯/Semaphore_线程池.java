package com.experiment.thread.线程通讯;


import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenxuegui
 * @since 2024/12/12
 */
@Slf4j
public class Semaphore_线程池 {

	public static void main(String[] args) {
		Pool pool = new Pool(3);

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					Connection connection = null;
					try {
						log.info("等待获取连接...");
						Thread.interrupted();//初始化log时设置了中断标志位，不清除时影响获取锁
						connection = pool.getConnection();
						log.info("获取连接成功，connection=" + connection);
						SleepUtil.sleepSec(new Random().nextInt(10));

					} catch (Exception e) {
						log.info("获取连接失败",e);
					}finally {
						if(connection != null){
							log.info("释放连接，connection=" + connection);
							pool.releaseConnection(connection);
						}
					}

				}
			});
		}

		executorService.shutdownNow();

	}


	private static class Pool{
		private final Connection[] connections;
		private final int size;
		private final Semaphore semaphore;

		public Pool(int size) {
			this.size = size;
			this.semaphore = new Semaphore(size);
			this.connections = new Connection[size];
			initConnection();
		}

		private void initConnection(){
			for (int i = 0; i < size; i++) {
				this.connections[i] = new Connection();
			}
		}

		public Connection getConnection() throws Exception{
            /* 限时获锁，三种情况返回 1、获锁成功；2、中断；3、超时 */
			boolean tryAcquire = semaphore.tryAcquire(1, 5, TimeUnit.SECONDS);
			if(!tryAcquire){
				throw new TimeoutException();
			}
			//获锁成功
			synchronized (this){
				for (int i = 0; i < size; i++) {
					Connection connection = this.connections[i];
					if(connection.used == false){
						connection.used = true;
						return connection;
					}
				}
			}
			return null;
		}

		public synchronized void releaseConnection(Connection connect){
			if(connect == null){
				return;
			}
			for (int i = 0; i < size; i++) {
				if(this.connections[i] == connect){
					this.connections[i].used = false;
					semaphore.release();
					return;
				}
			}
		}

	}


	private static class Connection{
		private boolean used = false;
		private static AtomicInteger idNext = new AtomicInteger(1);
		private int id = idNext.getAndAdd(1);

		@Override
		public String toString() {
			return "Connection{" +
					"used=" + used +
					", id=" + id +
					'}';
		}
	}

}
