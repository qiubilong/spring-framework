package com.experiment.web.controller;

import com.experiment.web.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenxuegui
 * @since 2024/10/30
 */
public interface FeignController {

	@GetMapping("/getUser")
	public UserVO getUser(@RequestParam Long id);
}
