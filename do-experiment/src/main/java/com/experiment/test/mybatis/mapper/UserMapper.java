package com.experiment.test.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

public interface UserMapper {

	@Select("select 'user'")
	public String selectById();
}
