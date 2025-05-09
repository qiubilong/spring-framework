package com.experiment.my;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author chenxuegui
 * @since 2025/5/9
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyOnClassCondition.class)
public @interface MyOnClassConditional {
	String value();
}
