package com.experiment.test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement/* 开启事务 --> 导入 BeanFactoryTransactionAttributeSourceAdvisor、TransactionInterceptor */
@Configuration
@MapperScan("com.experiment.test.mybatis.test.mapper") /* Mapper接口扫描 -->Mybatis.Configuration.addMapper解析注入 --> 生成MapperProxy代理类 */
public class DBConfig {

	@Bean  /* 数据库操作工具类 - 支持事务 */
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean  /* 事务管理器 */
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Bean /* 数据源 */
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/experiment?characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean /* 创建Mybatis - DefaultSqlSessionFactory --> 创建Mybatis全局配置Configuration */
	public SqlSessionFactory createSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());/* 指定mybatis数据源 */
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(false);//关闭Mapper缓存 -- 不需要CachingExecutor
		sessionFactory.setConfiguration(configuration);
		return sessionFactory.getObject();
	}

}
