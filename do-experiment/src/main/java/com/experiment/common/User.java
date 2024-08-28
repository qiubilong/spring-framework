package com.experiment.common;

import lombok.Data;

/**
 * @author chenxuegui
 * @since 2024/8/26
 */
@Data
public class User {
	private Long uid;
	private String name;

	public User(Long uid, String name) {
		this.uid = uid;
		this.name = name;
		System.out.println("new " + toString());
	}

	@Override
	public String toString() {
		return "User{" +
				"uid=" + uid +
				", name='" + name + '\'' +
				'}';
	}
}
