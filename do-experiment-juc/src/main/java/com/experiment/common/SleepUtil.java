package com.experiment.common;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class SleepUtil {

	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void sleepSec(int sec){
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
