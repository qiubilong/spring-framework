package com.experiment.thread;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class SleepUtil {

	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {

		}
	}
}
