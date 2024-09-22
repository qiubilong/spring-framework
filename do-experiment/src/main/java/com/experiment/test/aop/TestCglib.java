package com.experiment.test.aop;

import com.experiment.test.aop.impl.UserServiceImpl;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class TestCglib {

	public static void main(String[] args) {
		UserServiceImpl target = new UserServiceImpl();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
			@Override
			public Object intercept(Object enhancedObj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				System.out.println("before");
				Object result = null;
				///result = method.invoke(target,args);
				result = methodProxy.invokeSuper(enhancedObj, args);
				System.out.println("after");
				return result;
			}
		}, NoOp.INSTANCE});
		enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if(method.getName().equals("ping")){
					return 0;
				}
				return 1;
			}
		});

		UserServiceImpl userService = (UserServiceImpl)enhancer.create();
		userService.ping();
		userService.test();

	}
}
