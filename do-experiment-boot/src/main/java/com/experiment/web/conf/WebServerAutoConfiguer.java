package com.experiment.web.conf;

import com.experiment.my.JettyWebServer;
import com.experiment.my.MyOnClassConditional;
import com.experiment.my.TomcatWebServer;
import com.experiment.my.WebServer;
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


