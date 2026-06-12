package com.experiment.my;

import com.experiment.my.auto.webserver.WebServer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Map;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
public class MySpringApplication {

	public static void run(Class<?> configurationClass, String[] args) {
		//1、创建spring容器
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //2、执行配置类
		applicationContext.register(configurationClass); //注册配置类
        //3、初始化spring容器
		applicationContext.refresh();

		//4、启动tomcat
		startWebServer(applicationContext);

	}

	private static void startWebServer(WebApplicationContext applicationContext) {
		Map<String, WebServer> webServerMap = applicationContext.getBeansOfType(WebServer.class);
		if(webServerMap.isEmpty()){
			throw new IllegalStateException("WebServer is empty");
		}
		if(webServerMap.size()>1){
			throw new IllegalStateException("WebServer webServerMap.size()>1");
		}

		webServerMap.values().stream().findFirst().get().start(applicationContext);

	}


}
