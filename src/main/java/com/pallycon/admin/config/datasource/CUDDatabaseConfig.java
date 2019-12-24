package com.pallycon.admin.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Brown on 2019-06-26.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
        (
                basePackages = "com.pallycon.admin.api.entity.repository.cud",
                entityManagerFactoryRef = "cudEntityManagerFactory",
                transactionManagerRef = "cudTransactionManager"
        )
public class CUDDatabaseConfig {
    @Value("${datasource.master}")
    private String MASTER;
    @Value("${datasource.username}")
    private String USERNAME;
    @Value("${datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.hikari.driver-class-name}")
    private String DRIVER_CLASS_NAME;

    @Primary
    @Bean(name="cudDataSource")
    public DataSource cudDataSource() {
        return getDataSource(MASTER);
    }

    @Primary
    @Bean(name = "cudEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cudEntityManagerFactory() {
        return DatabaseConfig.createEntityManagerFactory(cudDataSource(), "write-db");
    }

    @Primary
    @Bean(name="cudTransactionManager")
    public PlatformTransactionManager cudTransactionManager(@Qualifier("cudEntityManagerFactory")EntityManagerFactory cudEntityManagerFactory) {
        return new JpaTransactionManager(cudEntityManagerFactory);
    }

    private DataSource getDataSource(String url) {
        HikariConfig config = DatabaseConfig.createHikariConfig(DRIVER_CLASS_NAME, USERNAME, PASSWORD, url);
        config.setPoolName("read-write");
        config.setReadOnly(false);
        config.setAutoCommit(true);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

}
