package com.experiment.web.spi;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author chenxuegui
 * @since 2024/10/30
 */
/*
* tomcat启动的时候会通过spi机制找到文件spring-web/src/main/resources/META-INF/services/jakarta.servlet.ServletContainerInitializer
* 定义的实现类 - SpringServletContainerInitializer  --> 实例化所有 WebApplicationInitializer 子类 --> WebApplicationInitializer.onStartup()
* */
public class MyWebApplicationInitializer_spi implements WebApplicationInitializer {

	/* 使用SPI机制创建SpringMVC容器 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);


		DispatcherServlet dispatcherServlet = new DispatcherServlet(context); /* 不开启@EnableWebMvc的话加载DispatcherServlet.properties配置文件 */
		ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("spiApp", dispatcherServlet);
		servletRegistration.setLoadOnStartup(1);// 1=容器启动加载;0=第一次请求加载
		servletRegistration.addMapping("/spiApp/*"); //匹配路径


		//不开启@EnableWebMvc的话，实例化RequestMappingHandlerAdapter --> 没有ConfigurableWebBindingInitializer对象
		// -->没有DefaultFormattingConversionService --> 无法处理复杂对象类型转换

		//Tomcat初始化servlet --> DispatcherServlet.init() --> AnnotationConfigWebApplicationContext.refresh()
	}

}
