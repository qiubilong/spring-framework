package com.experiment.thread.线程通讯;

import com.experiment.common.SleepUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxuegui
 * @since 2024/12/9
 */
@Slf4j
public class ReentranLock_抢票 {

	final static ReentrantLock reentrantLock = new ReentrantLock();/* 可重入独占锁，可中断，可超时，可公平（FIFO） */

	static int ticket = 10;

	public static void buyTicket(){
		reentrantLock.lock();//独占锁
		try {
			if(ticket > 0){
				SleepUtil.sleepSec(3);
				ticket --;
				log.info("buyTicket success");

				buyTicket();//重入锁
			}else {
				log.info("buyTicket fail, ticket out");
			}
		}finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					buyTicket();
				}
			},"抢票用户"+i).start();
		}

		SleepUtil.sleepSec(2);
		log.info("剩余票数 ticket="+ticket);
	}
}
