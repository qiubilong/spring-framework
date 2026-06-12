package com.experiment.web;

import com.experiment.my.MySpringApplication;
import com.experiment.my.MySpringBootApplication;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@MySpringBootApplication
public class AppMainByMy {

	public static void main(String[] args) {
		MySpringApplication.run(AppMainByMy.class,args);
	}
}
