package com.worthto.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author gezz
 * @description
 * @date 2020/6/1.
 */
public class C3P0DataSourceFactory {

    public static DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/warehouse?characterEncoding=UTF-8&useSSL=false");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setUser("root");
        dataSource.setPassword("wjsw546");
        // 配合preferredTestQuery或 automaticTestTable使用，必须要保证有合理配置才能把testConnectionOnCheckout设置成true
        // 每次把链接取出时都会检查链接是否有效，无效则取新的链接
        dataSource.setTestConnectionOnCheckout(true);
//        dataSource.setAutomaticTestTable("t_test");//c3p0将建一张名为Test的空表，并使用其自带的查询语句进行测试。如果定义了这个参数那么属性preferredTestQuery将被忽略。你不能在这张Test表上进行任何操作，它将只供c3p0测试使用。默认值: null
//        dataSource.setPreferredTestQuery(MySqlPropertyConfig.getInstance().getJdbcValidationQuery());

        //连接池中保留的最小连接数，默认为：3
        dataSource.setMinPoolSize(40);
        //连接池中保留的最大连接数。默认值: 15
        dataSource.setMaxPoolSize(100);
        //初始化连接池中的连接数，取值应在minPoolSize与maxPoolSize之间，默认为3
        dataSource.setInitialPoolSize(3);

        // 60s后未被使用，则丢弃
        dataSource.setMaxIdleTime(60);
        //当连接池连接耗尽时，客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException，如设为0则无限期等待。单位毫秒。默认: 0
        dataSource.setCheckoutTimeout(4000);
        //当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。默认值: 3
        dataSource.setAcquireIncrement(4);
        //重新尝试的时间间隔，默认为：1000毫秒
        dataSource.setAcquireRetryAttempts(2000);
        //关闭连接时，是否提交未提交的事务，默认为false，即关闭连接，回滚未提交的事务
        dataSource.setAutoCommitOnClose(false);
        //如果为false，则获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常，但是数据源
        //  仍有效保留，并在下次调用getConnection()的时候继续尝试获取连接。
        //如果设为true，那么在尝试获取连接失败后该数据源将申明已断开并永久关闭。默认: false
        dataSource.setBreakAfterAcquireFailure(false);
        //每60秒检查所有连接池中的空闲连接。默认值: 0，不检查
        dataSource.setIdleConnectionTestPeriod(60);
        //c3p0全局的PreparedStatements缓存的大小。如果maxStatements与maxStatementsPerConnection均为0，则缓存不生效，只要有一个不为0，则语句的缓存就能生效。如果默认值: 0
        dataSource.setMaxStatements(0);
        dataSource.setMaxStatementsPerConnection(0);
        return dataSource;
    }
}
