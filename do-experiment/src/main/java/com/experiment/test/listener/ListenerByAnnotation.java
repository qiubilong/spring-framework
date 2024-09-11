package com.experiment.test.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/9
 */
@Component
public class ListenerByAnnotation {

	/* EventListenerMethodProcessor (BeanFactory处理器) --> DefaultEventListenerFactory --> 创建ApplicationListener适配器  --> 添加到容器的listener列表
	*   */
	@EventListener
	public void listenerByAnnotationTest(ApplicationEvent event){
		System.out.println("listenerByAnnotationTest,event="+event);

	}
	//EventListenerMethodProcessor是一个BeanFactoryPostProcessor
}
