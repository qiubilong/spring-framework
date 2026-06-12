package com.experiment.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class Log {
	private static final Logger logger = LoggerFactory.getLogger("");
	public static void info(Object... arguments){
		logger.info("{}",arguments);
	}

	public static void log(String message, Throwable t) {
		log(message);
		t.printStackTrace();
	}

	public static void log(String message) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String threadName = Thread.currentThread().getName();
		System.out.printf("[%s] [Thread: %s] %s%n", timestamp, threadName, message);
	}
}
