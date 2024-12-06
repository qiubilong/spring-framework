package com.experiment.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenxuegui
 * @since 2024/12/2
 */
public class Log {
	private static final Logger logger = LoggerFactory.getLogger("");
	public static void info(Object... arguments){
		logger.info("{}",arguments);
	}
}
