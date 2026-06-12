package com.experiment.test.beanPostProcessor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTask {

	/* 模拟出现并发执行，未出现并发，单线程池执行？ */
	/* ScheduledAnnotationBeanPostProcessor --> 解析@Scheduled -->  */
    @Scheduled(fixedRate = 1000) // Executes every 1 second
    public void executeTask() {
        //System.out.println("Task is running at: " + System.currentTimeMillis()/1000);
        try {
            // Simulate a long-running task
            Thread.sleep(3000); // Task takes 3 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}