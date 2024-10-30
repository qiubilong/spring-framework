package com.experiment.web.controller;

import com.experiment.web.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
@Controller
@RequestMapping("/modelAttribute")
public class ModelAttributeController {


	/* 公共参数填充 */
	@ModelAttribute("commonData")
	public String populateCommonData() {
		System.out.println();
		return "This is common data for all views";
	}

	@RequestMapping("/requestMappingByParam")
	public String requestMappingByParam(@ModelAttribute UserVO userVo){
		// "commonData" 会自动添加到模型中
		return "common";
	}
}
