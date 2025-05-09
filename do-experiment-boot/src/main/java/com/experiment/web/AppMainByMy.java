package com.experiment.web;

import com.experiment.my.MySpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@ComponentScan
public class AppMainByMy {

	public static void main(String[] args) {
		MySpringApplication.run(AppMainByMy.class,args);
	}
}
