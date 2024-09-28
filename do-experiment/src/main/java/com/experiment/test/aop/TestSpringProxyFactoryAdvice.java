package com.experiment.test.aop;

import com.experiment.test.aop.advice.MyMethodAfterAdvice;
import com.experiment.test.aop.advice.MyMethodAroundAdvice;
import com.experiment.test.aop.advice.MyMethodBeforeAdvice;
import com.experiment.test.aop.advice.MyMethodExceptionAdvice;
import com.experiment.test.aop.api.UserServiceAopApi;
import com.experiment.test.aop.impl.UserServiceAopImpl;
import org.springframework.aop.framework.ProxyFactory;

/* Advisor = Advice + PointCut   */
public class TestSpringProxyFactoryAdvice {
	public static void main(String[] args) {

		UserServiceAopImpl target = new UserServiceAopImpl();

		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);/* target包装成SingletonTargetSource */


		//添加方法执行通知
		proxyFactory.addAdvice(new MyMethodAfterAdvice());
		proxyFactory.addAdvice(new MyMethodBeforeAdvice());
		proxyFactory.addAdvice(new MyMethodAroundAdvice());
		proxyFactory.addAdvice(new MyMethodExceptionAdvice());

		UserServiceAopApi proxy = (UserServiceAopApi)proxyFactory.getProxy();

		proxy.ping();
		//proxy.test();//抛出异常

	}
}
