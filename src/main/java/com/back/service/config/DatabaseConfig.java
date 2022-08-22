package com.back.service.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@Primary
public class DatabaseConfig {

    public final String MAPPER_LOCATIONS_PATH = "classpath:mapper/**/*.xml";
    public final String CONFIG_PATH = "classpath:mybatis-config.xml";
    public final String JNDI_PRIFIX = "java:/comp/env/";
    @Autowired
    Environment env;
    private Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean(name = "dataSource")
    public DataSource dataSource() throws IllegalArgumentException, NamingException {

        String jndiName = JNDI_PRIFIX + env.getProperty("spring.datasource.jndi-name");
        /*return (DataSource) new JndiDataSourceLookup().getDataSource(jndiName); */

        logger.info("name >> " + jndiName);

        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiName);
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();

        return (DataSource) bean.getObject();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired @Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource(CONFIG_PATH));
        factoryBean.setMapperLocations(applicationContext.getResources(MAPPER_LOCATIONS_PATH));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSession")
    public SqlSessionTemplate sqlSession(@Autowired @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
