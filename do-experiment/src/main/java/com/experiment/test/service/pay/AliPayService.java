package com.experiment.test.service.pay;

import jakarta.annotation.Priority;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/30
 */
@Component
@Priority(1)
/* @Priority(1)作用是当找到依赖注入的class对应多个候选对象时，且无@Primary时，取优先级高的 */
public class AliPayService implements IPayService{


}
