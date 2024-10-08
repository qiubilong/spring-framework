package com.experiment.test.config;

import com.experiment.test.mybatis.spring.MyBatisMapperScan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@MyBatisMapperScan("com.experiment.test.mybatis.test.mapper")
// @see  @MapperScan("com.experiment.test.mybatis.test.mapper")
public class MyBatisConfig {

	@Bean
	SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sessionFactory;
	}
}
