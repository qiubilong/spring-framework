package com.experiment.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenxuegui
 * @since 2024/10/16
 */
/* Handler表示请求处理器，Handler由HandlerMapping解析，由HandlerAdapter执行，在 DispatcherServlet.properties定义默认解析执行器

   spring有四种处理器
   1、实现了Controller接口的bean对象，spring早期古老的处理器，返回页面视图       -- BeanNameUrlHandlerMapping 负责检测保存映射关系 -->  SimpleControllerHandlerAdapter 负责转发执行
   2、实现了HttpRequestHandler接口的bean对象，spring早期古老的处理器，返回数据  -- BeanNameUrlHandlerMapping 负责检测保存映射关系 -->  HttpRequestHandlerAdapter 负责转发执行
   3、添加了 @RequestMapping 的方法  --- RequestMappingHandlerMapping 负责检测保存映射关系 -->  RequestMappingHandlerAdapter 负责转发执行
* */
@Controller
public class RequestMappingHandlerController {

	@RequestMapping("/requestMappingByParam")
	public String requestMappingByParam(@RequestParam(value = "name") String name){
		return "name";
	}

	@RequestMapping("/requestMappingByNone")
	public String requestMappingByNone(String name){
		return "requestMappingByNone";
	}
}
