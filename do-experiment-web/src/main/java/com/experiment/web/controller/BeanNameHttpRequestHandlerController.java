package com.experiment.web.controller;

import com.experiment.web.config.MyResponseStatusException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * @author chenxuegui
 * @since 2024/10/15
 */
/* Handler表示请求处理器，Handler由HandlerMapping解析，由HandlerAdapter执行，在 DispatcherServlet.properties定义默认解析执行器

   spring有四种处理器
   1、实现了Controller接口的bean对象，spring早期古老的处理器，返回页面视图       -- BeanNameUrlHandlerMapping 负责检测保存映射关系 -->  SimpleControllerHandlerAdapter 负责转发执行
   2、实现了HttpRequestHandler接口的bean对象，spring早期古老的处理器，返回数据  -- BeanNameUrlHandlerMapping 负责检测保存映射关系 -->  HttpRequestHandlerAdapter 负责转发执行

* */

@Component("/beanNameHttpRequestHandler")
public class BeanNameHttpRequestHandlerController implements HttpRequestHandler {


	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().println("请求处理器Handler -- 实现了HttpRequestHandler接口的bean对象");
		//throw new MyResponseStatusException("@ResponseStatus声明异常");
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"ResponseStatus声明异常");
	}
}
