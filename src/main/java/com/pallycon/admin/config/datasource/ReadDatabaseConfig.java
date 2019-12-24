package com.pallycon.admin.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Brown on 2019-06-26.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
        (
                basePackages = "com.pallycon.admin.api.entity.repository.r",
                entityManagerFactoryRef = "readEntityManagerFactory",
                transactionManagerRef = "readTransactionManager"
        )
public class ReadDatabaseConfig {
    @Value("${datasource.slave}")
    private String SLAVE;
    @Value("${datasource.username}")
    private String USERNAME;
    @Value("${datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.hikari.driver-class-name}")
    private String DRIVER_CLASS_NAME;

    @Bean(name="readDataSource")
    public DataSource readDataSource() {
        return getDataSource(SLAVE);
    }

    @Bean(name = "readEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory() {
        return DatabaseConfig.createEntityManagerFactory(readDataSource(), "read-db");
    }

    @Bean(name="readTransactionManager")
    public PlatformTransactionManager readTransactionManager() {
        return new JpaTransactionManager(readEntityManagerFactory().getObject());
    }

    private DataSource getDataSource(String url) {
        HikariConfig config = DatabaseConfig.createHikariConfig(DRIVER_CLASS_NAME, USERNAME, PASSWORD, url);
        config.setPoolName("read-only");
//        config.setReadOnly(true);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

}
