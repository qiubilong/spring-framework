package com.experiment.web.vo;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
public class UserVo {

	private String name;

	private Integer age;

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

	@Override
	public String toString() {
		return "UserVo{" +
				"name='" + name + '\'' +
				", age=" + age +
				", sysTime=" + sysTime +
				'}';
	}
}
