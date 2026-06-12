package com.experiment.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
@Controller
@RequestMapping("/webAsync")
public class WebAsyncController {

	@RequestMapping("/requestMappingByParam")
	@ResponseBody
	/* 异步http请求 */
	public WebAsyncTask<String> requestMappingByParam(@RequestParam(value = "name") String name){
		long timeout = 5000;

		WebAsyncTask<String> asyncTask = new WebAsyncTask<>(timeout, () -> {
			// 模拟长时间运行的任务
			Thread.sleep(3000);
			return "Async Response";
		});

		asyncTask.onCompletion(() -> System.out.println("Request completed"));
		asyncTask.onTimeout(() -> {
			System.out.println("Request timed out");
			return "Timeout Response";
		});
		asyncTask.onError(() -> {
			return "Error Response";
		});

		return asyncTask;
	}
}
