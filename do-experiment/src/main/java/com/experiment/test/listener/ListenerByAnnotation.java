package com.experiment.test.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/9
 */
@Component
public class ListenerByAnnotation {

	@EventListener
	public void listenerByAnnotationTest(ApplicationEvent event){
		System.out.println("listenerByAnnotationTest,event="+event);
	}
}
