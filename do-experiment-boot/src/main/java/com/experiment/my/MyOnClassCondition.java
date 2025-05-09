package com.experiment.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@Slf4j
public class MyOnClassCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MyOnClassConditional.class.getName());
		String className = (String)annotationAttributes.get("value");
		log.info("MyOnClassCondition -->metadata={},className={}",metadata,className);
		if(!StringUtils.isEmpty(className)){
			try {
				context.getClassLoader().loadClass(className);
				return true;
			} catch (ClassNotFoundException e) {
			}
		}
		return false;
	}
}
