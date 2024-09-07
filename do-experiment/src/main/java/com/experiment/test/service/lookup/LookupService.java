package com.experiment.test.service.lookup;

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

	@Lookup("jdPayService")
	public IPayService findPayService(){
		return null;
	}
}
