package com.experiment.web.controller;

import com.experiment.web.vo.ServiceResult;
import com.experiment.web.vo.UserAddressVO;
import com.experiment.web.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
public class RequestMappingHandlerController {

	//////////////////////////@RequestParam//////////////////////
	/* 参数解析器  -- RequestParamMethodArgumentResolver -- @RequestParam  */
	@RequestMapping("/byRequestParam")
	public String byRequestParam(@RequestParam(value = "name") String name){/*参数来源--request.getParameter() -- url参数、multipart/form-data、x-www-form-urlencoded  */
		return name;
	}

	/* 参数解析器  -- RequestParamMapMethodArgumentResolver -- @RequestParam */
	@RequestMapping("/byRequestParamMap")
	public String byRequestParamMap(@RequestParam Map<String,String> data){/* 参数来源-- url查询参数、表单www-form-urlencoded（POST）、表单multipart/form-data（post请求类型解析为part类型属性） */
		return data.toString();
	}


	/* 参数解析器  -- RequestParamMethodArgumentResolver -- 无注解 -- 兜底基本类型参数 */
	@RequestMapping("/byNone")
	public String byNone(String name){
		return name;
	}

	@RequestMapping("/byRequestParamVo")
	public String byRequestParamVo(@RequestParam(value = "userVo") UserVO userVo){/*参数来源--request.getParameter() -- url参数、multipart/form-data、x-www-form-urlencoded  */
		return userVo.toString();
	}


	/* 参数解析器  -- ServletModelAttributeMethodProcessor --无注解兜底 -- 对象vo */
	@RequestMapping("/byVoNone")
	public String byVoNone(UserVO userVo){/* 参数来源-- url查询参数、表单www-form-urlencoded（POST）、表单multipart/form-data（post请求类型解析为part类型属性） */
		return userVo.toString();
	}

	/* 参数解析器  -- ServletModelAttributeMethodProcessor --ModelAttribute -- 对象vo */
	@RequestMapping("/byModelAttribute")
	public String byModelAttribute(@Valid @ModelAttribute UserVO userVo, BindingResult bindingResult){/* 参数来源-- url查询参数、表单www-form-urlencoded（POST）、表单multipart/form-data（post请求类型解析为part类型属性） */
		if(bindingResult.hasErrors()){
			return bindingResult.getAllErrors().get(0).getDefaultMessage();
		}
		return userVo.toString();
	}

	@RequestMapping("/byModelAttributeVo")
	public String byModelAttributeVo(@ModelAttribute UserAddressVO userAddressVO){/* 参数来源-- url查询参数、表单www-form-urlencoded（POST）、表单multipart/form-data（post请求类型解析为part类型属性） */
		return userAddressVO.toString();
	}



	/* 参数解析器  -- RequestParamMethodArgumentResolver -- MultipartFile pic */
	/* 参数解析器  -- RequestParamMethodArgumentResolver(兜底) -- String email */
	@RequestMapping("/byRequestFile")
	public String byRequestFile(MultipartFile pic,String email){ /* pic是文件名 */
		return pic.getOriginalFilename() + "----" + email;
	}

    // 表单提交 Content-Type: multipart/form-data; boundary=--分隔符
	/* 参数解析器  -- RequestParamMethodArgumentResolver -- MultipartFile pic */
	@RequestMapping("/byRequestPart")
	public String byRequestPart(@RequestPart MultipartFile pic, @RequestPart String email){ /* pic是文件名 */
		return pic.getOriginalFilename() + "----" + email;
	}



	///////////////////////@RequestBody///////////////////////////////
	/* 参数解析器 -- @RequestBody -- RequestResponseBodyMethodProcessor -- MappingJackson2HttpMessageConverter */
	@RequestMapping("/byRequestBody")
	public ServiceResult<UserVO> byRequestBody(@RequestBody UserVO userVo){ //Content-type ==  application/json
		return ServiceResult.success(userVo);
	}
	/* 参数解析器 -- @RequestBody -- RequestResponseBodyMethodProcessor -- StringHttpMessageConverter */
	@RequestMapping("/byRequestBodyString")
	public String byRequestBodyString(@RequestBody String body){
		return body;
	}

	@RequestMapping("/byRequestBodyValid")
	public String byRequestBodyValid(@Valid @RequestBody UserVO userVo){
		return userVo.toString();
	}


	@RequestMapping("/byPath/{id}")
	public String byPath(@PathVariable String id){
		return id;
	}

}

/*
*  request.getParameter() --> url查询参数、表单multipart/form-data（GET/POST-part属性）、 表单www-form-urlencoded（POST）
*
*
*    */