package com.experiment.web.vo;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
public class UserVO {

	private String name;

	private Integer age;

	@NotEmpty(message = "email cannot empty")
	private String email;

	private List<Long> ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

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
