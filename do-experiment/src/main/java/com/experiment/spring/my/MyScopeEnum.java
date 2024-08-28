package com.experiment.spring.my;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
public enum MyScopeEnum {
	SINGLETON, PROTOTYPE;


	public static MyScopeEnum getEnum(String value){
		return Arrays.stream(values()).filter(v-> Objects.equals(value,v.name().toLowerCase(Locale.ROOT))).findFirst().orElse(null);
	}
}
