package com.experiment.test.autowired.lookup;

import com.experiment.test.service.pay.IPayService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/9/6
 */
@Component
public class LookupService {


	public void test(){
		IPayService service = findPayService();
		System.out.println(service);
	}

	@Lookup("jdPayService")/* 实例化对象时，优化寻找@Lookup，生成增强子类对象（非AOP） */
	public IPayService findPayService(){
		return null;
	}
}
