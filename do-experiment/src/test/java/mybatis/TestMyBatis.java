package mybatis;


import com.experiment.test.mybatis.test.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class TestMyBatis {

	public static void main(String[] args) throws Exception{

		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		sessionFactory.getConfiguration().addMapper(UserMapper.class);

		SqlSession sqlSession = sessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(mapper.selectById());

		sqlSession.commit();
		sqlSession.flushStatements();
		sqlSession.close();
	}
}
