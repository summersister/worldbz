package com.dear.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.dear.mapper.bbs", sqlSessionTemplateRef = "bbsSqlSessionTemplate")
public class BbsDSConfig {

    @Bean(name = "bbsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.bbsdb")
    public DataSource threeDataSource() {

        return DataSourceBuilder.create().build();

    }

    @Bean(name = "bbsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("bbsDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setDataSource(dataSource);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/bbs/*.xml"));

        return bean.getObject();

    }

    @Bean(name = "bbsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("bbsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);

        return template;

    }

    @Bean("bbsTM")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("bbsDataSource")DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);

    }

}
