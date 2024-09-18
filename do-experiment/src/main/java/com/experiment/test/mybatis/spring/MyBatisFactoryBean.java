package com.experiment.test.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;


public class MyBatisFactoryBean implements FactoryBean {

	private final Class<?> mapperInterface;

	private  SqlSession sqlSession;

	public MyBatisFactoryBean(Class<?> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	@Autowired /* 依赖注入 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		sqlSessionFactory.getConfiguration().addMapper(mapperInterface);
		sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public Object getObject() {
		return sqlSession.getMapper(mapperInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
