package com.experiment.web.controller;

import com.experiment.web.config.UserVoEditor;
import com.experiment.web.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
@Controller
@RequestMapping("/binder")
public class InitBinderController {

	/* 自定义参数转换器，当前Controller有效 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UserVo.class, new UserVoEditor());/* 注册参数类型转换器 */
	}

	@RequestMapping("/requestMappingByParam")
	@ResponseBody
	public String requestMappingByParam(@RequestParam("user") UserVo userVo){//将字符串转换成UserVo对象
		return userVo.toString();
	}
}
