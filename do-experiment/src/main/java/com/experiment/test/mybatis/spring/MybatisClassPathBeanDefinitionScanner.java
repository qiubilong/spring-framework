package com.experiment.test.mybatis.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/* 自定义Bean扫描器  */
public class MybatisClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

	public MybatisClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> definitionHolders = super.doScan(basePackages);
		for (BeanDefinitionHolder definitionHolder : definitionHolders) {
			BeanDefinition beanDefinition = definitionHolder.getBeanDefinition();
			/* 设置构造函数参数 --> 指定构造函数 */
			beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
			/* FactoryBean --> 创建Interface代理对象 */
			beanDefinition.setBeanClassName(MyBatisFactoryBean.class.getName());
		}

		return definitionHolders;
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		/* 仅支持接口Interface */
		return beanDefinition.getMetadata().isInterface();
	}
}
