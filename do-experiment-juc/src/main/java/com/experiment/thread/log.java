package com.experiment.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class log {
	private static final Logger logger = LoggerFactory.getLogger("");
	public static void info(Object... arguments){
		logger.info("{}",arguments);
	}
}
