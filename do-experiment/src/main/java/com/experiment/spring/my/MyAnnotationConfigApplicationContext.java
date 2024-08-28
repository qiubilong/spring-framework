package com.experiment.spring.my;


import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
public class MyAnnotationConfigApplicationContext {
	private  Class<?> configClass;

	private Map<String,Object> singletonsMap = new HashMap<>(64);
	private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>(64);

	private List<MyBeanPostProcessor> beanPostProcessors = new ArrayList<>(10);


	public MyAnnotationConfigApplicationContext(Class<?> configClass) {
		assert configClass!=null;
		this.configClass = configClass;
		scan();

		//扫描结束后创建单例非懒加载bean
		for (BeanDefinition beanDefinition : beanDefinitionMap.values()) {
			if(beanDefinition.ifSingleton() && !beanDefinition.isLazy()){
				getBean(beanDefinition.getBeanName());
			}
		}
	}

	private void scan(){
		ClassLoader classLoader = MyAnnotationConfigApplicationContext.class.getClassLoader();
		//配置类
		if(configClass.isAnnotationPresent(MyComponentScan.class)){
			MyComponentScan myComponentScan = configClass.getDeclaredAnnotation(MyComponentScan.class);
			String scanPath = myComponentScan.value();
			scanPath = scanPath.replaceAll("\\.","/");
			URL resource = classLoader.getResource(scanPath);
			File file = new File(resource.getFile());
			if (file.isDirectory()) {
				for (File listFile : file.listFiles()) {
					String absolutePath = listFile.getAbsolutePath();
					String name = absolutePath.substring(absolutePath.indexOf("java\\main")+10,absolutePath.indexOf(".class"));
					name = name.replaceAll("\\\\",".");
					try {
						Class<?> aClass = classLoader.loadClass(name);
						//扫描bean
						if (aClass.isAnnotationPresent(MyComponent.class)) {
							MyComponent myComponent = aClass.getDeclaredAnnotation(MyComponent.class);
							String componentName = myComponent.value();
							if(!StringUtils.hasText(componentName)){
								componentName = Introspector.decapitalize(aClass.getSimpleName());
							}
							if(MyBeanPostProcessor.class.isAssignableFrom(aClass)){
								beanPostProcessors.add((MyBeanPostProcessor) aClass.getConstructors()[0].newInstance());
								continue;
							}

							BeanDefinition beanDefinition = new BeanDefinition();
							beanDefinition.setBeanClass(aClass);
							beanDefinition.setBeanName(componentName);

							if (aClass.isAnnotationPresent(MyScope.class)) {
								MyScope myScope = aClass.getAnnotation(MyScope.class);
								beanDefinition.setScope(MyScopeEnum.getEnum(myScope.value()));
							}

							if (aClass.isAnnotationPresent(MyLazy.class)) {
								beanDefinition.setLazy(true);
							}
							beanDefinitionMap.put(componentName,beanDefinition);
						}
					} catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> clazz){
		Object bean = getBean(beanName);
		if(clazz.isInstance(bean)){
			return (T) bean;
		}
		throw new ClassCastException(clazz.getName());
	}

	public Object getBean(String beanName){
		BeanDefinition definition = beanDefinitionMap.get(beanName);
		if(definition == null){
			throw new NoSuchElementException(beanName);
		}

		Object instance = singletonsMap.get(beanName);
		if(instance!=null){
			return instance;
		}

		if(definition.getScope() == MyScopeEnum.SINGLETON){
			Object bean = createBean(definition);
			singletonsMap.put(definition.getBeanName(),bean);
			return bean;
		}else if(definition.getScope() == MyScopeEnum.PROTOTYPE){
			Object bean = createBean(definition);
			return bean;
		}
		throw new NoSuchElementException(beanName);
	}

	private Object createBean(BeanDefinition beanDefinition){
		Class<?> beanClass = beanDefinition.getBeanClass();
		try {
			//实例化
			Object bean = beanClass.getConstructors()[0].newInstance();

			//属性赋值
			for (Field field : beanClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(MyAutowired.class)) {
					field.setAccessible(true);
					field.set(bean,getBean(Introspector.decapitalize(field.getType().getSimpleName())));
				}
			}
			if(bean instanceof MyInitializingBean){
				try {
					((MyInitializingBean) bean).afterPropertiesSet();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(bean instanceof MyBeanNameAware){
				((MyBeanNameAware) bean).setBeanName(beanDefinition.getBeanName());
			}

			//初始化方法
			for (MyBeanPostProcessor beanPostProcessor : beanPostProcessors) {
				Object lastBean = beanPostProcessor.postProcessAfterInitialization(bean, beanDefinition.getBeanName());
				if(lastBean != null){
					bean = lastBean;
				}
			}

			return bean;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
