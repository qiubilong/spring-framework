package com.experiment.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chenxuegui
 * @since 2024/10/16
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MyResponseStatusException extends RuntimeException{

	static final long serialVersionUID = -7034897190745766939L;

	public MyResponseStatusException(String message) {
		super(message);
	}
}
