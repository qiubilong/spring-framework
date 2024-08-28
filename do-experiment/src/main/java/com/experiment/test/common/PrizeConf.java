package com.experiment.test.common;

import lombok.Data;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@Data
public class PrizeConf {

	private Long id;
	private String name;

	private User prizeUser;

	public PrizeConf(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setPrizeUser(User prizeUser) {
		this.prizeUser = prizeUser;
	}

	@Override
	public String toString() {
		return "PrizeConf{" +
				"id=" + id +
				", name='" + name + '\'' +
				", prizeUser=" + prizeUser +
				'}';
	}
}
