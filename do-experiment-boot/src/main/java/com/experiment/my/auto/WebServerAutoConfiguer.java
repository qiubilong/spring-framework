package com.experiment.my.auto;

import com.experiment.my.auto.webserver.JettyWebServer;
import com.experiment.my.auto.webserver.TomcatWebServer;
import com.experiment.my.auto.webserver.WebServer;
import com.experiment.my.condition.MyOnClassConditional;
import org.springframework.context.annotation.Bean;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
public class WebServerAutoConfiguer implements AutoConfiguer {

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


