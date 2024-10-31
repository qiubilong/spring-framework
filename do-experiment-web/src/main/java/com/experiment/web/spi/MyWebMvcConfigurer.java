package com.experiment.web.spi;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author chenxuegui
 * @since 2024/10/31
 */
//@Component
//@EnableWebMvc /* 自定义mvc --> 导入 DelegatingWebMvcConfiguration --> 使用@Bean实例化mvc相关Bean（DispatcherServlet.properties类似） */
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	/* 自定义数据类型转换器*/
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//不需要手动添加，DelegatingWebMvcConfiguration中已经自动探测
		//converters.add(new MappingJackson2HttpMessageConverter());/* 支持Content-Type = application/json 数据类型转换器 */
	}
}
