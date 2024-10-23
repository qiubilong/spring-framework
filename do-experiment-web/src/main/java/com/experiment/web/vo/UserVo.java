package com.experiment.web.vo;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
public class UserVo {

	private String name;

	private Integer age;

	@NotEmpty(message = "email cannot empty")
	private String email;

	private Long sysTime = System.currentTimeMillis();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSysTime() {
		return sysTime;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVo{" +
				"name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", sysTime=" + sysTime +
				'}';
	}
}
