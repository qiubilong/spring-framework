package com.experiment.test.service.pay;

import jakarta.annotation.Priority;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/30
 */
@Component
@Primary
/* 依赖注入找到多个候选对象时，@Primary优先级最高 */
public class WeixinPayService implements IPayService {

}
