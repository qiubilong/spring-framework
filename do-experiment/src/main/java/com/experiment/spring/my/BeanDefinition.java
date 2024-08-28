package com.experiment.spring.my;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@Data
public class BeanDefinition {

	private Class<?> beanClass;

	private boolean isLazy;

	private MyScopeEnum scope = MyScopeEnum.SINGLETON;

	private String beanName;

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public MyScopeEnum getScope() {
		return scope;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setLazy(boolean lazy) {
		isLazy = lazy;
	}

	public void setScope(MyScopeEnum scope) {
		this.scope = scope;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public boolean ifSingleton() {
		return scope == MyScopeEnum.SINGLETON;
	}
}
