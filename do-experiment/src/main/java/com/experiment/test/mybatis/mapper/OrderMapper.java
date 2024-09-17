package com.experiment.test.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

	@Select("select 'order'")
	public String selectById();
}
