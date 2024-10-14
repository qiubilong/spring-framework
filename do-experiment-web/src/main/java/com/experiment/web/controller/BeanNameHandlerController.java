package com.experiment.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


/**
 * @author chenxuegui
 * @since 2024/10/11
 */
/* Handler表示请求处理器，Handler由HandlerMapping解析，由HandlerAdapter调用，DispatcherServlet.properties
   spring有四种处理器
   1、实现了Controller接口的bean对象，spring早期古老的处理器 -- BeanNameUrlHandlerMapping负责检测保存映射关系 -->  SimpleControllerHandlerAdapter负责转发执行
* */
@Component("/beanNameHandlerController")
public class BeanNameHandlerController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("beanNameHandler");
		modelAndView.addObject("Title", "beanNameHandlerController");
		modelAndView.addObject("END", "handlerControllerByBeanName");
		return modelAndView;
	}
}
