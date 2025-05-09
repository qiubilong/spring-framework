package com.experiment.my;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@Configuration
public class WebServerAutoConfiguer {

	@Bean
	@MyOnClassConditional("org.apache.catalina.startup.Tomcat")
	public WebServer tomcatWebServer(){
		return new TomcatWebServer();
	}

	@Bean
	@MyOnClassConditional("org.eclipse.jetty.server.Server")
	public WebServer jettyWebServer(){
		return new JettyWebServer();
	}

}


