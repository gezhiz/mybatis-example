package dao;

import com.worthto.bean.User;
import com.worthto.factory.C3P0DataSourceFactory;
import com.worthto.mapper.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author gezz
 * @description
 * @date 2020/6/1.
 */
public class MybatisTest {
    SqlSessionFactory sqlSessionFactory;

    SqlSessionFactory sqlSessionFactory2;

    private Long userId = 9304568295064L;

    @Before
    public void init() throws IOException, PropertyVetoException {
        init1();
        init2();
    }

    private void init1() {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    private void init2() {
        DataSource dataSource = null;
        try {
            dataSource = C3P0DataSourceFactory.getDataSource();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment.Builder("development")
                .dataSource(dataSource)
                .transactionFactory(transactionFactory).build();
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        sqlSessionFactory2 = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Test
    public void testQuery() {
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("UserMapper.selectByPrimaryKey", userId);
        System.out.println(user);

    }


    @Test
    public void testQuery2() {
        SqlSession session = sqlSessionFactory2.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUser(userId);
        System.out.println(user);

    }
}
