package com.experiment.test.mybatis.test.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

public interface UserMapper {

	@Select("select 'UserMapper.selectById'")
	public String selectById();
}
