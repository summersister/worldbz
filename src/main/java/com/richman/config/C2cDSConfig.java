package com.richman.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.richman.mapper.c2c", sqlSessionTemplateRef = "c2cSqlSessionTemplate")
public class C2cDSConfig {

    @Bean(name = "c2cDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.c2cdb")
    public DataSource dataSource() {

        return DataSourceBuilder.create().build();

    }

    @Bean(name = "c2cSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("c2cDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/c2c/*.xml"));

        return bean.getObject();

    }

    @Bean(name = "c2cSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("c2cSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);

        return template;

    }

    @Bean("c2cTM")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("c2cDataSource")DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);

    }

}
