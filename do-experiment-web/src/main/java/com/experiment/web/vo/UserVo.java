package com.experiment.web.vo;

/**
 * @author chenxuegui
 * @since 2024/10/22
 */
public class UserVo {

	private String name;

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

	@Override
	public String toString() {
		return "UserVo{" +
				"name='" + name + '\'' +
				", sysTime=" + sysTime +
				'}';
	}
}
