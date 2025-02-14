package com.experiment.test.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author chenxuegui
 * @since 2024/9/11
 */
/*  ApplicationListenerDetector(BeanPostProcessor)  --> postProcessAfterInitialization(对象初始化后) --> 是否ApplicationListener子类   --> 添加到容器的listener列表 */
@Component
public class ListenerByImpl implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("ListenerByImplTest event="+event);
		if(event instanceof PayloadApplicationEvent<?> applicationEvent){
			System.out.println("ListenerByImplTest PayloadApplicationEvent="+applicationEvent.getPayload());
		}

	}

	@PreDestroy
	public void closeTest(){
		System.out.println("ListenerByImplTest closeTest=");
	}
}
