package com.test.spring5.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration//配置类
@ComponentScan(basePackages = "com.test.spring5")//开启组件扫描
@EnableTransactionManagement//开启事务
public class TXConfig {

    //创建数据库连接池
    @Bean//用bean注解
    public DruidDataSource getDruidDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/book");
        dataSource.setUsername("root");
        dataSource.setPassword("12345612");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    //创建jdbcTemplate对象
    @Bean
    public JdbcTemplate getJdbeTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //注入datasource
        jdbcTemplate.setDataSource(dataSource);//到ioc容器中找到匹配类型，然后注入
        return jdbcTemplate;
    }

    //创建事务管理器对象
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }


}
