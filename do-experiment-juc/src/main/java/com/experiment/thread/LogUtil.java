package com.experiment.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger("");
	public static void log(Object... arguments){
		logger.info("{}",arguments);
	}
}
