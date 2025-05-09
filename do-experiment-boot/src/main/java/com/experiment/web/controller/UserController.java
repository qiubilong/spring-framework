package com.experiment.web.controller;

import com.experiment.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getUser")
	public String getUser(){
		return userService.getUser();
	}
}
