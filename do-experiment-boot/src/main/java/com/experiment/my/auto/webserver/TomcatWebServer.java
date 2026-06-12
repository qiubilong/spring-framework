package com.experiment.my.auto.webserver;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
public class TomcatWebServer implements WebServer {
	@Override
	public void start(WebApplicationContext applicationContext) {
		System.out.println("start Tomcat");

		Tomcat tomcat = new Tomcat();

		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");

		Connector connector = new Connector();
		connector.setPort(8081);

		Engine engine = new StandardEngine();
		engine.setDefaultHost("localhost");

		Host host = new StandardHost();
		host.setName("localhost");

		String contextPath = "";
		Context context = new StandardContext();
		context.setPath(contextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());

		host.addChild(context);
		engine.addChild(host);

		service.setContainer(engine);
		service.addConnector(connector);

		tomcat.addServlet(contextPath, "dispatcher", (Servlet) new DispatcherServlet(applicationContext));/* DispatcherServlet关联spring容器applicationContext */
		context.addServletMappingDecoded("/*", "dispatcher");

		try {
			tomcat.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
