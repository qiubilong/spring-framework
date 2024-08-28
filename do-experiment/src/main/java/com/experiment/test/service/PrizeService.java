package com.experiment.test.service;

import com.experiment.test.common.PrizeConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenxuegui
 * @since 2024/8/28
 */
@Component
public class PrizeService {

	@Autowired
	private PrizeConf conf;


	public void doPrize(){
		System.out.println("doPrize---->" +conf);
	}
}
