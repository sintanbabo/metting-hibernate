package com.sintanbabo.metting.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScans(value = {
		@ComponentScan("com.sintanbabo.metting.dao"),
		@ComponentScan("com.sintanbabo.metting.service") })
@MapperScan("com.sintanbabo.metting.mapper")
public class AppConfig {
   @Bean
   public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
      dataSource.setUrl("jdbc:mariadb://localhost:3306/metting");
      dataSource.setUsername("sintanbabo");
      dataSource.setPassword("Rjsgud00#");
      return dataSource;
  }
   
  @Bean
  public DataSourceTransactionManager transactionManager() {
      return new DataSourceTransactionManager(getDataSource());
  }
  
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
     SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
     sessionFactory.setDataSource(getDataSource());
     return sessionFactory.getObject();
  }
  
}