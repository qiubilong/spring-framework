package com.experiment.thread.线程通讯;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenxuegui
 * @since 2024/12/12
 */
@Slf4j
public class wait_notify_线程池 {

	public static void main(String[] args) {
		Pool pool = new Pool(3);
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

		public Connection getConnect(long waitMills) throws InterruptedException {
			long deadLine = waitMills + System.currentTimeMillis();
			long waitTime = waitMills;
			synchronized (lock){
				while (connections.isEmpty()){
					if(waitMills<=0){
						lock.wait();
					}else {
						lock.wait(waitTime);
						waitTime = deadLine - System.currentTimeMillis();
					}
				}

				if(!connections.isEmpty()){
					return connections.removeFirst();
				}
			}

			return null;
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
