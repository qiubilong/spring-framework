package com.experiment.web.controller;

import com.experiment.web.vo.UserVo;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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
@RestController
@RequestMapping("/args")
public class RequestMappingHandlerController_Args {

	//////////////////////////@RequestParam//////////////////////
	/* 参数解析器 -- @RequestParam -- RequestParamMethodArgumentResolver */
	@RequestMapping("/byRequestParam")
	public String byRequestParam(@RequestParam(value = "name") String name){
		return name;
	}

	/* 参数解析器 -- @RequestParam -- RequestParamMapMethodArgumentResolver */
	@RequestMapping("/byRequestParamMap")
	public String byRequestParamMap(@RequestParam Map<String,String> data){
		return data.toString();
	}

	/* 参数解析器 -- 无注解 -- 兜底基本类型参数 -- RequestParamMethodArgumentResolver */
	@RequestMapping("/byNone")
	public String byNone(String name){
		return name;
	}


	///////////////////////@RequestBody///////////////////////////////
	/* 参数解析器 -- @RequestBody -- RequestParamMethodArgumentResolver -- MappingJackson2HttpMessageConverter */
	@RequestMapping("/byRequestBody")
	public String byRequestBody(@RequestBody UserVo userVo){
		return userVo.toString();
	}
	/* 参数解析器 -- @RequestBody -- RequestParamMethodArgumentResolver -- StringHttpMessageConverter */
	@RequestMapping("/byRequestBodyString")
	public String byRequestBodyString(@RequestBody String body){
		return body;
	}

	@RequestMapping("/byRequestBodyValid")
	public String byRequestBodyValid(@Valid @RequestBody UserVo userVo, BindingResult bindingResult){
		if(bindingResult.hasErrors()){

		}
		return userVo.toString();
	}

	/* 参数解析器  -- RequestParamMethodArgumentResolver -- MultipartFile pic */
	/* 参数解析器  -- RequestParamMethodArgumentResolver(兜底) -- String email */
	@RequestMapping("/byRequestFile")
	public String byRequestFile(MultipartFile pic,String email){ /* pic是文件名 */
		return pic.getName() + "----" + email;
	}
}
