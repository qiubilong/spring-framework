package com.experiment.test.mybatis.test.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

	@Select("select 'OrderMapper.selectById'")
	public String selectById();
}
