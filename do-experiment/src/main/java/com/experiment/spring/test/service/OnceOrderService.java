package com.experiment.spring.test.service;

import com.experiment.spring.my.MyComponent;
import com.experiment.spring.my.MyScope;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@MyComponent
@MyScope("prototype")
public class OnceOrderService {
}
