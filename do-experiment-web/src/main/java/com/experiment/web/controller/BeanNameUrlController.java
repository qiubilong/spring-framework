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
/* Handler表示请求处理器，spring有四种处理器
*  1、实现了Controller接口的bean对象，spring早期古老的处理器 -- BeanNameUrlHandlerMapping负责检查注册 -->  SimpleControllerHandlerAdapter负责转发调用
* */
@Component("/myBeanName")
public class BeanNameUrlController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("Title", "BeanNameUrlController");
		modelAndView.addObject("END", "BeanNameUrlController---");
		return modelAndView;
	}
}
