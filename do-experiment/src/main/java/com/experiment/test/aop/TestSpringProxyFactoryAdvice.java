package com.experiment.test.aop;

import com.experiment.test.aop.advice.MyMethodAfterAdvice;
import com.experiment.test.aop.advice.MyMethodAroundAdvice;
import com.experiment.test.aop.advice.MyMethodBeforeAdvice;
import com.experiment.test.aop.advice.MyMethodExceptionAdvice;
import com.experiment.test.aop.api.UserServiceAopApi;
import com.experiment.test.aop.impl.UserServiceAopImpl;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* Advisor = Advice + PointCut   */
public class TestSpringProxyFactoryAdvice {
	public static void main(String[] args) {
		//该设置用于输出cglib动态代理产生的类
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.dir")+"/jdk/proxy1");

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
