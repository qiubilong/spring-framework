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
/*
* 请求处理器 -- 实现Controller
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
