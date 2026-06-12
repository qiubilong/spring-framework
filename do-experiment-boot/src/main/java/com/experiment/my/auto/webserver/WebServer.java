package com.experiment.my.auto.webserver;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
public interface WebServer {
	public void start(WebApplicationContext applicationContext);
}
