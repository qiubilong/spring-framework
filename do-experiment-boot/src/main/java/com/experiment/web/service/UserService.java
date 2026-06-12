package com.experiment.web.service;

import org.springframework.stereotype.Service;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@Service
public class UserService {


	public String getUser() {
		return "xxx"+System.currentTimeMillis();
	}
}
